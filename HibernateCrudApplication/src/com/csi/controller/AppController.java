package com.csi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csi.dao.EmployeeDao;
import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;

/**
 * Servlet implementation class AppController
 */
//@WebServlet("/AppController")
public class AppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppController() {
        super();
        // TODO Auto-generated constructor stub
    }

    Employee employee=new Employee();
    EmployeeDaoImpl daoImpl=new EmployeeDaoImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameter("addEmployee") != null) {
			String empName = request.getParameter("empname");
			String empSalary = request.getParameter("empsalary");
			employee.setEmpName(empName);
			employee.setEmpSalary(Integer.parseInt(empSalary));
			daoImpl.saveEmployee(employee);
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeAdd.jsp");
			rd.forward(request, response);
			System.out.println("hiiiiiii");
		//avahs
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if (request.getParameter("showEmployee") != null) {
			List<Employee> employeeList = new ArrayList();
			employeeList = daoImpl.showEmployees();
			request.setAttribute("employeeList", employeeList);
			RequestDispatcher rd = request.getRequestDispatcher("ShowAll.jsp");
			rd.forward(request, response);
		}
		if (request.getParameter("updateEmployee") != null) {
			int empid1 = Integer.parseInt(request.getParameter("empId"));
			String empName = request.getParameter("empName");
			int empSalary = Integer.parseInt(request.getParameter("empSalary"));
			daoImpl.updateEmployee(empid1, empName, empSalary);
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeAdd.jsp");
			rd.forward(request, response);
		}
		if (request.getParameter("deleteEmployee") != null) {
			int empid2 = Integer.parseInt(request.getParameter("empId"));
			employee.setEmpId(empid2);
			daoImpl.deleteEmployee(employee);
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeAdd.jsp");
			rd.forward(request, response);
		}
	}


}
