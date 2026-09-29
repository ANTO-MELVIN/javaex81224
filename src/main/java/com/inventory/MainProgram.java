package com.inventory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {

        SessionFactory factory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        ProductDAO dao = new ProductDAO();

        boolean running = true;

        while (running) {

            System.out.println("\n========== PRODUCT INVENTORY SYSTEM ==========");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    scanner.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();

                    Product product =
                            new Product(name, category, price, quantity);

                    Session addSession = factory.openSession();

                    dao.addProduct(product, addSession);

                    addSession.close();

                    break;

                case 2:

                    Session viewSession = factory.openSession();

                    dao.viewProducts(viewSession);

                    viewSession.close();

                    break;

                case 3:

                    System.out.print("Enter Product ID to update: ");
                    int updateId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter New Product Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter New Category: ");
                    String newCategory = scanner.nextLine();

                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = scanner.nextInt();

                    Session updateSession =
                            factory.openSession();

                    dao.updateProduct(
                            updateId,
                            newName,
                            newCategory,
                            newPrice,
                            newQuantity,
                            updateSession
                    );

                    updateSession.close();

                    break;

                case 4:

                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = scanner.nextInt();

                    Session deleteSession =
                            factory.openSession();

                    dao.deleteProduct(deleteId, deleteSession);

                    deleteSession.close();

                    break;

                case 5:

                    running = false;

                    System.out.println("Exiting application...");

                    break;

                default:

                    System.out.println("Invalid choice!");
            }
        }

        factory.close();
        scanner.close();
    }
}