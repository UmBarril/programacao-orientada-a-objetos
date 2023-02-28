public class Fib { // implementações do algorítmo de fibonacci (sendo que a posição "n" começa no 0)
    public static void main(String[] args) {
        final int QUANTIDADE_DE_ELEMENTOS_DA_SERIE = 20;
        // fib1
        for(int i = 0; i < QUANTIDADE_DE_ELEMENTOS_DA_SERIE; i++) {
            System.out.printf("fib1(%d) = %d\n", i, fib1(i));
        }
        System.out.println();

        // fib2
        for(int i = 0; i < QUANTIDADE_DE_ELEMENTOS_DA_SERIE; i++) {
            System.out.printf("fib2(%d) = %d\n", i, fib2(i));
        }
        System.out.println();
        
        // fib3
        for(int i = 0; i < QUANTIDADE_DE_ELEMENTOS_DA_SERIE; i++) {
            System.out.printf("fib3(%d) = %d\n", i, fib3(i));
        }
    }

    // Recursivo
    public static int fib1(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    // Laço for
    public static int fib2(int n) {
        int a = 0, b = 1, temp;
        for(int i = 0; i < n; i++) {
            temp = a;
            a = b;
            b += temp;
        }
        return a;
    }

    // Laço while
    public static int fib3(int n) {
        int a = 0, b = 1, temp;
        int i = 0;
        while(i < n) {
            temp = a;
            a = b;
            b += temp;
            i++;
        }
        return a;
    }
}

