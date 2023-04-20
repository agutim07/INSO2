/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriasFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Categorias;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class AltaCategoriaController implements Serializable{
    
    @Inject
    private Categorias cat;
    
    @EJB
    private CategoriasFacadeLocal categoriasEJB;
    
    @PostConstruct
    public void init(){
        
    }
    
    public void insertarCategoria(){
        try{
            categoriasEJB.create(cat);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+e.getMessage());
        }
    }

    public Categorias getCat() {
        return cat;
    }

    public void setCat(Categorias cat) {
        this.cat = cat;
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
        hash = 83 * hash + Objects.hashCode(this.cat);
        hash = 83 * hash + Objects.hashCode(this.categoriasEJB);
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
        final AltaCategoriaController other = (AltaCategoriaController) obj;
        if (!Objects.equals(this.cat, other.cat)) {
            return false;
        }
        if (!Objects.equals(this.categoriasEJB, other.categoriasEJB)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
