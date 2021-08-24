package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Abono;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbonoDAO implements Serializable {

    List<Abono> listaAbonos;
    Conexion conex;
    Abono abono;
    ResultSet result;

    //Constructor sin parámetros, para iniciar una conexion.
    public AbonoDAO() {
        conex = new Conexion();
    }

    //Constructor que recibe el objeto Abono e inicia una nueva conexion.
    public AbonoDAO(Abono abono) {
        conex = new Conexion();
        this.abono = abono;
    }

    public AbonoDAO(List<Abono> listaAbonos, Conexion conex, Abono abono, ResultSet result) {
        this.listaAbonos = listaAbonos;
        this.conex = conex;
        this.abono = abono;
        this.result = result;
    }

    //Funcion para enlistar todos los abonos de un determinado plan de pago.
    public List<Abono> obtenerAbonos(int idVenta) {
        listaAbonos = new ArrayList<>();

        //verificamos la conexion
        if (conex.isEstado()) {
            try {
                /* Se obtiene una TABLA con todos los abonos que se pagaron 
                de un plan de pago correspondiente */
                String sentencia = "Select*from obtener_abonos_de_plan_de_pago(" + idVenta + ")";
                result = conex.ejecutarConsulta(sentencia);
                
                //Instanciamos al servicio
                servicios.ServicioCxC_Service service = new servicios.ServicioCxC_Service();
                servicios.ServicioCxC port = service.getServicioCxCPort();

                //Recorremos la TABLA retornada y la almacenamos en la lista.
                while (result.next()) {

                    //Concatenamos la sucursal, el punto de emision y el numero de la factura
                     //Guardando en el String la concatenación.
                    String numFact = port.obtenerConcatenacionFactura(result.getInt("id_sucursal_r"),
                            result.getInt("puntoemision_r"), result.getInt("secuencia_r"));

                    listaAbonos.add(new Abono(result.getInt("idabono_r"),
                            result.getInt("idventa_r"),
                            result.getDouble("totalabono_r"),
                            result.getObject("fechadeabono_r", LocalDate.class),
                            result.getString("nombrepago_r"),
                            numFact));//TENGO QUE PASAR LA SURCURSAL, SECUENCIA, NUM FACT

                }

            } catch (SQLException ex) {

                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                listaAbonos.add(new Abono(-1,
                        -1,
                        -1,
                        null,
                        "", ""));
            } finally {

                conex.cerrarConexion();

            }
        }
        return listaAbonos;
    }

    /*Procedimiento para insertar un nuevo abono.
    Nota:Al momento de insertar un nuevo abonos, automaticamente el valor 
    pendiente de el plan de pago se actualiza en el procedimiento de PostGre*/
    public int insertarNuevoAbono(int idCliente, int idPlanPago) {

        try {
            /*Se ubica en el siguiente orden: 
        (ID cliente, id plan de pago, forma de pago, valor abonado, fecha)*/
            String sentenciaSQL = "select ingresar_abono(" + idCliente + "," + idPlanPago + ","
                    + abono.getIdFormaDePago() + "," + abono.getValorAbonado() + ",'"
                    + abono.getFechaAbono() + "')";

            //Verificamos la conexion
            if (conex.isEstado()) {
                /*Una vez se asegura que la conexion este correcta.
            Se ejecuta la sentencia ingresada.*/
                return conex.ejecutarProcedimiento(sentenciaSQL);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conex.cerrarConexion();
        }
        return -1;
    }

    /*Funcion para devolver el valor pendiente de cobro de un determinado plan*/
    public double obtenerValorPendiente(int idVenta) {
        double valorPendiente = 0;

        try {
            /*El valor pendiente de una venta se lo obtiene sumando todos los 
            los montos de los abonos menos el total de la factura.
             */
            String sentencia = "select*from "
                    + "obtener_valor_pendiente_de_una_venta"
                    + "(" + idVenta + ")";
            result = conex.ejecutarConsulta(sentencia);

            result.next();

            //Almacenamos el valor obtenido en la variable
            valorPendiente = result.getDouble(1);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            //Si hay algun error o el valor es nulo, se retorna -1.
            return -1;

        } finally {

            conex.cerrarConexion();

        }

        return valorPendiente;
    }

    /*Funcion para devolver la suma de todos los abonos de una vente*/
    public double obtenerSumAbonos(int idVenta) {
        double sumAbonos = 0;

        try {
            /*En este apartado obtendremos la suma de todos los abonos de una
            venta
             */
            String sentencia = "select*from "
                    + " obtener_sum_de_abonos_de_una_venta"
                    + "(" + idVenta + ")";
            result = conex.ejecutarConsulta(sentencia);

            result.next();

            //Almacenamos el valor obtenido en la variable
            sumAbonos = result.getDouble(1);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            //Si hay algun error o el valor es nulo, se retorna -1.
            return -1;

        } finally {

            conex.cerrarConexion();

        }

        return sumAbonos;
    }

    /*Funcion para devolver el id del plan de pago y poder validar*/
    public int obtenerIdPlanPago(int idVenta) {
        int idPlanDePago = 0;

        try {
            /*El valor pendiente de una venta se lo obtiene sumando todos los 
            los montos de los abonos menos el total de la factura.
             */
            String sentencia = "select*from "
                    + "obtener_id_PlanDePago"
                    + "(" + idVenta + ")";
            result = conex.ejecutarConsulta(sentencia);

            result.next();

            //Almacenamos el valor obtenido en la variable
            idPlanDePago = result.getInt(1);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            //Si hay algun error o el valor es nulo, se retorna -1.
            return -1;

        } finally {

            conex.cerrarConexion();

        }

        return idPlanDePago;
    }

    /*Funcion para obtener la fecha de credito y vencimiento de un plan de pago.
    Donde [0] es la fecha de credito y [1] la fecha de vencimiento*/
    public LocalDate[] obtenerFechaCreditoVencimiento(int idVenta) {

        LocalDate[] fechasPlanPago = {null, null}; //[0] Total Venta, [1] Cartera P.

        //Verificamos la conexion
        if (conex.isEstado()) {
            try {
                //Se obtiene una TABLA con 1 fila y 2 columnas.
                String sentencia = "Select *from "
                        + "obtener_fechas_plandepago("
                        + idVenta + ")";
                result = conex.ejecutarConsulta(sentencia);

                result.next();

                //Almacenamos en sus respectiva posicion los resultados.
                fechasPlanPago[0] = result.getObject("fechacredito_r", LocalDate.class);
                fechasPlanPago[1] = result.getObject("fechavencimiento_r", LocalDate.class);

            } catch (SQLException ex) {
                /*Si hay algun error retornamos 0,0 para cada valor
                    y su respectivo mensaje de error.*/
                System.out.println(ex.getMessage());
                return fechasPlanPago;

            } finally {

                conex.cerrarConexion();

            }
        }

        return fechasPlanPago;
    }

}
