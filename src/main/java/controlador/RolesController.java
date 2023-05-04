/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.RolesFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Roles;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class RolesController implements Serializable{
    private List<Roles> roles;
    private Roles rol;
    private char accion;
    
    @EJB
    private RolesFacadeLocal rolesEJB;
    
    @PostConstruct
    public void init(){
        List<Roles> allRoles = rolesEJB.findAll();
        setRoles(allRoles);
    }
    
    public void establecerRolModificar(Roles rol){
        setRol(rol);
        setAccion('M');
    }
    
    public void establecerRolEliminar(Roles rol){
        setRol(rol);
        setAccion('E');
    }
    
    public void establecerRolInsertar(){
        setRol(new Roles());
        setAccion('A');
    }
    
    public void insertarRol(){
        if(rol.getDescripcion().length()==0 || !Character.isLetter(rol.getTipoUsuario())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al añadir: atributos incompletos", "Error al registrar el rol: atributos incompletos"));
            return;
        }
        
        try{
            rolesEJB.create(rol);
            setRoles(rolesEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Adicción correcta", "Rol registrado con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al añadir", "Error al registrar el rol"));
            System.out.println("Error al insertar el rol "+e.getMessage());
        }
    }
    
     public void modificarRol(){
        if(rol.getDescripcion().length()==0 || !Character.isLetter(rol.getTipoUsuario())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar: atributos incompletos", "Error al modificar el rol: atributos incompletos"));
            setRoles(rolesEJB.findAll());
            return;
        }
        
        try{
            rolesEJB.edit(rol);
            setRoles(rolesEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación correcta", "Rol modificado con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al modificar", "Error al modificar el rol"));
            System.out.println("Error al modificar el rol "+e.getMessage());
        }
    }
    
    public void eliminarRol(){
        try{
            rolesEJB.remove(rol);
            setRoles(rolesEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación correcta", "Rol eliminado con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar", "Error al eliminar el rol"));
            System.out.println("Error al eliminar el rol "+e.getMessage());
        }
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public RolesFacadeLocal getRolesEJB() {
        return rolesEJB;
    }

    public void setRolesEJB(RolesFacadeLocal rolesEJB) {
        this.rolesEJB = rolesEJB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.roles);
        hash = 59 * hash + Objects.hashCode(this.rol);
        hash = 59 * hash + Objects.hashCode(this.rolesEJB);
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
        final RolesController other = (RolesController) obj;
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.rolesEJB, other.rolesEJB)) {
            return false;
        }
        return true;
    }
}
