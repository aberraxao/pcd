package ex1;

public class Exercicio1 {

    public static void main(String[] args) {

        // Criar uma mesa partilhada
        BufferMesa mesa = new BufferMesa();
        // Criar 5 cozinheiros que produzem 10 javalis
        Cozinheiro[] cozinheiros = new Cozinheiro[5];
        for (int i = 0; i < cozinheiros.length; i++) {
            cozinheiros[i] = new Cozinheiro(i, 10, mesa);
        }
        // Criar 10 glutoes que consomem 5 javalis
        Glutao[] glutoes = new Glutao[10];
        for (int i = 0; i < glutoes.length; i++) {
            glutoes[i] = new Glutao(i, 5, mesa);
        }

        // Iniciar todas as threadas e verificar mensagens de coordenacao
        for (Cozinheiro cozinheiro: cozinheiros){
            cozinheiro.start();
        }
        for (Glutao glutao: glutoes){
            glutao.start();
        }

    }
}
