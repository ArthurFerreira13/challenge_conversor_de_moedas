import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private String daMoeda;
    private String paraMoeda;
    private double valorOriginal;
    private double valorConvertido;
    private String dataHora;

    public Transacao(String daMoeda, String paraMoeda, double valorOriginal, double valorConvertido) {
        this.daMoeda = daMoeda;
        this.paraMoeda = paraMoeda;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s => %.2f %s", dataHora, valorOriginal, daMoeda, valorConvertido, paraMoeda);
    }
}