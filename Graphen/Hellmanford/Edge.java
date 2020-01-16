public class Edge {
    int capacity;
    Node start;
    Node end;

    public Edge(int capacity, Node start, Node end) {
        this.capacity = capacity;
        this.start = start;
        this.end = end;
    }

    public boolean canHoldCapacity(int flow){
        return capacity>=flow;
    }
}
