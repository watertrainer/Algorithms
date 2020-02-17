import java.util.Arrays;

public class Damenproblem {
    public static void main(String[] args){
        int n = 8;
        Dame[] damen = new Dame[n];
        for(int i = 0;i<n;i++){
            damen[i] = new Dame();
            damen[i].x = i;
            damen[i].y = i;
        }
        damenproblem(damen,0,n);
        System.out.println(count);
    }
    static int count = 0;
    public static void damenproblem(Dame[] damen,int row,int n){
        if(row >= n){
            count++;
            System.out.println("FINISH: "+Arrays.toString(damen));
            return;
        }
        for(int i = 0;i<n;i++){
            if(cert(damen,row,i%n)){
                damen[row].y = i;

                damenproblem(damen,row+1,n);
            }
        }
        damen[row].y = -1;
    }

    public static boolean cert(Dame[] damen, int row, int column){
        for(int i = row-1;i>=0;i--){
            if(damen[i].y == -1){

            }
            else if(row == damen[i].x){
                return false;
            }
            else if(column == damen[i].y){
                return false;
            }
            else if(Math.abs(row-damen[i].x) == Math.abs(column-damen[i].y)){
                return false;
            }
        }
        return true;
    }
}
