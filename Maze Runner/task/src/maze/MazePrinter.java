package maze;

public class MazePrinter {

    public static void printMaze(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 0) {
                    System.out.print("  ");
                } else if (maze[i][j] == 1) {
                    System.out.print("\u2588\u2588");
                }
            }
            System.out.println();
        }
    }
}
