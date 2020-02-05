import java.util.ArrayList;

public class Tiefenbreitensuche {

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

        Knoten re = tiefenSuche(eins, sechs);
        System.out.println("Der Wert " + re.wert + " wurde zurückgegeben!");
    }

    static ArrayList<Knoten> stack = new ArrayList<>();

    public static Knoten tiefenSuche(Knoten node, Knoten ziel) {

        if (node == ziel) {

            System.out.println("Der Wert " + node.wert + " wurde gefunden!");
            return node;

        } else {

            stack.addAll(node.kinder);

            while (!stack.isEmpty()) {

                System.out.println("Der Wert " + node.wert + " wurde besucht!");
                node = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                return tiefenSuche(node, ziel);
            }
        }
        return node;
    }
}
