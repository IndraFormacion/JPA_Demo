/**
 * 
 */
package ejecutable;

import java.util.List;

import dao.DaoCiudad;
import dao.DaoCurso;
import dao.DaoEmpleado;
import dao.DaoEstudiante;
import dao.DaoPais;
import dao.DaoPersona;
import model.Ciudad;
import model.Curso;
import model.Empleado;
import model.Estudiante;
import model.Pais;
import model.Persona;

/**
 * @author aocarballo
 *
 */
public class Inicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testPersona();
		testEmpleado();
		testPaisCdad();
		testEstudCurso();
		testCursoEstud();
	}

	public static void testPersona() {
		Persona p = DaoPersona.find(3L);
		System.out.println("---- PERSONA de base de datos -----------");
		System.out.println(p);
		System.out.println(p.getNombre());

		p.setNombre("Marcela");
		p.setApellido("Garcia");

		DaoPersona.update(p);
		System.out.println("---- PERSONA MODIFICADA de base de datos -----------");
		System.out.println(p);
		System.out.println(p.getNombre());

	}

	public static void testEmpleado() {
		Empleado e = DaoEmpleado.find(2L);
		System.out.println("---- EMPLEADO de base de datos -----------");
		System.out.println(e.getUsuario());
		System.out.println(e.getPerson().getNombre());

	}

	public static void testPaisCdad() {
		Pais p=DaoPais.find(2L);
		System.out.println("---- PAIS de base de datos -----------");
		System.out.println(p);
		System.out.println(p.getNombre());
		
		System.out.println("---- CIUDADES -------");
		List<Ciudad> ciudades=DaoCiudad.findAllCiudades(2);
		for(int i =0; i<ciudades.size();i++) {
			System.out.println(ciudades.get(i));
		}
		
	}
	public static void testEstudCurso() {
		Estudiante e=DaoEstudiante.find(2L);
		System.out.println("---- Estudiante de base de datos -----------");
		System.out.println(e);
		System.out.println(e.getNombre());
		
		System.out.println("---- Curso -------");
		List<Curso> curso=DaoCurso.findAllCurso(2);
		for(int i =0; i<curso.size();i++) {
			System.out.println(curso.get(i));
		}
	}
	public static void testCursoEstud() {
		Curso c=DaoCurso.find(2L);
		System.out.println("---- Curso de base de datos -----------");
		System.out.println(c);
		System.out.println(c.getNombre());
		
		System.out.println("---- Estudiantes -------");
		List<Estudiante> estudiante=DaoEstudiante.findAllEstudiante(2);
		for(int i =0; i<estudiante.size();i++) {
			System.out.println(estudiante.get(i));
		}
	}
}
