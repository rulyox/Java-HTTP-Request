package request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest implements SendRequest {

    @Override
    public String requestGet(String url) {

        String result;

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client
                    .newCall(request)
                    .execute();

            result = response.body().string();

        } catch(IOException error) {

            result = "EXCEPTION";

            error.printStackTrace();

        }

        return result;

    }

    @Override
    public String requestPost(String url, String data) {

        String result;

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(data, MediaType.parse("application/json")))
                    .build();

            Response response = client
                    .newCall(request)
                    .execute();

            result = response.body().string();

        } catch(IOException error) {

            result = "EXCEPTION";

            error.printStackTrace();

        }

        return result;

    }

}
