/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author aocarballo
 *
 */
@Entity
@Table(name = "paises")
public class Pais implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy="pais", targetEntity = Ciudad.class, cascade = CascadeType.ALL)
	private Set<Ciudad> ciudades;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the ciudades
	 */
	public Set<Ciudad> getCiudades() {
		return ciudades;
	}
	/**
	 * @param ciudades the ciudades to set
	 */
	public void setCiudades(Set<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
}
