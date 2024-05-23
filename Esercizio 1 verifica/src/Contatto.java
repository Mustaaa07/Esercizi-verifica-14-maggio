public class Contatto {
    public String nome;
    public String cognome;
    public boolean nascosto;

    public Contatto(String nome, String cognome, boolean nascosto) {
        this.nome = nome;
        this.cognome = cognome;
        this.nascosto = nascosto;
    }

    public String toString() {
        return nome + " " + cognome;
    }
}
