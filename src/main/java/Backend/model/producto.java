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
public class producto {

    private String codigoProducto, nombre, fabricante, descripcion, tiendaCodigo;
    private int cantidad, garantia;
    private double precio;

    public producto(String nombre, String fabricante, String codigoProducto, int cantidad, double precio, String tiendaCodigo, String descripcion, int garantia) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.tiendaCodigo = tiendaCodigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.garantia = garantia;
        this.precio = precio;
    }

    /**
     * *
     *
     * @param nombre
     * @param fabricante
     * @param codigoProducto
     * @param cantidad
     * @param precio
     * @param tienda
     */
    public producto(String nombre, String fabricante, String codigoProducto, int cantidad, double precio, String tiendaCodigo) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.tiendaCodigo = tiendaCodigo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getTiendaCodigo() {
        return tiendaCodigo;
    }

    public void setTiendaCodigo(String tiendaCodigo) {
        this.tiendaCodigo = tiendaCodigo;
    }

    public producto() {
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getGarantia() {
        return garantia;
    }

    public double getPrecio() {
        return precio;
    }

}
