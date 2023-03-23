/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author algut
 */

@Entity
@Table(name="menus")

public class Menus implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMenu;
    
    @Column(name="nombre")
    private String nombreMenu;
    
    @Column(name="tipo")
    private char tipo;
    
    @Column(name="estado")
    private boolean estado;
    
    @JoinColumn(name="idRol")
    @ManyToOne
    private Roles rol;
    
    @JoinColumn(name="idMenu_Menu")
    @ManyToOne
    private Menus idMenu_Menu;
    
    @Column(name="url")
    private String url;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Menus getIdMenu_Menu() {
        return idMenu_Menu;
    }

    public void setIdMenu_Menu(Menus idMenu_Menu) {
        this.idMenu_Menu = idMenu_Menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idMenu;
        hash = 17 * hash + Objects.hashCode(this.nombreMenu);
        hash = 17 * hash + this.tipo;
        hash = 17 * hash + (this.estado ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.rol);
        hash = 17 * hash + Objects.hashCode(this.idMenu_Menu);
        hash = 17 * hash + Objects.hashCode(this.url);
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
        final Menus other = (Menus) obj;
        if (this.idMenu != other.idMenu) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.nombreMenu, other.nombreMenu)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.idMenu_Menu, other.idMenu_Menu)) {
            return false;
        }
        return true;
    }
    
    
}
