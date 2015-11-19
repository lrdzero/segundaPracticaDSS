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
	 Query query = em.createQuery(
		      "DELETE FROM Usuario c WHERE c.email = :p");
		  int deletedCount = query.setParameter("p","'"+usuario.getEmail()+"'" ).executeUpdate();
}//... }

//Recuperar un usuario desde la base de datos

public static Usuario seleccionarUsuario ( String email ) {
	return null;//...}
}
//Comprobar que existe el usuario cuyo email pasamos como

public static boolean existeEmail ( String email ) {
	return false; //...}
}
//Listar los usuarios de la base de datos

public List<Usuario > listarUsuarios ( ) {
	
	TypedQuery<Usuario> q=em.createQuery("select c from Usuario c",Usuario.class);
	List<Usuario> listaCompleto = q.getResultList();
	
	
	return listaCompleto;//...}
	
}

}

	

