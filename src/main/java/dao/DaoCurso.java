/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Curso;
import utils.JpaUtils;

/**
 * @author aocarballo
 *
 */
public class DaoCurso {
	public static List<Curso> findAllCurso(int fk){
		EntityManager em=JpaUtils.getEmf().createEntityManager();
		Query cursoq=em.createNativeQuery("SELECT nombre FROM indra2022.cursos where id IN (SELECT fk_curso FROM indra2022.estudiantes_cursos where fk_estudiante = "+ fk+")");
		List<Curso>cursos=cursoq.getResultList();
		return cursos;
	}
	public static Curso find(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		Curso curso = null;
		try {
			curso = em.find(Curso.class, id);
		} catch (Exception ex) {
			// TODO: handle exception

			System.out.println("ups !!! ocurrio un error buscando al curso");
			ex.printStackTrace();
		}

		return curso;
	}

	public static void create(Curso curso) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(curso);// Esta haciendo un INSERT
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("ups !!! ocurrio un error creando a la curso");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(Curso curso) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(curso);// Esta haciendo un UPDATE
			tx.commit();

			System.out.println("actualizacion exitosa!!");
		} catch (Exception ex) {
			// TODO: handle exception
			tx.rollback();
			System.out.println("ups !!! ocurrio un error actualizando a la curso");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void delete(Long id) {
		EntityManager em = JpaUtils.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			Curso curso = DaoCurso.find(id);
			em.remove(curso);
			em.getTransaction().commit();
			System.out.println("Curso Borrada!!");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ups !!! ocurrio un error eliminando a la persona");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
