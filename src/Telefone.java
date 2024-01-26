
public class Telefone {
    private Long id;
    private String ddd;
    private Long numero;

    //Construtor:
    public Telefone(long id, String ddd,long numero){
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
    }

    //Métodos Set:

    public void setId(long id){this.id = id;}
    public void setDdd(String ddd){this.ddd = ddd;}
    public void setNumero(long numero){this.numero = numero;}

    //Métodos Get:

    public Long getId(){return id;}
    public String getDdd(){return ddd;}
    public Long getNumero() {return numero;}


}