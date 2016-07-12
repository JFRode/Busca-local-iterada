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
        double a = 0.1;
        do {
            System.out.println("alfa: " + a);
            for (int i = 0; i < 10; i++) {                                          // Laço para realizar as 10 replicações necessarias
                Solucao solucao = new Solucao();
                for (int j = 0; j < qtdMaquinas; j++) {                             // Criando as maquinas com suas lista de tarefas
                    solucao.getMaquinas().add(new ArrayList<>());
                }
                /*/PARA TESTES, USAREMOS VALORES FIXOS
            for (int j = 0; j < qtdTarefas; j++) {                              // Tarefas de valores Randomicos são inseridas na primeira maquina
                solucao.getMaquinas().get(0).add(rand.nextInt(100) + 1);        // Gera numero aleatorio entre 0 e 100 para cada tarefa
            }*/

                // VALORES RETIRADOS DA APRESENTAÇÃO DO ALEX.
                solucao.getMaquinas().get(0).add(6);
                solucao.getMaquinas().get(0).add(1);
                solucao.getMaquinas().get(0).add(4);
                solucao.getMaquinas().get(0).add(5);
                primeiraMelhora(solucao, a);
            }
            a += 0.1;
        } while (a < 0.99);
    }

    private void primeiraMelhora(Solucao solucao, double a) {
        Solucao solucaoGlobal = new Solucao(solucao);  
        Solucao novaSolucao;
        int makespanAtual, cont = 0;
        do {
            makespanAtual = solucaoGlobal.calcularMakespan();                   // Salva makespanAtual para não precisar calcular duas vezes a cada iteração
            novaSolucao = vizinho(solucao);
            if (novaSolucao.calcularMakespan() < makespanAtual) {               // Se a solução vizinha teve melhora
                solucaoGlobal = novaSolucao;
                solucao = novaSolucao;                                          // Atribui como nova solução
                cont = 0;
            } else {
                solucao = vizinhoPerturbado(novaSolucao, a);
                cont++;
            }
        } while (cont <= 4);                                                    // Enquanto tiver melhora vai continuar
        System.out.println("");
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

    private Solucao vizinhoPerturbado(Solucao novaSolucao, double a) {
        // Implementar aqui a perturbação
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
