package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLIterada;

public class ControladorBLIterada {
    private BLIterada bli;
    private String relatorio;

    public ControladorBLIterada() {
        bli = new BLIterada();
    }

    public String getRelatorio() {
        return relatorio;
    }
}
