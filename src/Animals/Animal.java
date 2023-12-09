package Animals;

import Model.Commands;

import java.util.ArrayList;

public abstract class Animal {
    public static int count;
    private int id;
    private String name;
    private String birthDay;
    private ArrayList<Commands> commands = new ArrayList<>();

    public Animal(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
        this.id = count++;
    }

    public void addCommand(int var){
        Commands curVal = null;
        switch (var){
            case 1:
                curVal = Commands.VOICE;
                break;
            case 2:
                curVal = Commands.SIT;
                break;
            case 3:
                curVal = Commands.LAY;
                break;
            case 4:
                curVal = Commands.GALLOP;
                break;
            default: break;
        }
        if (commands.contains(curVal)) System.out.println("Already learned!");
        else commands.add(curVal);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", commands=" + commands +
                '}';
    }

    public int getId() {
        return id;
    }

    public void showCommands(){
        if (this.commands.isEmpty()) System.out.println("Pet does`t know any command!\n");
        else {
            for (Commands el : this.commands) {
                System.out.println(el);
            }
        }
    }

}
