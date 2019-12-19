package com.csi.jpa;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class Service {
	private static SessionFactory factory;

	public static void main(String[] args) {

		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		Employee e1 = new Employee();
		e1.setEmpName("JERRY");
		Employee e2 = new Employee();
		e2.setEmpName("TOM");

		Address a1 = new Address();
		a1.setAddressCity("PCMC");
		Address a2 = new Address();
		a2.setAddressCity("PUNE");
		session.save(a1);
		session.save(a2);
		Set<Address> addressList = new HashSet<>();
		addressList.add(a1);
		addressList.add(a2);

		e1.setAddress(addressList);
		e2.setAddress(addressList);

		session.save(e1);
		session.save(e2);
		transaction.commit();

	}

}
