package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        displayProducts();
    }

    //--------------- Helping Method ------------------//

    public static ArrayList<ProductDetails> returnProductsData(){
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
        for(ProductDetails products: returnProductsData()){



        }
    }

}