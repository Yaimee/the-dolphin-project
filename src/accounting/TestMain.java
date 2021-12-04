package accounting;

public class TestMain {

    public void run() {

        TestMember member1 = new TestMember(TestTypeOfSwimmer.CASUAL, 60);
        Subscription subscription = new Subscription(member1);
    }

    public static void main(String[] args) {
        new TestMain().run();

    }
}
