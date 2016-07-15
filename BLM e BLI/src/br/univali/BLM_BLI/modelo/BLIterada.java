package br.univali.BLM_BLI.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BLIterada {

    private Random rand;
    private String relatorio;
    private long tempo;
    private int iteracoes;

    public BLIterada() {
        rand = new Random();
        relatorio = "";
        iteracoes = 0;
        tempo = 0;
    }

    public void novoMaquinario(int qtdMaquinas, int qtdTarefas) {
        double per = 0.1;                                                       // Define o parametro Per
        do {
            for (int i = 0; i < 10; i++) {                                      // Laço para realizar as 10 replicações necessarias
                tempo = System.nanoTime();
                relatorio += "iterada,";
                Solucao solucao = new Solucao();
                relatorio += qtdTarefas + "," + qtdMaquinas + "," + (i+1) + ",";
                for (int j = 0; j < qtdMaquinas; j++) {                         // Criando as maquinas com suas listas de tarefas
                    solucao.getMaquinas().add(new ArrayList<>());
                }
                for (int j = 0; j < qtdTarefas; j++) {                          // Tarefas de valores Randomicos são inseridas na primeira maquina
                    solucao.getMaquinas().get(0).add(rand.nextInt(100) + 1);    // Gera numero aleatorio entre 0 e 100 para cada tarefa
                }
                int totalTarefa = solucao.getMaquinas().get(0).size();
                solucao = primeiraMelhora(solucao, per, totalTarefa);          // Passa a solução e o per como parametros
                relatorio += (System.nanoTime() - tempo) + "," + iteracoes + "," 
                        + solucao.calcularMakespan() + "," + per + "\r\n";      // tempo,iteracoes,valor,parametro 
                iteracoes = 0;                                                  // Zera o contador para a prixima replicação
            }
            per += 0.1;                                                         // Incrementa o Per em 0.1
            per = Math.round(per*100);                                          // Resolver problema do arredondamento no relarorio
            per = per/100;
        } while (per < 0.99);                                                   // Enquanto per >= 0.1 e <= 0.9
    }

    private Solucao primeiraMelhora(Solucao solucao, double a, int totalTarefa) {
        Solucao solucaoGlobal = new Solucao(solucao);
        Solucao novaSolucao;
        int cont = 0;
        do {
            novaSolucao = new Solucao(vizinho(solucao));
            if (novaSolucao.calcularMakespan() < solucaoGlobal.calcularMakespan()) { 
                solucaoGlobal = new Solucao(novaSolucao);                       // Se a solução vizinha teve melhora atribui para a global
                solucao = new Solucao(novaSolucao);                             // Atribui como nova solução
                cont = 0;                                                       // Se teve alguma melhora zera o contador
            } else {
                cont++;                                                         // Incrementa contador de não melhora
                solucao = new Solucao(vizinhoPerturbado(novaSolucao, a, totalTarefa));// Se não teve nenhuma melhora Perturba
            }
        } while (cont < 1000);                                                  // Enquanto nao tiver 1000 iterações sem melhora vai continuar
        return solucaoGlobal;
    }

    private Solucao vizinho(Solucao solucao) {
        Solucao temp = new Solucao(solucao);                                    // Cria nova solução COPIANDO a antiga
        List<Integer> maquinaCritica
                = temp.getMaquinas().get(temp.getIndexMaquinaCritica());        // Pegar maquina critica
        int makespanAtual = temp.calcularMakespan();                            // Salva antigo maquespan antes de alterar a solução
        int tarefa = maquinaCritica.get(maquinaCritica.size() - 1);             // Salva tarefa a ser trocada
        maquinaCritica.remove(maquinaCritica.size() - 1);                       // Remove tarefa a ser trocada da solução

        for (List<Integer> maquina : temp.getMaquinas()) {
            maquina.add(tarefa);                                                // Muda tarefa crítica para a próxima maquina
            if (temp.calcularMakespan() < makespanAtual) {                      // Verifica se melhorou o makespan
                iteracoes++;
                return temp;                                                    // Se sim retorna a solução atual
            } else {
                maquina.remove(maquina.size() - 1);                             // Caso não tenha melhorado da um "Undo"
            }
        }
        return solucao;                                                         // Se não teve nenhuma melhora retorna solução sem alteraração
    }

    private Solucao vizinhoPerturbado(Solucao solucao, double a, int totalTarefa) {
        int percent = Math.round((float) a * 100);                              // Calculo do % a ser perturbado
        double perTotal = Math.ceil((((float)totalTarefa*(float)percent)/100));
        for (int i = 0; i < perTotal; i++) {                                    // Perturba n%
            int randMaquina;
            do {
                randMaquina = rand.nextInt(solucao.getMaquinas().size());       // Sorteia uma maquina origem
            } while (solucao.getMaquinas().get(randMaquina).size() < 1);        // Garante que maquina sorteada tenha tarefa(s)
            int randTarefa = 
                    rand.nextInt(solucao.getMaquinas().get(randMaquina).size());// Serteia uma tarefa
            int aux = solucao.getMaquinas().get(randMaquina).get(randTarefa);   // Copia tarefa
            solucao.getMaquinas().get(randMaquina).remove(randTarefa);          // Remove tarefa da maquina origem
            randMaquina = rand.nextInt(solucao.getMaquinas().size());           // Sorteia uma maquina destino
            solucao.getMaquinas().get(randMaquina).add(aux);                    // Atribui a tarefa copiada na maquina destino
        }
        return solucao;
    }
    
    public String getRelatorio() {
        return relatorio;
    }
}
