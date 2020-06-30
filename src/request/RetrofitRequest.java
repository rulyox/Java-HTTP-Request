package request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.io.IOException;

public class RetrofitRequest implements SendRequest {

    private interface ApiService {

        @GET("/get")
        Call<JsonObject> get();

        @POST("/post")
        Call<JsonObject> post(@Body JsonObject json);

    }

    private final ApiService apiService;

    public RetrofitRequest(String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    @Override
    public String get(String url) {

        String result;

        try {

            Response<JsonObject> response = apiService.get().execute();

            if(response.isSuccessful()) result = response.body().toString();
            else result = "ERROR";

        } catch(IOException error) {

            result = "EXCEPTION";

            error.printStackTrace();

        }

        return result;

    }

    @Override
    public String post(String url, String data) {

        String result;

        try {

            JsonObject json = (JsonObject) JsonParser.parseString(data);

            Response<JsonObject> response = apiService.post(json).execute();

            if(response.isSuccessful()) result = response.body().toString();
            else result = "ERROR";

        } catch(IOException error) {

            result = "EXCEPTION";

            error.printStackTrace();

        }

        return result;

    }

}
