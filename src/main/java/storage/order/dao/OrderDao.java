package storage.order.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import storage.order.entity.Order;
import util.Request;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class OrderDao extends Request {
    public String placeAnOrder(Order order) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/store/order";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);

        HttpRequest request = createPostRequest(url, json);
        return doRequest(client, request);
    }

    public String findPurchaseOrderById(int id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/store/order/" + id;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }

    public String deletePurchaseOrderById(int id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/store/order/" + id;

        HttpRequest request = createDeleteRequest(url);
        return doRequest(client, request);
    }

    public String returnsPetInventoriesByStatus() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/store/inventory" ;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }


}
