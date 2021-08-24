/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.controllers;

import com.cuentasporcobrar.daos.Cartera_X_EdadesDAO;
import com.cuentasporcobrar.daos.PersonaDAO;
import com.cuentasporcobrar.models.Cartera_X_Edades;
import com.cuentasporcobrar.models.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

@Named(value = "cartera_X_EdadesController")
@ViewScoped
public class Cartera_X_EdadesController implements Serializable {

    //Componentes para tener estilos en las exportaciones
    ExcelOptions excelOpt;
    PDFOptions pdfOpt;

    //Se Declaran las clases Cartera_X_Edades y Cartera_X_EdadesDAO
    Cartera_X_Edades cartera_X_Edades;
    Cartera_X_EdadesDAO cartera_X_EdadesDAO;

    //Declaro mi lista de la cartera por edades de todos los clientes.
    List<Cartera_X_Edades> lista_Cartera_X_Edades;

    //Esta clase nos permitirá reutilizar el codigo para cargar los clientes en
    // el select one.
    Persona persona;
    PersonaDAO personaDAO;
    //Lista con todos los clientes
    List<SelectItem> listaCliente;

    //Declaro mi lista con la sumatoria 
    List<Cartera_X_Edades> listaSum_Cartera_X_Edades;

    public Cartera_X_EdadesController() {
        //Para que carguen en el data table todas las facturas las cuales 
        //hayan sido pagadas como plan de pagos de todos los clientes.
        cartera_X_EdadesDAO = new Cartera_X_EdadesDAO();
        lista_Cartera_X_Edades = new ArrayList<>();
        lista_Cartera_X_Edades = cartera_X_EdadesDAO.obtenerCarteraxEdades();

        //Carga la sumatoria de todas las ventas .
        listaSum_Cartera_X_Edades = new ArrayList<>();

        //Recibe un parámetros que será el id del cliente, en caso de ser -1 (Predeterminado)
        //Se carga la suma de todos los clientes.
        listaSum_Cartera_X_Edades = cartera_X_EdadesDAO.obtenerSumCarteraxEdades(-1);
        Design();

    }

    public List<SelectItem> getListaCliente() {
        try{
        listaCliente = new ArrayList<>();
        personaDAO = new PersonaDAO();
        List<Persona> p = personaDAO.obtenerNombresClientes();
        listaCliente.clear();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return listaCliente;
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
    public List<Cartera_X_Edades> getLista_Cartera_X_Edades() {
        return lista_Cartera_X_Edades;
    }

    public void setLista_Cartera_X_Edades(List<Cartera_X_Edades> lista_Cartera_X_Edades) {
        this.lista_Cartera_X_Edades = lista_Cartera_X_Edades;
    }

    public List<Cartera_X_Edades> getListaSum_Cartera_X_Edades() {
        return listaSum_Cartera_X_Edades;
    }

    public void setListaSum_Cartera_X_Edades(List<Cartera_X_Edades> listaSum_Cartera_X_Edades) {
        this.listaSum_Cartera_X_Edades = listaSum_Cartera_X_Edades;
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

    //FIN
}
