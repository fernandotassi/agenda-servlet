package controladores;

import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Dao;
import modelos.JavaBeans;

@WebServlet(urlPatterns = {"/Controle", "/main", "/inserir", "/seletor", "/upar", 
	                    "/deletar", "/relatorio"})
public class Controle extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	JavaBeans contato = new JavaBeans();
   
        public Controle() 
        {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.getWriter().append("Hospedado em: ").append(request.getContextPath());
		dao.teste();
		String acao = request.getServletPath();
		System.out.println(acao);
		if(acao.equals("/main"))
		        contatos(request, response);
		else if(acao.equals("/inserir"))
		         novoContato(request, response);
		else if(acao.equals("/seletor"))
		          selecionar(request, response);    
		else if(acao.equals("/upar"))
		          updatear(request, response);
		else if(acao.equals("/deletar"))
		          deletar(request, response);
		else if(acao.equals("/relatorio"))
		{    
		          relatorio(request, response);	      
		} 
		else 
		         response.sendRedirect("index.html");
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	         //response.sendRedirect("agenda.jsp");
	         ArrayList<JavaBeans> lista = dao.buscar();
	         for(int i = 0; i < lista.size(); i++)
	         {   
	                 System.out.println(lista .get(i).getId());
        	         System.out.println(lista.get(i).getNome());
        	         System.out.println(lista.get(i).getFone());
        	         System.out.println(lista.get(i).getEmail());
 	         }
	         request.setAttribute("contatos", lista);
	         RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
	         rd.forward(request, response);
	    }
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
	        System.out.println(request.getParameter("nome"));
	        System.out.println(request.getParameter("fone"));
	        System.out.println(request.getParameter("email"));
	        
	        contato.setNome(request.getParameter("nome"));
	        contato.setFone(request.getParameter("fone"));
	        contato.setEmail(request.getParameter("email"));
	        
	        dao.inserir(contato);
	        response.sendRedirect("main");
	}
	
	protected void selecionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	        String id = request.getParameter("id");
	        System.out.println(id);
	        contato.setId(id);
	        dao.achar(contato);
	        //System.out.println(contato.getNome());
	        request.setAttribute("id", contato.getId());
	        request.setAttribute("nome", contato.getNome());
	        request.setAttribute("fone", contato.getFone());
	        request.setAttribute("email", contato.getEmail());
	        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
	        rd.forward(request, response);
	   }
	   protected void updatear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {        
	             System.out.println(request.getParameter("id"));
	            System.out.println(request.getParameter("nome"));
	            System.out.println(request.getParameter("fone"));
	            System.out.println(request.getParameter("email"));
	            
	            contato.setId(request.getParameter("id"));
	           contato.setNome(request.getParameter("nome"));
	           contato.setFone(request.getParameter("fone"));
	           contato.setEmail(request.getParameter("email"));
	           
	           dao.upar(contato);
	           response.sendRedirect("main");
	   }
	   
	   protected void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
	            String id= request.getParameter("id");
                    System.out.println(id);
                    contato.setId(id);
                    dao.deletar(contato);
                    response.sendRedirect("main");
	   }
	   
	   protected void relatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	   
	   {
	             Document doc = new Document();
	             try
	             {
	        	     response.setContentType("application/pdf");
	        	     response.addHeader("Content-Disposition", "inline; filename=relatorio.pdf");
	        	     PdfWriter.getInstance(doc, response.getOutputStream());
	        	     doc.open();
	        	     doc.add(new Paragraph("lista de contatos: "));
	        	     doc.close();
	        	 
	             }
	             catch(Exception e){System.out.println(e); doc.close();}	           	
	   }
}
 