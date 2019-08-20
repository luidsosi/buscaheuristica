import java.util.*;

public class AlgoritmoGenetico {
    private int populacaoInicial;
    private ArrayList<OitoRainhas> populacao;
    private Random random = new Random();

    public AlgoritmoGenetico(int populacaoInicial) {
        this.populacaoInicial = populacaoInicial;
        this.populacao = new ArrayList<OitoRainhas>();

        for (int i = 0; i < populacaoInicial; i++) {
            populacao.add(new OitoRainhas(true));
        }
    }

    public OitoRainhas resolve() {
        System.out.println("Buscando...");
        long tempoInicial = System.currentTimeMillis();

        int quantidadeIteracoes = 0;
        boolean solucaoEncontrada = false;
        OitoRainhas solucao = new OitoRainhas();
        int quantidadeElitismo = (int) (populacaoInicial * 0.1);
        int quantidadeMutacao = (int) (populacaoInicial * 0.1);
        int quantidadeReproducao = populacaoInicial - quantidadeElitismo - quantidadeMutacao;

        do {
            for (OitoRainhas individuo : populacao) {
                if (funcaoObjetivo(individuo)) {
                    solucao = individuo;
                    solucaoEncontrada = true;
                    break;
                }
            }
            ArrayList<OitoRainhas> novaPopulacao = new ArrayList<OitoRainhas>();
            novaPopulacao.addAll(reproducao(populacao, quantidadeReproducao));
            novaPopulacao.addAll(mutacao(populacao, quantidadeMutacao));
            novaPopulacao.addAll(elitismo(populacao, quantidadeElitismo));

            populacao = novaPopulacao;
            quantidadeIteracoes++;
        } while (!solucaoEncontrada);
        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;

        solucao.print();
        System.out.println("Geracao: " + quantidadeIteracoes);
        System.out.println("Tempo de execucao: " + tempoExecucao + "ms");

        return solucao;
    }

    private boolean funcaoObjetivo(OitoRainhas oitoRainhas) {
        return oitoRainhas.getQuantidadeAtaques() == 0;
    }

    private ArrayList<OitoRainhas> elitismo(ArrayList<OitoRainhas> populacao, int quantidade) {
        populacao.sort(new Comparator<OitoRainhas>() {
            @Override
            public int compare(OitoRainhas individuo1, OitoRainhas individuo2) {
                if (individuo1.getQuantidadeAtaques() < individuo2.getQuantidadeAtaques())
                    return -1;
                if (individuo1.getQuantidadeAtaques() > individuo2.getQuantidadeAtaques())
                    return 1;
                return 0;
            }
        });

        for (int i = populacao.size() - 1; i >= quantidade; i--) {
            populacao.remove(i);
        }

        return populacao;
    }

    private ArrayList<OitoRainhas> reproducao(ArrayList<OitoRainhas> populacao, int quantidade) {
        ArrayList<OitoRainhas> filhos = new ArrayList<OitoRainhas>();

        for (int i = 0; i < quantidade; i++) {
            ArrayList<OitoRainhas> pais = new ArrayList<OitoRainhas>();

            pais = rodeio(populacao, 2);

            int colunaCorte = random.nextInt(8);

            int[] posicaoRainhasFilho = new int[8];

            for (int j = 0; j < posicaoRainhasFilho.length; j++) {
                if (j <= colunaCorte) {
                    posicaoRainhasFilho[j] = pais.get(0).getPosicaoRainhaColuna()[j];
                } else {
                    posicaoRainhasFilho[j] = pais.get(1).getPosicaoRainhaColuna()[j];
                }
            }

            OitoRainhas filho = new OitoRainhas();
            filho.setPosicaoRainhaColuna(posicaoRainhasFilho);

            filhos.add(filho);
        }

        return filhos;
    }

    private ArrayList<OitoRainhas> mutacao(ArrayList<OitoRainhas> populacao, int quantidade) {
        ArrayList<OitoRainhas> mutantes = new ArrayList<OitoRainhas>();

        for (int i = 0; i < quantidade; i++) {
            OitoRainhas mutante = rodeio(populacao, 1).get(0);

            int colunaMutacao = random.nextInt(8);
            mutante.getPosicaoRainhaColuna()[colunaMutacao] = random.nextInt(8);

            mutantes.add(mutante);
        }

        return mutantes;
    }

    private ArrayList<OitoRainhas> rodeio(ArrayList<OitoRainhas> populacao, int quantidade) {
        ArrayList<OitoRainhas> rodeio = new ArrayList<OitoRainhas>();

        Collections.shuffle(populacao);

        rodeio.add(populacao.get(0));
        rodeio.add(populacao.get(1));
        rodeio.add(populacao.get(2));

        return elitismo(rodeio, quantidade);
    }
}
