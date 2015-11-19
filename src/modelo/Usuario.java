package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L ;
	@Id
	@GeneratedValue( strategy= GenerationType.TABLE)
	
	private long id; //Identificador numerico del usuario
	private String nombre ; //nombre del usuario
	private String apellidos ; //apellidos del usuario
	private String email ; //su direccion de correo electronico
	public Usuario(Usuario usuarioAt) {
		this.nombre=usuarioAt.getNombre();
		this.apellidos=usuarioAt.getApellido();
		this.email=usuarioAt.getEmail();
	}
	//Constructor vacio por defecto
	public Usuario() {
		
	}

	public void setNombre(String value) {
		this.nombre=value;
		
	}

	public void setApellido(String value) {
		this.apellidos=value;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String text) {
		this.email=text;
		
	}
	public String toString(){
		return nombre+" - "+apellidos+" - "+email;
	}

}
