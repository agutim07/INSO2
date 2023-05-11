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
import modelo.Categorias;
import modelo.Publicaciones;
import modelo.Usuarios;

/**
 *
 * @author algut
 */
@Stateless
public class PublicacionesFacade extends AbstractFacade<Publicaciones> implements PublicacionesFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacionesFacade() {
        super(Publicaciones.class);
    }
    
    @Override
    public List<Publicaciones> obtenerPublicacionesUsuario(Usuarios us){
        String consulta = "FROM Publicaciones p WHERE p.persona.idPersona=:param1";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", us.getPersona().getIdPersona());
        List<Publicaciones> resultado = query.getResultList();
        
        return resultado;
    }
    
    @Override
    public List<Publicaciones> obtenerPublicacionesCategoria(Categorias cat){
        String consulta = "FROM Publicaciones p WHERE p.categoria.idCategoria=:param1";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", cat.getIdCategoria());
        List<Publicaciones> resultado = query.getResultList();
        
        return resultado;
    }
    
}
