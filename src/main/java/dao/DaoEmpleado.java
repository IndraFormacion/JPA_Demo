/**
 * 
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Empleado;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoEmpleado {
	public static Empleado find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Empleado empleado = null;
		try {
			empleado = em.find(Empleado.class, id);
		} catch (Exception ex) {
			// TODO: handle exception

			System.out.println("ups !!! ocurrio un error buscando al empleado");
			ex.printStackTrace();
		}

		return empleado;
	}

	public static void create(Empleado empleado) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(empleado);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la empleado");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Empleado empleado) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(empleado);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la empleado");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Empleado empleado = DaoEmpleado.find(id);
			em.remove(empleado);
			em.getTransaction().commit();
			System.out.println("Empleado Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
