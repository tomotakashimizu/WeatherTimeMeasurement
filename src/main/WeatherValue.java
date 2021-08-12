package main;

import java.util.List;

public class WeatherValue {
    public String currentTime;
    public String city;
    public String targetWeatherDescription;
    public String currentWeatherDescription;
    public int currentWeatherTime = 0;
    public List<Integer> pastWeatherTimeList;
    public List<String> weatherDescriptionList;

    WeatherValue(String city, String targetWeatherDescription, String currentWeatherDescription, List<String> weatherDescriptionList) {
        this.city = city;
        this.targetWeatherDescription = targetWeatherDescription;
        this.currentWeatherDescription = currentWeatherDescription;
        this.weatherDescriptionList = weatherDescriptionList;
    }

    public void printData() {
        System.out.println("\n=== Weatherクラスのデータ 開始 ===");
        System.out.println("現在時刻：" + this.currentTime);
        System.out.println("都市：" + this.city);
        System.out.println("計測対象の天気：" + this.targetWeatherDescription);
        System.out.println("現在の天気：" + this.currentWeatherDescription);
        System.out.println("現在の天気が継続している時間：" + this.currentWeatherTime);
        System.out.println("過去の計測対象の天気の時間：" + this.pastWeatherTimeList);
        System.out.println("プログラムを回している間の天気のリスト：" + this.weatherDescriptionList);
        System.out.println("=== Weatherクラスのデータ 終了 ===\n");
    }

    public String getWeatherDescription() {
        return currentWeatherDescription;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public List<Integer> getPastWeatherTime() {
        return pastWeatherTimeList;
    }
    public void setPastWeatherTime(List<Integer> pastWeatherTimeList) {
        this.pastWeatherTimeList = pastWeatherTimeList;
    }
    public String getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    public int getWeatherTime() {
        return currentWeatherTime;
    }
    public void setWeatherTime(int currentWeatherTime) {
        this.currentWeatherTime = currentWeatherTime;
    }
    public List<String> getWeatherDescriptionList() {
        return weatherDescriptionList;
    }
    public void setWeatherDescriptionList(List<String> weatherDescriptionList) {
        this.weatherDescriptionList = weatherDescriptionList;
    }
    public void setWeatherDescription(String weatherDescription) {
        this.currentWeatherDescription = weatherDescription;
    }
}
