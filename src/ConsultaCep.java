import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsultaCep {
    private String cep;

    public ConsultaCep(String cep) {
        this.cep = cep;
    }

    public String consultar() throws IOException, InterruptedException {
        // Configura o Gson com políticas de nomeação e formatação
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        // Codifica o CEP para URL
        String buscaCepCodificado = URLEncoder.encode(cep, StandardCharsets.UTF_8);
        // Monta a URL da API
        String endereco = "https://viacep.com.br/ws/" + buscaCepCodificado + "/json/";

        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma requisição HTTP GET para a URL montada
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        // Envia a requisição e obtém a resposta
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body(); // Extrai o corpo da resposta (o JSON retornado)

        // Retorna o JSON formatado
        return gson.toJson(gson.fromJson(json, Object.class));
    }
}
