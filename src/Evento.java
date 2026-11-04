import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Representa um evento com nome, data/hora e dia da semana.
public class Evento {

    // Guarda os dados do evento.
    private String nome;
    private LocalDateTime dataHora;
    private DiaDaSemana diaDaSemana;

    // Define o formato usado para apresentar a data e a hora.
    private static final DateTimeFormatter FORMATADOR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Inicializa o evento com os três dados recebidos.
    public Evento(String nome, LocalDateTime dataHora,
                  DiaDaSemana diaDaSemana) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.diaDaSemana = diaDaSemana;
    }

    // Permite consultar o nome do evento.
    public String getNome() {
        return nome;
    }

    // Permite consultar a data e a hora do evento.
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Permite consultar o dia da semana do evento.
    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    // Apresenta os dados do evento no console.
    public void exibirEvento() {
        System.out.println("Nome: " + nome);
        System.out.println("Data e hora: " + dataHora.format(FORMATADOR));
        System.out.println("Dia da semana: " + diaDaSemana);
    }
}