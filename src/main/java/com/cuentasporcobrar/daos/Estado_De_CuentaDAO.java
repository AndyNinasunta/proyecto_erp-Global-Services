/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Estado_De_Cuenta;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Estado_De_CuentaDAO implements Serializable {
    
    List<Estado_De_Cuenta> lista_Estado_de_Cuenta;
    Conexion conex;
    Estado_De_Cuenta estado_de_Cuenta;
    ResultSet result;
    
    public Estado_De_CuentaDAO() {
        conex = new Conexion();
    }
    
    public Estado_De_CuentaDAO(Estado_De_Cuenta estado_de_Cuenta) {
        conex = new Conexion();
        this.estado_de_Cuenta = estado_de_Cuenta;
    }
    
    public Estado_De_CuentaDAO(List<Estado_De_Cuenta> lista_Estado_de_Cuenta, Conexion conex, Estado_De_Cuenta estado_de_Cuenta, ResultSet result) {
        this.lista_Estado_de_Cuenta = lista_Estado_de_Cuenta;
        this.conex = conex;
        this.estado_de_Cuenta = estado_de_Cuenta;
        this.result = result;
    }

    //Funcion para enlistar el estado de cuenta general de todos los cliente
    public List<Estado_De_Cuenta> obtenerTodosLosEstadosCuenta() {
        lista_Estado_de_Cuenta = new ArrayList<>();

        //verificamos la conexion
        if (conex.isEstado()) {
            try {
                /*Se obtiene una tabla con el estado de cuenta general de 
                todos los clientes en el siguiente orden: 
                idVenta/factura, fecha de credito, total facturas, total abonos,
                valor pendiente
                 */
                String sentencia = "select*from obtener_estado_cuenta_general()";
                result = conex.ejecutarConsulta(sentencia);
                
                //Instanciamos la clase AbonoDAO.        
                AbonoDAO abonoDAO= new AbonoDAO();
                
                while (result.next()) {
                    
                     //Concatenamos la sucursal, el punto de emision y el numero de la factura
                    String numFact =abonoDAO.obtenerConcatenacionFactura(result.getInt("id_sucursal_r"),
                            result.getInt("puntoemision_r"), result.getInt("secuencia_r"));
                    
                    
                    lista_Estado_de_Cuenta.add(new Estado_De_Cuenta(
                            result.getInt("idventa_r"),
                            result.getObject("fechacredito_r", LocalDate.class),
                            result.getDouble("totalfactura_r"),
                            result.getDouble("totalabonos_r"),
                            result.getDouble("valorpendiente_r"),numFact));
                }
                
            } catch (SQLException ex) {
                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_Estado_de_Cuenta.add(new Estado_De_Cuenta(
                            -1,
                            null,
                            -1,
                            -1,
                            -1,
                ""));
                
            }finally {

                conex.cerrarConexion();

            }
        }
        return lista_Estado_de_Cuenta;
    }

    //Funcion para enlistar el estado de cuenta general de un cliente
    public List<Estado_De_Cuenta> obtenerEstadosCuentaDeCliente(int idCliente) {
        lista_Estado_de_Cuenta = new ArrayList<>();

        //verificamos la conexion
        if (conex.isEstado()) {
            try {
                /*Se obtiene una tabla con el estado de cuenta del cliente 
                 en el siguiente orden: 
                idVenta/factura, fecha de credito, total facturas, total abonos,
                valor pendiente
                 */
                String sentencia = "select*from obtener_estado_cuenta_cliente("+idCliente+")";
                result = conex.ejecutarConsulta(sentencia);
                
                 //Instanciamos la clase AbonoDAO.        
                AbonoDAO abonoDAO= new AbonoDAO();
                
                while (result.next()) {
                    
                    
                     //Concatenamos la sucursal, el punto de emision y el numero de la factura
                    String numFact =abonoDAO.obtenerConcatenacionFactura(result.getInt("id_sucursal_r"),
                            result.getInt("puntoemision_r"), result.getInt("secuencia_r"));
                    
                    lista_Estado_de_Cuenta.add(new Estado_De_Cuenta(
                            result.getInt("idventa_r"),
                            result.getObject("fechacredito_r", LocalDate.class),
                            result.getDouble("totalfactura_r"),
                            result.getDouble("totalabonos_r"),
                            result.getDouble("valorpendiente_r"),
                            numFact));
                }
                
            } catch (SQLException ex) {
                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_Estado_de_Cuenta.add(new Estado_De_Cuenta(
                            -1,
                            null,
                            -1,
                            -1,
                            -1,
                            ""));
                
            }finally {

                conex.cerrarConexion();

            }
        }
        return lista_Estado_de_Cuenta;
    }
}
