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

    WebAPI openWeatherAPI = new WebAPI("https://api.openweathermap.org/data/2.5/weather",
                                            "?q=tokyo&units=metric",
                                            APIKey.getMyAPIKey(),
                                            "&lang=ja");

    String weatherJson = openWeatherAPI.createJSON();

    Gson gson = new Gson();
    OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);

    String weatherDescription = openWeatherModel.weather.get(0).description;
    String weatherCity = openWeatherModel.name;
    String targetWeatherDescription = "晴れ";
    List<String> weatherDescriptionList = new ArrayList<String>(Arrays.asList(weatherDescription));

    WeatherValue weather = new WeatherValue(weatherCity, targetWeatherDescription, weatherDescription, weatherDescriptionList);

    @Override
    public void run() {
        try {
            
            String weatherJson = openWeatherAPI.createJSON();
            System.out.println("\n=== weatherJson ===\n" + weatherJson);

            OpenWeatherModel openWeatherModel = gson.fromJson(weatherJson, OpenWeatherModel.class);
            
            String weatherDescription = openWeatherModel.weather.get(0).description;
            System.out.println("\n=== weatherDescription ===\n" + weatherDescription);

            String weatherCity = openWeatherModel.name;
            System.out.println("\n=== weatherCity ===\n" + weatherCity);

            // 現在日時情報で初期化されたインスタンスの取得
            LocalDateTime nowDateTime = LocalDateTime.now(); 
            DateTimeFormatter javaFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            // 日時情報を指定フォーマットの文字列で取得
            String currentTime = nowDateTime.format(javaFormat);
            System.out.println("\n=== 現在時刻 ===\n" + currentTime + "\n");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
