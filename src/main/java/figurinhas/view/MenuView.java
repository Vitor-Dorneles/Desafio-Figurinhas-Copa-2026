package figurinhas.view;

import java.util.Collection;
import java.util.Scanner;

import figurinhas.model.Figurinha;

/**
 * Responsável por toda a interação com o terminal: exibir menus, ler
 * entradas do usuário e mostrar mensagens. Não toma nenhuma decisão de
 * negócio — só mostra informação e coleta o que o usuário digita.
 */
public class MenuView {

    public void exibirMenu() {
        System.out.println("\n   FIGURINHAS COPA 26");
        System.out.println("===================================");
        System.out.println("1 - Cadastrar figurinhas repetidas pessoais");
        System.out.println("2 - Listar figurinhas repetidas pessoais");
        System.out.println("3 - Cadastrar figuras desejadas pessoais");
        System.out.println("4 - Listar figuras desejadas pessoais");
        System.out.println("5 - Carregar figuras repetidas OUTRO (Match com desejadas)");
        System.out.println("6 - Carregar figuras desejadas OUTRO (Match com repetidas)");
        System.out.println("7 - Sair");
    }

    public String lerTexto(Scanner teclado, String mensagem) {
        System.out.print(mensagem);
        return teclado.nextLine().trim();
    }

    public int lerInteiro(Scanner teclado, String mensagem) {
        // Agora a entrada é sempre lida como texto (nextLine()) e convertida com
        // parseInt(); se falhar, pede de novo em vez de travar o programa.
        while (true) {
            System.out.print(mensagem);
            String entrada = teclado.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido — digite um número inteiro.");
            }
        }
    }

    public boolean lerBooleano(Scanner teclado, String mensagem) {
        // Mesma proteção do lerInteiro, aplicada ao campo de raridade (que antes
        // usava teclado.nextBoolean().
        while (true) {
            System.out.print(mensagem);
            String entrada = teclado.nextLine().trim().toLowerCase();
            if (entrada.equals("true") || entrada.equals("false")) {
                return Boolean.parseBoolean(entrada);
            }
            System.out.println("Valor inválido, digite 'true' ou 'false'.");
        }
    }

    public void exibirFigurinhas(Collection<Figurinha> figurinhas, String mensagemSeVazio) {
        if (figurinhas.isEmpty()) {
            System.out.println(mensagemSeVazio);
        } else {
            for (Figurinha f : figurinhas) {
                System.out.println(f);
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
