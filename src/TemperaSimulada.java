import java.util.ArrayList;
import java.util.Collections;

public class TemperaSimulada {
	private final int TEMPERATURA_INICIAL = 2000;
	private OitoRainhas tabuleiroInicial;
	
	public TemperaSimulada() {
		this.tabuleiroInicial = new OitoRainhas(true);
	}
	
	public TemperaSimulada(OitoRainhas tabuleiroInicial) {
		this.tabuleiroInicial = tabuleiroInicial;
	}
	
	public OitoRainhas resolve() {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();

		int quantidadeIteracoes = 0;
		OitoRainhas atual = new OitoRainhas();
		atual.setPosicaoRainhaColuna(tabuleiroInicial.getPosicaoRainhaColuna());
		atual.setQuantidadeAtaques();
		
        ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();

        for (int temperatura = TEMPERATURA_INICIAL; temperatura > 0 && atual.getQuantidadeAtaques() != 0; temperatura--) {
        	vizinhos = getVizinhos(atual);
        	Collections.shuffle(vizinhos);
        	
        	OitoRainhas proximo = vizinhos.get(0);
        	
        	int delta = atual.getQuantidadeAtaques() - proximo.getQuantidadeAtaques();
        	
        	if (delta > 0) {
				atual = proximo;
			} else if (Math.random() >= Math.exp(-delta/temperatura)) {
				atual = proximo;
			}

        	quantidadeIteracoes++;
		}
		long tempoFinal = System.currentTimeMillis();
		long tempoExecucao = tempoFinal - tempoInicial;

		atual.print();
		System.out.println("Quantidade de iteracoes:" + quantidadeIteracoes);
		System.out.println("Tempo de execucao: " + tempoExecucao + "ms");

		return atual;
    }
	
	public int[] clonaVetor(int[] vetor) {
		int[] clone = new int[vetor.length];
		
		for (int i = 0; i < clone.length; i++) {
    		clone[i] = vetor[i];
    	}
		
		return clone;
	}
	
	public ArrayList<OitoRainhas> getVizinhos(OitoRainhas oitoRainhas) {
		ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();
		
		for (int i = 0; i < oitoRainhas.TAMANHO_TABULEIRO; i++) {
            int xOriginal = oitoRainhas.getPosicaoRainhaColuna()[i];

            for (int j = 0; j < oitoRainhas.TAMANHO_TABULEIRO; j++) {
                if (j != xOriginal) {
                	oitoRainhas.getPosicaoRainhaColuna()[i] = j;
                	
                	OitoRainhas aux = new OitoRainhas();
                	aux.setPosicaoRainhaColuna(clonaVetor(oitoRainhas.getPosicaoRainhaColuna()));
                	aux.setQuantidadeAtaques();
                	
                	vizinhos.add(aux);						
				}
            }
            
            oitoRainhas.getPosicaoRainhaColuna()[i] = xOriginal;
        }
		
		return vizinhos;
	}
}
