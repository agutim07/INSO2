/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.UsuariosFacadeLocal;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Usuarios;

/**
 *
 * @author algut
 */

@Named
@RequestScoped

public class ListaUsuariosController {
    private List<Usuarios> usuarios;
    private Usuarios usuario;
    
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    
    @PostConstruct
    public void init(){
        List<Usuarios> allUsers = usuariosEJB.findAll();
        setUsuarios(allUsers);
    }
    
    public void establecerUsuario(Usuarios user){
        setUsuario(user);
    }
    
    public void eliminarUsuario(Usuarios user){
        try{
            usuariosEJB.remove(user);
            setUsuarios(usuariosEJB.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación correcta", "Usuario eliminado con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar", "Error al eliminar el usuario"));
            System.out.println("Error al eliminar el usuario "+e.getMessage());
        }
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public UsuariosFacadeLocal getUsuariosEJB() {
        return usuariosEJB;
    }

    public void setUsuariosEJB(UsuariosFacadeLocal usuariosEJB) {
        this.usuariosEJB = usuariosEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.usuarios);
        hash = 83 * hash + Objects.hashCode(this.usuario);
        hash = 83 * hash + Objects.hashCode(this.usuariosEJB);
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
        final ListaUsuariosController other = (ListaUsuariosController) obj;
        if (!Objects.equals(this.usuarios, other.usuarios)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.usuariosEJB, other.usuariosEJB)) {
            return false;
        }
        return true;
    }
}
