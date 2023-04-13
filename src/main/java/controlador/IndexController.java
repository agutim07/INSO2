/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuarios;

/**
 *
 * @author algut
 */

@Named
@ViewScoped

public class IndexController implements Serializable{
    private Usuarios user;
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    
    @PostConstruct
    public void init(){
        user = new Usuarios();
    }
    
    public String verificarUsuario() {
        try{
            Usuarios test = usuariosEJB.verificarUsuario(user);
            if(test==null){
                return "publico/permisosinsuficientes.xhtml?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",test);
                return "privado/principal.xhtml?faces-redirect=true";
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "index.xhtml?faces-redirect=true";
        }
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    public UsuariosFacadeLocal getUsuariosEJB() {
        return usuariosEJB;
    }

    public void setUsuariosEJB(UsuariosFacadeLocal usuariosEJB) {
        this.usuariosEJB = usuariosEJB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.usuariosEJB);
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
        final IndexController other = (IndexController) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.usuariosEJB, other.usuariosEJB)) {
            return false;
        }
        return true;
    }
    
    
}
