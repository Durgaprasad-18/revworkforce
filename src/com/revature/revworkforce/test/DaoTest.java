
package com.revature.revworkforce.test;

import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.model.Employee;

public class DaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 	EmployeeDAO dao = new EmployeeDAO();
	        Employee emp = dao.login(1, "admin123");
	        System.out.println(emp != null ? "Login OK" : "Login Failed");
	    }

	}
