import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Contato {
    private long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones = new ArrayList<>();

    // Construtor
    public Contato(long id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = new ArrayList<>(telefones);
    }

    // Métodos set
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    // Métodos Get
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    // Controle de dados
    public boolean verificaIdExistente(long idNovo) {
        for (Telefone telefone : this.telefones) {
            if (telefone.getId() == idNovo) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaNumExistente(String ddd, long numero) {
        for (Telefone telefone : this.telefones) {
            if (telefone.getDdd().equals(ddd) && telefone.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }
    public static List<Telefone> recebeTelefones(Scanner scanner) {
        List<Telefone> telefones = new ArrayList<>();

        System.out.println("Quantos telefones você deseja adicionar?");
        int quantidadeTelefones = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < quantidadeTelefones; i++) {
            System.out.println("Digite o Id do contato:");
            long idContato;
            do {
                System.out.println("Digite o ID:");
                while (!scanner.hasNextLong()) {
                    System.out.println("O ID registrado é inválido! Digite novamente:");
                    scanner.next();
                }
                idContato = scanner.nextLong();
                if (idContato <= 0) {
                    System.out.println("O ID registrado deve ser positivo! Digite novamente:");
                }
            } while (idContato <= 0);

            System.out.println("Digite o DDD do telefone:");
            String ddd = scanner.nextLine();

            // Caso o DDD seja inválido ele vai solicitar um DDD válido
            do {
                System.out.println("Digite o DDD (Apenas 2 dígitos numéricos):");
                ddd = scanner.nextLine();

                if (ddd.length() == 1) {
                    ddd = "0" + ddd; // Adiciona zero no início se o DDD tiver apenas um dígito
                }

            } while (ddd.length() < 2 || contemLetra(ddd) || ddd.length() > 2);

            System.out.println("Digite o Número do telefone:");
            long numero;
            do {
                System.out.println("Digite um número de telefone válido (11 dígitos):");
                numero = scanner.nextLong();
            } while (Long.toString(numero).length() != 11);

            // Instanciando um novo telefone e adicionando à lista
            Telefone novoTelefone = new Telefone(idContato, ddd, numero);
            telefones.add(novoTelefone);
        }

        // Retornando a lista de telefones
        return telefones;
    }

    public static boolean contemLetra(String input) {
        for (char caractere : input.toCharArray()) {
            if (Character.isDigit(caractere)) {
                return true;
            }
        }
        return false;
    }

    // Adição, Remoção, Edição
    public void addTelefone(long id, String ddd, long numero) {
        if (id > 0) {//Antes de adicionar é verificado se o id ou o numero já existe na lista de telefones e o id se é maior que 0
            if (!verificaIdExistente(id) && !verificaNumExistente(ddd, numero)) {
                Telefone novoTelefone = new Telefone(id, ddd, numero);
                this.telefones.add(novoTelefone);
                System.out.println("Telefone cadastrado com sucesso.");
            } else {
                if (verificaIdExistente(id)) {
                    System.out.println("O Id " + id + " já está cadastrado na lista de telefones.");
                } else if (verificaNumExistente(ddd, numero)) {
                    System.out.println("O número " + ddd + " " + numero + " já foi cadastrado na lista de telefones.");
                }
            }
        } else {
            System.out.println("O Id deve ser Obrigatoriamente maior que zero");
        }
    }

    public void rmTelefone(long id) {
        if (verificaIdExistente(id)) {
            telefones.removeIf(telefone -> telefone.getId() == id);
            System.out.println("Telefone removido com sucesso.");
        } else {
            System.out.println("Id " + id + " não encontrado na lista de numeros. Por favor, tente novamente.");
        }
    }

    public void editTelefone(long id, String ddd, long numero) {
        //Verificação de existencia de ID e numero
        if (verificaIdExistente(id) && verificaNumExistente(ddd, numero)) {
            Scanner scanner = new Scanner(System.in);

            boolean numeroUnicoInserido = false;

            do {
                System.out.println("Digite o novo DDD:");
                String novoDdd = scanner.next();
                System.out.println("Digite o novo número:");
                long novoNumero = scanner.nextLong();
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

            scanner.close();
        } else {
            System.out.println("Telefone não encontrado na agenda.");
        }
    }
}

