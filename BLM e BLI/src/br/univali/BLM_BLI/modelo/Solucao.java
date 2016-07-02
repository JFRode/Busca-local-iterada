package br.univali.BLM_BLI.modelo;

import java.util.ArrayList;
import java.util.List;

public class Solucao {
    private List<List<Integer>> maquinas;
    private int makespan = 0;
    private int maquinaCritica;

    public Solucao() {
        maquinas = new ArrayList();
    }   
    
    public void calcularMakespan() {
        for (int i = 0; i < maquinas.size(); i++) {
            int temp = 0;
            for (Integer tarefa : maquinas.get(i)) {
                temp += tarefa;
            }
            if (temp > makespan){
                makespan = temp;
                maquinaCritica = i;
            }
        }
    }

    public List<List<Integer>> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<List<Integer>> maquinas) {
        this.maquinas = maquinas;
    }

    public int getMakespan() {
        calcularMakespan();
        return makespan;
    }

    public void setMakespan(int makespan) {
        this.makespan = makespan;
    }
    
}
