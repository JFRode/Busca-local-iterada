package br.univali.BLM_BLI.controle;

import br.univali.BLM_BLI.modelo.BLIterada;

public class ControladorBLIterada {
    private BLIterada bli;
    private String relatorio;

    public ControladorBLIterada() {
        bli = new BLIterada();
        relatorio = "";
        //System.out.println("\n\n-------------------\n1º Maquinario");
        bli.novoMaquinario(10, 32);     //  10^1.5 = 31,6227
        //System.out.println("\n\n-------------------\n2º Maquinario");
        bli.novoMaquinario(10, 100);    //  10^2.0 = 100
        //System.out.println("\n\n-------------------\n3º Maquinario");
        bli.novoMaquinario(20, 89);     //  20^1.5 = 89,4427
        //System.out.println("\n\n-------------------\n4º Maquinario");
        bli.novoMaquinario(20, 400);    //  20^2.0 = 400
        //System.out.println("\n\n-------------------\n5º Maquinario");
        bli.novoMaquinario(50, 354);    //  50^1.5 = 353,5533
        //System.out.println("\n\n-------------------\n6º Maquinario");
        bli.novoMaquinario(50, 2500);   //  50^2.0 = 2500
    }

    public String getRelatorio() {
        return relatorio;
    }
}
