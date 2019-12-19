package com.csi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.csi.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	static SessionFactory factory;

	@Override
	public List<Employee> showEmployees() {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		List employeeList = session.creeateQuery("from Employee").list();
		return employeeList;
		System.out.println();
	}

	@Override
	public void saveEmployee(Employee employee) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(employee);
		transaction.commit();
	}

	@Override
	public void updateEmployee(int empId, String empName, double empSalary) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		List<Employee> employeeList = session.createQuery("from Employee").list();
		for (Employee emp : employeeList) {
			if (emp.getEmpId() == empId) {
				emp.setEmpName(empName);
				emp.setEmpSalary(empSalary);
				session.save(emp);
				transaction.commit();
			}
		}
	}

	@Override
	public void deleteEmployee(Employee employee) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

//		List<Employee> employeeList = session.createQuery("from Employee").list();
		session.delete(employee);
		transaction.commit();
	}

}
