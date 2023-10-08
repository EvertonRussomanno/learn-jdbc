package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n=== TEST 1: department findById =====");
        Department department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println("\n=== TEST 2: department findAll =====");
        List<Department> listAll = departmentDao.findAll();
        for (Department obj : listAll) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: department insert =====");
        Department newDepartment = new Department(null, "Utilities");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New ID = " + newDepartment.getId());

        System.out.println("\n=== TEST 4: department update =====");
        Department department2 = departmentDao.findById(1);
        department2.setName("Super Computers");
        departmentDao.update(department2);
        System.out.println("Update complete");

        System.out.println("\n=== TEST 5: department deleteById =====");
        System.out.print("Enter id for delete department: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete complete!");

        sc.close();
    }
}
