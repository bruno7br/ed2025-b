import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            System.out.println("Escolha o tamanho do tabuleiro:");
            int tamanho = sc.nextInt();

            JogoDaVelha partida = new JogoDaVelha(tamanho);
            int estadoJogo = partida.poePecaAutomatico();

            while (estadoJogo == 2) {
                System.out.println(partida.toString());
                System.out.println("Vez do jogador " + (partida.getJogador() == 1 ? "X" : "O"));
                System.out.println();
                estadoJogo = partida.poePecaAutomatico();
            }

            System.out.println(partida.toString());

            if (estadoJogo == 1) {
                System.out.println("Jogador X venceu!");
            } else if (estadoJogo == -1) {
                System.out.println("Jogador O venceu!");
            } else {
                System.out.println("Empate!");
            }

            System.out.println("O jogador venceu usando: " + partida.venceuUsando());

            int resposta = -1;
            do {
                System.out.println("Jogar de novo? 1 para sim e 0 para nao: ");
                try {
                    resposta = sc.nextInt();
                    if (resposta != 0 && resposta != 1) {
                        System.out.println("acao invalida!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Apenas 0 ou 1:");
                    sc.nextLine(); 
                }
            } while (resposta != 0 && resposta != 1);

            jogarNovamente = (resposta == 1);
        }

        System.out.println("Fim de jogo!!!");
        sc.close();
    }
}
