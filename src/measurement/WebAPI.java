package measurement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class WebAPI {
    private String stringUrl;
    private String param;
    private String apiKey;
    private String lang = "";
    private JSONObject json;

    WebAPI(String stringUrl, String param, String apiKey, String lang) {
        this.stringUrl = stringUrl;
        this.param = param;
        this.apiKey = apiKey;
        this.lang = lang;
    }

    public JSONObject createJSON() {
        String jsonString = "empty";
        URL url;
        try {
            String completeStringUrl = this.stringUrl + this.param + "&appid=" + this.apiKey + this.lang;
            url = new URL(completeStringUrl);
            URLConnection urlConnection;
            urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            if (line != null) {
                jsonString = line;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(jsonString);
        this.json = jsonObject;
        return this.json;
    }
}
