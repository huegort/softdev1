package forstudent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.Employee;
import employee.EmployeeDAO;

/**
 * Servlet implementation class ProcessCreateForm
 */
@WebServlet("/ProcessCreateForm")
public class ProcessCreateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// read in the parameters
		String firstname = request.getParameter("firstname");
		
		// put into employee
		Employee employee1 = new Employee();
		employee1.setFirstname(firstname);
		// put into database
		EmployeeDAO.create(employee1);
		
		response.getWriter().append("Done");
	}

	
}
