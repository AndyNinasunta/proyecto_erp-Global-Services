/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Plan_Pago implements Serializable {

    //Declaración de las Variables para los planes de pago
    private int idCliente;
    private LocalDate fechaFacturacion;
    private int diasCredito;
    private LocalDate fechaVencimiento;
    private String nombreDelCliente;
    private int idFactura;
    private double valorTotalFactura;
    private double valorPendiente;
    private double totalAbonos;
    private LocalDate fechaUltimoPago=LocalDate.now();
    private int idEstadoFactura;
    private String estadoFactura;
    private int diasMora;
    private double intereses;
    private String numFactura;
    

    public Plan_Pago() {
    }

    //Constructor para almacenar las facturas pendientes
    public Plan_Pago(LocalDate fecha_Facturacion, int dias_Credito,
            LocalDate fecha_Vencimiento, String nombre_Del_Cliente, int id_factura,
            double valor_Total_Factura, double valor_Pendiente,
            LocalDate fecha_Ultimo_Pago, String estado_Factura, int diasMora) {
        this.fechaFacturacion = fecha_Facturacion;
        this.diasCredito = dias_Credito;
        this.fechaVencimiento = fecha_Vencimiento;
        this.nombreDelCliente = nombre_Del_Cliente;
        this.idFactura = id_factura;
        this.valorTotalFactura = valor_Total_Factura;
        this.valorPendiente = valor_Pendiente;
        this.fechaUltimoPago = fecha_Ultimo_Pago;
        this.estadoFactura = estado_Factura;
        this.diasMora = diasMora;
    }

    //Constructor para cargar los cobros 
    public Plan_Pago(LocalDate fechaFacturacion, int diasCredito, 
            LocalDate fechaVencimiento, int idFactura, double valorTotalFactura,
            double valorPendiente, double totalAbonos, String estadoFactura, 
            int diasMora, String numFactura) {
        this.fechaFacturacion = fechaFacturacion;
        this.diasCredito = diasCredito;
        this.fechaVencimiento = fechaVencimiento;
        this.idFactura = idFactura;
        this.valorTotalFactura = valorTotalFactura;
        this.valorPendiente = valorPendiente;
        this.totalAbonos = totalAbonos;
        this.estadoFactura = estadoFactura;
        this.diasMora = diasMora;
        this.numFactura=numFactura;
    }
    
    public String estadoFac(){

        switch (estadoFactura) {

            case "PENDIENTE":
                return "pendiente";

            case "PAGADO":
                return "pagado";

            case "VENCIDO":
                return "vencido";

            default:
                return "DEFAULT";
        }
    }

    //Declaracion de  Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(LocalDate fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public int getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(int diasCredito) {
        this.diasCredito = diasCredito;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreDelCliente() {
        return nombreDelCliente;
    }

    public void setNombreDelCliente(String nombreDelCliente) {
        this.nombreDelCliente = nombreDelCliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getValorTotalFactura() {
        return valorTotalFactura;
    }

    public void setValorTotalFactura(double valorTotalFactura) {
        this.valorTotalFactura = valorTotalFactura;
    }

    public double getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(double valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public LocalDate getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public int getIdEstadoFactura() {
        return idEstadoFactura;
    }

    public void setIdEstadoFactura(int idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(double totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }
}
