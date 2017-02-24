package a404_notfound.sourceappwater.model;

/**
 * Created by Lase on 2/12/2017.
 *
 * A Manager for the model.
 */

public class Manager extends Worker {
    public Manager(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Manager";
    }
}
