import Animals.Animal;
import Controller.Registry;
import Model.PetStorage;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PetStorage<Animal> storage = new PetStorage<>();
        Registry registry = new Registry(storage);
        View view = new View(storage, registry);
        view.StartButton();
    }
}