package main;

public class Weather {
    public String currentTime;
    public String city;
    public String currentWeatherDescription;
    public int currentWeatherTime;
    public int[] pastWeatherTimeList;
    public String[] weatherDescriptionList;

    Weather(String currentTime, String city, String currentWeatherDescription, int currentWeatherTime, String[] weatherDescriptionList) {
        this.currentTime = currentTime;
        this.city = city;
        this.currentWeatherDescription = currentWeatherDescription;
        this.currentWeatherTime = currentWeatherTime;
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
    public int[] getPastWeatherTime() {
        return pastWeatherTimeList;
    }
    public void setPastWeatherTime(int[] pastWeatherTimeList) {
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
    public String[] getWeatherDescriptionList() {
        return weatherDescriptionList;
    }
    public void setWeatherDescriptionList(String[] weatherDescriptionList) {
        this.weatherDescriptionList = weatherDescriptionList;
    }
    public void setWeatherDescription(String weatherDescription) {
        this.currentWeatherDescription = weatherDescription;
    }
}
