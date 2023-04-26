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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private String rol;
    private List<String> roles;
    
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
        roles = new ArrayList<>();
        persona.setSexo('M');
        
        List<Roles> allRoles = rolesEJB.findAll();
        for(int i=0; i<allRoles.size(); i++){
            roles.add(allRoles.get(i).getDescripcion());
        }
        rol = roles.get(0);
    }
    
    public void insertarUsuario(){
        user.setRol(rolesEJB.findByDesc(rol));
        user.setPersona(persona);

        try{
            usuariosEJB.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto", "Usuario registrado correctamente"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al registrar", "Error al registrar el usuario"));
            System.out.println("Error al insertar el usuario "+e.getMessage());
        }
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.persona);
        hash = 97 * hash + Objects.hashCode(this.rol);
        hash = 97 * hash + Objects.hashCode(this.roles);
        hash = 97 * hash + Objects.hashCode(this.usuariosEJB);
        hash = 97 * hash + Objects.hashCode(this.personasEJB);
        hash = 97 * hash + Objects.hashCode(this.rolesEJB);
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
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
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
