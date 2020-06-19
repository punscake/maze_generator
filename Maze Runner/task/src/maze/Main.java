package maze;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int heightInput = scan.nextInt();
        if (heightInput < 3) heightInput = 3;
        int widthInput = scan.nextInt();
        if (widthInput < 3) widthInput = 3;


        int[][] currentMaze = MazeGen.generateMaze(heightInput, widthInput);

        MazePrinter.printMaze(currentMaze);




    }
}
