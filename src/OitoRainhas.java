import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class OitoRainhas {
    private char[][] estado;
    private int quantidadeAtaques = 0;
    public int[] posicaoRainhaColuna;
    public final int TAMANHO_TABULEIRO = 8;

    public OitoRainhas() {        
    	
    }

    public OitoRainhas(boolean aleatorio) {        
    	this.posicaoRainhaColuna = new int[8];
    	Random random = new Random();
      
    	for (int i = 0; i < posicaoRainhaColuna.length; i++) {
    		this.posicaoRainhaColuna[i] = random.nextInt(8);
    	}
      
    	setQuantidadeAtaques();
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
    
    public void setEstado() {
        this.estado = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        
        for (int i = 0; i < estado.length; i++) {
			for (int j = 0; j < estado.length; j++) {
				estado[i][j] = ' ';
			}
		}
        
        for (int i = 0; i < estado.length; i++) {
			estado[posicaoRainhaColuna[i]][i] = 'R';
		}
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
    	setEstado();
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
}
	