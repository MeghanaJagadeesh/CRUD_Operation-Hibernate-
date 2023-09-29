package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import dao.EmployeeDAO;
import dto.Employee;

public class Controller_Operation {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		EmployeeDAO emp = new EmployeeDAO();
		
		boolean flag = true;
		while (flag) {
			System.out.println(
					"1. Add Employee \n2. Fetch Employee \n3. Update Employee \n4. Remove Employee \n5.Fetch Employee \n6.exit");
			System.out.println("Enter Your Choice");
			switch (sc.nextInt()) {
			case 1: {
				Employee empObj=new Employee();
				System.out.println("Enter Name");
				empObj.setName(sc.next());
				System.out.println("Enter age");
				empObj.setAge(sc.nextInt());
				System.out.println("Enter address");
				empObj.setAddress(sc.next());
				System.out.println("Enter Image path");
				Scanner sc2=new Scanner(System.in);
				FileInputStream fs=new FileInputStream(sc2.nextLine());
				byte[] arr=new byte[fs.available()];
				fs.read(arr);
				empObj.setImage(arr);
				emp.addEmployee(empObj);
				System.out.println("---Added---");
			}
				break;
			case 2: {
				Scanner sc2=new Scanner(System.in);
				System.out.println("Enter id to fetch");
				int id=sc.nextInt();
				System.out.println("Enter path to save image");
				String path=sc2.nextLine();
				emp.fetchEmployee(id, path);
			}
				break;
			case 3: {
				System.out.println("Enter id for updation");
				int id=sc.nextInt();
				System.out.println("Enter New Name ");
				String newName=sc.next();
				emp.updateEmployee(id, newName);
				
			}break;
			case 4: {
				System.out.println("Enter id to be removed");
				emp.removeEmployee(sc.nextInt());
				System.out.println("---Removed----");
			}
				break;
			case 5: {
				emp.fetchAll();
			}
				break;
			
			case 6: {
				flag=false;
				System.out.println("Thank you");
			}break;
				

			default:{
				System.out.println("Invalid Choice");
			}
				break;
			}
		}

	}
}
