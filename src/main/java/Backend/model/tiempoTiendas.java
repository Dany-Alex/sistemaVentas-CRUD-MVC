/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model;

/**
 *
 * @author Artist
 */
public class tiempoTiendas {

    private String tiendaOrigen, TiendaDestino;
    private int tiempo;

    /**
     *
     * @param tiendaOrigen
     * @param TiendaDestino
     * @param tiempo
     */
    public tiempoTiendas(String tiendaOrigen, String TiendaDestino, int tiempo) {
        this.tiendaOrigen = tiendaOrigen;
        this.TiendaDestino = TiendaDestino;
        this.tiempo = tiempo;
    }

    public tiempoTiendas() {
    }

    public void setTiendaOrigen(String tiendaOrigen) {
        this.tiendaOrigen = tiendaOrigen;
    }

    public void setTiendaDestino(String TiendaDestino) {
        this.TiendaDestino = TiendaDestino;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiendaOrigen() {
        return tiendaOrigen;
    }

    public String getTiendaDestino() {
        return TiendaDestino;
    }

    public int getTiempo() {
        return tiempo;
    }

}
