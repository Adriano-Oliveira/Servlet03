import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ListaAlunos extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException    {
		res.setContentType("text/html");
      	PrintWriter out = res.getWriter();
    	String url = "jdbc:mysql:alunos";
	   	Connection con;
	   	Statement stmt;
	   	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
 		} catch(Exception e) {
			out.println("<P>ClassNotFoundException: ");
			out.println("<P>"+e.getMessage());
	   	}	
	   	try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");	
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from alunos");
			out.println("<P> Nome"+"   "+"Codigo"+"   "+"Nota1"+"   "+"Nota2"+"   "+"Frequencia"+"   "+"Media"+"   "+"Resultado<br>");
			while (rs.next()) {
				String s = rs.getString("nome");
				int n = rs.getInt("codigo");
				float n01 = rs.getFloat("nota1");
				float n02 = rs.getFloat("nota2");
				float media = (2*n01+3*n02)/5;
				int fr = rs.getInt("freq");
				String result="";
				if (media>=6 && fr>75) {
					result="Aprovado";
				} else {
					result="Reprovado";
				}
					out.println("<P>"+s+"   "+n+"   "+n01+"   "+n02+"   "+fr+"   "+media+"   "+result+"<br>");
			}
			stmt.close();
			con.close();
		} catch(SQLException ex) {
				out.println("<P>SQLException: " + ex.getMessage());
		}
	    out.println("<html>");
	    out.println("<title>Listagem de Alunos</title>");
	    out.println("</html>");
        out.close();
    }
}