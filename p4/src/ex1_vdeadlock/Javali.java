package ex1_vdeadlock;

/**
 * Classe dos dados / informação a ser recebido / transmitida pelas threads
 */
public class Javali {
    private int idCozinheiro; // Id do cozinheiro
    private int ordem; // i-easimo javali do cozinheiro

    Javali(int idCozinheiro, int ordem) {
        this.idCozinheiro = idCozinheiro;
        this.ordem = ordem;
    }

    @Override
    public String toString(){
        return "Javali #" + this.ordem + " produzido pelo Cozinheiro-" + idCozinheiro + ".";
    }
}