/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Cartera_X_Edades implements Serializable {

    //Declaracion de las variables que necesita una Cartera x Edades
    private LocalDate fechaEmision = LocalDate.now();
    private int idDocumento;
    private int idCliente;
    private String nombreClientes;
    private int diasDeCredito;
    private LocalDate fechaVencimiento = LocalDate.now();
    private double valorTotalDoc;
    private double valorACobrar;
    private int diasMora;
    private double vencido30Dias;
    private double vencido60Dias;
    private double vencidoMas60Dias;
    private String numFactura;

    public Cartera_X_Edades() {
    }

    //Constructor para llenar una lista de las carteras por edades.
    public Cartera_X_Edades(LocalDate fechaEmision, int idDocumento,
            int idCliente, String nombreClientes, int diasDeCredito,
            LocalDate fechaVencimiento, double valorTotalDoc, double valorACobrar,
            int diasMora, double vencido30Dias, double vencido60Dias,
            double vencidoMas60Dias, String numFactura) {
        this.fechaEmision = fechaEmision;
        this.idDocumento = idDocumento;
        this.idCliente = idCliente;
        this.nombreClientes = nombreClientes;
        this.diasDeCredito = diasDeCredito;
        this.fechaVencimiento = fechaVencimiento;
        this.valorTotalDoc = valorTotalDoc;
        this.valorACobrar = valorACobrar;
        this.diasMora = diasMora;
        this.vencido30Dias = vencido30Dias;
        this.vencido60Dias = vencido60Dias;
        this.vencidoMas60Dias = vencidoMas60Dias;
        this.numFactura=numFactura;
    }

    //Constructor para llenar una lista con las sumas de las carteras por edades
    public Cartera_X_Edades(double valorTotalDoc, double valorACobrar, double vencido30Dias, double vencido60Dias, double vencidoMas60Dias) {
        this.valorTotalDoc = valorTotalDoc;
        this.valorACobrar = valorACobrar;
        this.vencido30Dias = vencido30Dias;
        this.vencido60Dias = vencido60Dias;
        this.vencidoMas60Dias = vencidoMas60Dias;
    }

    //Declaracion de  Getters y Setters
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getDiasDeCredito() {
        return diasDeCredito;
    }

    public void setDiasDeCredito(int diasDeCredito) {
        this.diasDeCredito = diasDeCredito;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getValorTotalDoc() {
        return valorTotalDoc;
    }

    public void setValorTotalDoc(double valorTotalDoc) {
        this.valorTotalDoc = valorTotalDoc;
    }

    public double getValorACobrar() {
        return valorACobrar;
    }

    public void setValorACobrar(double valorACobrar) {
        this.valorACobrar = valorACobrar;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public double getVencido30Dias() {
        return vencido30Dias;
    }

    public void setVencido30Dias(double vencido30Dias) {
        this.vencido30Dias = vencido30Dias;
    }

    public double getVencido60Dias() {
        return vencido60Dias;
    }

    public void setVencido60Dias(double vencido60Dias) {
        this.vencido60Dias = vencido60Dias;
    }

    public double getVencidoMas60Dias() {
        return vencidoMas60Dias;
    }

    public void setVencidoMas60Dias(double vencidoMas60Dias) {
        this.vencidoMas60Dias = vencidoMas60Dias;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreClientes() {
        return nombreClientes;
    }

    public void setNombreClientes(String nombreClientes) {
        this.nombreClientes = nombreClientes;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

}

