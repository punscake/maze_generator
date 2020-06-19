package maze;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveLoadMaze {

    public static void saveMaze(int[][] maze) {

        File savedMaze = new File("savedMaze.txt");

        try (PrintWriter writer = new PrintWriter(savedMaze)) {

        } catch (IOException e) {

        }


    }
}
