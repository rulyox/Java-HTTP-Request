import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionRequest {

    public static void main(String[] args) {

        String getUrl = "https://postman-echo.com/get";
        String postUrl = "https://postman-echo.com/post";
        String data = "{ \"key\" : \"value\" }";

        String getResult = requestGet(getUrl);
        System.out.println(getResult);

        String postResult = requestPost(postUrl, data);
        System.out.println(postResult);

    }

    private static String requestGet(String url) {

        String result;

        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode == 200) result = getResponse(connection);
            else result = "ERROR " + responseCode;

            connection.disconnect();

        } catch (IOException error) {

            result = "ERROR";

            error.printStackTrace();

        }

        return result;

    }

    private static String requestPost(String url, String data) {

        String result;

        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            int responseCode = connection.getResponseCode();

            if(responseCode == 200) result = getResponse(connection);
            else result = "ERROR " + responseCode;

            connection.disconnect();

        } catch (IOException error) {

            result = "ERROR";

            error.printStackTrace();

        }

        return result;

    }

    private static String getResponse(HttpURLConnection connection) throws IOException {

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        StringBuilder stringBuilder = new StringBuilder();
        for(int c; (c = inputStreamReader.read()) >= 0;) stringBuilder.append((char) c);

        return stringBuilder.toString();

    }

}
