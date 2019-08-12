
public class Tabuleiro {
	private char [][] estado;
	private int quantidadeAtaques = 0;
	private Posicao[] posicaoRainhas;
	private final int TAMANHO_TABULEIRO = 8;
	
	public Tabuleiro() {
		// TODO Auto-generated constructor stub
	}
	
	public Tabuleiro(Posicao[] posicaoRainhas) {
		this.posicaoRainhas = posicaoRainhas;
		this.quantidadeAtaques = 0;
		setQuantidadeAtaques();
	}

	public Tabuleiro(char[][] estado) {
		this.estado = estado;
		this.posicaoRainhas = new Posicao[8];
		this.quantidadeAtaques = 0;
		setPosicaoRainhas();
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

		return  quantidadeAtaques;
	}

	public void setQuantidadeAtaques(int quantidadeAtaques) {
		this.quantidadeAtaques = quantidadeAtaques;
	}
	
	public void setQuantidadeAtaques() {
		for (int i = 0; i < posicaoRainhas.length; i++) {
			for (int j = 0; j < posicaoRainhas.length; j++) {
				if (i != j) {
//					Verifica coluna
					if(posicaoRainhas[i].getY() == posicaoRainhas[j].getY()) {
						quantidadeAtaques++;
					}
					
//					Verifica Linha
					if(posicaoRainhas[i].getX() == posicaoRainhas[j].getX()) {
						quantidadeAtaques++;
					}
					
//					Verifica diagonal
					if ((posicaoRainhas[i].getY() + posicaoRainhas[i].getX()) == (posicaoRainhas[j].getY() + posicaoRainhas[j].getX()) ||
							(posicaoRainhas[i].getX() - posicaoRainhas[i].getY() + 7) == (posicaoRainhas[j].getX() - posicaoRainhas[j].getY() + 7)) {
						quantidadeAtaques++;
					}
				}
			}
		}
		
		quantidadeAtaques /= 2;
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
		int indexPosicaoRainhas = 0;
		for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
			for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
				if(estado[j][i] == 'R') {
					posicaoRainhas[indexPosicaoRainhas] = new Posicao(j, i);
					indexPosicaoRainhas++;
				}
			}
		}
	}
	
	public void printPosicaoRainhas() {
		for (int i = 0; i < posicaoRainhas.length; i++) {
			System.out.println(i + ". X = " + posicaoRainhas[i].getX() + (", Y = " + posicaoRainhas[i].getY()));
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
		int menorQuantidadeAtaques = quantidadeAtaques;
		Tabuleiro tabuleiroMenor = new Tabuleiro();
		int menorFinal = quantidadeAtaques +1;
		Posicao[] testePosicaoRainhas = new Posicao[8];

		for (int i = 0; i < testePosicaoRainhas.length; i++) {
			testePosicaoRainhas[i] = new Posicao();
			testePosicaoRainhas[i].setX(getPosicaoRainhas()[i].getX());
			testePosicaoRainhas[i].setY(getPosicaoRainhas()[i].getY());
		}
		Tabuleiro tabuleiroFinal = new Tabuleiro();
		tabuleiroFinal.setPosicaoRainhas(testePosicaoRainhas);

		
		while (menorQuantidadeAtaques < menorFinal) {
			menorFinal = menorQuantidadeAtaques;
//			Posicao[] testePosicaoRainhas = new Posicao[8];
			
			for (int i = 0; i < testePosicaoRainhas.length; i++) {
				testePosicaoRainhas[i] = new Posicao();
				testePosicaoRainhas[i].setX(getPosicaoRainhas()[i].getX());
				testePosicaoRainhas[i].setY(getPosicaoRainhas()[i].getY());
			}
			tabuleiroFinal = new Tabuleiro();
			tabuleiroFinal.setPosicaoRainhas(testePosicaoRainhas);
			for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
				int xOriginal = tabuleiroFinal.getPosicaoRainhas()[i].getX();
				for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
					tabuleiroFinal.getPosicaoRainhas()[i].setX(j);
					
					if (menorQuantidadeAtaques > (new Tabuleiro(tabuleiroFinal.getPosicaoRainhas())).getQuantidadeAtaques()) {
						Posicao[] menorPosicaoRainhas = new Posicao[8];
						
						for (int k = 0; k < menorPosicaoRainhas.length; k++) {
							menorPosicaoRainhas[i] = new Posicao(tabuleiroFinal.getPosicaoRainhas()[k].getX(), tabuleiroFinal.getPosicaoRainhas()[k].getY());
						}
						tabuleiroMenor = new Tabuleiro();
						tabuleiroMenor.setPosicaoRainhas(tabuleiroFinal.getPosicaoRainhas());
						menorQuantidadeAtaques = tabuleiroMenor.getQuantidadeAtaques();
					}
				}
				tabuleiroFinal.getPosicaoRainhas()[i].setX(xOriginal);
			}			
		}
		
		
		System.out.println(menorFinal);
		tabuleiroFinal.printPosicaoRainhas();
	}
	
	
}
