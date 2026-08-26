package figurinhas.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

import figurinhas.model.Figurinha;

// Responsável por ler e escrever Figurinhas em arquivos CSV.
public class FiguraCsvRepository {

    public void carregarArquivo(String caminhoArquivo, TreeSet<Figurinha> arvore) {
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo, StandardCharsets.UTF_8))) {
            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;

                if (linha.isBlank()) {
                    continue;
                }

                //cada linha é processada num try/catch próprio: se uma linha for inválida, ela é avisada e ignorada, e a leitura das demais linhas continua normalmente.
                try {
                    String[] campos = linha.split(",");

                    if (campos.length != 5) {
                        System.out.println("Aviso: linha " + numeroLinha + " ignorada (esperado 5 campos): " + linha);
                        continue;
                    }

                    String selecao = campos[0].trim(); // trim para desconsiderar espaços em branco no início e no final
                    int numero = Integer.parseInt(campos[1].trim());
                    String descricao = campos[2].trim();
                    int quantidade = Integer.parseInt(campos[3].trim());
                    boolean rara = Boolean.parseBoolean(campos[4].trim());

                    arvore.add(new Figurinha(selecao, numero, descricao, quantidade, rara));
                } catch (NumberFormatException e) {
                    System.out.println("Aviso: linha " + numeroLinha + " ignorada (número inválido): " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void salvarFiguraCSV(String caminhoArquivo, Figurinha figura) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            String linhaCSV = figura.getNomeSelecao() + "," + figura.getNumeroFigura() + "," + figura.getDescricao()
                    + "," + figura.getQuantidade() + "," + figura.isRaridade();

            escritor.write(linhaCSV);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar: " + e.getMessage());
        }
    }
}
