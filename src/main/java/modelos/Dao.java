package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao 
{
         private String driver = "com.mysql.cj.jdbc.Driver";
         private String url = "jdbc:mysql://127.0.0.1:3306/agenda";
         private String user = "fer";
         private String password = "hadson";
         
         private Connection conecta()
         {
                 Connection conn = null;
                 try
                 {
                         Class.forName(driver);
                         conn = DriverManager.getConnection(url, user, password);     
                         return conn;
                 }
                 catch(Exception e){System.out.println(e); return null;} 
         }     
         
         public void teste()
         {
                  try
                  {
                           Connection conn = conecta();
                           System.out.println(conn);
                           conn.close();
                  }
                  catch(Exception e){System.out.println(e); }
             }
         
             public void inserir(JavaBeans contato)
             {   
        	     String salvar = "insert into contatos(nome, fone, email) "
		        	+ "values(?, ?, ?)";
                     try
                     {
                	Connection conn = conecta();   		      
    		        PreparedStatement ps = conn.prepareStatement(salvar);
    		        ps.setString(1, contato.getNome());
    		        ps.setString(2, contato.getFone());
    		        ps.setString(3, contato.getEmail());
    		        ps.executeUpdate();
    		        conn.close();
    		 }
                     catch (Exception e){System.out.println("erro de banco");}
                 }
             
                 public ArrayList<JavaBeans> buscar()
                 {
                         ArrayList<JavaBeans> contatos = new ArrayList<>();                     
                         String busca = "select * from contatos order by nome"; 
                         try
                         {             
                                Connection conn = conecta();
                                PreparedStatement ps = conn.prepareStatement(busca);
                                ResultSet sr = ps.executeQuery();
                                
                               while(sr.next())
                               {
                        	   String idcon = sr.getString(1);
                        	   String nome = sr.getString(2);
                        	   String fone = sr.getString(3);
                        	   String email = sr.getString(4);
                        	   contatos.add(new JavaBeans(idcon, nome, fone, email));                    	   
                               }
                               conn.close();                                 
                          }
                         catch(Exception e){System.out.println("Erro de banco");}
			 return contatos;
                 }  
                 
                 public void achar(JavaBeans contato)
                 {
                                                                        
                            String updatar = "select * from contatos where idcon = ?";
                                   
                            try
                            {  
                        	   Connection conn = conecta();    
                                   PreparedStatement ps = conn.prepareStatement(updatar);
                                   ps.setString(1, contato.getId());
                                   ResultSet sr = ps.executeQuery();
                                   while(sr.next())
                                   {                                                                             
                                           contato.setId(sr.getString(1));
                                           contato.setNome(sr.getString(2));
                                           contato.setFone(sr.getString(3));
                                           contato.setEmail(sr.getString(4));
                                   }
                                   conn.close();
                             }
                             catch(Exception e){System.out.println("Erro de banco");}                         
                 }
                 
                 public  void upar(JavaBeans contato)
                 {   
                         String upe = "update contatos set nome=?, fone=?, "
                         	                 + "email=? where idcon=?";
                         try
                         {
                                Connection conn = conecta();
                                PreparedStatement ps = conn.prepareStatement(upe);
                                ps.setString(4, contato.getId());
                                ps.setString(1, contato.getNome());
                                ps.setString(2, contato.getFone());
                                ps.setString(3, contato.getEmail());
                                ps.executeUpdate();
                                conn.close();
                         }
                         catch(Exception e){System.out.println("Erro de banco");}
                  }
                 
                 public void deletar(JavaBeans contato)
                 {
                        String del = "delete from contatos where idcon=?";
                        try
                        {
                                 Connection conn = conecta();
                                 PreparedStatement ps = conn.prepareStatement(del);
                                 ps.setString(1, contato.getId());
                                 ps.executeUpdate();
                                 conn.close();
                        }
                        catch(Exception e){System.out.println("Erro de banco");}
                 }
}
