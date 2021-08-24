/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Cartera_X_Edades;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cartera_X_EdadesDAO implements Serializable {

    List<Cartera_X_Edades> lista_CarteraxEdades;
    Conexion conex;
    Cartera_X_Edades cartera_X_Edades;
    ResultSet result;

    public Cartera_X_EdadesDAO() {
        conex = new Conexion();
    }

    public Cartera_X_EdadesDAO(Cartera_X_Edades cartera_X_Edades) {
        conex = new Conexion();
        this.cartera_X_Edades = cartera_X_Edades;
    }

    public Cartera_X_EdadesDAO(List<Cartera_X_Edades> lista_CarteraxEdades, Conexion conex, Cartera_X_Edades cartera_X_Edades, ResultSet result) {
        this.lista_CarteraxEdades = lista_CarteraxEdades;
        this.conex = conex;
        this.cartera_X_Edades = cartera_X_Edades;
        this.result = result;
    }

    //Funcion para enlistar la cartera por edades
    public List<Cartera_X_Edades> obtenerCarteraxEdades() {
        lista_CarteraxEdades = new ArrayList<>();

        //verificamos la conexion
        if (conex.isEstado()) {
            try {
                /* Se obtiene una TABLA con las cartera vencida por edades*/
                String sentencia = "select*from obtener_cartera_vencidaxedades()";
                result = conex.ejecutarConsulta(sentencia);

                //Instanciamos al servicio
                servicios.ServicioCxC_Service service = new servicios.ServicioCxC_Service();
                servicios.ServicioCxC port = service.getServicioCxCPort();
                
                //Recorremos la TABLA retornada y la almacenamos en la lista.
                while (result.next()) {

                    //Concatenamos la sucursal, el punto de emision y el numero de la factura
                    String numFact =port.obtenerConcatenacionFactura(result.getInt("id_sucursal_r"),
                            result.getInt("puntoemision_r"), result.getInt("secuencia_r"));
                    
                    lista_CarteraxEdades.add(new Cartera_X_Edades(
                            result.getObject("fechaemision_r", LocalDate.class),
                            result.getInt("documento_r"),
                            result.getInt("idcliente_i"),
                            result.getString("nombres"),
                            result.getInt("diasdecredito_"),
                            result.getObject("fechavencimiento_r", LocalDate.class),
                            result.getDouble("valortotaldoc_r"),
                            result.getDouble("valoracobrar_r"),
                            result.getInt("diasmora_r"),
                            result.getDouble("vencido30dias_r"),
                            result.getDouble("vencido60dias_r"),
                            result.getDouble("vencidomas60dias_r"),
                    numFact));

                }

            } catch (SQLException ex) {
                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_CarteraxEdades.add(new Cartera_X_Edades(
                            null,
                            -1,
                            -1,
                            "",
                            -1,
                            null,
                            -1,
                            -1,
                            -1,
                            -1,
                            -1,
                            -1,
                ""));
                
            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_CarteraxEdades;
    }
    
    //Funcion para enlistar la cartera por edades con las sumatorias
    public List<Cartera_X_Edades> obtenerSumCarteraxEdades(int idCliente) {
        lista_CarteraxEdades = new ArrayList<>();

        //verificamos la conexion
        if (conex.isEstado()) {
            try {
                /* Se obtiene una TABLA con las cartera vencida por edades*/
                String sentencia = "select*from obtener_sum_carteraxedades("+idCliente+")";
                result = conex.ejecutarConsulta(sentencia);

                //Recorremos la TABLA retornada y la almacenamos en la lista.
                while (result.next()) {

                    lista_CarteraxEdades.add(new Cartera_X_Edades(
                            result.getDouble("totalvalordoc_r"),
                            result.getDouble("totalvalorcobrar_r"),
                            result.getDouble("totalvencido30_r"),
                            result.getDouble("totalvencido60_r"),
                            result.getDouble("totalvencidomas60_r")));

                }

            } catch (SQLException ex) {
                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_CarteraxEdades.add(new Cartera_X_Edades(
                            -1,
                            -1,
                            -1,
                            -1,
                            -1));
                
            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_CarteraxEdades;
    }
    
}
