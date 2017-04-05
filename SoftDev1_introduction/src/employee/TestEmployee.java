package employee;

import java.util.Date;

public class TestEmployee {
	public static void main(String[] args) {
		Employee employee1 = new Employee();
		employee1.setFirstname("joe");
		employee1.setSurname("jsmith");
		employee1.setPosition("boss");
		//employee1.setDob(new Date());
		
		
		EmployeeDAO.create(employee1);
	}
}
