package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Retencion;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetencionDAO implements Serializable {

    Conexion conex;
    Retencion retencion;
    ResultSet result;
    List<Retencion> lista_Retencion;

    public RetencionDAO(Conexion conex, ResultSet result, List<Retencion> lista_Retencion) {
        this.conex = conex;
        this.result = result;
        this.lista_Retencion = lista_Retencion;
    }

    public RetencionDAO() {
        conex = new Conexion();
    }

    public RetencionDAO(Retencion retencion) {
        conex = new Conexion();
        this.retencion = retencion;
    }

    //Esta funcion retorna una lista con todas las retenciones de un cliente.
    public List<Retencion> obtenerRetenciones(int idVenta) {
        lista_Retencion = new ArrayList<>();
        if (conex.isEstado()) {
            try {
                String sentencia = "Select *from Obtener_Retenciones(" + idVenta + ") "
                        + "order by idretencion_r desc";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    lista_Retencion.add(new Retencion(result.getInt("idretencion_r"),
                            result.getInt("idventa_r"),
                            result.getInt("porcentaje_r"),
                            result.getObject("fechaemision_r", LocalDate.class),
                            result.getDouble("baseimponible_r"),
                            result.getString("descripcion_r"),
                            result.getString("ejerciciofiscal_r"),
                            result.getDouble("total_r")));
                }
            } catch (SQLException ex) {
                lista_Retencion.add(new Retencion(-1, -1, 0, null, 0.0, "", "", 0.0));
            } finally {
                conex.cerrarConexion();
            }
        }
        return lista_Retencion;
    }

    // Con esta función retornamos todos los id de las ventas/facturas de un
    // Cliente en específico.
    public List<Retencion> obtenerVentas(int idCliente) {
        lista_Retencion = new ArrayList<>();
        if (conex.isEstado()) {
            try {

                String sentencia = "select*from obtener_idfacturas_de_Cliente(" + idCliente + ")";
                result = conex.ejecutarConsulta(sentencia);

                while (result.next()) {

                    lista_Retencion.add(new Retencion(result.getInt("idventa_r"),
                            result.getInt("id_sucursal_r"),
                            result.getInt("puntoemision_r"),
                            result.getInt("secuencia_r")));

                }

            } catch (SQLException ex) {
                lista_Retencion.add(new Retencion(-1, -1, -1, -1));
                conex.cerrarConexion();
            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_Retencion;
    }

    //funcion para Insertar una retencion, retorna 1 o -1 dependiendo si la
    //funcion ejecuta correctamente.
    public int insertarRetencion(int idCliente, int idVenta) {
        try {
            String sentenciaSQL = "Select Ingresar_Retencion(" + idCliente + ","
                    + idVenta + ",'"
                    + retencion.getFechaEmision() + "',"
                    + retencion.getBaseImponible() + ",'"
                    + retencion.getDescImpuesto() + "')";

            //Verificamos la conexion
            if (conex.isEstado()) {
                //Una vez se asegura que la conexion este correcta.
                //Se ejecuta la sentencia ingresada.
                System.out.println(sentenciaSQL);
                return conex.ejecutarProcedimiento(sentenciaSQL);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conex.cerrarConexion();
        }
        //Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;

    }

    //Modificar/Actualizar una retencion, retorna 1 o -1 dependiendo si la
    //funcion ejecuta correctamente.
    public int actualizarRetencion(Retencion ret, int idcliente) {
        try {
            String sentenciaSQL = "Select actualizar_retencion(" + idcliente + ","
                    + ret.getIdRetencion() + ",'"
                    + ret.getFechaEmision() + "',"
                    + ret.getBaseImponible() + ",'"
                    + ret.getDescImpuesto() + "')";

            //Verificamos la conexion
            if (conex.isEstado()) {

                //Una vez se asegura que la conexion este correcta.
                //Se ejecuta la sentencia ingresada.
                System.out.println("update 1: " + sentenciaSQL);
                return conex.ejecutarProcedimiento(sentenciaSQL);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conex.cerrarConexion();
        }

        //Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;
    }
}
