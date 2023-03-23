/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.PersonasFacadeLocal;
import EJB.RolesFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Personas;
import modelo.Roles;
import modelo.Usuarios;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class AltaUsuarioController implements Serializable{
    private Usuarios user;
    private Personas persona;
    private Roles rol;
    
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    
    @EJB
    private PersonasFacadeLocal personasEJB;
    
    @EJB
    private RolesFacadeLocal rolesEJB;
    
    @PostConstruct
    public void init(){
        user = new Usuarios();
        persona = new Personas();
        rol = new Roles();
        persona.setSexo('M');
    }
    
    public void altaUsuario(){
        System.out.println("hola user");
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public UsuariosFacadeLocal getUsuariosEJB() {
        return usuariosEJB;
    }

    public void setUsuariosEJB(UsuariosFacadeLocal usuariosEJB) {
        this.usuariosEJB = usuariosEJB;
    }

    public PersonasFacadeLocal getPersonasEJB() {
        return personasEJB;
    }

    public void setPersonasEJB(PersonasFacadeLocal personasEJB) {
        this.personasEJB = personasEJB;
    }

    public RolesFacadeLocal getRolesEJB() {
        return rolesEJB;
    }

    public void setRolesEJB(RolesFacadeLocal rolesEJB) {
        this.rolesEJB = rolesEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.user);
        hash = 61 * hash + Objects.hashCode(this.persona);
        hash = 61 * hash + Objects.hashCode(this.rol);
        hash = 61 * hash + Objects.hashCode(this.usuariosEJB);
        hash = 61 * hash + Objects.hashCode(this.personasEJB);
        hash = 61 * hash + Objects.hashCode(this.rolesEJB);
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
        final AltaUsuarioController other = (AltaUsuarioController) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.usuariosEJB, other.usuariosEJB)) {
            return false;
        }
        if (!Objects.equals(this.personasEJB, other.personasEJB)) {
            return false;
        }
        if (!Objects.equals(this.rolesEJB, other.rolesEJB)) {
            return false;
        }
        return true;
    } 
    
}
