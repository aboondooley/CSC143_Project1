package world;

public abstract class Building {
    // private field a string which indicates the location
    private String location;

    // protected constructor which means only children can initialize
    protected Building(String location) {
        this.location = location;
    }

    // public instance method which lets the client retrieve the location (but NOT set it)
    public String getLocation() {
        return location;
    }
}
