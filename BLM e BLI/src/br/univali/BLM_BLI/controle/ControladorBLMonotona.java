package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLMonotona;

public class ControladorBLMonotona {
    public static String relatorio;
    private BLMonotona blm;
        
    public ControladorBLMonotona() {
        blm = new BLMonotona();
        relatorio = "----------\r\nBLMonotona\r\n----------\r\n";
        //blm.novoMaquinario(3, 4);
        blm.novoMaquinario(10, 15); //  10*1.5 = 15
        blm.novoMaquinario(10, 20); //  10*2.0 = 20
        blm.novoMaquinario(20, 30); //  20*1.5 = 30
        blm.novoMaquinario(20, 40); //  20*2.0 = 40
        blm.novoMaquinario(50, 75); //  50*1.5 = 75
        blm.novoMaquinario(50, 100);//  50*2.0 = 100
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
}
