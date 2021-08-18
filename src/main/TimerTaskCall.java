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
    String targetWeatherDescription = "晴れ";
    String weatherDescription = openWeatherModel.weather.get(0).description;
    List<String> weatherDescriptionList = new ArrayList<String>(Arrays.asList(weatherDescription));

    WeatherValue weatherValue = new WeatherValue(weatherCity, targetWeatherDescription, weatherDescription, weatherDescriptionList);

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
