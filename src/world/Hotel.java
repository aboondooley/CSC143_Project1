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
    HashSet<String> rentals = new HashSet<String>();
    HashSet<String> occupants = new HashSet<>();

    public Hotel(String location, String company) {
        super(location);
        this.company = company;
    }

    @Override
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getCompany() {
        return this.company;
    }

    @Override
    public void registerRental(String occupant) {
            this.rentals.add(occupant);
    }

    @Override
    public void endRental(String occupant) {
            this.rentals.remove(occupant);
            this.occupants.remove(occupant);
    }

    @Override
    public void moveIn(String occupant) {
        if (this.rentals.contains(occupant)) {
            this.occupants.add(occupant);
        }
    }

    @Override
    public void moveOut(String occupant) {
        this.occupants.remove(occupant);
        this.rentals.remove(occupant);
    }

    @Override
    public Collection<String> getOccupants() {
        return this.occupants;
    }

}
