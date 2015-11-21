package comunicacion;
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.BDUsuario;
import modelo.Usuario;
public class ListaCorreosServlet extends HttpServlet{
	private static final long serialVersionUID =1L;
	private BDUsuario db;
	private static Usuario us;
	public void init(){
		db=new BDUsuario();
		us=new Usuario();
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 // Set response content type
		//doPost(request,response);
		
		  List<Usuario> nueva = db.listarUsuarios();
		  //doPost(request,response);
	      //response.setContentType("text/html");
	     
	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<HTML><HEAD><TILLTE>Leyendo</TITLE></HEAD>");
	      out.println("<H2>Lista de Usuarios</H2><P>");
	      out.println("<table><tr><th>Nombre</th><th>Apellidos</th><th>Email<th></tr>");
	      for(int i=0;i<nueva.size();i++){
	    	  out.println("<tr>");
	    	  out.println("<td id=\"name\""+i+">"+nueva.get(i).getNombre()+"</td>");
	    	  out.println("<td id=\"name\""+i+">"+nueva.get(i).getApellido()+"</td>");
	    	  out.println("<td id=\"name\""+i+">"+nueva.get(i).getEmail()+"</td>");
	    	  out.println("</tr>");
	      }
	    
	    out.close();
	    
		
	
	     
			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// Obtenemos un objeto Print Writer para enviar respuesta
		System.out.println("soy server"+request.getParameter("action"));
		//PrintWriter out = response.getWriter();
		String form = request.getParameter("action");
		
		if(form.equals("eliminarUsuario")){
			System.out.println("Soy el servlet: estoy en Borrar");
			Usuario nuevo = new Usuario();
			
			nuevo.setEmail(request.getParameter("email"));
			if(db.existeEmail(nuevo.getEmail())){
				db.eliminar(nuevo);
			
				ObjectOutputStream neto = new ObjectOutputStream(response.getOutputStream());
				neto.writeInt(0);
				neto.writeUTF("Borrado con exito");
				neto.flush();
				neto.close();
			}
			else{
				ObjectOutputStream neto = new ObjectOutputStream(response.getOutputStream());
				neto.writeInt(1);
				neto.writeUTF("Borrado error de borrado el email no se encuentra");
				neto.flush();
				neto.close();
			}
		
			
			
			
		}
		else if(form.equals("listarUsuarios")){
			
			String datString = db.listarUsuarios().toString();
			
			System.out.println("Soy el servlet: estoy en listar");
			
			
			ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
			objectOutput.writeObject(db.listarUsuarios());
			objectOutput.flush();
			objectOutput.close();
			
			//data.wr
			
		}
		else if(form.equals("aniadirUsuario")){
			System.out.println("Soy el servlet: estoy en Aniadir");
			Usuario nuevo = new Usuario();
			nuevo.setNombre(request.getParameter("nombre"));
			nuevo.setApellido(request.getParameter("apellido"));
			nuevo.setEmail(request.getParameter("email"));
			if(!db.existeEmail(nuevo.getEmail())){
				db.insertar(nuevo);
				ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
				objectOutput.writeInt(0);
				objectOutput.writeUTF("Usuario insertado con exito");
				objectOutput.flush();
				objectOutput.close();
			}
			else{
				ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
				objectOutput.writeInt(1);
				objectOutput.writeUTF("Este email ya existe");
				objectOutput.flush();
				objectOutput.close();
				
			}
			
			
		}
		else if(form.equals("actualizarUsuario")){
			System.out.println("Soy el servlet: estoy en actualizar");
			Usuario nuevo = new Usuario();
			nuevo.setNombre(request.getParameter("nombre"));
			nuevo.setApellido(request.getParameter("apellido"));
			nuevo.setEmail(request.getParameter("email"));
			if(db.existeEmail(nuevo.getEmail())){
				db.actualizar(nuevo);
				ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
				objectOutput.writeInt(0);
				objectOutput.writeUTF("Actualizado correcto");
				objectOutput.flush();
				objectOutput.close();
			}
			else{
				ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
				objectOutput.writeInt(0);
				objectOutput.writeUTF("Actualizado incorrecto: no se pudo encontrar coincidencia");
				objectOutput.flush();
				objectOutput.close();
			}
			
		}
				
					
			
				
				
			
			
	}
}
