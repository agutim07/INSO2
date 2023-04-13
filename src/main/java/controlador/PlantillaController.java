/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class PlantillaController implements Serializable{
    public void verificarYMostrar() throws IOException{
        Object us = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us==null){
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/publico/permisosinsuficientes.xhtml?faces-redirect=true");
        }
    }
    
    public void redirect(int op) throws IOException{
        if(op==1){
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/privado/altausuario.xhtml?faces-redirect=true");
            return;
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/privado/principal.xhtml?faces-redirect=true");
    }
}
