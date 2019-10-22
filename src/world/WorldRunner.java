package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class WorldRunner {
    /*
    Welcome to the WorldRunner sandbox! Here's a place where you can test your little world
    and imagine yourself in it. Ideally, you should work here only when you're passing all tests.

    Completion of these programming challenges is optional and will not be graded.
    However, there is a 99% chance that a similar problem will appear on an upcoming exam,
    so, maybe give them a try?

    "Never tell me the odds." --You 'Han Solo' the Student

    Hint: instanceOf may prove useful.
     */
    public static void main(String args[]) {
        /* Challenges:
           1. - Create a virtual "street" of different Properties
           2. - Print out all company names for all the businesses
           3. - Buy out all the products from all the stores
           4. - Buy something, move in somewhere, buy something else, move in somewhere
           5. - Create a supply chain of businesses where one business supplies another
         */

        /* YOUR CODE HERE */
        // 1.
        // Create a street with buildings
        Store costco = new Supermarket("312 James St", "Costco");
        Restaraunt rositas = new Restaraunt ("313 James St", "Rosita's");
        Home cheMoi = new Home("314 James St");
        Hotel marriot = new Hotel("315 James St", "Marriot");
        // Create an array list
        ArrayList street = new ArrayList<Building>();
        street.add(costco);
        street.add(rositas);
        street.add(cheMoi);
        street.add(marriot);

        // 2.
        // Print out all the company names
        for (Object b : street){
            if (b instanceof Business){
                System.out.println(((Business) b).getCompany());
            }
        }

        // 3.
        // Buy all products from all of the stores
        // First supply the stores with products
        costco.supply(Arrays.asList(
                new Pair<>("cereal", 5),
                new Pair<>("tomato", 12),
                new Pair<>("lemon-aid", 3),
                new Pair<>("cake", 10)));

        // Restaraunt needs to learn recipes first
        Collection<Pair<String, Integer>> appIngredients = Arrays.asList(
                new Pair<>("chips", 2),
                new Pair<>("avocado", 1),
                new Pair<>("tomato", 1));

        Collection<Pair<String, Integer>> drinkIngredients = Arrays.asList(
                new Pair<>("ice", 1),
                new Pair<>("tequila", 1),
                new Pair<>("lime", 2));

        Collection<Pair<String, Integer>> mainIngredients = Arrays.asList(
                new Pair<>("onion", 1),
                new Pair<>("peppers", 2),
                new Pair<>("chicken", 2),
                new Pair<>("tortillas", 3));

        Collection<Pair<String, Integer>> dessertIngredients = Arrays.asList(
                new Pair<>("cream", 1),
                new Pair<>("caramel", 1),
                new Pair<>("egg", 1));
        rositas.learnRecipe("chips and guac", appIngredients);
        rositas.learnRecipe("margarita", drinkIngredients);
        rositas.learnRecipe("chicken fajitas", mainIngredients);
        rositas.learnRecipe("flan", dessertIngredients);
        // Supply the store
        rositas.supply(Arrays.asList(
                // 2 appetizers
                new Pair<>("chips", 4),
                new Pair<>("avocado", 2),
                new Pair<>("tomato", 2),
                // 4 drinks
                new Pair<>("ice", 4),
                new Pair<>("tequila", 4),
                new Pair<>("lime", 8),
                // 10 entrees
                new Pair<>("onion", 10),
                new Pair<>("peppers", 20),
                new Pair<>("chicken", 20),
                new Pair<>("tortillas", 30),
                // 4 desserts
                new Pair<>("cream", 4),
                new Pair<>("caramel", 4),
                new Pair<>("egg", 4)));

        // A list to catch the returned products
        Collection<Pair<String, Integer>> purchases = new ArrayList<>();
        for (Object b : street){
            if (b instanceof Store){
                Collection<Pair<String, Integer>> products = new ArrayList<>(((Store) b).getProducts());
                purchases.addAll(((Store) b).purchase(products));
            }
        }

        // 4.
        // Re-supply the stores and then then buy something, move in, and repeat
        costco.supply(Arrays.asList(
                new Pair<>("cereal", 5),
                new Pair<>("tomato", 12),
                new Pair<>("lemon-aid", 3),
                new Pair<>("cake", 10)));

        rositas.supply(Arrays.asList(
                // 2 appetizers
                new Pair<>("chips", 4),
                new Pair<>("avocado", 2),
                new Pair<>("tomato", 2),
                // 4 drinks
                new Pair<>("ice", 4),
                new Pair<>("tequila", 4),
                new Pair<>("lime", 8),
                // 10 entrees
                new Pair<>("onion", 10),
                new Pair<>("peppers", 20),
                new Pair<>("chicken", 20),
                new Pair<>("tortillas", 30),
                // 4 desserts
                new Pair<>("cream", 4),
                new Pair<>("caramel", 4),
                new Pair<>("egg", 4)));

        // Move in to a house
        cheMoi.moveIn("Alie");
        System.out.println(cheMoi.getOccupants());
        // Buy some groceries
        Collection<Pair<String, Integer>> groceries = new ArrayList<>(costco.purchase(Arrays.asList(
                new Pair<>("cereal", 1),
                new Pair<>("cake", 3))));
        // Go on a trip, get a hotel
        marriot.registerRental("Alie");
        marriot.moveIn("Alie");
        // Buy dinner
        Collection<Pair<String, Integer>> dinner = new ArrayList<>(rositas.purchase(Arrays.asList(
                new Pair<>("chips and guac", 1),
                new Pair<>("margarita", 2),
                new Pair<>("chicken fajitas", 2),
                new Pair<>("flan", 1))));
        // 5.
        // Supply chain from the Supermarket to the Restaraunt
        // First, supply the  supermarket with the ingredients for the recipes
        for (int i = 0; i < 3; ++i){
            costco.supply(appIngredients);
            costco.supply(mainIngredients);
            costco.supply(drinkIngredients);
            costco.supply(dessertIngredients);
        }
        // Now we create an order for the Restauarant to buy
        Collection<Pair<String, Integer>> order = new ArrayList<>();
        order.addAll(appIngredients);
        order.addAll(drinkIngredients);
        order.addAll(mainIngredients);
        order.addAll(dessertIngredients);
        order.addAll(mainIngredients);
        order.addAll(dessertIngredients);
        order.addAll(appIngredients);
// Now supply the restauarant with the igredients!
rositas.supply(order);




    }
}
