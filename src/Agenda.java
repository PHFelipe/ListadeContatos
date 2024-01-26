import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos = new ArrayList<>();

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

    public void addContato(Contato novoContato) {
        if (novoContato != null) {
            long id = novoContato.getId();
            if (id > 0) {
                //verifica se nao existe um ID na lista de contatos e se o nome completo é diferente de todos na lista
                if (!verificaIdExistente(id) && !verificaNomeExistente(novoContato.getNome(), novoContato.getSobreNome())) {
                    this.contatos.add(novoContato);
                    System.out.println("Contato cadastrado na agenda com sucesso.");
                }else{//Tratamento de erros de ID igual e nome igual dentro da agenda
                        if(verificaIdExistente(id)) {
                            System.out.println("O Id " + id + " já está cadastrado na agenda.");
                        }else if(verificaNomeExistente(novoContato.getNome(), novoContato.getSobreNome())){
                            System.out.println(novoContato.getNome() + " " + novoContato.getSobreNome() + " já está cadastrado na agenda.");
                        }
                }
            }
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
