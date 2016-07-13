package br.univali.BLM_BLI.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BLIterada {

    private Random rand;

    public BLIterada() {
        rand = new Random();
    }

    public void novoMaquinario(int qtdMaquinas, int qtdTarefas) {
        double per = 0.1;                                                       // Define o parametro Per
        do {
            //System.out.println("-------------------\nalfa: " + per);
            for (int i = 0; i < 10; i++) {                                      // Laço para realizar as 10 replicações necessarias
                //System.out.println("\t" + (i + 1) + "ª Replicação");
                Solucao solucao = new Solucao();
                for (int j = 0; j < qtdMaquinas; j++) {                         // Criando as maquinas com suas lista de tarefas
                    solucao.getMaquinas().add(new ArrayList<>());
                }
                for (int j = 0; j < qtdTarefas; j++) {                          // Tarefas de valores Randomicos são inseridas na primeira maquina
                    solucao.getMaquinas().get(0).add(rand.nextInt(100) + 1);    // Gera numero aleatorio entre 0 e 100 para cada tarefa
                }
                primeiraMelhora(solucao, per);                                  // Passa a solução e o per como parametros
            }
            per += 0.1;                                                         // Incrementa o Per em 0.1
        } while (per < 0.99);                                                   // Enquanto per >= 0.1 e <= 0.9
    }

    private void primeiraMelhora(Solucao solucao, double a) {
        Solucao solucaoGlobal = new Solucao(solucao);
        Solucao novaSolucao;
        int cont = 0;
        do {
            novaSolucao = vizinho(solucao);
            if (novaSolucao.calcularMakespan() < solucaoGlobal.calcularMakespan()) { 
                solucaoGlobal = novaSolucao;                                    // Se a solução vizinha teve melhora atribui para a global
                solucao = novaSolucao;                                          // Atribui como nova solução
//                cont = 0;                                                       // Se teve alguma melhora zera o contador
            } else {
                solucao = vizinhoPerturbado(novaSolucao, a);                    // Se não teve nenhuma melhora Perturba
                cont++;                                                         // Incrementa contador de não melhora
            }
        } while (cont < 1000);                                                  // Enquanto nao tiver 1000 iterações sem melhora vai continuar
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
                return temp;                                                    // Se sim retorna a solução atual
            } else {
                maquina.remove(maquina.size() - 1);                             // Caso não tenha melhorado da um "Undo"
            }
        }
        return solucao;                                                         // Se não teve nenhuma melhora retorna solução sem alteraração
    }

    private Solucao vizinhoPerturbado(Solucao solucao, double a) {
        int percent = Math.round((float) a * 100);                              // Calculo do % a ser perturbado
        for (int i = 0; i < percent; i++) {                                     // Perturba n%
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
}
