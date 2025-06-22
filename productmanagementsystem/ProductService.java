package productmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private static final List<Product> products = new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    public static void findAll() {
        if(products.isEmpty()) {
            System.out.println("No products available.");
        } else
            products.forEach(System.out::println);

    }

    public void addProduct(Product p) {
        if (p == null || p.getId() <= 0 || p.getName() == null || p.getPrice() <= 0 || p.getQuantity() < 0) {
            System.out.println("Invalid product details. Please try again.");
            return;
        }
        products.add(p);
        System.out.println("Product added successfully: " + p);
        
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        System.out.println("Product with ID " + id + " not found.");
        return null;
    }

    public void updateProduct(Product p) {
        System.out.println("enter product details to update");
        System.out.println("enter product name to update");
        String name = sc.nextLine();
        System.out.println("enter product price to update");
        double price = sc.nextDouble();
        System.out.println("enter product quantity to update");
        int quantity = sc.nextInt();
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        System.out.println("product updated successfully: " + p);




    }

    public boolean removeproduct(Product product) {
        if(products.isEmpty()||!products.contains(product))
            return false;
        products.remove(product);
        return true;

    }

    public Product findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .peek(System.out::println)
                .findFirst()
                .orElse(null);

    }

}
