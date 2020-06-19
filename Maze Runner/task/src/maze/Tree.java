package maze;

public class Tree {

    Node[] nodes;
    Edge[] edges;
    int currentSize;
    int maxSize;
    static int idGenerator = 0;
    private int id;
    Tree mainTree = this;


    Tree (Node root, int maxSize) {
        if (maxSize > 1) {
            nodes = new Node[maxSize];
            edges = new Edge[maxSize];
            this.maxSize = maxSize;
        } else {
            this.maxSize = 1;
            nodes = new Node[1];
            edges = new Edge[1];
        }

        currentSize = 1;
        id = idGenerator;
        idGenerator++;

        nodes[0] = root;
        root.parent = this;
    }

    boolean addNode(Edge edge) { //Edge has to connect the node to the tree
        Node node;
        if (edge.to.parent == this && edge.from.parent != this && !edge.directional) {
            node = edge.from;
        } else if (edge.from.parent == this && edge.to.parent != this) {
            node = edge.to;
        } else return false;


        if(this.isFull()) {
            maxSize++;
            Node[] newNodes = new Node[maxSize];
            for (int i = 0; i < nodes.length; i++) {
                newNodes[i] = nodes[i];
            }
            Edge[] newEdges = new Edge[maxSize];
            for (int i = 0; i < edges.length; i++) {
                newEdges[i] = edges[i];
            }
            nodes = newNodes;
            edges = newEdges;
        }


        if (node.parent == null) {
            nodes[currentSize] = node;
            edges[currentSize - 1] = edge;
            currentSize++;

            node.parent = this;
            return true;

        } else if(node.parent != this) {
            edges[currentSize - 1] = edge;
            maxSize += node.parent.currentSize;

            Node[] newNodes = new Node[maxSize];

            Tree toAbsorb = node.parent;
            int finalSize = currentSize + node.parent.currentSize;

            for (int i = 0; i < currentSize; i++) {
                newNodes[i] = nodes[i];
            }
            for (int i = 0; i < toAbsorb.currentSize; i++) {
                newNodes[i + currentSize] = toAbsorb.nodes[i];
                toAbsorb.nodes[i].parent = this;
            }

            Edge[] newEdges = new Edge[maxSize];
            for (int i = 0; i < currentSize; i++) {
                newEdges[i] = edges[i];
            }
            for (int i = 0; i < toAbsorb.currentSize - 1; i++) {
                newEdges[i + currentSize] = toAbsorb.edges[i];
            }

            currentSize = finalSize;
            nodes = newNodes;
            edges = newEdges;

            toAbsorb.mainTree = this;
        }
        return false;
    }

    void setId(int id) {
        if (id < idGenerator)
            this.id = id;

    }
    int getId() {
        return id;
    }

    boolean isFull(){
        if (currentSize == maxSize)
            return true;
        return false;
    }

    Node getRoot() {
        return nodes[0];
    }
}
