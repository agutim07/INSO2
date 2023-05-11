/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriasFacadeLocal;
import EJB.PublicacionesFacadeLocal;
import java.io.Serializable;
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

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class CategoriasController implements Serializable{
    private List<Categorias> categorias;
    private Categorias categoria;
    private char accion;
    
    @EJB
    private CategoriasFacadeLocal categoriasEJB;
    
    @EJB
    private PublicacionesFacadeLocal publicacionesEJB;
    
    @PostConstruct
    public void init(){
        List<Categorias> allCategorias = categoriasEJB.findAll();
        setCategorias(allCategorias);
    }
    
    public void establecerCategoriaModificar(Categorias categoria){
        setCategoria(categoria);
        setAccion('M');
    }
    
    public void establecerCategoriaEliminar(Categorias categoria){
        setCategoria(categoria);
        setAccion('E');
    }
    
    public void establecerCategoriaInsertar(){
        setCategoria(new Categorias());
        setAccion('A');
    }
    
    public void insertarCategoria(){
        if(categoria.getNombreCategoria().length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al añadir: atributos incompletos", "Error al registrar la categoria: atributos incompletos"));
            return;
        }
        
        try{
            categoriasEJB.create(categoria);
            setCategorias(categoriasEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Adicción correcta", "Categoria registrada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al añadir", "Error al registrar la categoria"));
            System.out.println("Error al insertar la categoria "+e.getMessage());
        }
    }
    
     public void modificarCategoria(){
        if(categoria.getNombreCategoria().length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar: atributos incompletos", "Error al modificar la categoria: atributos incompletos"));
            setCategorias(categoriasEJB.findAll());
            return;
        }
        
        try{
            categoriasEJB.edit(categoria);
            setCategorias(categoriasEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación correcta", "Categoría modificada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al modificar", "Error al modificar la categoria"));
            System.out.println("Error al modificar la categoria "+e.getMessage());
        }
    }
    
    public void eliminarCategoria(){
        try{
            List<Publicaciones> pubs = publicacionesEJB.obtenerPublicacionesCategoria(categoria);
            for(int i=0; i<pubs.size(); i++){
                publicacionesEJB.remove(pubs.get(i));
            }
            categoriasEJB.remove(categoria);
            setCategorias(categoriasEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación correcta", "Categoria eliminada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar", "Error al eliminar la categoria"));
            System.out.println("Error al eliminar la categoria "+e.getMessage());
        }
    }

    public List<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categorias> categorias) {
        this.categorias = categorias;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
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
        hash = 83 * hash + Objects.hashCode(this.categorias);
        hash = 83 * hash + Objects.hashCode(this.categoria);
        hash = 83 * hash + this.accion;
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
        final CategoriasController other = (CategoriasController) obj;
        if (this.accion != other.accion) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.categoriasEJB, other.categoriasEJB)) {
            return false;
        }
        return true;
    }
    
    
}
