package maze;

public class Node {


    Tree parent;

    Node(Tree parent) {

        this.parent = parent;
    }
    Node() {
        //this.id = id;
        this.parent = null;
    }

}
