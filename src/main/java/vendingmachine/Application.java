package vendingmachine;

public class Application {
    public static void main(String[] args) {
        Message message = new Message();
        User user = new User();
        Inventory inventory = new Inventory();
        Products products = new Products();

        message.printInputHolding();
        int holding = user.inputHolding();
        inventory.makeCoins(holding);

        message.printInputProducts();
        String[] productsList = user.inputProducts();
        products.addProducts(productsList);

        while (true) {
            message.printInputAmount();
            int amount = user.inputAmount();
            Change change = new Change(amount);
            message.printChanges(change.getAmount());
            if (change.getAmount() < products.getMaxPrice()) {
                message.printLackOfChanges();
                break;
            }
        }

    }
}
