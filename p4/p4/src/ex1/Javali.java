package ex1;

public class Javali {
    private int cozinheiroID; // Id do cozinheiro
    private int ordem; // i-easimo javali do cozinheiro

    public Javali(int cozinheiroID, int ordem) {
        this.cozinheiroID = cozinheiroID;
        this.ordem = ordem;
    }

    @Override
    public String toString(){
        return "Javali #" + this.ordem + " do cozinheiro " + cozinheiroID + ".";
    }

}