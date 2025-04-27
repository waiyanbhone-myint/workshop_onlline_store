package com.ps;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        switch (welcomeDisplay()){
            case 1:
                displayProducts();
                break;
            case 2:
                System.out.println("Thank you");
        }
    }

    //--------------- Helping Method ------------------//
    public static int welcomeDisplay(){
        System.out.println("===== Welcome to the Store =====");
        System.out.println("1. Display Products");
        System.out.println("2. Display Cart");
        System.out.println("3. Exit");

        System.out.print("Choose an option as a number: ");
        return Integer.parseInt(scanner.next());

    }

    public static ArrayList<ProductDetails> returnProductsDataFromCsvFile(){
        ArrayList<ProductDetails> productsData = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("products.csv"));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] eachLine = line.split("\\|");
                String sku = eachLine[0];
                String productName = eachLine[1];
                double price = Double.parseDouble(eachLine[2]);
                String department = eachLine[3];

                ProductDetails productDetails = new ProductDetails(sku, productName, price, department);
                productsData.add(productDetails);
            }
            bufferedReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productsData;
    }

    public static void displayProducts(){

        System.out.println("===== Product List =====");
        //int i = 1;
        for (ProductDetails products : returnProductsDataFromCsvFile()) {
            System.out.printf("%-4s %-30s %-10.2f %-15s%n",
                    products.getSku(),
                    products.getProductName(),
                    products.getPrice(),
                    products.getDepartment());
        }
        storeFunction();
    }

    public static void storeFunction(){
        System.out.println("\n1. Search by Name");
        System.out.println("2. Filter by Price");
        System.out.println("3. Filter by Department");
        System.out.println("4. Add Product to Cart");
        System.out.println("0. Back to Home");

        System.out.println("Choose an option: ");
        int userChoice = scanner.nextInt();

        switch (userChoice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("Enter SKU to add to cart: ");
                String skuNumber = scanner.next();
                userAddedProduct(skuNumber);
        }
    }

    public static void userAddedProduct(String sku){
        int num = 1;
        double subtotal = 0;
        ArrayList<String> product = new ArrayList<>();
        StringBuilder total = new StringBuilder();

        try{
            StringBuilder sb = new StringBuilder();
            for(ProductDetails productDetails: returnProductsDataFromCsvFile()){
                if (sku.equals(productDetails.getSku())){
                    subtotal += productDetails.getPrice();

                    product.add(productDetails.getProductName());

                    System.out.println(productDetails.getProductName() + " added to your cart.\n");
                    System.out.println("Your Shopping Cart");
                    System.out.println("Qty | Product Name | Price | Subtotal");

                    sb.append(num).append(" | ");
                    sb.append(productDetails.getProductName()).append(" | ");
                    sb.append(productDetails.getPrice()).append(" | ");
                    sb.append(subtotal).append("\n");
                }
            }
            System.out.println(sb.toString());
            total.append("Total: ").append(subtotal);
            System.out.println(total.toString());

            userFinalDecision(subtotal, product);

        }
        catch(Exception e){
        }


    }

    public static void userFinalDecision(double subtotal, ArrayList productArrayList){
        System.out.println("\n1) Remove Item");
        System.out.println("2) Check Out");
        System.out.println("3) Back to Home");

        System.out.println("Choose an option: ");
        int userChoice = scanner.nextInt();

        switch(userChoice){
            case 1:
                break;
            case 2:
                System.out.println("Check Out");
                System.out.println("Total Amount: "+ subtotal);
                System.out.println("Enter your payment amount: $");
                double userPaymentAmount = scanner.nextDouble();
                System.out.println("Payment Accepted\n");


                System.out.println("SALES RECEIPT");
                LocalDateTime todayDateAndTime = LocalDateTime.now();
                System.out.println("Order Date: " + LocalDate.now()
                        + todayDateAndTime.getHour()
                        + " : "+ todayDateAndTime.getMinute());

                try{
                    for(ProductDetails product : returnProductsDataFromCsvFile()){
                        int i =1;
                        if(productArrayList.contains(product)){
                            System.out.println(i + " " + product + "@ " + product.getPrice());
                        }
                    }
                }
                catch (Exception e){}

                System.out.println("Total: " + subtotal);
                System.out.println("Paid: " + userPaymentAmount);
                double change = userPaymentAmount - subtotal;
                System.out.printf("Change: %.2f", change);
                System.out.println("\nThank you for shopping!");

        }
    }
}


