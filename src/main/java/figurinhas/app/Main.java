package figurinhas.app;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import figurinhas.controller.FigurinhaController;
import figurinhas.persistence.FiguraCsvRepository;
import figurinhas.view.MenuView;

public class Main {
    public static void main(String[] args) {
        // feature para ajuste dos acentos em qualquer terminal
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner teclado = new Scanner(System.in, StandardCharsets.UTF_8);

        MenuView view = new MenuView();
        FiguraCsvRepository repositorio = new FiguraCsvRepository();
        FigurinhaController controller = new FigurinhaController(teclado, view, repositorio);

        controller.iniciar();
        controller.executar();

        teclado.close();
    }
}
