import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones = new ArrayList<>();

    // Construtor
    public Contato() {
        this.id = 0L;
        this.nome = new String();
        this.sobreNome = new String();
        this.telefones = new ArrayList<>(telefones);
    }

    // Métodos set
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    // Métodos Get
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    // Controle de dados
    public boolean verificaIdExistente(Long idNovo) {
        for (Telefone telefone : this.telefones) {
            if (telefone.getId() == idNovo) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaNumExistente(String ddd, Long numero) {
        for (Telefone telefone : this.telefones) {
            if (telefone.getDdd().equals(ddd) && telefone.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }


    // Adição, Remoção, Edição
    public boolean addTelefone(Long id, String ddd, Long numero) {
            if (!verificaIdExistente(id) && !verificaNumExistente(ddd, numero)) {
                Telefone novoTelefone = new Telefone(id, ddd, numero);
                this.telefones.add(novoTelefone);
                return true;
            } else {
                if (verificaIdExistente(id)) {
                    System.out.println("O Id " + id + " já está cadastrado na lista de telefones.");
                } else if (verificaNumExistente(ddd, numero)) {
                    System.out.println("O número " + ddd + " " + numero + " já foi cadastrado na lista de telefones.");
                }
            }
            return false;
    }

    public void rmTelefone() {
        Scanner scanner = new Scanner(System.in);
        boolean idRemovido = false;

        do {
            System.out.println("Digite o ID do Telefone que voce deseja remover:");
            String idTelefone = scanner.nextLine();


            if (!Agenda.contemNumero(idTelefone)) {
                System.out.println("O ID deve ser numérico! Digite o ID novamente:");
                continue;
            }

            if (verificaIdExistente(Long.valueOf(idTelefone))) {
                String finalIdTelefone = idTelefone;
                telefones.removeIf(telefone -> telefone.getId() == Long.valueOf(finalIdTelefone));
                System.out.println("Telefone removido com sucesso.");
                idRemovido = true;
            } else {
                System.out.println("Id " + id + " não encontrado na lista de numeros. Por favor, tente novamente.");
            }

        }while(!idRemovido);
    }

    public void editTelefone(Contato contato) {
        Scanner scanner = new Scanner(System.in);
        boolean numeroUnico = false;

        System.out.println("Digite o ID do Telefone:");
        String idTelefone = scanner.nextLine();

        while (!Agenda.contemNumero(idTelefone)) {
            System.out.println("O ID deve ser numérico! Digite o ID novamente:");
            idTelefone = scanner.nextLine();
        }

        if (verificaIdExistente(Long.valueOf(idTelefone))) {
            do {
                System.out.println("Informe o novo número com o DDD Exemplo->(11939254969):");
                String numeroEddd = scanner.nextLine();

                while (numeroEddd.length() != 11 || !Agenda.contemNumero(numeroEddd)) {
                    System.out.println("Número inválido, Digite um número com 11 digitos.");
                    System.out.println("Informe o número com o DDD Exemplo->(11939254969):");
                    numeroEddd = scanner.nextLine();
                }

                //Adiciona o Telefone caso seja único
                if (!verificaNumExistente(numeroEddd.substring(0, 2), Long.valueOf(numeroEddd.substring(2)))) {

                    for (Telefone telefone : contato.telefones) {
                        if (Objects.equals(telefone.getId(), Long.valueOf(idTelefone))) {

                            telefone.setDdd(numeroEddd.substring(0, 2));
                            telefone.setNumero(Long.valueOf(numeroEddd.substring(2)));
                            numeroUnico = true;
                            System.out.println("Telefone atualizado com sucesso.");
                        }
                    }
                } else {
                    System.out.println("Este número já existe na agenda. Tente novamente.");
                }

            } while (!numeroUnico);
        }
    }

}