/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Estudiante;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoEstudiante {
	public static List<Estudiante> findAllEstudiante(int fk){
		EntityManager em=JpaUtils.getEmf().createEntityManager();
		Query estudianteq=em.createNativeQuery("SELECT nombre FROM indra2022.estudiantes where id IN (SELECT fk_estudiante FROM indra2022.estudiantes_cursos where fk_curso = "+ fk+")");
		List<Estudiante>estudiante=estudianteq.getResultList();
		return estudiante;
	}
	public static Estudiante find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Estudiante estudiante = null;
		try {
			estudiante = em.find(Estudiante.class, id);
		} catch (Exception ex) {
			// TODO: handle exception

			System.out.println("ups !!! ocurrio un error buscando al estudiante");
			ex.printStackTrace();
		}

		return estudiante;
	}

	public static void create(Estudiante estudiante) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(estudiante);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la estudiante");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Estudiante estudiante) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(estudiante);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la estudiante");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Estudiante estudiante = DaoEstudiante.find(id);
			em.remove(estudiante);
			em.getTransaction().commit();
			System.out.println("Estudiante Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
