import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SubidaEncosta {
	private OitoRainhas tabuleiroInicial;
	
	public SubidaEncosta() {
		tabuleiroInicial = new OitoRainhas(true);
	}
	
	public SubidaEncosta(OitoRainhas tabuleiroInicial) {
		this.tabuleiroInicial = tabuleiroInicial;
	}
	
	public OitoRainhas resolve() {
        System.out.println("Buscando...");
        
        long tempoInicial = System.currentTimeMillis();

        int quantidadeIteracoes = 0;
        OitoRainhas atual = new OitoRainhas(tabuleiroInicial.getPosicaoRainhaColuna());

        ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();


        while (true) {
        	vizinhos = getVizinhos(atual);
        	
        	vizinhos.sort(new Comparator<OitoRainhas>() {
    			@Override
    			public int compare(OitoRainhas individuo1, OitoRainhas individuo2) {
    				if (individuo1.getQuantidadeAtaques() < individuo2.getQuantidadeAtaques())
    		            return -1;
    		        if (individuo1.getQuantidadeAtaques() > individuo2.getQuantidadeAtaques())
    		            return 1;
    				return 0;
    			}
    		});
	        
        	quantidadeIteracoes++;
        	
        	if (vizinhos.get(0).getQuantidadeAtaques() >= atual.getQuantidadeAtaques()) {
				break;
			} else {
				atual = vizinhos.get(0);
			}
        }
        
        long tempoFinal = System.currentTimeMillis();
		long tempoExecucao = tempoFinal - tempoInicial;
        
        atual.print();
        System.out.println("Quantidade de iteracoes: " + quantidadeIteracoes);
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
