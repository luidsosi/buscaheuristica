
public class Main {
	public static void main(String[] args) {
		char [][] tabuleiroTeste = {
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ','R',' ',' ',' ',' '},
				{'R',' ',' ',' ','R',' ',' ',' '},
				{' ','R',' ',' ',' ','R',' ','R'},
				{' ',' ','R',' ',' ',' ','R',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '}
		};
		
		OitoRainhas oitoRainhas = new OitoRainhas(tabuleiroTeste);
		System.out.println(oitoRainhas.getQuantidadeAtaques());
		oitoRainhas.printPosicaoRainhas();
		
		oitoRainhas.subidaEncosta2();
		
//		Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTeste);
//		System.out.println(tabuleiro.getQuantidadeAtaques());
//		tabuleiro.printPosicaoRainhas();
//		
//		tabuleiro.subidaEncosta();
	}
}
