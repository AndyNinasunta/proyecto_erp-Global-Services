/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.controllers;

import com.cuentasporcobrar.daos.Clientes_Con_Sin_DeudaDAO;
import com.cuentasporcobrar.models.Clientes_Con_Sin_Deuda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

@Named(value = "clientes_Con_Sin_DeudaController")
@ViewScoped
public class Clientes_Con_Sin_DeudaController implements Serializable {

    //Componentes para tener estilos en las exportaciones
    ExcelOptions excelOpt;
    PDFOptions pdfOpt;
    
    //Se Declaran las clases Clientes_Con_Sin_Deuda y Clientes_Con_Sin_DeudaDAO
    Clientes_Con_Sin_Deuda clientes_Con_Sin_Deuda;
    Clientes_Con_Sin_DeudaDAO clientes_Con_Sin_DeudaDAO;

    //Declaro mi lista de Clientes con y sin Deudas que van hacer cargadas en el
    //datatable
    List<Clientes_Con_Sin_Deuda> lista_Clientes_con_sin_deudas;
    List<Clientes_Con_Sin_Deuda> lista_Clientes_Con_Deudas;//clientes con deudas
    List<Clientes_Con_Sin_Deuda> lista_Clientes_Sin_Deudas;//clientes sin deudas

    //Procedimiento principal(Se ejecuta una vez se llame al controlador)
    public Clientes_Con_Sin_DeudaController() {
        
        //Para que carguen en el data table todos los cliente que tengan y no 
        //tengan deudas.
        clientes_Con_Sin_DeudaDAO = new Clientes_Con_Sin_DeudaDAO();
        lista_Clientes_con_sin_deudas = new ArrayList<>();
        lista_Clientes_con_sin_deudas = clientes_Con_Sin_DeudaDAO.obtenerClientesConSinDeudas();

        //Lista con los todos los cliente CON deudas
        lista_Clientes_Con_Deudas=new ArrayList<>();
        lista_Clientes_Con_Deudas=clientes_Con_Sin_DeudaDAO.obtenerClientesConDeudas();
        
        //Lista con los todos los cliente SIN deudas
        lista_Clientes_Sin_Deudas=new ArrayList<>();
        lista_Clientes_Sin_Deudas=clientes_Con_Sin_DeudaDAO.obtenerClientesSinDeudas();
        Design();

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
    public List<Clientes_Con_Sin_Deuda> getLista_Clientes_con_sin_deudas() {
        return lista_Clientes_con_sin_deudas;
    }

    public void setLista_Clientes_con_sin_deudas(List<Clientes_Con_Sin_Deuda> lista_Clientes_con_sin_deudas) {
        this.lista_Clientes_con_sin_deudas = lista_Clientes_con_sin_deudas;
    }

    public List<Clientes_Con_Sin_Deuda> getLista_Clientes_Con_Deudas() {
        return lista_Clientes_Con_Deudas;
    }

    public void setLista_Clientes_Con_Deudas(List<Clientes_Con_Sin_Deuda> lista_Clientes_Con_Deudas) {
        this.lista_Clientes_Con_Deudas = lista_Clientes_Con_Deudas;
    }

    public List<Clientes_Con_Sin_Deuda> getLista_Clientes_Sin_Deudas() {
        return lista_Clientes_Sin_Deudas;
    }

    public void setLista_Clientes_Sin_Deudas(List<Clientes_Con_Sin_Deuda> lista_Clientes_Sin_Deudas) {
        this.lista_Clientes_Sin_Deudas = lista_Clientes_Sin_Deudas;
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
