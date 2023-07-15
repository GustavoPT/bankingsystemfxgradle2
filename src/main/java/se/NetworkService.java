package se;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
public class NetworkService {
    //
    // call the rest api
    // we will get the information

    public NetworkService() {

    }


    //
    public static void main(String[] args) {
        String username = "your_username";
        String password = "your_password";
        String apiUrl = "https://api.example.com/resource";

        // Create the authentication credentials
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);

        // Create the HttpClient with Basic Authentication
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        // Make a GET request with authentication
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);

            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the HttpClient
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}






