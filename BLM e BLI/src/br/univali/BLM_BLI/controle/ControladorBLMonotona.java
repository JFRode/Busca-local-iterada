package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLMonotona;

public class ControladorBLMonotona {
    private BLMonotona blm;
    private String relatorio;
    
    public ControladorBLMonotona() {
        blm = new BLMonotona();
        relatorio = "";
        
        blm.novoMaquinario(10, 15); //  10*1.5 = 15
        blm.novoMaquinario(10, 20); //  10*2.0 = 20
        blm.novoMaquinario(20, 15); //  10*1.5 = 15
        blm.novoMaquinario(20, 20); //  10*2.0 = 20
        blm.novoMaquinario(50, 15); //  10*1.5 = 15
        blm.novoMaquinario(50, 20); //  10*2.0 = 20
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
}
