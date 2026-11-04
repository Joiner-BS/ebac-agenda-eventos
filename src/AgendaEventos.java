import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.time.ZonedDateTime;

// Demonstra a manipulação de datas e fusos de um evento.
public class AgendaEventos {

    // Ponto de entrada do programa.
    public static void main(String[] args) {

        // Define explicitamente o fuso usado para obter a data e a hora atuais.
        ZoneId fusoOrigem = ZoneId.of("America/Sao_Paulo");

        // Obtém a data e a hora atuais de São Paulo.
        // O LocalDateTime resultante não armazena o fuso.
        LocalDateTime agora = LocalDateTime.now(fusoOrigem);

        // Identifica o dia da semana correspondente à data.
        DiaDaSemana diaDaSemana = converterDiaDaSemana(agora.getDayOfWeek());

        // Cria o evento pelo construtor completo.
        Evento evento = new Evento(
                "Reunião de estudos",
                agora,
                diaDaSemana
        );

        System.out.println("===== EVENTO ORIGINAL =====");
        evento.exibirEvento();

        // Define o formato de apresentação da nova data.
        DateTimeFormatter formatador =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Adiciona cinco dias e guarda a nova data retornada.
        LocalDateTime novaDataHora = evento.getDataHora().plusDays(5);

        System.out.println("\n===== DATA ACRESCIDA DE 5 DIAS =====");
        System.out.println("Data e hora: " + novaDataHora.format(formatador));

        // Calcula o dia da semana da nova data.
        System.out.println(
                "Dia da semana: "
                        + converterDiaDaSemana(novaDataHora.getDayOfWeek())
        );

        // Associa a data/hora original ao fuso em que ela foi obtida.
        ZonedDateTime eventoNaOrigem =
                evento.getDataHora().atZone(fusoOrigem);

        // Representa o mesmo instante no fuso de São Paulo.
        ZonedDateTime eventoSaoPaulo =
                eventoNaOrigem.withZoneSameInstant(
                        ZoneId.of("America/Sao_Paulo")
                );

        // Representa o mesmo instante no fuso GMT.
        ZonedDateTime eventoGmt =
                eventoNaOrigem.withZoneSameInstant(
                        ZoneId.of("GMT")
                );

        // Inclui o deslocamento em relação a UTC e o identificador do fuso.
        DateTimeFormatter formatadorComFuso =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss XXX '['VV']'"
                );

        System.out.println("\n===== EVENTO NOS DIFERENTES FUSOS =====");

        System.out.println(
                "São Paulo: " + eventoSaoPaulo.format(formatadorComFuso)
        );

        System.out.println(
                "GMT: " + eventoGmt.format(formatadorComFuso)
        );

        // Confirma que os dois horários representam o mesmo instante.
        System.out.println(
                "Mesmo instante: "
                        + eventoSaoPaulo.toInstant().equals(eventoGmt.toInstant())
        );
    }

    // Converte o enum de dias do Java para o enum criado no projeto.
    private static DiaDaSemana converterDiaDaSemana(DayOfWeek dia) {
        switch (dia) {
            case MONDAY:
                return DiaDaSemana.SEGUNDA;
            case TUESDAY:
                return DiaDaSemana.TERÇA;
            case WEDNESDAY:
                return DiaDaSemana.QUARTA;
            case THURSDAY:
                return DiaDaSemana.QUINTA;
            case FRIDAY:
                return DiaDaSemana.SEXTA;
            case SATURDAY:
                return DiaDaSemana.SÁBADO;
            case SUNDAY:
                return DiaDaSemana.DOMINGO;
            default:
                throw new IllegalArgumentException("Dia da semana inválido.");
        }
    }
}