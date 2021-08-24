/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.controllers;

import com.cuentasporcobrar.daos.Facturas_PendientesDAO;
import com.cuentasporcobrar.models.Facturas_Pendientes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

@Named(value = "facturas_PendientesController")
@ViewScoped
public class Facturas_PendientesController implements Serializable {

    //Componentes para tener estilos en las exportaciones
    ExcelOptions excelOpt;
    PDFOptions pdfOpt;
    
    //Declaro mis Facturas_Pendientes y Facturas Pendientes DAO
    Facturas_Pendientes facturas_Pendientes;
    Facturas_PendientesDAO facturas_PendientesDAO;

    //Declaro mi lista de facturas pendientes
    List<Facturas_Pendientes> listaFacturas_Pendientes;

    //Declaro un arreglo con el total de la venta [0] y la cartera pendiente[1]
    double[] totalVentaCartera;

    public Facturas_PendientesController() {
        
        try{
        //Para cargar el data table con los datos de las facturas pendientes.
        facturas_PendientesDAO=new Facturas_PendientesDAO();
        listaFacturas_Pendientes=new ArrayList<>();
        listaFacturas_Pendientes=facturas_PendientesDAO.obtenerFacturasPendientes();
        
        //Para cargar el total de las ventas y la cartera pendiente.
        totalVentaCartera=facturas_PendientesDAO.obtenerTotalVentayCarteraPendiente();
        
        Design();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public void Design() {
        excelOpt = new ExcelOptions();
        excelOpt.setFacetBgColor("#2E8BE4");
        excelOpt.setFacetFontSize("12");
        excelOpt.setFacetFontColor("#FFFFFF");
        excelOpt.setFacetFontStyle("BOLD");
        excelOpt.setCellFontSize("11");
        excelOpt.setAutoSizeColumn(true);
        excelOpt.setFontName("Roboto");

        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#2E8BE4");
        pdfOpt.setFacetFontSize("14");
        pdfOpt.setFacetFontColor("#FFFFFF");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("12");
        pdfOpt.setFontName("Roboto");
    }

    //Getters y Setters de las Listas
    //Inicio
    
    public List<Facturas_Pendientes> getListaFacturas_Pendientes() {
        return listaFacturas_Pendientes;
    }

    public void setListaFacturas_Pendientes(List<Facturas_Pendientes> listaFacturas_Pendientes) {
        this.listaFacturas_Pendientes = listaFacturas_Pendientes;
    }

    public double[] getTotalVentaCartera() {
        return totalVentaCartera;
    }

    public void setTotalVentaCartera(double[] totalVentaCartera) {
        this.totalVentaCartera = totalVentaCartera;
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }
    //Fin
}
