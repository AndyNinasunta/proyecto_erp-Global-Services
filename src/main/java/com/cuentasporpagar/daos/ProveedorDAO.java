/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporpagar.daos;

import com.global.config.Conexion;
import com.cuentasporpagar.models.Condiciones;
import com.cuentasporpagar.models.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebert
 */
public class ProveedorDAO extends Conexion {

     Connection con;
     Conexion conexion = new Conexion();
     private List<Proveedor> listaProveedor;
     private Proveedor proveedor;
     private ResultSet result;
     private Condiciones condiciones;
     private CondicionesDAO condicionesDAO;

     public Proveedor getProveedor() {
          return proveedor;
     }

     public void setProveedor(Proveedor proveedor) {
          this.proveedor = proveedor;
     }

     public ProveedorDAO() {
          conexion = new Conexion();
          listaProveedor = new ArrayList<>();
          condiciones = new Condiciones();
          proveedor = new Proveedor();
     }

     public void insertarp(Proveedor p) {
          try {
               this.conexion.Conectar();
               String cadena = "INSERT INTO public.proveedor(\n"
                       + "	codigo, razonsocial, ruc, nombre, direccion, email, webpage, contacto, telefono, estado)\n"
                       + "	VALUES ('" + p.getCodigo() + "','" + p.getRazonSocial() + "'"
                       + ",'" + p.getRuc() + "','" + p.getNombre() + "','" + p.getDireccion() + "'"
                       + ",'" + p.getEmail() + "','" + p.getWebPage() + "','" + p.getContacto() + "',"
                       + " '" + p.getTelefono() + "','" + p.isEstado() + "');";
               System.out.print(cadena);
               conexion.Ejecutar2(cadena);

          } catch (SQLException e) {

          } finally {
               conexion.cerrarConexion();
          }
     }

     public void update(Proveedor proveedor, int codigo) throws SQLException {         
          try {
               this.conexion.Conectar();
                String cadena = "UPDATE public.proveedor\n"
                       + "	SET  razonsocial= '" + proveedor.getRazonSocial() + "',"
                       + " ruc='" + proveedor.getRuc() + "', "
                       + "nombre='" + proveedor.getNombre() + "',"
                       + " direccion='" + proveedor.getDireccion() + "', "
                       + "email='" + proveedor.getEmail() + "', "
                       + "webpage='" + proveedor.getWebPage() + "', "
                       + "contacto='" + proveedor.getContacto() + "',"
                       + " telefono='" + proveedor.getTelefono() + "',"
                       + " estado='" + proveedor.isEstado() + "' "
                       + " Where idproveedor = "+codigo+"";
               conexion.ejecutar(cadena);
          } catch (SQLException e) {
               throw e;
          } finally {
               this.conexion.cerrarConexion();
          }

     }

}
