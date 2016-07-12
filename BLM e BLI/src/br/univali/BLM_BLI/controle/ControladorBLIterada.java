package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLIterada;

public class ControladorBLIterada {
    private BLIterada bli;
    private String relatorio;

    public ControladorBLIterada() {
        bli = new BLIterada();
        relatorio = "";
        bli.novoMaquinario(10, 32);     //  10^1.5 = 31,6227
        bli.novoMaquinario(10, 100);    //  10^2.0 = 100
        bli.novoMaquinario(20, 89);     //  20^1.5 = 89,4427
        bli.novoMaquinario(20, 400);    //  20^2.0 = 400
        bli.novoMaquinario(50, 354);    //  50^1.5 = 353,5533
        bli.novoMaquinario(50, 2500);   //  50^2.0 = 2500
    }
    
    public String getRelatorio() {
        return relatorio;
    }
    
    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
}
