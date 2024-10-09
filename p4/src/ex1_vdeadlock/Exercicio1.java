package ex1_vdeadlock;

public class Exercicio1 {

    public static void main(String[] args) {
        System.out.println("------ versão 3 ------");
        // Cria um deadlock, não lança exceção
        versao3();
    }


    private static void versao3() {
        BufferMesa mesa = new BufferMesa();

        Cozinheiro[] cozinheiros = new Cozinheiro[2];
        for (int i = 0; i < cozinheiros.length; i++) {
            cozinheiros[i] = new Cozinheiro(i, 100, mesa);
        }
        Gaules[] glutoes = new Gaules[2];
        for (int i = 0; i < glutoes.length; i++) {
            glutoes[i] = new Gaules(i, 100, mesa);
        }

        for (Cozinheiro cozinheiro : cozinheiros) {
            cozinheiro.start();
        }
        for (Gaules gaules : glutoes) {
            gaules.start();
        }
    }
}
