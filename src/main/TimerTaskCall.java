package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

public class TimerTaskCall extends TimerTask {

    WebAPI openWeatherAPI = new WebAPI("https://api.openweathermap.org/data/2.5/weather",
                                            "?q=tokyo&units=metric",
                                            APIKey.getMyAPIKey(),
                                            "&lang=ja");

    JSONObject weatherJson = openWeatherAPI.createJSON();
    String weatherInfo = weatherJson.get("weather").toString();
    JSONArray weatherInfoJsonArray = new JSONArray(weatherInfo);

    String weatherDescription = weatherInfoJsonArray.getJSONObject(0).get("description").toString();
    String weatherCity = weatherJson.get("name").toString();
    String targetWeatherDescription = "晴れ";
    List<String> weatherDescriptionList = new ArrayList<String>(Arrays.asList(weatherDescription));

    WeatherValue weather = new WeatherValue(weatherCity, targetWeatherDescription, weatherDescription, weatherDescriptionList);

    @Override
    public void run() {
        try {
            
            JSONObject weatherJson = openWeatherAPI.createJSON();
            System.out.println("\n=== weatherJson ===");
            System.out.println(weatherJson);

            String weatherInfo = weatherJson.get("weather").toString();
            System.out.println("\n=== weatherInfo ===");
            System.out.println(weatherInfo);
            
            JSONArray weatherInfoJsonArray = new JSONArray(weatherInfo);
            String weatherDescription = weatherInfoJsonArray.getJSONObject(0).get("description").toString();
            System.out.println("\n=== weatherDescription ===");
            System.out.println(weatherDescription);

            String weatherCity = weatherJson.get("name").toString();
            System.out.println("\n=== weatherCity ===");
            System.out.println(weatherCity);

            // 現在日時情報で初期化されたインスタンスの取得
            LocalDateTime nowDateTime = LocalDateTime.now(); 
            DateTimeFormatter javaFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            // 日時情報を指定フォーマットの文字列で取得
            String currentTime = nowDateTime.format(javaFormat);
            System.out.println("\n=== 現在時刻 ===");
            System.out.println(currentTime);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
