/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.PublicacionesFacadeLocal;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Publicaciones;

/**
 *
 * @author algut
 */

@Named
@RequestScoped

public class ListaPublicacionesController {
    private List<Publicaciones> publicaciones;
    private Publicaciones publicacion;
    
    @EJB
    private PublicacionesFacadeLocal publicacionesEJB;
    
    @PostConstruct
    public void init(){
        List<Publicaciones> allPubs = publicacionesEJB.findAll();
        setPublicaciones(allPubs);
    }
    
    public void establecerPublicacion(Publicaciones pub){
        setPublicacion(pub);
    }

    public List<Publicaciones> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicaciones> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    public PublicacionesFacadeLocal getPublicacionesEJB() {
        return publicacionesEJB;
    }

    public void setPublicacionesEJB(PublicacionesFacadeLocal publicacionesEJB) {
        this.publicacionesEJB = publicacionesEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.publicaciones);
        hash = 29 * hash + Objects.hashCode(this.publicacion);
        hash = 29 * hash + Objects.hashCode(this.publicacionesEJB);
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
        final ListaPublicacionesController other = (ListaPublicacionesController) obj;
        if (!Objects.equals(this.publicaciones, other.publicaciones)) {
            return false;
        }
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesEJB, other.publicacionesEJB)) {
            return false;
        }
        return true;
    }
    
    
}
