/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Publicaciones;

/**
 *
 * @author algut
 */
@Local
public interface PublicacionesFacadeLocal {

    void create(Publicaciones publicaciones);

    void edit(Publicaciones publicaciones);

    void remove(Publicaciones publicaciones);

    Publicaciones find(Object id);

    List<Publicaciones> findAll();

    List<Publicaciones> findRange(int[] range);

    int count();
    
}
