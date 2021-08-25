package main;

import java.util.List;

public class WeatherValue {
    public String currentTime;
    public String city;
    public String targetWeather;
    public String currentWeather;
    public int currentWeatherTime = 0;
    public List<Integer> targetWeatherTimeList;
    public List<String> pastWeatherList;

    WeatherValue(String city, String targetWeather, String currentWeather, List<String> pastWeatherList) {
        this.city = city;
        this.targetWeather = targetWeather;
        this.currentWeather = currentWeather;
        this.pastWeatherList = pastWeatherList;
    }

    public void printData() {
        System.out.println("\n=== Weatherクラスのデータ 開始 ===");
        System.out.println("現在時刻：" + this.currentTime);
        System.out.println("都市：" + this.city);
        System.out.println("計測対象の天気：" + this.targetWeather);
        System.out.println("現在の天気：" + this.currentWeather);
        System.out.println("現在の天気が継続している時間：" + this.currentWeatherTime);
        System.out.println("過去の計測対象の天気の時間：" + this.targetWeatherTimeList);
        System.out.println("プログラムを回している間の天気のリスト：" + this.pastWeatherList);
        System.out.println("=== Weatherクラスのデータ 終了 ===\n");
    }
    
}
