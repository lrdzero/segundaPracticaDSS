package modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.* ;




public class BDUsuario {

private static final String PERSISTENCE_UNIT_NAME = "usuario" ;

private static EntityManagerFactory factoria=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME ) ;
private static EntityManager em;
public BDUsuario(){
	em=factoria.createEntityManager();
}
//Insertar un usuario; si ya existe, no tiene efecto.
public static void insertar ( Usuario usuario ) { //...}
	
	em.getTransaction().begin();
	em.persist(usuario);
	em.getTransaction().commit();
	
}

//Actualizar los datos de un usuario en la base de datos

public static void actualizar ( Usuario usuario ) {
	
}//... }

//Eliminar un usuario de la base de datos

public static void eliminar( Usuario usuario ) {
	 System.out.println("ELIMINANDOOOOO.....");
	 Usuario yo = em.find(Usuario.class, usuario.getEmail());
	 em.getTransaction().begin();
	 em.remove(yo);
	 em.getTransaction().commit();
}//... }

//Recuperar un usuario desde la base de datos

public static Usuario seleccionarUsuario ( String email ) {
	
	return null;//...}
}
//Comprobar que existe el usuario cuyo email pasamos como

public static boolean existeEmail ( String email ) {
	boolean em = false;

	return em; //...}
}
//Listar los usuarios de la base de datos

public List<Usuario > listarUsuarios ( ) {
	
	TypedQuery<Usuario> q=em.createQuery("select c from Usuario c",Usuario.class);
	List<Usuario> listaCompleto = q.getResultList();
	
	
	return listaCompleto;//...}
	
}

}

	

