/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Clientes_Con_Sin_Deuda;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clientes_Con_Sin_DeudaDAO implements Serializable {

    List<Clientes_Con_Sin_Deuda> lista_Clientes_Con_Sin_Deudas;
    List<Clientes_Con_Sin_Deuda> lista_Clientes_Con_Deudas;
    List<Clientes_Con_Sin_Deuda> lista_Clientes_Sin_Deudas;
    Conexion conex;
    Clientes_Con_Sin_Deuda cliente_Con_Sin_Deuda;
    ResultSet result;

    public Clientes_Con_Sin_DeudaDAO() {
        conex = new Conexion();
    }

    public Clientes_Con_Sin_DeudaDAO(Clientes_Con_Sin_Deuda cliente_Con_Sin_Deuda) {
        conex = new Conexion();
        this.cliente_Con_Sin_Deuda = cliente_Con_Sin_Deuda;
    }

    public Clientes_Con_Sin_DeudaDAO(List<Clientes_Con_Sin_Deuda> lista_Clientes_Con_Sin_Deudas, Conexion conex, Clientes_Con_Sin_Deuda cliente_Con_Sin_Deuda, ResultSet result) {
        this.lista_Clientes_Con_Sin_Deudas = lista_Clientes_Con_Sin_Deudas;
        this.conex = conex;
        this.cliente_Con_Sin_Deuda = cliente_Con_Sin_Deuda;
        this.result = result;
    }

    //Funcion para enlistar los cliente con y sin deuda
    public List<Clientes_Con_Sin_Deuda> obtenerClientesConSinDeudas() {
        lista_Clientes_Con_Sin_Deudas = new ArrayList<>();

        //verificamos la conexión
        if (conex.isEstado()) {
            try {

                /* Se obtiene una TABLA con todos los cliente que deben y no 
                deben*/
                String sentencia = "select*from obtener_clientes_con_y_sin_deudas()";
                result = conex.ejecutarConsulta(sentencia);

                while (result.next()) {

                    lista_Clientes_Con_Sin_Deudas.add(new Clientes_Con_Sin_Deuda(
                            result.getDouble("valorpendiente_r"),
                            result.getInt("idcliente_r"),
                            result.getString("identificacion_r"),
                            result.getString("razon_nombres_r"),
                            result.getString("direccion"),
                            result.getString("tlf1_r"),
                            result.getString("tlf2_r"),
                            result.getString("correo1")));

                }

            } catch (SQLException ex) {

                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_Clientes_Con_Sin_Deudas.add(new Clientes_Con_Sin_Deuda(
                        -1,
                        -1,
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));

            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_Clientes_Con_Sin_Deudas;
    }

    //Funcion para enlistar los cliente condeuda
    public List<Clientes_Con_Sin_Deuda> obtenerClientesConDeudas() {
        lista_Clientes_Con_Deudas = new ArrayList<>();

        //verificamos la conexión
        if (conex.isEstado()) {
            try {

                /* Se obtiene una TABLA con todos los cliente que deben y no 
                deben*/
                String sentencia = "select*from obtener_cliente_con_deudas()";
                result = conex.ejecutarConsulta(sentencia);

                while (result.next()) {

                    lista_Clientes_Con_Deudas.add(new Clientes_Con_Sin_Deuda(
                            result.getDouble("valorpendiente_r"),
                            result.getInt("idcliente_r"),
                            result.getString("identificacion_r"),
                            result.getString("razon_nombres_r"),
                            result.getString("direccion"),
                            result.getString("tlf1_r"),
                            result.getString("tlf2_r"),
                            result.getString("correo1")));

                }

            } catch (SQLException ex) {

                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_Clientes_Con_Deudas.add(new Clientes_Con_Sin_Deuda(
                        -1,
                        -1,
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));

            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_Clientes_Con_Deudas;
    }

    //Funcion para enlistar los cliente condeuda
    public List<Clientes_Con_Sin_Deuda> obtenerClientesSinDeudas() {
        lista_Clientes_Sin_Deudas = new ArrayList<>();

        //verificamos la conexión
        if (conex.isEstado()) {
            try {

                /* Se obtiene una TABLA con todos los cliente que deben y no 
                deben*/
                String sentencia = "select*from obtener_cliente_sin_deudas()";
                result = conex.ejecutarConsulta(sentencia);

                while (result.next()) {

                    lista_Clientes_Sin_Deudas.add(new Clientes_Con_Sin_Deuda(
                            result.getDouble("valorpendiente_r"),
                            result.getInt("idcliente_r"),
                            result.getString("identificacion_r"),
                            result.getString("razon_nombres_r"),
                            result.getString("direccion"),
                            result.getString("tlf1_r"),
                            result.getString("tlf2_r"),
                            result.getString("correo1")));

                }

            } catch (SQLException ex) {

                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_Clientes_Sin_Deudas.add(new Clientes_Con_Sin_Deuda(
                        -1,
                        -1,
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));

            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_Clientes_Sin_Deudas;
    }
}
