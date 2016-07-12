package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLMonotona;

public class ControladorBLMonotona {
    private BLMonotona blm;
    private String relatorio;
        
    public ControladorBLMonotona() {
        blm = new BLMonotona();
        relatorio = "heuristica,n,m,replicacao,tempo,iteracoes,valor,parametro\r\n";
        
        blm.novoMaquinario(10, 15, 1); //  10*1.5 = 15
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(10, 20, 1); //  10*2.0 = 20
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(20, 30, 1); //  20*1.5 = 30
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(20, 40, 1); //  20*2.0 = 40
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(50, 75, 1); //  50*1.5 = 75
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(50, 100, 1);//  50*2.0 = 100
        relatorio += blm.getRelatorio();
    }

    public String getRelatorio() {
        return relatorio;
    }
}
