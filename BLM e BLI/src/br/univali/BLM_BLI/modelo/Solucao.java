package br.univali.BLM_BLI.modelo;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

    private List<List<Integer>> maquinas;
    private int makespan = 0;
    private int indexMaquinaCritica;

    public Solucao() {
        maquinas = new ArrayList();
    }

    // Copia de uma solução antiga
    public Solucao(Solucao solucao) {
        maquinas = new ArrayList(solucao.getMaquinas().size());
        for (List<Integer> maquina : solucao.getMaquinas()) {
            this.maquinas.add(new ArrayList<>(maquina));
        }
    }

    public int calcularMakespan() {
        this.makespan = 0;
        for (int i = 0; i < maquinas.size(); i++) {
            int temp = 0;
            for (Integer tarefa : maquinas.get(i)) {
                temp += tarefa;
            }
            if (temp > makespan) {
                makespan = temp;
                indexMaquinaCritica = i;
            }
        }
        return makespan;
    }

    public List<List<Integer>> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<List<Integer>> maquinas) {
        this.maquinas = maquinas;
    }

    /*public int getMakespan() {
        //calcularMakespan();       //Removido pois iria gerar processamento desnecessario cada vez que chamasse o getMakespan
        return makespan;
    }*/

    public int getIndexMaquinaCritica() {
        calcularMakespan();
        return indexMaquinaCritica;
    }

}
