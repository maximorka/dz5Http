package pet.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import pet.entity.Pet;
import util.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class PetDao extends Request {


    public String uploadImage(int id, String metaData, String pathToFile) throws IOException {
        Connection.Response response = Jsoup.connect("https://petstore.swagger.io/v2/pet/" + id + "/uploadImage")
                .header("Content-Type", "multipart/form-data")
                .header("Accept", "application/json")
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .data("additionalMetadata", metaData)
                .data("file", "image.jpg", new FileInputStream(pathToFile))
                .execute();
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        return response.body();
    }

    public String addPet(Pet pet) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(pet);

        HttpRequest request = createPostRequest(url, json);
        return doRequest(client, request);
    }

    public String getPetById(int id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet/" + id;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }

    public String updatePet(Pet pet) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(pet);

        HttpRequest request = createPutRequest(url, json);
        return doRequest(client, request);
    }

    public String findByStatus(String status) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=" + status;

        HttpRequest request = createGetRequest(url);
        return doRequest(client, request);
    }

    public String updatePetWithFormData(int petId, String name, String status) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet/" + petId;
        String formedData = "name=" +
                name +
                "&status=" +
                status;
        HttpRequest request = createPostRequestFormUrlencoded(url, formedData);
        return doRequest(client, request);
    }

    public String deletePetById(int id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet/" + id;

        HttpRequest request = createDeleteRequest(url);
        return doRequest(client, request);
    }

    public String deletePetById(int id, String apikey) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://petstore.swagger.io/v2/pet/" + id;

        HttpRequest request = createGetRequest(url, "api_key: ", apikey);
        return doRequest(client, request);
    }
}
