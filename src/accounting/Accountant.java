package accounting;

public class Accountant {
    private String username;
    private String password;

    public Accountant(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addMemberToList() {
        System.out.println("Add member to list");
    }

}
