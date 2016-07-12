package br.univali.BLM_BLI.modelo;

import br.univali.BLM_BLI.controle.ControladorBLMonotona;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BLMonotona {
    private Random rand;
    Solucao solucao;

    public BLMonotona() {
        rand = new Random();
        solucao = new Solucao();
    }
    
    public void novoMaquinario(int qtdMaquinas, int qtdTarefas) {
        solucao.getMaquinas().clear();
        int randomico;
        
        for (int i = 0; i < qtdMaquinas; i++) {
            solucao.getMaquinas().add(new ArrayList<>());                       //  Instancia maquinas
        } 
        
        for (int i = 0; i < qtdTarefas; i++) {
            randomico = rand.nextInt(100) + 1;
            solucao.getMaquinas().get(0).add(randomico);            //  Instancia as tarefas na primeira maquina
        }
        
        ControladorBLMonotona.relatorio += "Maquinas:" + qtdMaquinas + "\tTarefas:" + qtdTarefas + "\r\n";
        relatorioMaquinario();
        
        primeiraMelhora(solucao);
    }
    
    private void primeiraMelhora(Solucao solucao) {
        Solucao novaSolucao;
        int makespanAtual;
        do {
            makespanAtual = solucao.calcularMakespan();
            novaSolucao = vizinho(solucao);
            if(novaSolucao.calcularMakespan() < makespanAtual){
                solucao = novaSolucao;
                relatorioMaquinario();
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
            if(temp.calcularMakespan() < makespanAtual){                        // Verifica se melhorou o makespan
                return temp;                                                    // Se sim retorna a solução atual
            }else{
                maquina.remove(maquina.size()-1);                               // Caso não tenha melhorado da um "Undo"
            }
        }
        return solucao;                                                         // Se não teve nenhuma melhora retorna solução sem alteraração
    }
    
    public void relatorioMaquinario() {
        ControladorBLMonotona.relatorio += "\r\n--------------------------\r\n";
        
        for (List<Integer> maquina : solucao.getMaquinas()) {
            ControladorBLMonotona.relatorio += "> ";
            for (Integer tarefa : maquina) {
                ControladorBLMonotona.relatorio += tarefa + "\t";
            }
            ControladorBLMonotona.relatorio += "\r\n";
        }
        ControladorBLMonotona.relatorio += "\r\n--------------------------\r\n";
    }
}
