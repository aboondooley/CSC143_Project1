package world;

import lib.Pair;

import java.util.*;

public class Supermarket extends Store {
    // Constructor, calls super on Store constructor
    public Supermarket(String location, String company) {
        super(location, company);
    }


    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        // Creates a blank list for us to return to the client
        ArrayList<Pair<String, Integer>> productList = new ArrayList<>();
        // Create a set with all the keys in supplies so we can iterate over
        // keySet() is iterable
        for (String key : this.supplies.keySet()) {
            // Build new pair, append that pair that matches the pair from the supplies
            productList.add(new Pair<>(key, this.supplies.get(key)));
        }
        // Return the list of products in store
        return productList;
    }

    /* YOUR CODE HERE */
    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        // Create a new list to return, don't modify the list that is the parameter
        Collection<Pair<String, Integer>> returnOrder = new ArrayList<>();
        // You can append a new node (pair) for each item purchased
        // For each item in the order, create a pair that matches
            for (Pair<String, Integer> o : order){
                if (this.supplies.containsKey(o.left)){
                    if (o.right>=this.supplies.get(o.left)){
                        // If the number requested in the order is greater than what is in stock, add all of what is in stock
                        returnOrder.add(new Pair<>(o.left, this.supplies.get(o.left)));
                        // Then set what's left in the supplies set to 0
                        this.supplies.put(o.left, 0);
                    } else { // If the number in inventory is larger than the number requested
                        //  Add a pair with the exact number requested
                        returnOrder.add(new Pair<>(o.left, o.right));
                        // Subtract the number ordered from the inventory
                        this.supplies.put(o.left, this.supplies.get(o.left)-o.right);

                    }
                }

            }
        return returnOrder;

    }
}
