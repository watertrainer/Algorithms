public class Fibonacci {

    public static int fib(int n) {

        if (n <= 2){

            return (n > 0) ? 1 : 0;
        }

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args){

        for(int i= 0; i < 30; i++)
            System.out.println(fib(i));
    }
}
