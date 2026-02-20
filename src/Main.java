import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Challenge Conversor de Moedas - Alura
 * @author Arthur Ferreira
 * @version 1.0
 */

public class Main {
    private static final List<Transacao> historico = new ArrayList<>();

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Conexao api = new Conexao();
        String chave = System.getenv("API_KEY");

        int escolha = 0;
        while (escolha != 9) {
            imprimirMenuCustomizado();
            escolha = leitor.nextInt();

            if (escolha == 9) break;
            if (escolha == 8) {
                imprimirHistorico();
                continue;
            }

            processarEscolha(escolha, leitor, api, chave);
        }
        System.out.println("Sistema encerrado.");
    }

    private static void processarEscolha(int opt, Scanner sc, Conexao api, String key) {
        String base = "", alvo = "";

        // Uso de Switch Expression (Java 14+) - Muito mais moderno
        switch (opt) {
            case 1 -> { base = "USD"; alvo = "BRL"; }
            case 2 -> { base = "BRL"; alvo = "USD"; }
            case 3 -> { base = "USD"; alvo = "EUR"; }
            case 4 -> { base = "EUR"; alvo = "USD"; }
            case 5 -> { base = "USD"; alvo = "ARS"; }
            case 6 -> { base = "ARS"; alvo = "USD"; }
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        System.out.print("Valor para converter: ");
        double quantia = sc.nextDouble();

        String url = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + base + "/" + alvo;
        double taxa = api.getTaxaDeConversao(url);

        if (taxa > 0) {
            double resultado = quantia * taxa;
            Transacao t = new Transacao(base, alvo, quantia, resultado);
            historico.add(t);
            System.out.println("Resultado: " + t);
        }
    }

    private static void imprimirMenuCustomizado() {
        System.out.println("\n===== MONITOR DE CÂMBIO V1.0 =====");
        System.out.println("1) USD para BRL | 2) BRL para USD");
        System.out.println("3) USD para EUR | 4) EUR para USD");
        System.out.println("5) USD para ARS | 6) ARS para USD");
        System.out.println("8) Ver Log de Transações");
        System.out.println("9) Sair");
        System.out.print("Seleção: ");
    }

    private static void imprimirHistorico() {
        System.out.println("\n--- LOG DE OPERAÇÕES ---");
        if (historico.isEmpty()) System.out.println("Nenhum registo encontrado.");
        historico.forEach(System.out::println);
        System.out.println("------------------------");
    }
}