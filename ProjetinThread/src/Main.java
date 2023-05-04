class Executavel implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("thread1");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) { }
        }
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Executavel());
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("thread2");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) { }
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("thread3");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) { }
            }
        });
        // thread1.run(); // <-- faz rodar na thread principal
        thread1.start();
        thread2.start();
        thread3.start();
    }
}