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
public class cliente {

    private String nombre, correoElectronico, direccion, nit;
    private int telefono, dpi, creditoCompra;

    /**
     *
     * @param nombre
     * @param nit
     * @param telefono
     * @param creditoCompra
     * @param dpi
     * @param correoElectronico
     * @param direccion
     */
    public cliente(String nombre, String nit, int telefono, int creditoCompra, int dpi, String correoElectronico, String direccion) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
        this.dpi = dpi;
        this.creditoCompra = creditoCompra;
    }

    /**
     *
     * @param nombre
     * @param nit
     * @param telefono
     * @param creditoCompra
     */
    public cliente(String nombre, String nit, int telefono, int creditoCompra) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.nit = nit;
        this.creditoCompra = creditoCompra;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public void setCreditoCompra(int creditoCompra) {
        this.creditoCompra = creditoCompra;
    }

    public cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNit() {
        return nit;
    }

    public int getDpi() {
        return dpi;
    }

    public int getCreditoCompra() {
        return creditoCompra;
    }

}
