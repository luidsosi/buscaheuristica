
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
		
		OitoRainhas oitoRainhas = new OitoRainhas(true);
		oitoRainhas.print();
		
		long tempoInicial = System.currentTimeMillis();
		oitoRainhas.subidaEncosta2();
		long tempoFinal = System.currentTimeMillis();
		long tempoExecucao = tempoFinal - tempoInicial;
		System.out.println("Tempo de execução: " + tempoExecucao + "ms");
	}
}
