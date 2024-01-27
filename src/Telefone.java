
public class Telefone {
    private Long id;
    private String ddd;
    private Long numero;

    //Construtor:
    public Telefone(Long id, String ddd,Long numero){
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
    }

    //Métodos Set:

    public void setId(Long id){this.id = id;}
    public void setDdd(String ddd){this.ddd = ddd;}
    public void setNumero(Long numero){this.numero = numero;}

    //Métodos Get:

    public Long getId(){return id;}
    public String getDdd(){return ddd;}
    public Long getNumero() {return numero;}


}