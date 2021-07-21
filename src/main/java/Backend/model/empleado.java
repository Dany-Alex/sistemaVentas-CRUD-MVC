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
public class empleado {

    private String nombre, correoElectronico, direccion, dpi;
    private int codigoEmpleado, telefono;

    public empleado(String nombre, int codigoEmpleado, int telefono, String dpi, String correoElectronico, String direccion) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.codigoEmpleado = codigoEmpleado;
        this.telefono = telefono;
        this.dpi = dpi;
    }

    public empleado(String nombre, int codigoEmpleado, int telefono, String dpi) {
        this.nombre = nombre;
        this.codigoEmpleado = codigoEmpleado;
        this.telefono = telefono;
        this.dpi = dpi;
    }

    public empleado() {
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

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
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

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDpi() {
        return dpi;
    }

}
