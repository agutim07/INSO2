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
import modelo.Usuarios;

/**
 *
 * @author algut
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    @Override
    public Usuarios verificarUsuario(Usuarios us){
        String consulta = "FROM Usuarios u WHERE u.user=:param1 and u.password=:param2";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", us.getUser());
        query.setParameter("param2", us.getPassword());
        List<Usuarios> resultado = query.getResultList();
        
        if(resultado.size()==0){
            return null;
        }
        
        return resultado.get(0);
    }
    
}
