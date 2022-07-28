/**
 * 
 */
package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author aocarballo
 *
 */
@Entity
@Table(name = "estudiantes_cursos")
public class EstudianteCurso implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="id")
		private Long id=null;
		@Column(name="fk_estudiante")
		private Estudiante fk_estudiante;
		@Column(name="fk_curso")
		private Curso fk_curso;
}
