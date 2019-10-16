package world;

import lib.Pair;

import java.util.*;

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
        return null;
    }

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        // Keeps a list of how many servings of each recipe can be made
        ArrayList<Pair<String, Integer>> recipeInventory = new ArrayList<>();
        // Loop through each recipe
       for(String r : this.recipes.keySet()){
           // In each recipe, loop through each ingredient
           // Save a counter for the lowest denominator
           for (Pair<String, Integer> i : recipes.get(r)){
               int amount = 0;
               // Need to see how many servings can be made of each ingredient in the supply room
               //Pair<String, Integer> currentRecipe = new Pair<String, Integer>(r, this.recipes.get(i));
               int quantity = i.right/ this.supplies.get(i.left);
               //Integer inventory = Integer.valueOf(this.supplies.get(i.left));
               if (quantity < amount){
                   amount = quantity;
               }
               //Integer necessary = Integer.valueOf(this.recipes.get(i.left));
           }
// QUESTION HERE
           recipeInventory.add(new Pair<>(r, amount));
       }
        return recipeInventory;

    }

    public void learnRecipe(String name, Collection<Pair<String, Integer>> ingredients) {
        recipes.put(name, ingredients);
    }

    /* YOUR CODE HERE */
}
