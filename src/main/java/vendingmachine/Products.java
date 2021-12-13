package vendingmachine;

import java.util.ArrayList;

public class Products {
    private ArrayList<Product> productsList = new ArrayList<>();

    public void addProducts(String[] products) {
        for (String product : products) {
            String removedBrackets = product.substring(1, product.length() - 1);
            String[] divided = removedBrackets.split(",");
            String name = divided[0];
            int price = Integer.parseInt(divided[1]);
            int number = Integer.parseInt(divided[2]);
            productsList.add(new Product(name, price, number));
        }
    }
}
