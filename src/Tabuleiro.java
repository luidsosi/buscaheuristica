
public class Tabuleiro {
	private char [][] estado;
	private int quantidadeAtaques;
	private Posicao[] posicaoRainhas;
	private final int TAMANHO_TABULEIRO = 8;
	
	public Tabuleiro() {
		// TODO Auto-generated constructor stub
	}

	public Tabuleiro(char[][] estado) {
		this.estado = estado;
		this.posicaoRainhas = new Posicao[8];
	}

	public char[][] getEstado() {
		return estado;
	}

	public void setEstado(char[][] estado) {
		this.estado = estado;
	}

	public int getQuantidadeAtaques() {
		return quantidadeAtaques;
	}

	public void setQuantidadeAtaques(int quantidadeAtaques) {
		this.quantidadeAtaques = quantidadeAtaques;
	}
	
	public void setQuantidadeAtaques() {
		for (int i = 0; i < posicaoRainhas.length; i++) {
			for (int j = 0; j < posicaoRainhas.length; j++) {
				
			}
		}
	}
	
	public Posicao[] getPosicaoRainhas() {
		if (posicaoRainhas[0] != null) {
			return posicaoRainhas;
		}
		
		setPosicaoRainhas();
		
		return posicaoRainhas;
	}

	public void setPosicaoRainhas(Posicao[] posicaoRainhas) {
		this.posicaoRainhas = posicaoRainhas;
	}

	private void setPosicaoRainhas() {
		for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
			for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
				if(estado[i][j] == 'R') posicaoRainhas[i] = new Posicao(i, j);
			}
		}
	}
}
