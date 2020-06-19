package maze;

import java.util.Arrays;
import java.util.Random;

public class MazeGen {

    public static int[][] generateMaze(int heightInput, int widthInput) {

        Random rng = new Random();

        int height = (int)(Math.ceil((heightInput - 2) / 2.0));
        int width = (int)(Math.ceil((widthInput - 2) / 2.0));

        Node[] rawNodes = new Node[height * width];

        for (int i = 0; i < rawNodes.length; i++){
            rawNodes[i] = new Node();
        }

        Edge[] rawEdges = new Edge[(height - 1) * width + (width - 1) * height];
        int edgeId = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j < width - 1) {
                    rawEdges[edgeId] = new Edge(rawNodes[i * width + j],rawNodes[i * width + j + 1],rng.nextInt(10) + 1);
                    rawEdges[edgeId].x = 2 * j + 2;
                    rawEdges[edgeId].y = 2 * i + 1;
                    edgeId++;
                }
                if (i < height - 1) {
                    rawEdges[edgeId] = new Edge(rawNodes[i * width + j],rawNodes[(i + 1) * width + j],rng.nextInt(10) + 1);
                    rawEdges[edgeId].x = 2 * j + 1;
                    rawEdges[edgeId].y = 2 * i + 2;
                    edgeId++;
                }
            }
        }

        Arrays.sort(rawEdges, new EdgeComparator());

        for (int i = 0; i < rawEdges.length; i++){
            rawEdges[i].formTree();
        }

        int[][] mazeArray = new int[heightInput][widthInput];



        //generate swiss cheese template
        for (int i = 0; i < heightInput; i++) {
            for (int j = 0; j < widthInput; j++) {
                mazeArray[i][j] = 1;
            }
        }
        for (int i = 1; i < heightInput - 1; i += 2) {
            for (int j = 1; j < widthInput - 1; j += 2) {
                mazeArray[i][j] = 0;
            }
        }

        //generate holes where the edges are
        if (rawNodes[0].parent != null) {

            Edge[] treeEdges = rawNodes[0].parent.edges;

            for (Edge n : treeEdges) {
                if (n != null)
                    mazeArray[n.y][n.x] = 0;
            }
        }

        //generate entrance
        mazeArray[rng.nextInt(height) * 2 + 1][0] = 0;

        //generate exit
        int exitCoordinate = rng.nextInt(height) * 2 + 1;
        mazeArray[exitCoordinate][widthInput - 1] = 0;
        if (widthInput % 2 == 0)
            mazeArray[exitCoordinate][widthInput - 2] = 0;

        return mazeArray;
    }
}
