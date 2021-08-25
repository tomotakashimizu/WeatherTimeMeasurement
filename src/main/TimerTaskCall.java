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
    String targetWeatherDescription = "曇りがち";
    String weatherDescription = openWeatherModel.weather.get(0).description;
    List<String> weatherDescriptionList = new ArrayList<String>(Arrays.asList(weatherDescription));

    WeatherValue weatherValue = new WeatherValue(weatherCity, targetWeatherDescription, weatherDescription, weatherDescriptionList);

    Postgres postgresTest = new Postgres("testdb", "testuser", "testpass");
    int i = 0;

    @Override
    public void run() {
        try {
            
            String weatherJson = openWeatherAPI.createJSON();
            System.out.println("\n=== weatherJson ===\n" + weatherJson);

            OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
            String weatherCity = openWeatherModel.name;
            System.out.println("\n=== weatherCity ===\n" + weatherCity);
            String weatherDescription = openWeatherModel.weather.get(0).description;
            System.out.println("\n=== weatherDescription ===\n" + weatherDescription);

            // 現在日時情報を指定フォーマットの文字列で取得
            String currentTime = LocalDateTime.now().format(dateTimeFormat);
            System.out.println("\n=== 現在時刻 ===\n" + currentTime + "\n");

            weatherValue.currentTime = currentTime;

            i += 1;
            String values = i + ", '" + weatherCity + "', '" + currentTime + "', '" + weatherDescription + "'";
            postgresTest.createValues("testtable6", values);

            // 現在の天気になる前の天気(weatherDescriptionListの最後の要素を取得)
            String weatherDescriptionBefore = weatherValue.pastWeatherList.get(weatherValue.pastWeatherList.size() - 1);

            // 前の天気も現在の天気も計測対象の天気と異なる場合
            if (!(weatherDescriptionBefore.equals(targetWeatherDescription)) && !(weatherDescription.equals(targetWeatherDescription))) {
                if (weatherDescription.equals(weatherDescriptionBefore)) {
                    // 現在の天気が前の天気と同じ場合
                    weatherValue.currentWeatherTime += 5;
                } else {
                    // 現在の天気が前の天気と異なる場合
                    weatherValue.currentWeatherTime = 5;
                    weatherValue.currentWeather = weatherDescription;
                    weatherValue.pastWeatherList.add(weatherDescription);
                }
            }
            
            // 前の天気は計測対象の天気以外で、現在の天気は計測対象の天気の場合
            else if (!(weatherDescriptionBefore.equals(targetWeatherDescription)) && (weatherDescription.equals(targetWeatherDescription))) {
                weatherValue.currentWeather = weatherDescription;
                weatherValue.currentWeatherTime = 5;
                weatherValue.pastWeatherList.add(weatherDescription);
            }

            // 前の天気も現在の天気も計測対象の天気の場合
            else if ((weatherDescriptionBefore.equals(targetWeatherDescription)) && (weatherDescription.equals(targetWeatherDescription))) {
                weatherValue.currentWeatherTime += 5;
            }

            // 前の天気は計測対象の天気で、現在の天気は計測対象の天気以外の場合
            else if ((weatherDescriptionBefore.equals(targetWeatherDescription)) && !(weatherDescription.equals(targetWeatherDescription))) {
                if (weatherValue.targetWeatherTimeList == null) {
                    weatherValue.targetWeatherTimeList = new ArrayList<Integer>(Arrays.asList(weatherValue.currentWeatherTime));
                } else {
                    weatherValue.targetWeatherTimeList.add(weatherValue.currentWeatherTime);
                }

                if (weatherDescription.equals(weatherDescriptionBefore)) {
                    // 現在の天気が前の天気と同じ場合
                    weatherValue.currentWeatherTime += 5;
                } else {
                    // 現在の天気が前の天気と異なる場合
                    weatherValue.currentWeatherTime = 5;
                    weatherValue.currentWeather = weatherDescription;
                    weatherValue.pastWeatherList.add(weatherDescription);
                }
            }

            weatherValue.printData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
