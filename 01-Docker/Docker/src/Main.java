public class Main {
    public static void main(String[] args) throws InterruptedException {
        int cnt = 0;

        while (true) {
            System.out.println("Hello world from Docker! Iteration: " + cnt++);
            Thread.sleep(1000);
        }
    }
}