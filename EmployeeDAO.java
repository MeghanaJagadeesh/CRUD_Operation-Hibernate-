package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Employee;

public class EmployeeDAO {
	EntityManagerFactory f = Persistence.createEntityManagerFactory("mock");
	EntityManager m = f.createEntityManager();
	EntityTransaction t = m.getTransaction();

	Employee e = new Employee();

	public void addEmployee(Employee empobj) {
		t.begin();
		m.persist(empobj);
		t.commit();
	}

	public void fetchEmployee(int id, String path) throws IOException {
		Employee fetch = m.find(Employee.class, id);
		if (fetch != null) {
			FileOutputStream fs = new FileOutputStream(path);
			fs.write(fetch.getImage());
			System.out.println(fetch.getName());
			System.out.println(fetch.getAge());
			System.out.println(fetch.getAddress());

		} else {
			System.out.println("Data not found");
		}
	}

	public void updateEmployee(int id, String name) {
		Employee fetch = m.find(Employee.class, id);
		if (fetch != null) {
			fetch.setName(name);
			t.begin();
			m.merge(fetch);
			t.commit();
		}
	}

	public void removeEmployee(int id) {

		Employee remove = m.find(Employee.class, id);
		if (remove != null) {
			t.begin();
			m.remove(remove);
			t.commit();
		} else {
			System.out.println("Invalid Data");
		}
	}

	public void fetchAll() {
		Query q = m.createQuery("select a from Employee a");
		List<Employee> l = q.getResultList();
		for (Employee e : l) {
			System.out.println(e.getId() + "\n" + e.getName() + "\n" + e.getAge() + "\n" + e.getAddress());
			System.out.println("\n");
		}
	}
}
