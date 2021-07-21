/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model;

import java.sql.Date;

/**
 *
 * @author Artist
 */
public class pedido {

    private int codigoPedido, cantidad;
    private double total, anticipo;
    private String tienda1, tienda2, cliente_nit, producto_codigo;
    private Date fecha;

    public pedido(int codigoPedido, String tienda1, String tienda2, Date fecha, String cliente_nit, String producto_codigo, int cantidad, double total, double anticipo) {
        this.codigoPedido = codigoPedido;
        this.cliente_nit = cliente_nit;
        this.producto_codigo = producto_codigo;
        this.cantidad = cantidad;
        this.total = total;
        this.anticipo = anticipo;
        this.tienda1 = tienda1;
        this.tienda2 = tienda2;
        this.fecha = fecha;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setCliente_nit(String cliente_nit) {
        this.cliente_nit = cliente_nit;
    }

    public void setProducto_codigo(String producto_codigo) {
        this.producto_codigo = producto_codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setAnticipo(double anticipo) {
        this.anticipo = anticipo;
    }

    public void setTienda1(String tienda1) {
        this.tienda1 = tienda1;
    }

    public void setTienda2(String tienda2) {
        this.tienda2 = tienda2;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public pedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public String getCliente_nit() {
        return cliente_nit;
    }

    public String getProducto_codigo() {
        return producto_codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public double getAnticipo() {
        return anticipo;
    }

    public String getTienda1() {
        return tienda1;
    }

    public String getTienda2() {
        return tienda2;
    }

    public Date getFecha() {
        return fecha;
    }

}
