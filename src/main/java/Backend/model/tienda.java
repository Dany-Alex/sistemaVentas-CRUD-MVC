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
public class tienda {

    private String nombre, direccion, codigo, correoElectronico, horario;
    private int telefono1, telefono2;

    /**
     *
     * @param nombre
     * @param direccion
     * @param codigo
     * @param telefono1
     * @param telefono2
     * @param correoElectronico
     * @param horario
     */
    public tienda(String nombre, String direccion, String codigo, int telefono1, int telefono2, String correoElectronico, String horario) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigo = codigo;
        this.correoElectronico = correoElectronico;
        this.horario = horario;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
    }

    /**
     * *
     *
     * @param nombre
     * @param direccion
     * @param codigo
     * @param telefono1
     */
    public tienda(String nombre, String direccion, String codigo, int telefono1) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigo = codigo;
        this.telefono1 = telefono1;
    }

    public tienda() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setTelefono1(int telefono1) {
        this.telefono1 = telefono1;
    }

    public void setTelefono2(int telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getHorario() {
        return horario;
    }

    public int getTelefono1() {
        return telefono1;
    }

    public int getTelefono2() {
        return telefono2;
    }

}
