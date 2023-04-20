/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.MenusFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menus;
import modelo.Usuarios;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author algut
 */

@Named
@SessionScoped

public class MenuController implements Serializable{
    private MenuModel modelo;
    @EJB
    private MenusFacadeLocal menusEJB;
    
    @PostConstruct
    public void init(){
        setModelo(obtenerMenu());
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
    
    public MenuModel obtenerMenu(){
        MenuModel model = new DefaultMenuModel();
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        List<Menus> m = menusEJB.obtenerMenusUsuario(us);
        
        List<DefaultMenuItem> items = new ArrayList<>(); 
        List<Menus> items_Menu = new ArrayList<>(); 
        
        for(int i=0; i<m.size(); i++){
            if(m.get(i).getTipo()=='I'){
                DefaultMenuItem item = DefaultMenuItem.builder().value(m.get(i).getNombreMenu()).url(m.get(i).getUrl()).build();
                items.add(item);
                items_Menu.add(m.get(i));
            }
        }
        
        for(int i=0; i<m.size(); i++){
            if(m.get(i).getTipo()=='S'){
                DefaultSubMenu subMenu = DefaultSubMenu.builder().label(m.get(i).getNombreMenu()).build();
                for(int x=0; x<items.size(); x++){
                    if(items_Menu.get(x).getIdMenu_Menu()!=null && items_Menu.get(x).getIdMenu_Menu().getIdMenu()==m.get(i).getIdMenu()){
                        subMenu.getElements().add(items.get(x));
                    }
                }
                model.getElements().add(subMenu);
            }
        }
        
        for(int i=0; i<items.size(); i++){
            if(items_Menu.get(i).getIdMenu_Menu()==null){
                model.getElements().add(items.get(i));
            }
        }
        
        return model;
    }
    
    public void destruirSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/index.xhtml");
    }
    
}
