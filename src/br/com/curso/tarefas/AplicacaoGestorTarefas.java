package br.com.curso.tarefas;

import br.com.curso.tarefas.dominio.Responsavel;
import br.com.curso.tarefas.dominio.Tarefa;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AplicacaoGestorTarefas {

    public static void main(String[] args) {
        String operacao = "CONSULTAR";
        switch (operacao) {
            case "INICIAR":
                System.out.println("Operação escolhida: iniciar tarefa.");
                break;
            case "CONCLUIR":
                System.out.println("Operação escolhida: concluir tarefa.");
                break;
            case "CONSULTAR":
                System.out.println("Operação escolhida: consultar tarefa.");
                break;
            default:
                System.out.println("Operação desconhecida.");
        }
        Responsavel responsavel = new Responsavel();
        responsavel.cadastrar("F12345", "Marina Souza");

        // Tarefa tarefaRevisarRequisitos = new Tarefa();
        // Tarefa tarefaImplementarTela = new Tarefa();
        // Tarefa tarefaValidarAplicacao = new Tarefa();
        // Tarefa tarefaSelecionada = tarefaRevisarRequisitos;

        Tarefa tarefaRevisarRequisitos = new Tarefa();
        tarefaRevisarRequisitos.cadastrar(
                "TAR-001",
                "Revisar requisitos",
                "Conferir os requisitos do projeto",
                3,
                LocalDate.of(2026, 10, 10),
                new BigDecimal("150.00"),
                responsavel);

        Tarefa tarefaImplementarTela = new Tarefa();
        tarefaImplementarTela.cadastrar(
                "TAR-002",
                "Implementar a tela inicial",
                "Criar a estrutura visual inicial",
                2,
                LocalDate.of(2026, 10, 12),
                new BigDecimal("300.00"),
                responsavel);

        Tarefa tarefaValidarAplicacao = new Tarefa();
        tarefaValidarAplicacao.cadastrar(
                "TAR-003",
                "Validar a aplicação",
                "Executar os testes previstos",
                1,
                LocalDate.of(2026, 10, 13),
                new BigDecimal("80.00"),
                responsavel);

        tarefaImplementarTela.iniciar();

        while (!tarefaImplementarTela.estaConcluida()) {
            tarefaImplementarTela.registrarHoraTrabalhada();

            System.out.println(
                    tarefaImplementarTela.obterTitulo()
                            + ": "
                            + tarefaImplementarTela.obterHorasRealizadas()
                            + " hora(s)");
        }

        int tentativa = 1;
        boolean tarefaIniciada;

        do {
            tarefaIniciada = tarefaValidarAplicacao.iniciar();
            System.out.println("Tentativa de início: " + tentativa);
            tentativa++;
        } while (!tarefaIniciada && tentativa <= 3);

        responsavel.ativar();

        boolean iniciou = tarefaRevisarRequisitos.iniciar();

        if (iniciou) {
            System.out.println("Tarefa iniciada.");
        } else {
            System.out.println("Tarefa não iniciada: responsável inativo.");
        }

        for (int hora = 1; hora <= 3; hora++) {
            boolean registrou = tarefaRevisarRequisitos.registrarHoraTrabalhada();

            if (registrou) {
                System.out.println(
                        "Hora registrada: "
                                + tarefaRevisarRequisitos.obterHorasRealizadas());
            }

            boolean concluiu = tarefaValidarAplicacao.concluir();

            if (concluiu) {
                System.out.println("Validação concluída.");
            }
        }

        boolean codigoEncontrado = tarefaRevisarRequisitos.possuiCodigo("TAR-001");

        if (codigoEncontrado) {
            System.out.println("Tarefa TAR-001 encontrada.");
        }

        BigDecimal limite = new BigDecimal("100.00");

        if (tarefaRevisarRequisitos.possuiCustoAcimaDe(limite)) {
            System.out.println("A tarefa possui custo acima de R$ 100,00.");
        }

        System.out.println("=== GESTOR DE TAREFAS ===");
        System.out.println("Modelo orientado a objetos inicializado.");
        System.out.println();
        System.out.println("Objetos criados:");
        System.out.println("- um responsável");
        System.out.println("- tarefa: revisar requisitos");
        System.out.println("- tarefa: implementar a tela inicial");
        System.out.println("- tarefa: validar a aplicação");
        System.out.println();
        System.out.println("As três tarefas são objetos distintos da classe Tarefa.");
        System.out.println("Responsavel :" + responsavel.obterNome());
        System.out.println("Matrícula: " + responsavel.obterMatricula());
        System.out.println("Ativo: " + responsavel.estaAtivo());
        System.out.println("- " + tarefaRevisarRequisitos.obterTitulo());
        System.out.println("- " + tarefaImplementarTela.obterTitulo());
        System.out.println("- " + tarefaValidarAplicacao.obterTitulo());
        System.out.println(tarefaRevisarRequisitos.obterSituacao());

        System.out.println();
        System.out.println("=== RESUMO FINAL ===");
        System.out.println(
                tarefaRevisarRequisitos.obterCodigo()
                        + " - "
                        + tarefaRevisarRequisitos.obterTitulo()
                        + " - "
                        + tarefaRevisarRequisitos.obterSituacao());

        System.out.println(
                tarefaImplementarTela.obterCodigo()
                        + " - "
                        + tarefaImplementarTela.obterTitulo()
                        + " - "
                        + tarefaImplementarTela.obterSituacao());

        System.out.println(
                tarefaValidarAplicacao.obterCodigo()
                        + " - "
                        + tarefaValidarAplicacao.obterTitulo()
                        + " - "
                        + tarefaValidarAplicacao.obterSituacao());

    }
}