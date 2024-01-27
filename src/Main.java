import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        //Cores
        String Azul = "\u001B[34m";
        String reset = "\u001B[0m";
        String Negrito = "\u001B[1m";
        String Vermelho = "\u001B[31m";

        Scanner scanner = new Scanner(System.in);
        //Coração do progama:
        while (true) {
            System.out.println("##################\n" +
                    "#####"+ Azul +" AGENDA "+ reset +"#####\n"+
                    "##################\n");

            System.out.println(Negrito +">>>>"+ reset + Azul + " Contatos "+ reset + Negrito +"<<<<\n"+ reset +
                    "Id | Nome");

            //Apresentação dos contatos na lista
            agenda.exibirAgenda();

            //Menu
            System.out.println("\n" +
                    Negrito+">>>>"+ reset + Vermelho +" Menu "+ reset + Negrito+"<<<<"+ reset);
            System.out.println("1 - Adicionar Contato\n"+
                    "2 - Remover Contato \n"+
                    "3 - Editar Contato\n"+
                    "4 - Sair");

            System.out.println("Selecione a opção:");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();

                switch(opcao){
                    case 1:
                        agenda.addContato();
                        break;

                    case 2:
                        agenda.rmContato();
                        break;
                    case 3:
                        // Restante do código do case 3
                        // ...
                        break;
                    case 4:
                        // Sair do programa
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, digite um número correspondente à opção desejada.");
                        break;
                }
            } else {
                System.out.println("Opção inválida. Por favor, digite um número correspondente à opção desejada.");
                scanner.next(); // Limpa o buffer do Scanner
            }
        }


    }

}