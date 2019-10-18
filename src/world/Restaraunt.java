package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static java.util.Collections.min;
//import static java.util.Collections;

public class Restaraunt extends Store {
    public HashMap<String, Collection<Pair<String, Integer>>> recipes = new HashMap<>();
    public Restaraunt(String location, String company) {
        super(location, company);
    }

    public static void main(String args[]){
        Restaraunt pizzaPlace = new Restaraunt("Inner Campus Drive", "Safeway");
        pizzaPlace.learnRecipe("Pizza", Arrays.asList(new Pair<>("crust", 1), new Pair<>("sauce", 1)));
        pizzaPlace.supply(Arrays.asList(new Pair<>("sauce", 5), new Pair<>("crust", 5)));

        Collection<Pair<String, Integer>> result = pizzaPlace.getProducts();
    }
    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
       ArrayList<Pair<String, Integer>> returnOrder = new ArrayList<>();
        // for each recipe in the order. burger(String), 5 (int)
       for (Pair<String, Integer> p : order) {
           Collection<Pair<String, Integer>> currentStock = this.getProducts();
           // Fill is the number of orders we need to fill
           int fill = min(currentStock.get(p.left), p.right);
           // For each ingredient, multiply fill by the number needed in the recipe,
           // then subtract this number from the number in supply
           for (Pair<String, Integer> i : recipes.get(p.right)) {
                   returnOrder.add(new Pair<>(p.left, fill));
                       this.supplies.put(i.left, i.right-(i.right*fill));
           }
       }

        return returnOrder;
    }

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        // Keeps a list of how many servings of each recipe can be made
        ArrayList<Pair<String, Integer>> recipeInventory = new ArrayList<>();
        // Loop through each recipe
       for(String r : this.recipes.keySet()){
           // In each recipe, loop through each ingredient
           // Save a counter for the lowest denominator
           // Collections with an S!!

           int totalAmount = max(this.supplies.values());
           for (Pair<String, Integer> i : recipes.get(r)){
               int currentAmount = this.supplies.get(i.left);
               // Need to see how many servings can be made of each ingredient in the supply room
               //Pair<String, Integer> currentRecipe = new Pair<String, Integer>(r, this.recipes.get(i));
               int quantity = this.supplies.get(i.left) / i.right;
               //Integer inventory = Integer.valueOf(this.supplies.get(i.left));
               if (quantity < totalAmount){
                   totalAmount = quantity;
               }
           }
           recipeInventory.add(new Pair<String, Integer>(r, totalAmount));
       }
        return recipeInventory;

    }

    public void learnRecipe(String name, Collection<Pair<String, Integer>> ingredients) {
        recipes.put(name, ingredients);
    }

    /* YOUR CODE HERE */

}
