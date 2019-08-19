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

        OitoRainhas atual = new OitoRainhas(tabuleiroInicial.getPosicaoRainhaColuna());

        ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();


        while (true) {
        	for (int i = 0; i < atual.TAMANHO_TABULEIRO; i++) {
	            int xOriginal = atual.getPosicaoRainhaColuna()[i];
	
	            for (int j = 0; j < atual.TAMANHO_TABULEIRO; j++) {
	                if (j != xOriginal) {
	                	atual.getPosicaoRainhaColuna()[i] = j;
	                	
	                	int[] temp = new int[8];
	                	
	                	for (int k = 0; k < temp.length; k++) {
	                		temp[k] = atual.getPosicaoRainhaColuna()[k];
	                	}
	                	OitoRainhas aux = new OitoRainhas();
	                	aux.setPosicaoRainhaColuna(temp);
	                	aux.setQuantidadeAtaques();
	                	
	                	vizinhos.add(aux);						
					}
	            }
	            
	            atual.getPosicaoRainhaColuna()[i] = xOriginal;
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
	        
	        if (vizinhos.get(0).getQuantidadeAtaques() >= atual.getQuantidadeAtaques()) {
				break;
			} else {
				atual = vizinhos.get(0);
			}
        }
        
        atual.print();
        return atual;
    }
}
