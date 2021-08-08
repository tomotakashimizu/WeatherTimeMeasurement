package measurement;

public class Main {
    
    public static void main(String[] args) {
        WebAPI openWeatherAPI = new WebAPI("https://api.openweathermap.org/data/2.5/weather",
                                            "?q=chiba&units=metric",
                                            APIKey.getMyAPIKey());
        String weatherJsonString = openWeatherAPI.createJsonString();
        System.out.println(weatherJsonString);
    }
    
}
