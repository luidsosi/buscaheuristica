
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
		oitoRainhas.print();
		
//		long tempoInicial = System.currentTimeMillis();
//		(new SubidaEncosta(oitoRainhas)).resolve();
//		long tempoFinal = System.currentTimeMillis();
//		long tempoExecucao = tempoFinal - tempoInicial;
//		System.out.println("Tempo de execução: " + tempoExecucao + "ms");
		
		long tempoInicial = System.currentTimeMillis();
		(new TemperaSimulada(oitoRainhas)).resolve();
		long tempoFinal = System.currentTimeMillis();
		long tempoExecucao = tempoFinal - tempoInicial;
		System.out.println("Tempo de execução: " + tempoExecucao + "ms");
		
//		long tempoInicial = System.currentTimeMillis();
//		(new AlgoritmoGenetico(100)).resolve();
//		long tempoFinal = System.currentTimeMillis();
//		long tempoExecucao = tempoFinal - tempoInicial;
//		System.out.println("Tempo de execução: " + tempoExecucao + "ms");
	}
}
