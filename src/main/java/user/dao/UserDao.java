package user.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import user.entity.User;
import util.Request;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class UserDao extends Request {
    public String addUser(User user) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);

        HttpRequest request = createPostRequest(url, json);
        return doRequest(client, request);
    }

    public String getUserByUserName(String userName) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/" + userName;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }

    public String deleteUser(String userName) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/" + userName;

        HttpRequest request = createDeleteRequest(url);
        return doRequest(client, request);
    }

    public String updateUser(User user, String oldName) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/oldName";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);

        HttpRequest request = createPutRequest(url, json);
        return doRequest(client, request);
    }

    public String createUsersList(User[] user) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/createWithArray";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);

        HttpRequest request = createPostRequest(url, json);
        return doRequest(client, request);
    }

    public String logsUserIntoSystem(String name, String paswword) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/login?username=" + name + "&password=" + paswword;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }

    public String logout() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/user/logout";

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }
}
