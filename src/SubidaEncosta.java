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
	
	public void resolve() {
        System.out.println("Buscando...");

        OitoRainhas solucao = new OitoRainhas(tabuleiroInicial.getPosicaoRainhaColuna());

        ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();


        while (true) {
        	for (int i = 0; i < solucao.TAMANHO_TABULEIRO; i++) {
	            int xOriginal = solucao.getPosicaoRainhaColuna()[i];
	
	            for (int j = 0; j < solucao.TAMANHO_TABULEIRO; j++) {
	                if (j != xOriginal) {
	                	solucao.getPosicaoRainhaColuna()[i] = j;
	                	
	                	int[] menorPosicaoRainhaColuna = new int[8];
	                	
	                	for (int k = 0; k < menorPosicaoRainhaColuna.length; k++) {
	                		menorPosicaoRainhaColuna[k] = solucao.getPosicaoRainhaColuna()[k];
	                	}
	                	OitoRainhas aux = new OitoRainhas();
	                	aux.setPosicaoRainhaColuna(menorPosicaoRainhaColuna);
	                	aux.setQuantidadeAtaques();
	                	
	                	vizinhos.add(aux);						
					}
	            }
	            
	            solucao.getPosicaoRainhaColuna()[i] = xOriginal;
	        }
        	
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
	        
	        if (vizinhos.get(0).getQuantidadeAtaques() >= solucao.getQuantidadeAtaques()) {
				break;
			} else {
				solucao = vizinhos.get(0);
			}
        }
        
        solucao.print();
    }
}
