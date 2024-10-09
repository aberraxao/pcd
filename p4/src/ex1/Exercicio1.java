package ex1;

public class Exercicio1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("------ versão 1 ------");
        versao1();

        System.out.println("------ versão 2 ------");
        // Lança uma InterruptedException
        versao2();
    }


    private static void versao1() {
        // Criar uma mesa partilhada
        BufferMesa mesa = new BufferMesa();

        // Criar 4 cozinheiros que produzem 5 javalis
        Cozinheiro[] cozinheiros = new Cozinheiro[4];
        for (int i = 0; i < cozinheiros.length; i++) {
            cozinheiros[i] = new Cozinheiro(i, 5, mesa);
        }
        // Criar 5 glutoes que consomem 4 javalis
        Gaules[] glutoes = new Gaules[5];
        for (int i = 0; i < glutoes.length; i++) {
            glutoes[i] = new Gaules(i, 4, mesa);
        }

        // Iniciar todas as threadas e verificar mensagens de coordenacao
        for (Cozinheiro cozinheiro : cozinheiros) {
            cozinheiro.start();
        }
        for (Gaules gaules : glutoes) {
            gaules.start();
        }

    }

    private static void versao2() throws InterruptedException {
        BufferMesa mesa = new BufferMesa();

        Cozinheiro[] cozinheiros = new Cozinheiro[4];
        for (int i = 0; i < cozinheiros.length; i++) {
            cozinheiros[i] = new Cozinheiro(i, -1, mesa);
        }
        Gaules[] glutoes = new Gaules[5];
        for (int i = 0; i < glutoes.length; i++) {
            glutoes[i] = new Gaules(i, -1, mesa);
        }

        for (Cozinheiro cozinheiro : cozinheiros) {
            cozinheiro.start();
        }
        for (Gaules gaules : glutoes) {
            gaules.start();
        }

        Thread.sleep(10000);
        for (Cozinheiro cozinheiro : cozinheiros) {
            cozinheiro.interrupt();
        }
        for (Gaules gaules : glutoes) {
            gaules.interrupt();
        }
    }

}
