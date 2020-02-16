public class TürmeHanoi {

    public static void tower(int n, char start, char end, char aux){

        if(n == 1){
            System.out.println("Bewege die Scheibe 1 von " + start + " nach " + end);
            return;
        }
        tower(n - 1, start, aux, end);
        System.out.println("Bewege Scheibe " + n + " von " + start + " nach " + end);
        tower(n-1, aux, end, start);
    }

    public static void main(String[] args){

        tower(10, 'A', 'C', 'B');
    }
}
