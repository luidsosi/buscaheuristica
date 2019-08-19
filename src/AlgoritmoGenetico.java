import java.util.ArrayList;

public class AlgoritmoGenetico {
	private int populacaoInicial;
	private ArrayList<OitoRainhas> populacao;
	
	
	public AlgoritmoGenetico(int populacaoInicial) {
		this.populacaoInicial = populacaoInicial;
		this.populacao = new ArrayList<OitoRainhas>();
		
		for (int i = 0; i < populacaoInicial; i++) {
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
