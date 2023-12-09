package Controller;

import Animals.Animal;
import Animals.Inheritors.*;
import Model.PetStorage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Registry {
    PetStorage<Animal> storage;

    public Registry(PetStorage<Animal> storage) {
        this.storage = storage;
    }

    public void addPet(String name, String birthDate, int var){
        switch (var){
            case 1:
                storage.add(new Dog(name,birthDate));
                break;
            case 2:
                storage.add(new Cat(name,birthDate));
                break;
            case 3:
                storage.add(new Hamster(name,birthDate));
                break;
            case 4:
                storage.add(new Horse(name,birthDate));
                break;
            case 5:
                storage.add(new Camel(name,birthDate));
                break;
            case 6:
                storage.add(new Donkey(name,birthDate));
                break;
            default: break;
        }
    }

}
