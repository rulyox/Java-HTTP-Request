package request;

public interface SendRequest {

    String requestGet(String url);
    String requestPost(String url, String data);

}
