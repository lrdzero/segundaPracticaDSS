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
	if(!existeEmail(usuario.getEmail())){
	em.getTransaction().begin();
	em.persist(usuario);
	em.getTransaction().commit();
	}
	
}

//Actualizar los datos de un usuario en la base de datos

public static void actualizar ( Usuario usuario ) {
	Usuario aActualizar=seleccionarUsuario(usuario.getEmail());
	 
	  em.getTransaction().begin();
	  aActualizar.setNombre(usuario.getNombre());
	  aActualizar.setEmail(usuario.getEmail());
	  aActualizar.setApellido(usuario.getApellido());
	  em.getTransaction().commit();
}//... }

//Eliminar un usuario de la base de datos

public static void eliminar( Usuario usuario ) {
	
	 Usuario aBorrar = seleccionarUsuario(usuario.getEmail());
	 if(aBorrar!=null){
		 em.getTransaction().begin();
		 em.remove(aBorrar);
		 em.getTransaction().commit();
	 }
	// Usuario yo = em.find(Usuario.class, usuario.getEmail());
	// em.getTransaction().begin();
	// em.remove(yo);
	// em.getTransaction().commit();
	 
}//... }

//Recuperar un usuario desde la base de datos

public static Usuario seleccionarUsuario ( String email ) {
	String arg1 =email;
	Usuario aDevolver = null;
	if(existeEmail(email)){
		Query q =em.createQuery("Select sm from Usuario sm where sm.email=:arg1");
		q.setParameter("arg1",arg1);
		List<Usuario>list =q.getResultList();
		aDevolver=list.get(0);
	}
	
	
	return aDevolver;//...}
}
//Comprobar que existe el usuario cuyo email pasamos como

public static boolean existeEmail ( String email ) {
	boolean emt = false;
	//TypedQuery<Usuario> q=em.createQuery("select c.nombre from Usuario c where c='"+email+"'",Usuario.class);
	//List<Usuario> l = q.getResultList();
	//System.out.println("Usuario es: "+l.get(0).getNombre());
	String arg1=email;
	Query query = em.createQuery("Select sm.nombre from Usuario sm where sm.email=:arg1");
	query.setParameter("arg1", arg1);
	List <String> nm =query.getResultList();
	if(nm!=null){
		if(nm.size()>0){
			emt=true;
		}
		else{
			emt=false;
		}
	}
	else{
		emt=false;
	}
	return emt; //...}
}
//Listar los usuarios de la base de datos

public List<Usuario > listarUsuarios ( ) {
	
	TypedQuery<Usuario> q=em.createQuery("select c from Usuario c",Usuario.class);
	List<Usuario> listaCompleto = q.getResultList();
	
	
	return listaCompleto;//...}
	
}

}

	

