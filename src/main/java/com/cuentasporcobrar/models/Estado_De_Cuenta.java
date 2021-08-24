/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Estado_De_Cuenta implements Serializable{
    //Declaracion de las variables que necesita un estado de cuenta.
    private int idVenta;
    private LocalDate fechacredito=LocalDate.now();
    private double totalFactura;
    private double totalAbonos;
    private double totalPendientes;
    private String numFactura;

    public Estado_De_Cuenta() {
    }

    public Estado_De_Cuenta(int idVenta, LocalDate fechacredito, 
            double totalFactura,double totalAbonos,double totalPendientes,String numFactura) {
        this.idVenta = idVenta;
        this.fechacredito = fechacredito;
        this.totalFactura = totalFactura;
        this.totalAbonos = totalAbonos;
        this.totalPendientes=totalPendientes;
        this.numFactura=numFactura;
    }

    //Declaracion de  Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFechacredito() {
        return fechacredito;
    }

    public void setFechacredito(LocalDate fechacredito) {
        this.fechacredito = fechacredito;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public double getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(double totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public double getTotalPendientes() {
        return totalPendientes;
    }

    public void setTotalPendientes(double totalPendientes) {
        this.totalPendientes = totalPendientes;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }
    
    
}
