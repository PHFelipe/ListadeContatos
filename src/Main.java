import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        List<Contato> contatos = new ArrayList<>();
        // Criando alguns contatos com telefones
        Contato contato1 = new Contato(1, "João", "Silva", new ArrayList<>());
        contato1.addTelefone(1, "123", 987654321);

        Contato contato2 = new Contato(2, "Maria", "Oliveira", new ArrayList<>());
        contato2.addTelefone(2, "456", 987654322);

        Contato contato3 = new Contato(3, "Carlos", "Santos", new ArrayList<>());
        contato3.addTelefone(3, "789", 987654323);

        // Adicionando os contatos à lista
        contatos.add(contato1);
        contatos.add(contato2);
        contatos.add(contato3);

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
            for (Contato contato : contatos) {
                System.out.println(contato.getId() + "  | " + contato.getNome() + " "+ contato.getSobreNome());
            }

            //Menu
            System.out.println("\n" +
                    Negrito+">>>>"+ reset + Vermelho +" Menu "+ reset + Negrito+"<<<<"+ reset);
            System.out.println("1 - Adicionar Contato\n"+
                    "2 - Remover Contato\n"+
                    "3 - Editar Contato\n"+
                    "4 - Sair");

            System.out.println("Selecione a opção:");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();

                switch(opcao){
                    case 1:
                        //Dados para um novo contato
                        long novoId = agenda.recebeId(scanner);
                        String[] nomes = agenda.recebeNome(scanner);
                        String primeiroNome = nomes[0];
                        String sobrenome = nomes.length > 1 ? nomes[1] : "";

                        agenda.addContato(novoId,primeiroNome,sobrenome,Contato.recebeTelefones(scanner));

                        break;

                    case 2:
                        System.out.println("Estamos em construção, contacte o programador");
                        break;
                    case 3:
                        // Restante do código do case 3
                        // ...
                        break;
                    case 4:
                        // Restante do código do case 4
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

            scanner.close();
            break;
        }

    }

}