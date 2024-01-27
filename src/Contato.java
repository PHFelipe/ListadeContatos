import java.util.List;
import java.util.ArrayList;
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

    public void editTelefone(Long id, String ddd, Long numero) {
        //Verificação de existencia de ID e numero
        if (verificaIdExistente(id) && verificaNumExistente(ddd, numero)) {
            Scanner scanner = new Scanner(System.in);

            boolean numeroUnicoInserido = false;

            do {
                System.out.println("Digite o novo DDD:");
                String novoDdd = scanner.next();
                System.out.println("Digite o novo número:");
                Long novoNumero = scanner.nextLong();
                //Verificação se o novo numero com ddd existe na lista e adiciona na lista caso seja realmente novo
                if (!verificaNumExistente(novoDdd, novoNumero)) {
                    for (Telefone telefone : telefones) {
                        if (telefone.getDdd().equals(ddd) && telefone.getNumero() == numero) {
                            telefone.setNumero(novoNumero);
                            System.out.println("Telefone atualizado com sucesso.");
                            numeroUnicoInserido = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("Este número já existe na agenda. Tente novamente.");
                }
            } while (!numeroUnicoInserido);

        } else {
            System.out.println("Telefone não encontrado na agenda.");
        }
    }

}


