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
		/*
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
	    */
		System.out.println("soy server"+request.getParameter("action"));
		//PrintWriter out = response.getWriter();
		String form = request.getParameter("action");
		
		if(form.equals("eliminarUsuario")){
			System.out.println("Soy el servlet: estoy en Borrar");
			Usuario nuevo = new Usuario();
			
			nuevo.setEmail(request.getParameter("email"));
			db.eliminar(nuevo);
			ObjectOutputStream neto = new ObjectOutputStream(response.getOutputStream());
			neto.writeInt(0);
			neto.writeUTF("Borrado con exito");
		
			
			
		}
		else if(form.equals("listarUsuarios")){
			
			String datString = db.listarUsuarios().toString();
			
			System.out.println("Soy el servlet: estoy en listar");
			//data.write(d);
			//OutputStream out1 = response.getOutputStream();
			//DataOutputStream out2 = new DataOutputStream(response.getOutputStream());
			
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
			db.insertar(nuevo);
			//List<Usuario> imprimir = db.listarUsuarios();
			   /*
			      out.println("<HTML><HEAD><TILLTE>Leyendo</TITLE></HEAD>");
			      out.println("<H2>Lista de Usuarios</H2><P>");
			      out.println("<table><tr><th>Nombre</th><th>Apellidos</th><th>Email<th></tr>");
			      for(int i=0;i<imprimir.size();i++){
			    	  out.println("<tr>");
			    	  out.println("<td id=\"name\""+i+">"+imprimir.get(i).getNombre()+"</td>");
			    	  out.println("<td id=\"name\""+i+">"+imprimir.get(i).getApellido()+"</td>");
			    	  out.println("<td id=\"name\""+i+">"+imprimir.get(i).getEmail()+"</td>");
			    	  out.println("</tr>");
			      }
			    
			     out.close();
			  */
			ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
			objectOutput.writeInt(0);
			objectOutput.writeUTF(nuevo.toString());
			objectOutput.flush();
			objectOutput.close();
		}
	
	     
			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// Obtenemos un objeto Print Writer para enviar respuesta
			doGet(request,response);
				
					
			
				
				
			
			
	}
}
