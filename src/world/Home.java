package world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Home extends Building implements Residential {
    Collection<String> occupant;

    public Home(String location) {
        super(location);
        this.occupant = new ArrayList<>();
    }

    @Override
    public void moveIn(String occupant) {
        if (this.occupant.isEmpty()){
            this.occupant.add(occupant);
        }
    }

    @Override
    public void moveOut(String occupant) {
        if (!this.occupant.isEmpty()){
            this.occupant.remove(occupant);
        }
    }

    @Override
    public Collection<String> getOccupants() {
        return occupant;
    }

}
