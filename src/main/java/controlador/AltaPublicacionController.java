/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriasFacadeLocal;
import EJB.PublicacionesFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;
import modelo.Publicaciones;
import modelo.Usuarios;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class AltaPublicacionController implements Serializable{
    private Publicaciones publicacion;
    private String categoria;
    private List<String> categorias;
    
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    
    @EJB
    private PublicacionesFacadeLocal publicacionesEJB;
    
    @EJB
    private CategoriasFacadeLocal categoriasEJB;
    
    @PostConstruct
    public void init(){
        publicacion = new Publicaciones();
        categorias = new ArrayList<>();
        
        List<Categorias> allCategorias = categoriasEJB.findAll();
        for(int i=0; i<allCategorias.size(); i++){
            categorias.add(allCategorias.get(i).getNombreCategoria());
        }
        categoria = categorias.get(0);
    }
    
    public void insertarPublicacion(){
        publicacion.setCategoria(categoriasEJB.findByName(categoria));
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        publicacion.setPersona(us.getPersona());
        publicacion.setFecha(new Date());

        try{
            publicacionesEJB.create(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto", "Publicación registrada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al registrar", "Error al registrar la publicación"));
            System.out.println("Error al insertar la publicación "+e.getMessage());
        }
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public UsuariosFacadeLocal getUsuariosEJB() {
        return usuariosEJB;
    }

    public void setUsuariosEJB(UsuariosFacadeLocal usuariosEJB) {
        this.usuariosEJB = usuariosEJB;
    }

    public PublicacionesFacadeLocal getPublicacionesEJB() {
        return publicacionesEJB;
    }

    public void setPublicacionesEJB(PublicacionesFacadeLocal publicacionesEJB) {
        this.publicacionesEJB = publicacionesEJB;
    }

    public CategoriasFacadeLocal getCategoriasEJB() {
        return categoriasEJB;
    }

    public void setCategoriasEJB(CategoriasFacadeLocal categoriasEJB) {
        this.categoriasEJB = categoriasEJB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.publicacion);
        hash = 13 * hash + Objects.hashCode(this.categoria);
        hash = 13 * hash + Objects.hashCode(this.categorias);
        hash = 13 * hash + Objects.hashCode(this.usuariosEJB);
        hash = 13 * hash + Objects.hashCode(this.publicacionesEJB);
        hash = 13 * hash + Objects.hashCode(this.categoriasEJB);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AltaPublicacionController other = (AltaPublicacionController) obj;
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
            return false;
        }
        if (!Objects.equals(this.usuariosEJB, other.usuariosEJB)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesEJB, other.publicacionesEJB)) {
            return false;
        }
        if (!Objects.equals(this.categoriasEJB, other.categoriasEJB)) {
            return false;
        }
        return true;
    }
    
    
    
}
