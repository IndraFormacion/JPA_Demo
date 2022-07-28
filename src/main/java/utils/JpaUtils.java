/**
 * 
 */
package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author aocarballo
 *
 */
public class JpaUtils {
	private static final EntityManagerFactory emf;
	//El inicializador static, reemplaza al constructor de instancias.
	static {
		try {
			emf=Persistence.createEntityManagerFactory("JPA_Demo");
		} catch (Throwable ex) {
			// TODO: handle exception
			System.out.println("La sesion de factoria no se pudo inicializar "+ ex);
			  throw new ExceptionInInitializerError(ex);
		}
	}
	public static EntityManagerFactory getEmf() {
	    return emf;
	}
}
