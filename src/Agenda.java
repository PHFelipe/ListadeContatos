import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Agenda {
    private List<Contato> contatos = new ArrayList<>();

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public boolean verificaIdExistente(Long idNovo) {
        for (Contato contato : this.contatos) {
            if (contato.getId() == idNovo) {
                return true;
            }
        }
        return false;
    }

    public void addContato() {
        Scanner scanner = new Scanner(System.in);
        Contato novoContato = new Contato();

        //######## Entrada de ID ########
        System.out.println("Digite o ID do Contato:");
        String idNovo = scanner.nextLine();

        while(!contemNumero(idNovo) || verificaIdExistente(Long.valueOf(idNovo))){
            if(!contemNumero(idNovo)) {
                System.out.println("O ID deve ser numérico! Digite o ID novamente:");
            }else if(verificaIdExistente(Long.valueOf(idNovo))){
                System.out.println("Id Já cadastrado na lista");
            }
            idNovo = scanner.nextLine();
        }
        System.out.println("Id Cadastrado com Sucesso.");

        //######## Entrada de Nome ########
        System.out.println("Digite o nome e o sobrenome separados por espaço:");
        String novoNome = scanner.nextLine();
        String[] nomeEsobrenome = novoNome.split(" ");

        while(novoNome.trim().isEmpty()){
            System.out.println("O nome não pode estar vazio");
            System.out.println("Digite o nome e o sobrenome separados por espaço:");
            novoNome = scanner.nextLine();
        }
        System.out.println("Nome Cadastrado com Sucesso.");

        //######## Entrada de Telefone ########
        boolean adicionouContato;
        do {
            System.out.println("Digite o ID do Telefone:");
            String idTelefone = scanner.nextLine();

            while (!contemNumero(idNovo)) {
                System.out.println("O ID deve ser numérico! Digite o ID novamente:");
                idTelefone = scanner.nextLine();
            }
            System.out.println("Id Cadastrado com Sucesso.");


            System.out.println("Informe o número com o DDD Exemplo->(11939254969):");
            String numeroEddd = scanner.nextLine();

            while (numeroEddd.length() != 11 || !contemNumero(numeroEddd)) {
                System.out.println("Número inválido, Digite um número com 11 digitos.");
                System.out.println("Informe o número com o DDD Exemplo->(11939254969):");
                numeroEddd = scanner.nextLine();
            }
            adicionouContato = novoContato.addTelefone(Long.valueOf(idTelefone), numeroEddd.substring(0, 2), Long.valueOf(numeroEddd.substring(2)));
        }while(!adicionouContato);
        System.out.println("Número Cadastrado com Sucesso.");

        //######## Setagem de dados ########
        novoContato.setId(Long.valueOf(idNovo));

        if(nomeEsobrenome.length == 1){
            novoContato.setNome(nomeEsobrenome[0]);
            novoContato.setSobreNome("");
        }else{
            novoContato.setNome(nomeEsobrenome[0]);
            novoContato.setSobreNome(nomeEsobrenome[1]);
        }

        this.contatos.add(novoContato);
        System.out.println("Contato cadastrado com sucesso na Agenda.");
    }

    public void rmContato() {
        Scanner scanner = new Scanner(System.in);
        boolean idRemovido = false;

        do {
            System.out.println("Digite o ID do Telefone que voce deseja remover:");
            String id = scanner.nextLine();


            if (!contemNumero(id)) {
                System.out.println("O ID deve ser numérico! Digite o ID novamente:");
                continue;
            }

            if (verificaIdExistente(Long.valueOf(id))) {
                String finalId = id;
                contatos.removeIf(contato -> contato.getId() == Long.valueOf(finalId));
                System.out.println("Telefone removido com sucesso.");
                idRemovido = true;
            } else {
                System.out.println("Id " + id + " não encontrado na lista de numeros. Por favor, tente novamente.");
            }

        }while (!idRemovido);
    }

    public void editContato() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do Telefone que voce deseja remover:");
        String id = scanner.nextLine();

        while(!contemNumero(id)) {
            System.out.println("O ID deve ser numérico! Digite o ID novamente:");
            id = scanner.nextLine();
        }


        if (verificaIdExistente(Long.valueOf(id))) {
            System.out.println("Digite o nome e o sobrenome separados por espaço:");
            String novoNome = scanner.nextLine();
            String[] nomeEsobrenome = novoNome.split(" ");

            while (novoNome.trim().isEmpty()) {
                System.out.println("O nome não pode estar vazio");
                System.out.println("Digite o nome e o sobrenome separados por espaço:");
                novoNome = scanner.nextLine();
            }

            for (Contato contato : this.contatos) {
                if (Objects.equals(contato.getId(), Long.valueOf(id))) {
                    if (nomeEsobrenome.length == 1) {
                        contato.setNome(nomeEsobrenome[0]);
                        contato.setSobreNome("");
                    } else {
                        contato.setNome(nomeEsobrenome[0]);
                        contato.setSobreNome(nomeEsobrenome[1]);
                    }
                }
            }
            System.out.println("Contato editado com sucesso!");
        }else{
            System.out.println("ID " + id + " não encontrado na lista de números. Por favor, tente novamente.");
        }
    }

    public void exibirAgenda(){
        if(this.contatos.size() >= 1){
            for(Contato contato : this.contatos){
                System.out.println(contato.getId() + " | " + contato.getNome() + " " + contato.getSobreNome());
            }
        }
    }
    public static boolean contemNumero(String input) {
        for (char caractere : input.toCharArray()) {
            if (Character.isDigit(caractere)) {
                return true;
            }
        }
        return false;
    }

}

