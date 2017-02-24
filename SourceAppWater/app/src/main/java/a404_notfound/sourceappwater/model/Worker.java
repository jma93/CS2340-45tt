package a404_notfound.sourceappwater.model;

/**
 * Created by Lase on 2/12/2017.
 *
 * A worker in the model.
 */

public class Worker extends RegisteredUser {
    public Worker(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Worker";
    }
}
