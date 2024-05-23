import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeConsultas {
    private List<String> resultadosConsultas;

    public GerenciadorDeConsultas() {
        this.resultadosConsultas = new ArrayList<>();
    }

    public void adicionarResultado(String resultado) {
        resultadosConsultas.add(resultado);
    }

    public List<String> getResultadosConsultas() {
        return resultadosConsultas;
    }

    public void salvarResultadosEmArquivo() throws IOException {
        String nomeArquivo = "log_" + System.currentTimeMillis() + ".json";
        try (JsonWriter writer = new JsonWriter(new FileWriter(nomeArquivo))) {
            writer.setIndent("  "); // Define a indentação para tornar o arquivo mais legível

            writer.beginArray(); // Inicia o array JSON
            for (String resultado : resultadosConsultas) {
                writer.jsonValue(resultado); // Escreve o JSON diretamente no arquivo
            }
            writer.endArray(); // Finaliza o array JSON
        }
    }
}
