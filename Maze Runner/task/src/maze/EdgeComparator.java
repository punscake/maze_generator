package maze;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge obj1, Edge obj2) {
        return obj1.weight - obj2.weight;
    }
}
