package accounting;

public class TestMember {
    private TestTypeOfSwimmer swimmer;
    private int age;


    public TestMember(TestTypeOfSwimmer swimmer, int age) {
        this.swimmer = swimmer;
        this.age = age;
    }

    public TestTypeOfSwimmer getSwimmer() {
        return swimmer;
    }

    public int getAge() {
        return age;
    }
}
