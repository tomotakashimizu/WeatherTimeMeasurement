package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import com.google.gson.Gson;

import model.gson.OpenWeatherModel;

public class TimerTaskCall extends TimerTask {

    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    WebAPI openWeatherAPI = new WebAPI("https://api.openweathermap.org/data/2.5/weather",
                                            "?q=tokyo&units=metric",
                                            APIKey.getMyAPIKey(),
                                            "&lang=ja");
    
    Gson gson = new Gson();
    String weatherJson = openWeatherAPI.createJSON();
    OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
    String weatherCity = openWeatherModel.name;
    String targetWeather = "曇りがち";
    String initialWeather = openWeatherModel.weather.get(0).description;
    List<String> initialWeatherList = new ArrayList<String>(Arrays.asList(initialWeather));
    WeatherValue weatherValue = new WeatherValue(weatherCity, targetWeather, initialWeather, initialWeatherList);

    Postgres postgresTest = new Postgres("testdb", "testuser", "testpass");
    int i = 0;

    @Override
    public void run() {
        try {
            
            String weatherJson = openWeatherAPI.createJSON();
            System.out.println("\n=== weatherJson ===\n" + weatherJson);

            OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
            String currentWeather = openWeatherModel.weather.get(0).description;
            System.out.println("\n=== weatherDescription ===\n" + currentWeather);

            // 現在日時情報を指定フォーマットの文字列で取得
            String currentTime = LocalDateTime.now().format(dateTimeFormat);
            System.out.println("\n=== 現在時刻 ===\n" + currentTime + "\n");

            i += 1;
            String values = i + ", '" + weatherCity + "', '" + currentTime + "', '" + currentWeather + "'";
            postgresTest.createValues("testtable6", values);

            weatherValue.currentTime = currentTime;
            weatherValue.currentWeather = currentWeather;
            weatherValue.measuringTime += 5;

            // 現在の天気になる前の天気(weatherDescriptionListの最後の要素を取得)
            String weatherBefore = weatherValue.pastWeatherList.get(weatherValue.pastWeatherList.size() - 1);

            // 前の天気も現在の天気も計測対象の天気と異なる場合
            if (!(weatherBefore.equals(targetWeather)) && !(currentWeather.equals(targetWeather))) {
                if (currentWeather.equals(weatherBefore)) {
                    // 現在の天気が前の天気と同じ場合
                    weatherValue.currentWeatherTime += 5;
                } else {
                    // 現在の天気が前の天気と異なる場合
                    weatherValue.currentWeatherTime = 5;
                    weatherValue.pastWeatherList.add(currentWeather);
                }
            }
            
            // 前の天気は計測対象の天気以外で、現在の天気は計測対象の天気の場合
            else if (!(weatherBefore.equals(targetWeather)) && currentWeather.equals(targetWeather)) {
                weatherValue.currentWeatherTime = 5;
                weatherValue.pastWeatherList.add(currentWeather);
                weatherValue.totalTargetWeatherTime += 5;
            }

            // 前の天気も現在の天気も計測対象の天気の場合
            else if (weatherBefore.equals(targetWeather) && currentWeather.equals(targetWeather)) {
                weatherValue.currentWeatherTime += 5;
                weatherValue.totalTargetWeatherTime += 5;
            }

            // 前の天気は計測対象の天気で、現在の天気は計測対象の天気以外の場合
            else if (weatherBefore.equals(targetWeather) && !(currentWeather.equals(targetWeather))) {
                if (weatherValue.targetWeatherTimeList == null) {
                    weatherValue.targetWeatherTimeList = new ArrayList<Integer>(Arrays.asList(weatherValue.currentWeatherTime));
                } else {
                    weatherValue.targetWeatherTimeList.add(weatherValue.currentWeatherTime);
                }
                weatherValue.currentWeatherTime = 5;
                weatherValue.pastWeatherList.add(currentWeather);
            }

            weatherValue.printData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
