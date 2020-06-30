import request.HttpURLConnectionRequest;
import request.OkHttpRequest;
import request.SendRequest;

public class Main {

    public static void main(String[] args) {

        String getUrl = "https://postman-echo.com/get";
        String postUrl = "https://postman-echo.com/post";
        String data = "{ \"key\" : \"value\" }";

        SendRequest[] reqs = new SendRequest[2];
        reqs[0] = new HttpURLConnectionRequest();
        reqs[1] = new OkHttpRequest();

        for(SendRequest req : reqs) {

            System.out.println(req.getClass());
            System.out.println();

            System.out.println("Request GET");
            String getResult = req.requestGet(getUrl);
            System.out.println(getResult);
            System.out.println();

            System.out.println("Request POST");
            String postResult = req.requestPost(postUrl, data);
            System.out.println(postResult);
            System.out.println();

        }

    }

}
