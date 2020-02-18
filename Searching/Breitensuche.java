import java.util.ArrayList;

public class Breitensuche {

    public static void main(String[] args) {

        Knoten eins = new Knoten(1);
        Knoten drei = new Knoten(3);
        Knoten zwei = new Knoten(2);
        Knoten sechs = new Knoten(6);
        Knoten vier = new Knoten(4);
        Knoten zehn = new Knoten(10);
        Knoten neun = new Knoten(9);
        Knoten acht = new Knoten(8);

        eins.kinder.add(zwei);
        eins.kinder.add(drei);
        zwei.kinder.add(sechs);
        drei.kinder.add(vier);
        drei.kinder.add(zehn);
        zehn.kinder.add(neun);
        zehn.kinder.add(acht);

        Knoten re = breitenSuche(eins, acht);
        System.out.println("Der Wert " + re.wert + " wurde zurückgegeben!");
    }

    static ArrayList<Knoten> queue = new ArrayList<>();

    public static Knoten breitenSuche(Knoten node, Knoten ziel){ //erste Node wird übergeben

        if (node == ziel){

            System.out.println("Der Wert " + node.wert + " wurde gefunden!");
            return node;

        } else{

            queue.addAll(node.kinder);

            while(!queue.isEmpty()){

                System.out.println("Der Wert " + node.wert + " wurde besucht!");
                node = queue.get(0); //erste Node der Warteschlange wird ausgewählt

                if(node == ziel){

                    System.out.println("Der Wert " + node.wert + " wurde gefunden!");
                    queue.removeAll(queue);
                    return node;

                } else {
                    queue.addAll(node.kinder);
                    queue.remove(node);
                }
            }
        }
        return node;
    }
}
