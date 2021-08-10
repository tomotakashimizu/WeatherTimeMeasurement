package main;

public class Weather {
    private String currentTime;
    private String city;
    private String currentWeatherDescription;
    private String currentWeatherTime;
    private String[] pastWeatherTimeList;
    private String[] weatherDescriptionList;

    public String getWeatherDescription() {
        return currentWeatherDescription;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String[] getPastWeatherTime() {
        return pastWeatherTimeList;
    }
    public void setPastWeatherTime(String[] pastWeatherTime) {
        this.pastWeatherTimeList = pastWeatherTime;
    }
    public String getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    public String getWeatherTime() {
        return currentWeatherTime;
    }
    public void setWeatherTime(String weatherTime) {
        this.currentWeatherTime = weatherTime;
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
