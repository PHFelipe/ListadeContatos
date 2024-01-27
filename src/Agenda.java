import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    private List<Contato> contatos = new ArrayList<>();

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public boolean verificaNomeExistente(String nome, String Sobrenome){
        String nomeNovo = nome + Sobrenome;
        for(Contato contatoExistente : this.contatos){
            String nomeExistente = contatoExistente.getNome() + contatoExistente.getSobreNome();
            if(nomeNovo.equals(nomeExistente)){
                return true;
            }
        }
        return false;
    }
    public boolean verificaIdExistente(long idNovo) {
        for (Contato contato : this.contatos) {
            if (contato.getId() == idNovo) {
                return true;
            }
        }
        return false;
    }
    public static long recebeId(Scanner scanner) {
        long novoId;

        do {
            System.out.println("Digite o ID:");
            while (!scanner.hasNextLong()) {// Verificação se o usuário está digitando um ID numérico
                System.out.println("O ID registrado é inválido! Digite novamente:");
                scanner.next();
            }
            novoId = scanner.nextLong();
            if (novoId <= 0) {// Verificação se o usuário está digitando um ID positivo
                System.out.println("O ID registrado deve ser positivo! Digite novamente:");
            }
        } while (novoId <= 0);

        return novoId;
    }

    public static String[] recebeNome(Scanner scanner) {
        String nomeCompleto;
        do {
            System.out.println("Digite o nome e o sobrenome separado por espaço:");
            nomeCompleto = scanner.nextLine();
            if (nomeCompleto.isEmpty()) {
                System.out.println("O nome não pode estar vazio! Digite novamente:");
            }
        } while (nomeCompleto.isEmpty());

        String[] nomeEsobrenome = nomeCompleto.split(" ");

        String primeiroNome = nomeEsobrenome[0];
        if(nomeEsobrenome.length > 1) {
            String ultimoSobrenome = nomeEsobrenome[nomeEsobrenome.length - 1];
            return new String[]{primeiroNome, ultimoSobrenome};
        }
        return new String[]{primeiroNome};
    }

    public void addContato(long id, String nome, String sobreNome, List<Telefone> telefones) {
        if (id > 0) {//Antes de adicionar é verificado se o id ou o numero já existe na lista de telefones e o id se é maior que 0
            if (!verificaIdExistente(id) && !verificaNomeExistente(nome,sobreNome)) {
                Contato novoContato = new Contato(id, nome, sobreNome,telefones);
                this.contatos.add(novoContato);
                System.out.println("Contato cadastrado com sucesso na Agenda.");
            } else {//Tratamento de erros de ID igual e nome igual dentro da agenda
                if (verificaIdExistente(id)) {
                    System.out.println("O Id " + id + " já está cadastrado na Agenda.");
                }else if (verificaNomeExistente(nome,sobreNome)) {
                    System.out.println(nome + " " + sobreNome + " já está cadastrado na agenda.");
                }
            }
        } else {
            System.out.println("O Id deve ser Obrigatoriamente maior que zero");
        }
    }

    public void rmContato(long id){
        if (verificaIdExistente(id)) {
            contatos.removeIf(contato -> contato.getId() == id);
            System.out.println("Contato removido da agenda com sucesso.");
        } else {
            System.out.println("Id " + id + " não encontrado na agenda. Por favor, tente novamente.");
        }
    }

}
