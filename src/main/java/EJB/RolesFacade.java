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
import modelo.Roles;

/**
 *
 * @author algut
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> implements RolesFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    
    @Override
    public Roles findByDesc(String desc){
        List<Roles> allRoles = findAll();
        for(int i=0; i<allRoles.size(); i++){
            if(allRoles.get(i).getDescripcion().equals(desc)){
                return allRoles.get(i);
            }
        }
        
        return null;
    }
    
}
