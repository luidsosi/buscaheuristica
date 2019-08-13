import java.util.ArrayList;

public class OitoRainhas {
    private char[][] estado;
    private int quantidadeAtaques = 0;
    public int[] posicaoRainhaColuna;
    private final int TAMANHO_TABULEIRO = 8;

    public OitoRainhas() {
        // TODO Auto-generated constructor stub
    }


    public OitoRainhas(int[] posicaoRainhaColuna) {
        this.posicaoRainhaColuna = posicaoRainhaColuna;
        setQuantidadeAtaques();
    }

    public OitoRainhas(char[][] estado) {
        this.estado = estado;
        this.posicaoRainhaColuna = new int[8];
        this.quantidadeAtaques = 0;
        setPosicaoRainhaColuna();
        setQuantidadeAtaques();
    }

    public char[][] getEstado() {
        return estado;
    }

    public void setEstado(char[][] estado) {
        this.estado = estado;
    }

    public int getQuantidadeAtaques() {
        if (quantidadeAtaques != 0) {
            return quantidadeAtaques;
        }

        setQuantidadeAtaques();

        return quantidadeAtaques;
    }

    public void setQuantidadeAtaques(int quantidadeAtaques) {
        this.quantidadeAtaques = quantidadeAtaques;
    }

    public void setQuantidadeAtaques() {
        for (int i = 0; i < posicaoRainhaColuna.length; i++) {
            for (int j = 0; j < posicaoRainhaColuna.length; j++) {
                if (i != j) {
//					Verifica Linha
                    if (posicaoRainhaColuna[i] == posicaoRainhaColuna[j]) {
                        quantidadeAtaques++;
                    }

//					Verifica diagonal
                    if ((i + posicaoRainhaColuna[i]) == (j + posicaoRainhaColuna[j]) ||
                            (posicaoRainhaColuna[i] - i + 7) == (posicaoRainhaColuna[j] - j + 7)) {
                        quantidadeAtaques++;
                    }
                }
            }
        }

        quantidadeAtaques /= 2;
    }

    public int[] getPosicaoRainhaColuna() {
        return posicaoRainhaColuna;
    }


    public void setPosicaoRainhaColuna(int[] posicaoRainhaColuna) {
        this.posicaoRainhaColuna = posicaoRainhaColuna;
    }

    public void setPosicaoRainhaColuna() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (estado[j][i] == 'R') {
                    posicaoRainhaColuna[i] = j;
                }
            }
        }
    }

    public void printPosicaoRainhas() {
        for (int i = 0; i < posicaoRainhaColuna.length; i++) {
            System.out.println(i + ". X = " + posicaoRainhaColuna[i] + (", Y = " + i));
        }
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "---------------------------------";

        for (int i = 0; i < estado.length; i++) {
            msg += "\n";
            for (int j = 0; j < estado.length; j++) {
                msg += "| " + estado[i][j] + " ";
            }
            msg += "|";
            if (i == 4) msg += " Quantidade de Ataques: " + getQuantidadeAtaques();
            msg += "\n";
            msg += "---------------------------------";
        }

        return msg;
    }

    public void print() {
        System.out.println(toString());
    }

    public void subidaEncosta() {
        System.out.println("Começando.");
        OitoRainhas estadoMenor = new OitoRainhas(posicaoRainhaColuna);
        OitoRainhas estadoFinal = new OitoRainhas(posicaoRainhaColuna);
        estadoFinal.setQuantidadeAtaques(estadoMenor.getQuantidadeAtaques() + 1);


        while (estadoFinal.getQuantidadeAtaques() != 1 && estadoMenor.getQuantidadeAtaques() < estadoFinal.getQuantidadeAtaques()) {
            System.out.println("Quantidade Ataques do menor: " + estadoMenor.getQuantidadeAtaques());
            System.out.println("Quantidade Ataques do final: " + estadoFinal.getQuantidadeAtaques());
            int[] aux = new int[TAMANHO_TABULEIRO];

            for (int i = 0; i < aux.length; i++) {
                aux[i] = estadoMenor.getPosicaoRainhaColuna()[i];
            }

            estadoFinal = new OitoRainhas();
            estadoFinal.setPosicaoRainhaColuna(aux);
            estadoFinal.setQuantidadeAtaques();
            for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
                int quantidadeAtaquesAtual = estadoMenor.getQuantidadeAtaques();
                int xOriginal = estadoFinal.getPosicaoRainhaColuna()[i];
                for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                    estadoFinal.getPosicaoRainhaColuna()[i] = j;

                    if (quantidadeAtaquesAtual > (new OitoRainhas(estadoFinal.getPosicaoRainhaColuna())).getQuantidadeAtaques()) {
                        int[] menorPosicaoRainhaColuna = new int[8];

                        for (int k = 0; k < menorPosicaoRainhaColuna.length; k++) {
                            menorPosicaoRainhaColuna[k] = estadoFinal.getPosicaoRainhaColuna()[k];
                        }
                        estadoMenor = new OitoRainhas();
                        estadoMenor.setPosicaoRainhaColuna(menorPosicaoRainhaColuna);
                        estadoMenor.setQuantidadeAtaques();
                    }
                }
                estadoFinal.getPosicaoRainhaColuna()[i] = xOriginal;
            }
        }


        System.out.println(estadoFinal.getQuantidadeAtaques());
        estadoFinal.printPosicaoRainhas();
    }

    public void subidaEncosta2() {
        System.out.println("Começando.");
        OitoRainhas estadoMenor = new OitoRainhas(posicaoRainhaColuna);
        OitoRainhas estadoFinal = new OitoRainhas(posicaoRainhaColuna);

        ArrayList<OitoRainhas> possiveis = new ArrayList<OitoRainhas>();


        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            int xOriginal = estadoFinal.getPosicaoRainhaColuna()[i];

            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                estadoFinal.getPosicaoRainhaColuna()[i] = j;

                int[] menorPosicaoRainhaColuna = new int[8];

                for (int k = 0; k < menorPosicaoRainhaColuna.length; k++) {
                    menorPosicaoRainhaColuna[k] = estadoFinal.getPosicaoRainhaColuna()[k];
                }
                OitoRainhas aux = new OitoRainhas();
                aux.setPosicaoRainhaColuna(menorPosicaoRainhaColuna);
                aux.setQuantidadeAtaques();

                possiveis.add(aux);
            }
            estadoFinal.getPosicaoRainhaColuna()[i] = xOriginal;
        }

        int menor = estadoFinal.quantidadeAtaques;

        for (OitoRainhas possivel:
             possiveis) {
            if (possivel.getQuantidadeAtaques() < menor) menor = possivel.getQuantidadeAtaques();
        }

        ArrayList<OitoRainhas> menores = new ArrayList<OitoRainhas>();

        for (OitoRainhas possivel:
             possiveis) {
            if (possivel.getQuantidadeAtaques() == menor) menores.add(possivel);
        }

        System.out.println(estadoFinal.getQuantidadeAtaques());
        estadoFinal.printPosicaoRainhas();
    }

    public void temperaSimulado() {

    }
}
	