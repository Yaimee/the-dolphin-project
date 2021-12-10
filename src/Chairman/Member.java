package Chairman;


public class Member {
    private int ID;
    private int age;
    private String name;
    private String gender;
    private String email;
    //private Subscription sub;
    private TypeOfSwimmer swimmer;
    private Team team;

    public Member(int age, String name, String gender, String email, TypeOfSwimmer swimmer, Team team) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.swimmer = swimmer;
        this.team = team;
    }

    public TypeOfSwimmer getSwimmer() {
        return swimmer;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Team getTeam() {
        return team;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", swimmer=" + swimmer +
                ", team=" + team +
                '}';
    }

}
