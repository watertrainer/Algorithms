import java.util.ArrayList;

public class Tiefenbreitensuche {

    public static void main(String[] args){

    }

    static ArrayList<Knoten> stack = new ArrayList<>();

    public static Knoten tiefenSuche(Knoten node, Knoten ziel){

        if (node == ziel)
            return node;
        else{
            stack.add(node);
            while(!stack.isEmpty()) {
                node.kinder.add(stack.get(stack.size() - 1));
                stack.remove(stack.size()-1);
                tiefenSuche(node, ziel);
            }
        }
    }
}
