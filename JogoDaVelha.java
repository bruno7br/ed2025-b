import java.util.Random;

public class JogoDaVelha {
    private int[][] tabuleiro;
    private int jogadorAtual;
    private int tamanho;
    private String tipoVitoria = "continuar";

    public JogoDaVelha(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new int[tamanho][tamanho];
        this.jogadorAtual = 1; 
    }

    public int getJogador() {
        return jogadorAtual;
    }

    public int poePecaAutomatico() {
        Random rand = new Random();
        int linha, coluna;

        do {
            linha = rand.nextInt(tamanho);
            coluna = rand.nextInt(tamanho);
        } while (tabuleiro[linha][coluna] != 0);

        tabuleiro[linha][coluna] = jogadorAtual;

        if (verificaVitoria(linha, coluna)) {
            return jogadorAtual; // 1 (X) ou -1 (O) ganhou
        } else if (verificaEmpate()) {
            tipoVitoria = "empate";
            return 0; // Empate
        } else {
            jogadorAtual *= -1; // Troca jogador
            return 2; // Continua o jogo
        }
    }

    private boolean verificaVitoria(int linha, int coluna) {
        // Verificar linha
        boolean linhaGanha = true;
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[linha][i] != jogadorAtual) {
                linhaGanha = false;
                break;
            }
        }
        if (linhaGanha) {
            tipoVitoria = "linha";
            return true;
        }

        // Verificar coluna
        boolean colunaGanha = true;
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[i][coluna] != jogadorAtual) {
                colunaGanha = false;
                break;
            }
        }
        if (colunaGanha) {
            tipoVitoria = "coluna";
            return true;
        }

        // Verificar diagonal principal
        if (linha == coluna) {
            boolean diagonalPrincipalGanha = true;
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[i][i] != jogadorAtual) {
                    diagonalPrincipalGanha = false;
                    break;
                }
            }
            if (diagonalPrincipalGanha) {
                tipoVitoria = "diagonal";
                return true;
            }
        }

        // Verificar diagonal secundária
        if (linha + coluna == tamanho - 1) {
            boolean diagonalSecundariaGanha = true;
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[i][tamanho - 1 - i] != jogadorAtual) {
                    diagonalSecundariaGanha = false;
                    break;
                }
            }
            if (diagonalSecundariaGanha) {
                tipoVitoria = "diagonal";
                return true;
            }
        }

        return false;
    }

    private boolean verificaEmpate() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == 0) {
                    return false;
                }
            }
        }
        return true; 
    }

    public String venceuUsando() {
        return tipoVitoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] linha : tabuleiro) {
            for (int valor : linha) {
                if (valor == 1) {
                    sb.append(" X ");
                } else if (valor == -1) {
                    sb.append(" O ");
                } else {
                    sb.append(" . ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
