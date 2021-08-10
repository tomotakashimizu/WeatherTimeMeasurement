package main;

import java.util.List;

public class Weather {
    public String currentTime;
    public String city;
    public String targetWeatherDescription;
    public String currentWeatherDescription;
    public int currentWeatherTime = 0;
    public List<Integer> pastWeatherTimeList;
    public List<String> weatherDescriptionList;

    Weather(String city, String targetWeatherDescription, String currentWeatherDescription, List<String> weatherDescriptionList) {
        this.city = city;
        this.targetWeatherDescription = targetWeatherDescription;
        this.currentWeatherDescription = currentWeatherDescription;
        this.weatherDescriptionList = weatherDescriptionList;
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
