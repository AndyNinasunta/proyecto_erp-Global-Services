/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuentasporcobrar.models;

public class Persona_Juridica extends Persona{
    
    //Declaración de las Variables
    private String razonSocial;

    public Persona_Juridica() {
    }
    
    public Persona_Juridica(String razonSocial, int idTipoIdenficacion,
                            String direccion, String identificacion,
                            boolean estado, String tlf1, String tlf2, 
                            String correo, int idTipoCliente) {
        super(idTipoIdenficacion, direccion, identificacion, estado, tlf1, tlf2, 
                correo, idTipoCliente);
        
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

}

