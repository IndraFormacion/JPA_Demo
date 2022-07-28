/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Ciudad;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoCiudad {
	public static List<Ciudad> findAllCiudades(int fk){
		EntityManager em=JpaUtils.getEmf().createEntityManager();
		Query cdades=em.createNativeQuery("SELECT nombre FROM indra2022.ciudades where fk_pais=" + fk);
		List<Ciudad>ciudades=cdades.getResultList();
		return ciudades;
	}
	public static Ciudad find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Ciudad ciudad = null;
		try {
			ciudad = em.find(Ciudad.class, id);
		} catch (Exception ex) {
			// TODO: handle exception

			System.out.println("ups !!! ocurrio un error buscando al ciudad");
			ex.printStackTrace();
		}

		return ciudad;
	}
	

	public static void create(Ciudad ciudad) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(ciudad);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la ciudad");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Ciudad ciudad) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(ciudad);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la ciudad");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Ciudad ciudad = DaoCiudad.find(id);
			em.remove(ciudad);
			em.getTransaction().commit();
			System.out.println("Ciudad Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
