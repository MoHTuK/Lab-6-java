package sample;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    private static final String USER_AGENT = "AppleCoreMedia/1.0.0.18B79";
    private String GET_URL;
    private Responce responseData;

    public Request(String GET_URL) {
        this.GET_URL = GET_URL;
    }

    public String sendRequest() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            this.responseData = JSON.parseObject(response.toString(),Responce.class);

            return responseData.getLyrics();
        }
        else {
            System.out.println("ERROR");
            return null;
        }
    }
}
