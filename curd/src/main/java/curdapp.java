

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/ImgUplode")
@MultipartConfig
public class curdapp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		

		String name =request.getParameter("username");
		String roll =request.getParameter("rollnumber");
		String gender =request.getParameter("gender");
		
		String subject =request.getParameter("subject");
		Part img=request.getPart("img");
		if(img!=null) {
			System.out.println(img.getContentType());
			System.out.println(img.getName());
			System.out.println(img.getSize());
		}
		InputStream is=img.getInputStream();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/imgx","root","123456789");
			PreparedStatement ps=cn.prepareStatement("insert into photos (name,roll,gender,subject,photo)value(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, roll);
			ps.setString(3, gender);
			ps.setString(4, subject);
			
			ps.setBlob(5, is);
			int i=ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("index.html");
			}
			
			
		}catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
		
	}

}
