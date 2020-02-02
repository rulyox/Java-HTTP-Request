import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest {

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

        try{

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            result = response.body().string();

        } catch(IOException error) {

            result = "ERROR";

            error.printStackTrace();

        }

        return result;

    }

    private static String requestPost(String url, String data) {

        String result;

        try{

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(data, MediaType.parse("application/json")))
                    .build();

            Response response = client.newCall(request).execute();

            result = response.body().string();

        } catch(IOException error) {

            result = "ERROR";

            error.printStackTrace();

        }

        return result;

    }

}
