package a404_notfound.sourceappwater.model;

/**
 * Created by Michelle on 2/27/2017.
 */

public enum WaterType {
    BOTTLED("Bottled"),
    LAKE("Lake"),
    RIVER("River"),
    STREAM("Stream"),
    WELL("Well");

    private final String rep;

    // Constructor
    WaterType(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public String toString() {
        return rep;
    }
}
