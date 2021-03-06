package com.contabilidad.dao;

import com.contabilidad.models.BalanceGeneral;
import com.global.config.Conexion;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceGeneralDAO {

    private Conexion conexion;
    private ResultSet result;
    private Gson gson;
    private String fecha;

    public BalanceGeneralDAO() {
        conexion = new Conexion();
        gson = new Gson();
    }

    private List<BalanceGeneral> getCalculoGrupo() {
        String sql = "select getcalculogrupobg('"+fecha+"')";
        List<BalanceGeneral> listaCalculosGrupo = new ArrayList<>();
        result = conexion.consultar(sql);
        try {
            while (result.next()) {
                String cadenaJSON = result.getString("getcalculogrupobg");
                BalanceGeneral balanceGeneral = gson.fromJson(cadenaJSON, BalanceGeneral.class);
                listaCalculosGrupo.add(balanceGeneral);
            }
            return listaCalculosGrupo;
        } catch (SQLException ex) {
            System.out.println("Error getCalculoGrupo: " + ex.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    private List<BalanceGeneral> getCalculoSubGrupo() {
        String sql = "select getcalculosubgrupobg('"+fecha+"')";
        List<BalanceGeneral> listaCalculosSubGrupo = new ArrayList<>();
        result = conexion.consultar(sql);
        try {
            while (result.next()) {
                String cadenaJSON = result.getString("getcalculosubgrupobg");
                BalanceGeneral balanceGeneral = gson.fromJson(cadenaJSON, BalanceGeneral.class);
                listaCalculosSubGrupo.add(balanceGeneral);
            }
            return listaCalculosSubGrupo;
        } catch (SQLException ex) {
            System.out.println("Error getCalculoGrupo: " + ex.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    private List<BalanceGeneral> getCalculoCuenta() {
        String sql = "select getcalculocuentabg('"+fecha+"')";
        List<BalanceGeneral> listaCalculosCuenta = new ArrayList<>();
        result = conexion.consultar(sql);
        try {
            while (result.next()) {
                String cadenaJSON = result.getString("getcalculocuentabg");
                BalanceGeneral balanceGeneral = gson.fromJson(cadenaJSON, BalanceGeneral.class);
                listaCalculosCuenta.add(balanceGeneral);
            }
            return listaCalculosCuenta;
        } catch (SQLException ex) {
            System.out.println("Error getcalculocuentabg: " + ex.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    private List<BalanceGeneral> getCalculoSubCuenta() {
        String sql = "select getcalculosubcuentabg('"+fecha+"')";
        List<BalanceGeneral> listaCalculosSubCuenta = new ArrayList<>();
        result = conexion.consultar(sql);
        try {
            while (result.next()) {
                String cadenaJSON = result.getString("getcalculosubcuentabg");
                BalanceGeneral balanceGeneral = gson.fromJson(cadenaJSON, BalanceGeneral.class);
                listaCalculosSubCuenta.add(balanceGeneral);
            }
            return listaCalculosSubCuenta;
        } catch (SQLException ex) {
            System.out.println("Error getcalculosubcuentabg: " + ex.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public List<BalanceGeneral> generateBalanceGeneral(String fecha) {
        this.fecha = fecha;
        List<BalanceGeneral> balanceGeneral = new ArrayList<>();
        List<BalanceGeneral> calculoGrupo = getCalculoGrupo();
        List<BalanceGeneral> calculoSubGrupo = getCalculoSubGrupo();
        List<BalanceGeneral> calculoCuenta = getCalculoCuenta();
        List<BalanceGeneral> calculoSubCuenta = getCalculoSubCuenta();
        
        calculoGrupo.forEach(g -> {
            balanceGeneral.add(g);
            calculoSubGrupo.forEach(sg -> {
                if (sg.getParent() == g.getId()) {
                    balanceGeneral.add(sg);

                    calculoCuenta.forEach(c -> {
                        if (c.getParent() == sg.getId()) {
                            balanceGeneral.add(c);

                            calculoSubCuenta.forEach(sc -> {
                                if (sc.getParent() == c.getId()) {
                                    balanceGeneral.add(sc);
                                }
                            });
                        }
                    });
                }
            });
        });
        return balanceGeneral;
    }
    
    public double sumaPasivoPatrimonio(String fecha) {
        String sql = "select sumapasivopatrimonio('"+fecha+"')";
        result = conexion.consultar(sql);
        try {
            if (result.next()) {
                return result.getDouble("sumapasivopatrimonio");
            }
        } catch (SQLException ex) {
            System.out.println("Error sumapasivopatrimonio: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
        return 0;
    }
}
