import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos = new ArrayList<>();

    public void addContato(long id, String nome, String sobrenome, List<Telefone> telefones){
        if(id >0){
            if (!verificaIdExistente(id) && !verificaNumExistente(ddd, numero)) {
                Telefone novoTelefone = new Telefone(id, ddd, numero);
                this.telefones.add(novoTelefone);
            }
        }
    }

}
