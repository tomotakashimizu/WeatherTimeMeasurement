package run;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import model.api.APIKey;
import model.api.WebAPI;
import model.gson.OpenWeatherModel;
import model.postgres.Postgres;
import model.weather.WeatherValue;

public class TimerTaskCall extends TimerTask {

    // 日付/時間をオフセット付きで書式設定または解析するISO日付/時間フォーマッタ(「2011-12-03T10:15:30+01:00」など)
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    WebAPI openWeatherAPI = new WebAPI("https://api.openweathermap.org/data/2.5/weather",
            "?q=tokyo&units=metric",
            APIKey.getMyAPIKey(),
            "&lang=ja");

    Gson gson = new Gson();
    String weatherJson = openWeatherAPI.createJSON();
    OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
    String weatherCity = openWeatherModel.name;
    String targetWeather = "晴れ";
    String initialWeather = openWeatherModel.weather.get(0).description;
    List<String> initialWeatherList = new ArrayList<String>(Arrays.asList(initialWeather));
    WeatherValue weatherValue = new WeatherValue(weatherCity, targetWeather, initialWeather, initialWeatherList);

    int id = 0;
    int timeInterval = 5;

    // Postgres クラスをインスタンス化
    Postgres postgresTest = new Postgres("testdb", "testuser", "testpass");

    // Elasticsearch に接続
    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));
    IndexRequest indexRequest = new IndexRequest("weatherindex");

    @Override
    public void run() {
        try {

            String weatherJson = openWeatherAPI.createJSON();
            System.out.println("\n=== weatherJson ===\n" + weatherJson);

            OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
            String currentWeather = openWeatherModel.weather.get(0).description;
            System.out.println("\n=== weatherDescription ===\n" + currentWeather);

            // 現在日時情報を指定フォーマットの文字列で取得
            String currentTime = ZonedDateTime.now().format(dateTimeFormat);
            System.out.println("\n=== 現在時刻 ===\n" + currentTime + "\n");

            id += 1;

            weatherValue.currentTime = currentTime;
            weatherValue.currentWeather = currentWeather;
            weatherValue.measuringTime += timeInterval;

            // 現在の天気になる前の天気(weatherDescriptionListの最後の要素を取得)
            String weatherBefore = weatherValue.pastWeatherList.get(weatherValue.pastWeatherList.size() - 1);

            // 前の天気も現在の天気も計測対象の天気と異なる場合
            if (!(weatherBefore.equals(targetWeather)) && !(currentWeather.equals(targetWeather))) {
                if (currentWeather.equals(weatherBefore)) {
                    // 現在の天気が前の天気と同じ場合
                    weatherValue.currentWeatherTime += timeInterval;
                } else {
                    // 現在の天気が前の天気と異なる場合
                    weatherValue.currentWeatherTime = timeInterval;
                    weatherValue.pastWeatherList.add(currentWeather);
                }
            }

            // 前の天気は計測対象の天気以外で、現在の天気は計測対象の天気の場合
            else if (!(weatherBefore.equals(targetWeather)) && currentWeather.equals(targetWeather)) {
                weatherValue.currentWeatherTime = timeInterval;
                weatherValue.pastWeatherList.add(currentWeather);
                weatherValue.totalTargetWeatherTime += timeInterval;

                if (weatherValue.targetWeatherTimeList == null) {
                    weatherValue.targetWeatherTimeList = new ArrayList<Integer>(
                            Arrays.asList(weatherValue.currentWeatherTime));
                } else {
                    weatherValue.targetWeatherTimeList.add(weatherValue.currentWeatherTime);
                }
            }

            // 前の天気も現在の天気も計測対象の天気の場合
            else if (weatherBefore.equals(targetWeather) && currentWeather.equals(targetWeather)) {
                weatherValue.currentWeatherTime += timeInterval;
                weatherValue.totalTargetWeatherTime += timeInterval;

                if (weatherValue.targetWeatherTimeList == null) {
                    weatherValue.targetWeatherTimeList = new ArrayList<Integer>(
                            Arrays.asList(weatherValue.currentWeatherTime));
                } else {
                    weatherValue.targetWeatherTimeList.set(weatherValue.totalTargetWeatherCount - 1,
                            weatherValue.currentWeatherTime);
                }
            }

            // 前の天気は計測対象の天気で、現在の天気は計測対象の天気以外の場合
            else if (weatherBefore.equals(targetWeather) && !(currentWeather.equals(targetWeather))) {
                weatherValue.currentWeatherTime = timeInterval;
                weatherValue.pastWeatherList.add(currentWeather);
            }

            Map<String, Long> counts = weatherValue.pastWeatherList.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            weatherValue.totalTargetWeatherCount = (int) (counts.get(targetWeather) != null ? counts.get(targetWeather)
                    : 0);

            weatherValue.printData();

            // Postgres にデータを保存
            String newValues = id + ", '" + weatherCity + "', '" + currentTime + "', " +
                    weatherValue.measuringTime + ", '" + targetWeather + "', '" +
                    currentWeather + "', " + weatherValue.currentWeatherTime + ", " +
                    weatherValue.totalTargetWeatherTime + ", " + weatherValue.totalTargetWeatherCount;
            postgresTest.createValues("testtable7", newValues);

            // POJO を JSON 形式にして データを Elasticsearch に送る
            indexRequest.id("" + id);
            indexRequest.source(new ObjectMapper().writeValueAsString(weatherValue), XContentType.JSON);
            System.out.println("JSONデータ" + new ObjectMapper().writeValueAsString(weatherValue));
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            // 正常に処理されたか確認
            System.out.println("response id: " + indexResponse.getId());
            System.out.println("response name: " + indexResponse.getResult().name());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
