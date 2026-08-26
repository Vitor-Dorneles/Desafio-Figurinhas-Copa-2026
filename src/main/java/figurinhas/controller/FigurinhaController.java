package figurinhas.controller;

import java.util.Scanner;
import java.util.TreeSet;

import figurinhas.model.Figurinha;
import figurinhas.persistence.FiguraCsvRepository;
import figurinhas.view.MenuView;

/**
 * interpreta a opção escolhida no menu, chama
 * o repositório para carregar os dados, e usa a view para exibir os
 * resultados.
 */
public class FigurinhaController {

    private static final String CAMINHO_REPETIDAS_PESSOAIS = "dados/figuras_repetidas_pessoais.csv";
    private static final String CAMINHO_DESEJADAS_PESSOAIS = "dados/figuras_desejadas_pessoais.csv";

    private final Scanner teclado;
    private final MenuView view;
    private final FiguraCsvRepository repositorio;

    private final TreeSet<Figurinha> arvoreRepetidas = new TreeSet<>();
    private final TreeSet<Figurinha> arvoreDesejadas = new TreeSet<>();

    public FigurinhaController(Scanner teclado, MenuView view, FiguraCsvRepository repositorio) {
        this.teclado = teclado;
        this.view = view;
        this.repositorio = repositorio;
    }

    public void iniciar() {
        System.out.println("Carregando arquivos do sistema...");
        repositorio.carregarArquivo(CAMINHO_REPETIDAS_PESSOAIS, arvoreRepetidas);
        repositorio.carregarArquivo(CAMINHO_DESEJADAS_PESSOAIS, arvoreDesejadas);
    }

    public void executar() {
        int opcao;
        do {
            view.exibirMenu();
            opcao = view.lerInteiro(teclado, "Selecione a opção: ");

            switch (opcao) {
                case 1 -> cadastrarRepetida();
                case 2 -> view.exibirFigurinhas(arvoreRepetidas, "Nenhuma encontrada, cadastre.");
                case 3 -> cadastrarDesejada();
                case 4 -> view.exibirFigurinhas(arvoreDesejadas, "Nenhuma figurinha, cadastre.");
                case 5 -> buscarMatchesRepetidasOutro();
                case 6 -> buscarMatchesDesejadasOutro();
                case 7 -> view.exibirMensagem("\nSaindo do programa...");
                default -> view.exibirMensagem("\nOpção INVÁLIDA");
            }
            System.out.println();
        } while (opcao != 7);
    }

    private void cadastrarRepetida() {
        view.exibirMensagem("\nCadastrar Repetida");
        Figurinha nova = lerFigurinha();
        arvoreRepetidas.add(nova);
        repositorio.salvarFiguraCSV(CAMINHO_REPETIDAS_PESSOAIS, nova);
        view.exibirMensagem("Figurinha salva.");
    }

    private void cadastrarDesejada() {
        view.exibirMensagem("\nCadastrar Figurinha Desejada");
        Figurinha nova = lerFigurinha();
        arvoreDesejadas.add(nova);
        repositorio.salvarFiguraCSV(CAMINHO_DESEJADAS_PESSOAIS, nova);
        view.exibirMensagem("Figurinha cadastrada.");
    }

    private Figurinha lerFigurinha() {
        String selecao = view.lerTexto(teclado, "Nome da seleção: ");
        int numero = view.lerInteiro(teclado, "Número da figurinha: ");
        String descricao = view.lerTexto(teclado, "Descrição (nome): ");
        int quantidade = view.lerInteiro(teclado, "Quantidade: ");
        boolean rara = view.lerBooleano(teclado, "Raridade (true/false): ");
        return new Figurinha(selecao, numero, descricao, quantidade, rara);
    }

    private void buscarMatchesRepetidasOutro() {
        view.exibirMensagem("\nProcurar matches nas figurinhas repetidas do outro");
        String caminho = view.lerTexto(teclado, "Caminho do arquivo CSV (repetidas do outro): ");

        TreeSet<Figurinha> arvoreOutro = new TreeSet<>();
        repositorio.carregarArquivo(caminho, arvoreOutro);

        if (arvoreOutro.isEmpty()) {
            view.exibirMensagem("O arquivo não foi encontrado ou está vazio.");
            return;
        }

        view.exibirMensagem("\nFigurinhas de: " + caminho);
        for (Figurinha figuraOutro : arvoreOutro) {
            view.exibirMensagem(figuraOutro.toString());
            if (arvoreDesejadas.contains(figuraOutro)) {
                view.exibirMensagem(" Match de troca! Essa é uma das suas figurinhas desejadas: " + figuraOutro.getDescricao());
            }
        }
    }

    private void buscarMatchesDesejadasOutro() {
        view.exibirMensagem("\nProcurar matches nas figurinhas desejadas do outro");
        String caminho = view.lerTexto(teclado, "Caminho do arquivo CSV (desejadas do outro): ");

        TreeSet<Figurinha> arvoreOutro = new TreeSet<>();
        repositorio.carregarArquivo(caminho, arvoreOutro);

        if (arvoreOutro.isEmpty()) {
            view.exibirMensagem("O arquivo não foi encontrado, está vazio, ou nenhuma linha era válida.");
            return;
        }

        view.exibirMensagem("\nFigurinhas de: " + caminho);
        for (Figurinha figuraOutro : arvoreOutro) {
            view.exibirMensagem(figuraOutro.toString());
            if (arvoreRepetidas.contains(figuraOutro)) {
                view.exibirMensagem(" Match de troca! Você tem essa figurinha repetida para trocar: " + figuraOutro.getDescricao());
            }
        }
    }
}
