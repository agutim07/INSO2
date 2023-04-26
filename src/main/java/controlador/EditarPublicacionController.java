/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriasFacadeLocal;
import EJB.PublicacionesFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Publicaciones;
import modelo.Usuarios;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class EditarPublicacionController implements Serializable{
    
    private Publicaciones publicacion;
    private String categoria;
    
    @Inject
    private ListaPublicacionesController listPubCon;
    
    @EJB
    private CategoriasFacadeLocal categoriasEJB;
    
    @EJB
    private PublicacionesFacadeLocal publicacionesEJB;
    
    @PostConstruct
    public void init(){
        setPublicacion(listPubCon.getPublicacion());
        setCategoria(listPubCon.getPublicacion().getCategoria().getNombreCategoria());
    }
    
    public String editarPublicacion(){
        publicacion.setCategoria(categoriasEJB.findByName(categoria));

        try{
            publicacionesEJB.edit(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación correcta", "Publicación modificada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al modficar", "Error al modificar la publicación"));
            System.out.println("Error al modificar la publicación "+e.getMessage());
        }
        
        return "listarpublicaciones.xhtml";
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public ListaPublicacionesController getListPubCon() {
        return listPubCon;
    }

    public void setListPubCon(ListaPublicacionesController listPubCon) {
        this.listPubCon = listPubCon;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public CategoriasFacadeLocal getCategoriasEJB() {
        return categoriasEJB;
    }

    public void setCategoriasEJB(CategoriasFacadeLocal categoriasEJB) {
        this.categoriasEJB = categoriasEJB;
    }
    
    
    
}
