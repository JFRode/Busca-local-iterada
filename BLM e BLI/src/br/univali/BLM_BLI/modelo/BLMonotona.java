package br.univali.BLM_BLI.modelo;

import br.univali.BLM_BLI.controle.ControladorBLMonotona;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    heuristica,n,m,replicacao,tempo,iteracoes,valor,parametro 
    monotona,100,10,1,3.2,1029,88123,NA 
    monotona,100,10,2,1.2,821,88123,NA 
    temperasimulada,100,10,1,100,101821,98123,0.99 
    temperasimulada,100,10,2,100,101833,99123,0.8
*/

public class BLMonotona {
    private Random rand;
    private Solucao solucao;
    private String relatorio;
    private long tempo;
    private int iteracoes;

    public BLMonotona() {
        rand = new Random();
        solucao = new Solucao();
        relatorio = "monotona,";
        iteracoes = 0;
        tempo = 0;
    }
    
    public void novoMaquinario(int qtdMaquinas, int qtdTarefas, int replicacao) {
        int randomico;
        
        tempo = System.nanoTime();
        solucao.getMaquinas().clear();
        relatorio += qtdTarefas + "," + qtdMaquinas + "," + replicacao + ",";
        
        for (int i = 0; i < qtdMaquinas; i++) {
            solucao.getMaquinas().add(new ArrayList<>());                       //  Instancia maquinas
        } 
        
        for (int i = 0; i < qtdTarefas; i++) {
            randomico = rand.nextInt(100) + 1;
            solucao.getMaquinas().get(0).add(randomico);            //  Instancia as tarefas na primeira maquina
        }
        
        primeiraMelhora(solucao);
        relatorio += (System.nanoTime() - tempo) + "," + iteracoes + "," + solucao.calcularMakespan() + "," + "NA\r\n";     //  tempo,iteracoes,valor,parametro 
    }
    
    private void primeiraMelhora(Solucao solucao) {
        Solucao novaSolucao;
        int makespanAtual;
        do {
            makespanAtual = solucao.calcularMakespan();
            novaSolucao = vizinho(solucao);
            if(novaSolucao.calcularMakespan() < makespanAtual){
                solucao = novaSolucao;
            }
        } while (novaSolucao.calcularMakespan() < makespanAtual);
    }

    private Solucao vizinho(Solucao solucao) {
        Solucao temp = new Solucao(solucao);                                    
        List<Integer> maquinaCritica = 
                temp.getMaquinas().get(temp.getIndexMaquinaCritica());          // Pegar maquina critica
        int makespanAtual = temp.calcularMakespan();                            // Salva antigo makespan antes de alterar a solução
        int tarefa = maquinaCritica.get(maquinaCritica.size()-1);               // Salva tarefa a ser trocada
        maquinaCritica.remove(maquinaCritica.size()-1);                         // Remove tarefa a ser trocada da solução
        
        for (List<Integer> maquina : temp.getMaquinas()) {
            maquina.add(tarefa);                                                // Muda tarefa crítica para a próxima maquina
            iteracoes++;
            if(temp.calcularMakespan() < makespanAtual){                        // Verifica se melhorou o makespan
                return temp;                                                    // Se sim retorna a solução atual
            }else{
                maquina.remove(maquina.size()-1);                               // Caso não tenha melhorado da um "Undo"
            }
        }
        return solucao;                                                         // Se não teve nenhuma melhora retorna solução sem alteraração
    }

    public String getRelatorio() {
        return relatorio;
    }
}
