package util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Request {
    public String doRequest(HttpClient client, HttpRequest httpRequest) {
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response + "\n" + response.body();
    }

    public HttpRequest createPostRequestFormUrlencoded(String url, String FormUrlencodedData) {


        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(FormUrlencodedData.toString()))
                .build();
    }
    public HttpRequest createPostRequest(String url, String json ) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public HttpRequest createPutRequest(String url, String putDataJson) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(putDataJson))
                .build();
    }

    public HttpRequest createGetRequest(String url) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    public HttpRequest createGetRequest(String url, String headerName, String headerValue) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header(headerName, headerValue)
                .GET()
                .build();
    }


    public HttpRequest createDeleteRequest(String url) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }

}
