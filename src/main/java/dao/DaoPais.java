/**
 * 
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Pais;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoPais {
	public static Pais find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Pais pais = null;
		try {
			pais = em.find(Pais.class, id);
		} catch (Exception ex) {
			// TODO: handle exception

			System.out.println("ups !!! ocurrio un error buscando al pais");
			ex.printStackTrace();
		}

		return pais;
	}

	public static void create(Pais pais) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(pais);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la pais");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Pais pais) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(pais);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la pais");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Pais pais = DaoPais.find(id);
			em.remove(pais);
			em.getTransaction().commit();
			System.out.println("Pais Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
