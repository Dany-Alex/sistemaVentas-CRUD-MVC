/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.dataAccess;

import Backend.model.consultas.*;
import Backend.model.cliente;
import Backend.model.empleado;
import Backend.model.pedido;
import Backend.model.producto;
import Backend.model.tiempoTiendas;
import Backend.model.tienda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Artist
 */
public class managerFilesTxt {

    // private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto", "txt");
    public File archivo = null;
    private boolean abierto = false;//bandera de control para saber si se abrio un archivo
    private ArrayList contenido = new ArrayList();//almacena los registros leidos de *.txt
    private int index = 0; //lleva control del registro actualmente visible

    public managerFilesTxt(JFileChooser fileChooser) {

        comprabacionYCreacionDatosExistentes(fileChooser);
    }

    public void Abrir(JFileChooser fileChooser) {
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.archivo = fileChooser.getSelectedFile();
            leer(this.archivo);
            this.abierto = true;
        }
    }

    /* Lee linea por linea un archivo de texto y almacena los registros
     * en un ArrayList segun orden de lectura
     * input: File
     */
    public boolean leer(File fichero) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fichero));
            this.contenido.clear();
            String linea;
            while ((linea = reader.readLine()) != null) {
                this.contenido.add(linea);
            }
            //muestra el primer registro en la interfaz
            //Siguiente();
            reader.close();
            return true;
        } catch (IOException ex) {
            System.out.println("managerFilesTxt dice: Error: " + ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                System.out.println("managerFilesTxt dice: Error: " + ex);
            }
        }
        return false;
    }

    public void imprimirArraylist() {
        for (int i = 0; i < contenido.size(); i++) {
            System.out.println(contenido.get(i) + "\n");
        }

    }
    consultaTabla consultaTabla;
    consultaCliente consultaCliente1;
    consultaTienda consultaTienda;
    consultaTiempo consultaTiempo;
    consultaProducto consultaProducto;
    consultaEmpleado consultaEmpleado;
    consultaPedido consultaPedido;

    /* funcion qye avanza al siguiente registro del ArrayList y lo muestra en pantalla
     */
    public void comprabacionYCreacionDatosExistentes(JFileChooser fileChooser) {
        try {

            consultaTabla = new consultaTabla();
            if (consultaTabla.verificarTablasVacias()
                    && (JOptionPane.showConfirmDialog(null, "La base de datos se encuentra vacia, desea cargar un archivo con datos?", "Intelaf - Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0) {

                Abrir(fileChooser);
                consultaCliente1 = new consultaCliente();
                consultaTienda = new consultaTienda();
                consultaTiempo = new consultaTiempo();
                consultaProducto = new consultaProducto();
                consultaEmpleado = new consultaEmpleado();
                consultaPedido = new consultaPedido();

                if (this.archivo != null) {
                    //incrementa en 1 la variable "index", si se supera el tamaño de lineas, vuelve a valor 1

                    Iterator It = contenido.iterator();
                    //comienza busqueda
                    while (It.hasNext()) {
                        String tmp = It.next().toString();
                        //separa el registro por campos. Separador = ","
                        String[] datos = tmp.split(",");

                        switch (datos[0]) {

                            case "TIENDA":
                                    try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                                System.out.println(consultaTienda.crear(new tienda(datos[1], datos[2], datos[3], Integer.parseInt(datos[4]))));

                            } catch (Exception e) {
                                System.out.println("case tienda dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            case "TIEMPO":
                                  try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3]);
                                System.out.println(consultaTiempo.crear(new tiempoTiendas(datos[1], datos[2], Integer.parseInt(datos[3]))));

                            } catch (Exception e) {
                                System.out.println("case tiempo dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            case "PRODUCTO":
                                  try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                                System.out.println(consultaProducto.crear(new producto(datos[1], datos[2], datos[3], Integer.parseInt(datos[4]), Double.parseDouble(datos[5]), datos[6])));

                            } catch (Exception e) {
                                System.out.println("case producto dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            case "EMPLEADO":
                                    try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                                System.out.println(consultaEmpleado.crear(new empleado(datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), datos[4], "", "")));

                            } catch (Exception e) {
                                System.out.println("case empleado dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            case "CLIENTE":
                                try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                                System.out.println(consultaCliente1.crear(new cliente(datos[1], datos[2], Integer.parseInt(datos[3]), Integer.parseInt(datos[4]))));

                            } catch (Exception e) {
                                System.out.println("case cliente dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            case "PEDIDO":
                                     try {
                                System.out.println("- Dato capturado: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                                System.out.println(consultaPedido.crear(new pedido(Integer.parseInt(datos[1]), datos[2], datos[3], Date.valueOf(datos[4]), datos[5], datos[6], Integer.parseInt(datos[7]), Double.parseDouble(datos[8]), Double.parseDouble(datos[9]))));

                            } catch (Exception e) {
                                System.out.println("case pedido dice: Ha ocurrido un error: " + e.getMessage());
                                System.out.println("- NO se logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);

                            } finally {
                                break;
                            }
                            default:
                                System.out.println("- NO logro capturar: " + datos[0] + " - " + datos[1] + " - " + datos[2]);

                        }

                    }
                }
            } else {
            }

        } catch (Exception e) {
            // System.err.print("managerFilesTxt dice: Ha ocurrido un error: " + e.getMessage());
            System.out.println("managerFilesTxt dice: Ha ocurrido un error: " + e.getMessage());
        }
    }

}
