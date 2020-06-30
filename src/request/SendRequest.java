package request;

public interface SendRequest {

    String get(String url);
    String post(String url, String data);

}
