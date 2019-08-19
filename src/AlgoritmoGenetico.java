import java.util.ArrayList;

public class AlgoritmoGenetico {
	private final int POPULACAO_INICIAL = 100;
	private ArrayList<OitoRainhas> populacao;
	
	public AlgoritmoGenetico() {
		this.populacao = new ArrayList<OitoRainhas>();
		
		for (int i = 0; i < POPULACAO_INICIAL; i++) {
			populacao.add(new OitoRainhas(true));
		}
	}
	
	public void resolve() {
		OitoRainhas solucao = new OitoRainhas();
		
		for (OitoRainhas individuo : populacao) {
			if(funcaoObjetivo(individuo)) {
				solucao = individuo;
				break;
			}
		}
		
		solucao.print();
	}
	
	private boolean funcaoObjetivo(OitoRainhas oitoRainhas) {
		return oitoRainhas.getQuantidadeAtaques() == 0;
	}
}
