import Animals.Animal;
import Controller.Registry;
import Model.Commands;
import Model.PetStorage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class View {
    PetStorage<Animal> storage;
    Registry registry;
    public View(PetStorage<Animal> storage, Registry registry) {
        this.storage = storage;
        this.registry = registry;
    }

    public void StartButton(){
        int select;
        System.out.println("Welcome to Pet Registry!");
        while (true) {
            try {
                select = promt("Select action:\n1)Add pet to registry\n2)Select pet\n3)Exit");
                if (select < 1 || select > 3){
                    System.out.println("Try again!");
                }
                else {
                    switch (select) {
                        case 1:
                            int n = 0;
                            try {
                                n = promt("Choose animal!\n1)Dog\n2)Cat\n3)Hamster\n4)Horse\n5)Camel\n6)Donkey\n7)Back\n");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            while (true) {
                                if (n > 0 && n < 7) {
                                    try {
                                        while (true) {
                                            String name = input("Enter name: ");
                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                            String birthDate = input("Enter birthdate: ");
                                            if (birthDate == null || !birthDate.matches("\\d{4}-[01]\\d-[0-3]\\d")){
                                                System.out.println("Not valid date format!\n");
                                            }
                                            else {
                                                format.parse(birthDate);
                                                registry.addPet(name, birthDate, n);
                                                break;
                                            }
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Not valid date format!\n");
                                    }catch (IOException ie){
                                        System.out.println(ie.getMessage());
                                    }
                                    break;
                                }
                                else if (n == 7) break;
                                else System.out.println("Try again!\n");
                            }
                            break;
                        case 2:
                            if (storage.isEmpty()) System.out.println("Registry is empty!\n");
                            else {
                                for (Animal el : storage) {
                                    System.out.println(el);
                                }
                                try {
                                    select = 0;
                                    select = promt("Select pet id in registry: ");

                                    while (true) {
                                        try {
                                            if (storage.contains(storage.get(select))) {
                                                while (true) {
                                                    n = 0;
                                                    n = promt("Select action:\n1)Show commands list\n2)Add new command to selected pet\n3)Back\n");
                                                    if (n > 0 && n < 4) break;
                                                    else System.out.println("Try again!");
                                                }
                                                if (n == 1) storage.get(select).showCommands();
                                                else if (n == 2) {
                                                    while (true) {
                                                        n = promt("Select command:\n1)" + Commands.VOICE + "\n2)" + Commands.SIT + "\n3)"
                                                                + Commands.LAY + "\n4)" + Commands.GALLOP + "\n5)Back");
                                                        if (n > 0 && n < 5) {
                                                            storage.get(select).addCommand(n);
                                                            break;
                                                        } else if (n == 5) break;

                                                        else System.out.println("Try again!");
                                                    }
                                                }
                                                break;
                                            } else System.out.println("Pet not found!");
                                        }catch (IndexOutOfBoundsException e){
                                            System.out.println("Pet with index: " + select + " not found!\n");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                            break;
                        case 3:
                            System.exit(1);
                        default:
                            throw new IllegalStateException("Unexpected value: " + select);
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    private int promt(String message) throws IOException{
        InputStream inputStream = System.in;
        Reader inputStramReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStramReader);
        System.out.println(message);
        String num = bufferedReader.readLine();
        return Integer.parseInt(num);
    }

    private String input(String message) throws IOException{
        InputStream inputStream = System.in;
        Reader inputStramReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStramReader);
        System.out.println(message);
        return bufferedReader.readLine();
    }
}
