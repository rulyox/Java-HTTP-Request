import request.HttpURLConnectionRequest;
import request.OkHttpRequest;
import request.RetrofitRequest;
import request.SendRequest;

public class Main {

    public static void main(String[] args) {

        String url = "https://postman-echo.com";
        String getApi = "/get";
        String postApi = "/post";
        String data = "{ \"key\" : \"value\" }";

        SendRequest[] reqs = {
            new HttpURLConnectionRequest(),
            new OkHttpRequest(),
            new RetrofitRequest(url)
        };

        for(SendRequest req : reqs) {

            System.out.println(req.getClass());
            System.out.println();

            System.out.println("Request GET");
            String getResult = req.get(url+getApi);
            System.out.println(getResult);
            System.out.println();

            System.out.println("Request POST");
            String postResult = req.post(url+postApi, data);
            System.out.println(postResult);
            System.out.println();

        }

    }

}
