package employee;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import employee.Employee;

public class EmployeeDAO {
	static String dbHost = "localhost";
	static String dbUser = "root";
	static String dbPassword ="";
	static String dbDatabase = "softdev2";
	static String tableName = "employeesimple";
	static String createTableSQL = "create table employeesimple ( firstname varchar(250), surname varchar(250),position varchar(250));";

	static String dbURL="jdbc:mysql://"+dbHost+"/"+dbDatabase+"?user="+dbUser+"&password="+dbPassword;

	static boolean databaseSetup = setUpDatabase();
	
	/**
	 * this gets run when the class is first loaded
	 * it registers the driver,
	 * checks the connction and database
	 * and checks that the table exists (create it if it doesn't
	 * @return
	 */
	
	
	static boolean setUpDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("driver not loaded");
		}	
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection(dbURL);
			// check if the table is there
			String sql = "show tables like ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,tableName);
			ResultSet rs = ps.executeQuery();
			// if we don't get anything then table does not exist so create it
			if (!rs.next()){
				ps = connection.prepareStatement(createTableSQL);
				ps.executeUpdate();
			}
			connection.close();
			return true;
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void create(Employee employee){
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection(dbURL);
			String sql = "insert into employeesimple (firstname, surname, position) values (?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,employee.getFirstname());
			ps.setString(2,employee.getSurname());
			ps.setString(3,employee.getPosition());
			
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void update(Employee employee){
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection(dbURL);
			String sql = "update employeesimple set surname= ?, position=? where firstname =?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(3,employee.getFirstname());
			ps.setString(1,employee.getSurname());
			ps.setString(2,employee.getPosition());
			
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public static void delete(String firstname){
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection(dbURL);
			String sql = "delete from employeesimple  where firstname =?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,firstname);
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public static Employee findById(String firstname){
		Connection connection = null;
		Employee employee = null;
		try {
			connection =  DriverManager.getConnection(dbURL);
			String sql = "select * from employeesimple  where firstname =?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,firstname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				employee = new Employee();
				employee.setFirstname(rs.getString("firstname"));
				employee.setSurname(rs.getString("surname"));
				employee.setPosition(rs.getString("position"));
				
				}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return employee;
	}
	public static List<Employee> getAll(){
		Connection connection = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			connection =  DriverManager.getConnection(dbURL);
			String sql = "select * from employeesimple ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Employee employee = new Employee();
				employee = new Employee();
				employee.setFirstname(rs.getString("firstname"));
				employee.setSurname(rs.getString("surname"));
				employee.setPosition(rs.getString("position"));
				
				employees.add(employee);
				}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return employees;
	}

}
