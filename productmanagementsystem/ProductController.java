package productmanagementsystem;

import java.util.Scanner;

public class ProductController {
    Scanner sc = new Scanner(System.in);
    ProductService productService = new ProductService();

    public void addProduct() {
        System.out.println("enter product details");
        // Here you would typically collect product details from the user
        System.out.print("Enter product id: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine(); // Consume newline left-over
        System.out.print("Enter product quanitty ");
        int quantity = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        productService .addProduct(new Product(id, name, price, quantity));



    }

    public void viewProducts() {
        ProductService.findAll();
    }

    public void updateProduct() {
        System.out.print("Enter product id to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        Product product = productService.findById(id);
        if (product != null)
            productService.updateProduct(product);

        else
            System.out.println("Product not found.");


    }

    public void deleteProduct() {
        System.out.print("Enter product id to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        Product product = productService.findById(id);
        if (product != null) {
            boolean deleted=productService.removeproduct(product);
            if(deleted)
                System.out.println("Product deleted successfully: " + product);
            else
                System.out.println("Failed to delete product: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void searchProduct() {
        System.out.print("Enter product id to search: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        Product product = productService.findById(id); // Statement expected
        if (product != null)
            System.out.println("Product found: " + product);
        else
            System.out.println("Product not found.");

    }

    public void searchProductByName() {
        System.out.print("Enter product name to search: ");
        String name = sc.nextLine();
        Product p= productService.findProductByName(name);
        if (p!=null)
            System.out.println("Product with name '" + name + "' found."+p);
        else
            System.out.println("Product with name '" + name + "' not found.");
    }
}
