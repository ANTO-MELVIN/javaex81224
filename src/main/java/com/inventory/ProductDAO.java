package com.inventory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {

    // ADD PRODUCT
    public void addProduct(Product product, Session session) {

        Transaction transaction = session.beginTransaction();

        session.persist(product);

        transaction.commit();

        System.out.println("Product added successfully!");
    }

    // VIEW PRODUCTS
    public void viewProducts(Session session) {

        List<Product> products =
                session.createQuery("from Product", Product.class).list();

        System.out.println("\n========== PRODUCT INVENTORY ==========");

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("=======================================");
    }

    // UPDATE PRODUCT
    public void updateProduct(int id, String name, String category,
                              double price, int quantity,
                              Session session) {

        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);

            session.merge(product);

            System.out.println("Product updated successfully!");

        } else {
            System.out.println("Product not found!");
        }

        transaction.commit();
    }

    // DELETE PRODUCT
    public void deleteProduct(int id, Session session) {

        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {

            session.remove(product);

            System.out.println("Product deleted successfully!");

        } else {
            System.out.println("Product not found!");
        }

        transaction.commit();
    }
}