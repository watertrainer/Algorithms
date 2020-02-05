import java.util.ArrayList;

public class Knoten {

    ArrayList<Knoten> kinder = new ArrayList<>();

    int wert;

    public Knoten(int wert){
        this.wert = wert;
    }
}
