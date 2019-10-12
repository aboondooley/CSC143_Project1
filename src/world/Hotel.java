package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Hotel extends Building implements Residential, Rentable, Business {
    String company;

    /* New to HashSet?
        Think of HashSet as a collection without duplicates.
        You can add items, remove items, and see if the set contains items.
        You don't have to use HashSet, but I recommend it.

        Check out the docs here:
        https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html
     */
    HashSet<String> rentals = new HashSet<>();
    HashSet<String> occupants = new HashSet<>();

    public Hotel(String location, String company) {
        super(location);
        this.company = company;
    }

    /* YOUR CODE HERE */
}
