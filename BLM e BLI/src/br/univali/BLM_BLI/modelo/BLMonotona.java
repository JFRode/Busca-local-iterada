package br.univali.BLM_BLI.modelo;

import java.util.ArrayList;
import java.util.Random;

public class BLMonotona {
    private Random rand;

    public BLMonotona() {
        rand = new Random();
    }
    
    public void novoMaquinario(int qtdMaquinas, int qtdTarefas) {
        Solucao solucao = new Solucao();
        solucao.getMaquinas().clear();
        for (int i = 0; i < qtdMaquinas; i++) {
            solucao.getMaquinas().add(new ArrayList<>());
        }
        for (int i = 0; i < qtdTarefas; i++) {
            solucao.getMaquinas().get(0).add(rand.nextInt(100) + 1);
        }
        primeiraMelhora(solucao);
    }
    
    private void primeiraMelhora(Solucao solucao) {
        Solucao novaSolucao = solucao;
        do {
            novaSolucao = visinho(solucao);
            if(novaSolucao.getMakespan() < solucao.getMakespan()){
                solucao = novaSolucao;
            }else{
                solucao.calcularMakespan();
            }
        } while (novaSolucao.getMakespan() < solucao.getMakespan());
    }

    private Solucao visinho(Solucao solucao) {
        solucao.getMaquinas().get(solucao.getIndexMaquinaCritica());            // pegar maquina critica
        
        return null;
    }
    
}
