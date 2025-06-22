package productmanagementsystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        Scanner sc=new Scanner(System.in);
        System.out.println("welcome to product management system");
       while(true){
           System.out.println("enter your choice from the following options:");
            System.out.println("1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product\n\n5.search product by id\n6.search product by name\n7. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> productController.addProduct();
                case 2 -> productController.viewProducts();
                case 3 -> productController.updateProduct();
                case 4 -> productController.deleteProduct();
                case 5 -> productController.searchProduct();
                case 6 -> productController.searchProductByName();
                case 7 -> {
                    System.out.println("Thank you for using the Product Management System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
       }

    }
