package world;

import lib.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import static java.util.Collections.max;
import static java.lang.Math.min;



public class Restaraunt extends Store {
    public HashMap<String, Collection<Pair<String, Integer>>> recipes = new HashMap<>();
    public Restaraunt(String location, String company) {
        super(location, company);
    }

    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        // Create list of purchased food to return
       ArrayList<Pair<String, Integer>> returnOrder = new ArrayList<>();
       for (Pair<String, Integer> o : order) {
           // For each recipe in the order, call getProducts, this will be updated to reflect the last ordered fulfilled.
           Collection<Pair<String, Integer>> currentStock = this.getProducts();
           int stockNum = 0;
           // Iterates through the recipes in getProducts to find the current recipe and returns the number that can be fulfilled
            for (Pair<String, Integer> c : currentStock){
                if (c.left == o.left){stockNum = c.right;}
            }
           // Fill is the number of orders we can fill
           int fill = min(stockNum, o.right);
            // Return the number of we can fill in our return list if the order can be filled
           if (fill > 0){
               returnOrder.add(new Pair<>(o.left, fill));
                for (Pair<String, Integer> i : recipes.get(o.left)) {
               // Then multiply fill by the number needed in the recipe and subtract from the number in supply
                    this.supplies.put(i.left, this.supplies.get(i.left) - (i.right * fill));
                }
           }
       }
        return returnOrder;
    }

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        // Keeps a list of how many servings of each recipe can be made
        ArrayList<Pair<String, Integer>> recipeInventory = new ArrayList<>();
        //Check through ingredients of the recipes, if supplies is not empty
        if (supplies.size()>0){
            for(String r : this.recipes.keySet()) {
                // In each recipe, loop through each ingredient
                // Save a counter for the lowest denominator, start it at the highest value of ingredients
                int totalAmount = max(this.supplies.values());
                for (Pair<String, Integer> i : recipes.get(r)) {
                    // Need to see how many servings can be made of each ingredient in the supply room
                    // Need to set amount to zero if the ingredient does not exist in this.supplies
                    int quantity = 0;
                    if (this.supplies.containsKey(i.left)) {
                        // Divide the amount in supplies by the number needed in the recipe
                        // Gives the number that can be created
                        quantity = this.supplies.get(i.left) / i.right;
                    }
                    if (quantity < totalAmount) {
                        // If the counter is bigger than the current number, update counter
                        totalAmount = quantity;
                    }
                }
                // Add the recipe and the number that can be made to the return list
                recipeInventory.add(new Pair<String, Integer>(r, totalAmount));
            }
       }
        return recipeInventory;
    }

    public void learnRecipe(String name, Collection<Pair<String, Integer>> ingredients) {
        recipes.put(name, ingredients);
    }

    /* YOUR CODE HERE */

}
