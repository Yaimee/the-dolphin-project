package Trainer;
public class Performance {
    private String name;
    private int age;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private int ID;

    Performance(String name, int age, int minutes, int seconds, int milliseconds, int ID) {
        this.name = name;
        this.age = age;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getTime() {
        return ((double)minutes + (double)seconds/60 + (double)milliseconds/(60*1000));
    }
    public String timeFormatted() {return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);}
}
