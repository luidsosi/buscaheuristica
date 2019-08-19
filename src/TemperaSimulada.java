import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TemperaSimulada {
	private final int TEMPERATURA = 2000;
	private OitoRainhas tabuleiroInicial;
	
	public TemperaSimulada() {
		this.tabuleiroInicial = new OitoRainhas(true);
	}
	
	public TemperaSimulada(OitoRainhas tabuleiroInicial) {
		this.tabuleiroInicial = tabuleiroInicial;
	}
	
	public OitoRainhas resolve() {
		System.out.println("Buscando...");
		OitoRainhas atual = tabuleiroInicial;
		
        ArrayList<OitoRainhas> vizinhos = new ArrayList<OitoRainhas>();

        for (int i = TEMPERATURA; i > 0 && atual.getQuantidadeAtaques() != 0; i--) {
        	for (int j = 0; j < atual.TAMANHO_TABULEIRO; j++) {
	            int xOriginal = atual.getPosicaoRainhaColuna()[j];
	
	            for (int k = 0; k < atual.TAMANHO_TABULEIRO; k++) {
	                if (k != xOriginal) {
		            	atual.getPosicaoRainhaColuna()[j] = k;
		
		                int[] temp = new int[8];
		
		                for (int k2 = 0; k2 < temp.length; k2++) {
		                    temp[k2] = atual.getPosicaoRainhaColuna()[k2];
		                }
		                OitoRainhas aux = new OitoRainhas();
		                aux.setPosicaoRainhaColuna(temp);
		                aux.setQuantidadeAtaques();
		
		                vizinhos.add(aux);
			
					}            
	            }
	            
	            atual.getPosicaoRainhaColuna()[j] = xOriginal;
	        }
        	Collections.shuffle(vizinhos);
        	
        	OitoRainhas proximo = vizinhos.get(0);
        	
        	int delta = atual.getQuantidadeAtaques() - proximo.getQuantidadeAtaques();
        	
        	if (delta > 0) {
				atual = proximo;
			} else if (Math.random() >= Math.exp(delta/i)) {
				atual = proximo;
			}
		}
        
        atual.print();
        return atual;
    }
}
