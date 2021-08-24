
package com.cuentasporcobrar.daos;

import com.cuentasporcobrar.models.Persona;
import com.cuentasporcobrar.models.Persona_Natural;
import com.global.config.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Persona_NaturalDAO extends PersonaDAO implements Serializable {

    Persona_Natural person_Natural;
    List<Persona_Natural> lista_ClientesNaturales;

    public Persona_NaturalDAO(Persona_Natural person_Natural, List<Persona_Natural> lista_ClientesNaturales, Conexion conex, Persona persona, ResultSet result) {
        super(conex, persona, result);
        this.person_Natural = person_Natural;
        this.lista_ClientesNaturales = lista_ClientesNaturales;
    }

    public Persona_NaturalDAO() {
        conex = new Conexion();
    }

    public Persona_NaturalDAO(Persona_Natural person_Natural) {
        conex = new Conexion();
        this.person_Natural = person_Natural;
    }

    //Método que retorna los clientes Naturales
    public int insertarClienteNatural() {
        try {
            String sentenciaSQL = "Select Ingresar_Cliente_Natural " //Es un Procedimiento Almacenado
                    + "(" + person_Natural.getIdTipoIdenficacion() + ",'"
                    + person_Natural.getIdentificacion() + "','"
                    + person_Natural.getNombre1() + "','"
                    + person_Natural.getNombre2() + "','"
                    + person_Natural.getApellido1() + "','"
                    + person_Natural.getApellidos2() + "','"
                    + person_Natural.getDireccion() + "','"
                    + person_Natural.getTlf1() + "','"
                    + person_Natural.getTlf2() + "','"
                    + person_Natural.getCorreo() + "','"
                    + person_Natural.getSexo() + "','"
                    + person_Natural.getGenero() + "','"
                    + person_Natural.getFechaNacimiento() + "',"
                    + person_Natural.getIdTipoCliente() + ")";

            //Verificamos la conexion
            if (conex.isEstado()) {
                //Una vez se asegura que la conexion este correcta.
                //Se ejecuta la sentencia ingresada.
                return conex.ejecutarProcedimiento(sentenciaSQL);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conex.cerrarConexion();
        }
        //@return Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;

    }

    //Método que actualiza un cliente natural
    public int actualizarClienteNatural() {
        try {
            String sentenciaSQL = "Select actualizar_persona_natural(" + person_Natural.getIdCliente() + ","
                    + person_Natural.getIdTipoIdenficacion() + ",'"
                    + person_Natural.getIdentificacion() + "','"
                    + person_Natural.getDireccion() + "','"
                    + person_Natural.getTlf1() + "','"
                    + person_Natural.getTlf2() + "','"
                    + person_Natural.getCorreo() + "','"
                    + person_Natural.getNombre1() + "','"
                    + person_Natural.getNombre2() + "','"
                    + person_Natural.getApellido1() + "','"
                    + person_Natural.getApellidos2() + "','"
                    + person_Natural.getSexo() + "','"
                    + person_Natural.getGenero() + "','"
                    + person_Natural.getFechaNacimiento() + "',"
                    + person_Natural.getIdTipoCliente() + ")";

            if (conex.isEstado()) {
                //Una vez se asegura que la conexion este correcta.
                //Se ejecuta la sentencia ingresada.
                return conex.ejecutarProcedimiento(sentenciaSQL);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conex.cerrarConexion();
        }
        //@return Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;

    }

    //Método que retorna los clientes Naturales
    public Persona_Natural obtenerClienteNatural(int idCliente) {
        Persona_Natural p_natural = new Persona_Natural();

        if (conex.isEstado()) {
            try {

                String sentencia = "select*from obtener_cliente_natural("
                        + idCliente + ")";

                result = conex.ejecutarConsulta(sentencia);

                while (result.next()) {

                    //Almacenamos en un objeto los datos personales de un 
                    //Cliente Natural.
                    p_natural = new Persona_Natural(
                            result.getString("sexo_r"),
                            result.getString("genero_r"),
                            result.getString("nombre1_r"),
                            result.getString("nombre2_r"),
                            result.getString("apellido1_r"),
                            result.getString("apellido2_r"),
                            result.getObject("fecha_nacimiento_r", LocalDate.class),
                            result.getInt("idtipoidentificacion_r"),
                            result.getString("direccion_r"),
                            result.getString("identificacion_r"),
                            result.getBoolean("estado_r"),
                            result.getString("telefono1_r"),
                            result.getString("telefono2_r"),
                            result.getString("correo1_r"),
                            result.getInt("idtipocliente_r"));

                }
            } catch (SQLException ex) {

                System.out.println("Error al Obtener los datos del cliente: "
                        + ex.getMessage());

            } finally {

                conex.cerrarConexion();

            }
        }
        return p_natural;
    }
}
