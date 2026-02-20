import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexao {

    public double getTaxaDeConversao(String url){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement element = JsonParser.parseString(response.body());
            JsonObject object = element.getAsJsonObject();

            double taxa = object.get("conversion_rate").getAsDouble();

            return taxa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
