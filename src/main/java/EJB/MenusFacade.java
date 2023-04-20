/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Menus;
import modelo.Usuarios;

/**
 *
 * @author algut
 */
@Stateless
public class MenusFacade extends AbstractFacade<Menus> implements MenusFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenusFacade() {
        super(Menus.class);
    }
    
    @Override
    public List<Menus> obtenerMenusUsuario(Usuarios us){
        String consulta = "FROM Menus m WHERE m.rol.idRol=:param1";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", us.getRol().getIdRol());
        List<Menus> resultado = query.getResultList();
        
        return resultado;
    }
    
}
