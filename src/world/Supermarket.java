package world;

import lib.Pair;

import java.util.*;

public class Supermarket extends Store {
    // Constructor, calls super on Store constructor
    public Supermarket(String location, String company) {
        super(location, company);
    }
    // Need to create our list of supplies as well
    // protected HashMap<String, Integer> supplies = new HashMap<>();

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        // Creates an array list the size of the HashMap that contains the products
        //int number = this.supplies.size();
        ArrayList<Pair<String, Integer>> productList = new ArrayList<>();
        /* YOUR CODE HERE */
        Set<String> keys = this.supplies.keySet();
        for (String key : this.supplies.keySet()) {
            // build new pair, append that pair to the end of the list, then at the end, return this list
            productList.add(new Pair<>(key, this.supplies.get(key)));
        }
        return productList;
    }

    /* YOUR CODE HERE */
    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        Collection<Pair<String, Integer>> returnOrder = new ArrayList<Pair<String, Integer>>();
        // you can append a new node (pair) for each item purchased
            for (Pair<String, Integer> o : order){
                if (this.supplies.containsKey(o.left)){
                    if (o.right>=this.supplies.get(o.left)){
                        // if the value of the purchase is greater than what is in stock, return all of what is in stock
                        returnOrder.add(new Pair<>(o.left, this.supplies.get(o.left)));
                        // so this creates a new pair, it doesn't reuse the old one, then append this pair to end of new collection list that I created.
                        // Then set what's left in the supplies set to 0
                        this.supplies.put(o.left, 0);
                    } else { // inventory is larger than order
                        returnOrder.add(new Pair<>(o.left, o.right));
                        this.supplies.put(o.left, this.supplies.get(o.left)-o.right);

                    }
                }

            }
        return returnOrder;

    }
}
