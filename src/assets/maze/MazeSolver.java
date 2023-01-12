package assets.maze;

import java.util.ArrayList;

import static assets.maze.MazeMove.*;
/**
 * Class for solving the maze
 * Takes a double char array which is the maze and solves it
 * Once the maze is solved you can access the solution
 * */
public class MazeSolver
{
    //Final char for knowing which block is the start block
    private final char START = 'S';

    //Field for getting/changing information related to the maze
    private char[][] maze;
    private ArrayList<MazeMove> mazePath;
    private int columns;
    private int rows;

    //Methods for solving the maze that is passed
    public boolean solveMaze(char[][] maze) {
        this.maze = maze;

        if(maze != null)
        {
            mazePath  = new ArrayList<>();
            int startX = 0;
            int startY = 0;

            columns = getColumns();
            rows = maze.length;

            for(int r = 0 ; r < maze.length; r++)
            {
                for(int c = 0 ; c < maze[r].length ; c++)
                {
                    if(maze[r][c] == START)
                    {
                        startX = c;
                        startY = r;
                    }
                }
            }

            if(findPath(startX , startY))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }


    }
    private boolean findPath(int x , int y) {
        if(( 0 <= x && x < columns) && ( 0 <= y && y < rows)) //Checks boundaries
        {
            char BLOCKED = '#';
            if(maze[y][x] == BLOCKED) //Check valid movements
            {
                return false;
            }
            else
            {
                char GOAL = 'G';
                if(maze[y][x] == GOAL) //Checks goal
                {
                    return true;
                }
                else
                {
                    char MAKRED = '+';
                    char OPEN = 'X';
                    if(maze[y][x] == OPEN || maze[y][x] == START || maze[y][x] != MAKRED) //Check movement
                    {
                        maze[y][x] = MAKRED;

                        if(findPath(x,y-1)) //North
                        {
                            mazePath.add(NORTH);
                            return true;
                        }
                        else if(findPath(x+1,y))  //East
                        {
                            mazePath.add(EAST);
                            return true;
                        }
                        else if(findPath(x,y+1)) //South
                        {
                            mazePath.add(SOUTH);
                            return true;
                        }

                        else if(findPath(x-1,y)) //West
                        {
                            mazePath.add(WEST);
                            return true;
                        }
                        else
                        {
                            char UNMARKED = 'x';
                            maze[y][x] = UNMARKED;
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            return false;
        }

    }
    private int getColumns() {
        int columns = 0;

        for(int r = 0 ; r < maze.length; r++)
        {
            if(maze[r].length > columns)
            {
                return columns = maze[r].length;
            }
        }

        return columns;
    }

    //Method for returning the solution moves to the maze
    public MazeMove[] getSolution() {
        if(maze != null)
        {
            MazeMove[] moves = new MazeMove[mazePath.size()];
            for(int i = 0 ; i < mazePath.size() ; i++)
            {
                moves[i] = mazePath.get((mazePath.size() - 1) - i);
            }
            return moves;
        }
        else
        {
            return null;
        }
    }
}
