package measurement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebAPI {
    private String stringUrl;
    private String param;
    private String apiKey = "";

    WebAPI(String stringUrl, String param) {
        this.stringUrl = stringUrl;
        this.param = param;
    }

    WebAPI(String stringUrl, String param, String apiKey) {
        this(stringUrl, param);
        this.apiKey = apiKey;
    }
    
    public String getStringUrl() {
        return stringUrl;
    }
    public String getParam() {
        return param;
    }
    public void setStringUrl(String stringUrl) {
        this.stringUrl = stringUrl;
    }
    public void setParam(String param) {
        this.param = param;
    }
    
    public String createJsonString() {
        String jsonData = "empty";
        URL url;
        try {
            String completeStringUrl = this.stringUrl + this.param + "&appid=" + this.apiKey;
            url = new URL(completeStringUrl);
            URLConnection urlConnection;
            urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            if (line != null) {
                jsonData = line;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
