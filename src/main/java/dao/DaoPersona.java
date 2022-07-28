/**
 * 
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Persona;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoPersona {

	public static Persona find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Persona persona = null;
		try {
			persona = em.find(Persona.class, id);
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error buscando a la persona");
			ex.printStackTrace();
		}

		return persona;
	}

	public static void create(Persona persona) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(persona);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Persona persona) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(persona);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Persona persona = DaoPersona.find(id);
			em.remove(persona);
			em.getTransaction().commit();
			System.out.println("Persona Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
