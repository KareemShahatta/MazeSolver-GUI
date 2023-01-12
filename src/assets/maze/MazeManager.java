package assets.maze;

import application.Start;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class for managing the maze file and preparing it
 * Takes care of loading the file with the maze and verifying it
 * Create a double array char of with the maze in it
 * */
public class MazeManager
{
    //Reference to the main application class
    private final Start start;

    //Field for getting/changing information related to the maze
    private char[][] maze;
    private String mazeFileName;
    private boolean valid = false;

    /**
     * Main constructor for MazeManager
     * @param start reference to the Start class
     * */
    public MazeManager(Start start)
    {
        this.start = start;
    }

    //Methods loading the maze file from windows and printing it in a .txt format
    public int loadMazeFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(start.getStage());
        try
        {
            String fileExtension = file.getName().substring(file.getName().length() - 4);
            if(fileExtension.equals(".txt"))
            {
                start.getMazeManager().loadMaze(file);
                mazeFileName = file.getName();
                valid = true;
                return 0;
            }
            else
            {
                valid = false;
                return 1;
            }
        }
        catch (NullPointerException error)
        {
            valid = false;
            return 2;
        }
    }
    public void printMazeFile() {
        StringBuilder buffer = new StringBuilder();

        for (char[] chars : maze)
        {
            for (char aChar : chars)
            {
                buffer.append(aChar);
            }

            buffer.append(System.lineSeparator());
        }

        System.out.println(buffer);
    }

    //Methods creating the maze double array list and loading the data in it
    public void loadMaze(File file) {
        try
        {
            initializeMaze(file);
            Scanner scanner = new Scanner(file);
            for(int r = 0 ; r < maze.length ; r++)
            {
                char[] chars = scanner.nextLine().toCharArray();

                for(int c = 0 ; c < maze[r].length ; c++)
                {
                    maze[r][c] = chars[c];
                }
            }

            scanner.close();

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }
    private void initializeMaze(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int box = 0;
        do
        {
            box++;
            scanner.nextLine();
        }
        while(scanner.hasNextLine());

        maze = new char[box][box];

        scanner.close();


    }

    ////Helper methods for getting information about the maze, validity, filename.
    public char[][] getMaze(){return maze;}
    public boolean isFileValid(){return valid;}
    public String getMazeFileName(){return mazeFileName;}

    ////Helper methods for creating a copy of the maze to be passed in the MazeSolver.
    //NOTE: We can't use the same maze reference that we have because it will edit that maze in order to solve it
    public char[][] getMazeCopy() {
        char[][] copyGrid = new char[maze.length][];
        for(int r = 0 ; r < maze.length; r++)
        {
            copyGrid[r] = new char[maze[r].length];

            for(int c = 0 ; c < maze[r].length ; c++)
            {
                copyGrid[r][c] = maze[r][c];
            }
        }
        return copyGrid;
    }
}
