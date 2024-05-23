import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Cria um scanner para ler a entrada do usuário
        GerenciadorDeConsultas gerenciador = new GerenciadorDeConsultas(); // Instancia o gerenciador de consultas

        while (true) {
            System.out.print("Informe o número do CEP (ou digite 'sair' para encerrar): ");
            String buscaCep = sc.nextLine(); // Lê o CEP digitado pelo usuário

            if (buscaCep.equalsIgnoreCase("sair")) {
                break; // Encerra o loop se o usuário digitar "sair"
            }

            if (buscaCep.length() != 8) { // Verifica se o CEP tem 8 caracteres
                System.out.println("CEP inválido. O CEP deve ter 8 caracteres. Tente novamente.");
                continue; // Volta ao início do loop para pedir um novo CEP
            }

            ConsultaCep consultarCep = new ConsultaCep(buscaCep);

            try {
                String resultado = consultarCep.consultar(); // Chama o método que faz a consulta e retorna o JSON formatado
                System.out.println(resultado); // Imprime o resultado no console
                gerenciador.adicionarResultado(resultado); // Adiciona o resultado da consulta à lista
            } catch (IOException | InterruptedException e) {
                e.printStackTrace(); // Imprime a stack trace em caso de erro
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando a aplicação!");
            }
        }

        // Salva todos os resultados das consultas em um único arquivo .json ao sair do programa
        try {
            gerenciador.salvarResultadosEmArquivo();
            System.out.println("Resultados das consultas salvos em um único arquivo .json.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close(); // Fecha o scanner
    }
}

