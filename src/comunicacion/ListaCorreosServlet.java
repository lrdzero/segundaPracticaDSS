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
		 
		  List<Usuario> nueva = db.listarUsuarios();
	      response.setContentType("text/html");

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
				System.out.println(request.getParameter("action"));
				PrintWriter out = response.getWriter();
				String form = request.getParameter("action");
				if(form.equals("aniadirUsuario")){
					Usuario nuevo = new Usuario();
					nuevo.setNombre(request.getParameter("nombre"));
					nuevo.setApellido(request.getParameter("apellido"));
					nuevo.setEmail(request.getParameter("email"));
					db.insertar(nuevo);
					List<Usuario> imprimir = db.listarUsuarios();
					   
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
					  
					
				}
				if(form.equals("listarUsuarios")){
					
					String datString = db.listarUsuarios().toString();
					
					
					//data.write(d);
					//OutputStream out1 = response.getOutputStream();
					//DataOutputStream out2 = new DataOutputStream(response.getOutputStream());
					
					ObjectOutputStream objectOutput = new ObjectOutputStream(response.getOutputStream());
					objectOutput.writeObject(db.listarUsuarios());
					
					//data.wr
					
				}
				
					
				
				
				
			
			
	}
}
