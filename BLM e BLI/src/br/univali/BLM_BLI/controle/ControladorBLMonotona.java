package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLMonotona;

public class ControladorBLMonotona {
    private BLMonotona blm;
    private String relatorio;
        
    public ControladorBLMonotona() {
        blm = new BLMonotona();
        relatorio = "heuristica,n,m,replicacao,tempo,iteracoes,valor,parametro\r\n";
        
        blm.novoMaquinario(10, 32);     //  10^1.5 = 31,6227
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(10, 100);    //  10^2.0 = 100
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(20, 89);     //  20^1.5 = 89,4427
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(20, 400);    //  20^2.0 = 400
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(50, 354);    //  50^1.5 = 353,5533
        relatorio += blm.getRelatorio();
        
        blm = new BLMonotona();
        blm.novoMaquinario(50, 2500);   //  50^2.0 = 2500
        relatorio += blm.getRelatorio();
    }

    public String getRelatorio() {
        return relatorio;
    }
}
