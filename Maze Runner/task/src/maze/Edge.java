package maze;

public class Edge {

    Node from;
    Node to;
    boolean directional;
    int weight;
    int defaultTreeSize = 10;
    int x = 0;
    int y = 0;

    Edge(Node from, Node to, int weight, boolean directional) {
        this.directional = directional;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    Edge(Node from, Node to, boolean directional) {
        this.directional = directional;
        this.from = from;
        this.to = to;
        this.weight = 0;
    }
    Edge(Node from, Node to, int weight) {
        this.directional = false;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    Edge(Node from, Node to) {
        this.directional = false;
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    void formTree() {
        if (to.parent == null && from.parent == null) {
            Tree tree = new Tree(from, defaultTreeSize);
            tree.addNode(this);
        } else if (from.parent != null) {
            from.parent.mainTree.addNode(this);
        } else if (to.parent != null && !directional) {
            to.parent.mainTree.addNode(this);
        }
    }
}
