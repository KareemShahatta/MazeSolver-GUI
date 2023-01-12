package assets.screen.screens;

import application.Start;
import assets.maze.MazeMove;
import assets.maze.MazeSolver;
import assets.screen.CustomScreen;
import assets.sound.CustomSoundID;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static assets.image.Images.*;
/**
 * Solve screen to solve the maze
 * NOTE: In DisplayMaze() r = Rows, c = Columns, and it's different from DisplayMaze() in the ViewScreen
 * NOTE: The way we load the map is by getting every row and populating every column in that row
 * [] [] [] [] []
 * [] => [] [] [] []
 * [] [] [] [] []
 * [] [] [] [] []
 * */
public class SolveScreen extends CustomScreen
{
    //Reference to the main application class and the screen pane
    private final Start start;
    private final Pane solvePane;

    //Player node reference used when animating
    private ImageView player;
    //Array with the moves to allow up to use recursion for animating the player
    private final MazeMove[] moves;
    //field for the speed and trail of the player
    private final double animationSpeed;
    private final boolean isAnimationTrailed;

    public SolveScreen(Start start) {
        this.start = start;
        solvePane = new Pane();
        solvePane.setBackground(new Background(new BackgroundFill(start.getThemeManager().getMazeBackgroundColor() , null , null)));

        animationSpeed = 1 - (0.3*start.getSettingsManager().getSpeed());
        isAnimationTrailed = start.getSettingsManager().isTrailed();

        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze(start.getMazeManager().getMazeCopy());
        moves = mazeSolver.getSolution();

        displayMaze();
        animateMazeSolution(0, (int) player.getX(), (int) player.getY());
    }

    //Method for display the maze in the solve screen
    //NOTE: Might look similar to displayMaze() in View screen but this one links the player block to a player reference
    private void displayMaze() {
        char[][] maze = start.getMazeManager().getMaze();
        int cubeSize = 25;
        for(int r = 0; r <= 19 ; r++)
        {
            for(int c = 0; c <= 19 ; c++)
            {
                ImageView block = new ImageView();
                block.setFitWidth(cubeSize);
                block.setFitHeight(cubeSize);
                block.setX((cubeSize * c));
                block.setY(cubeSize * r);

                switch (maze[r][c])
                {
                    case 'S' : block.setImage(BOX_START); player = block; break;
                    case 'G' : block.setImage(BOX_GOAL); break;
                    case '#' : block.setImage(start.getThemeManager().getWallboxImage()); break;
                }

                solvePane.getChildren().add(block);
            }
        }

        player.toFront();
    }

    //Method for animating the player solving the maze and having a trail behind him
    private void updateTrail(int x , int y) {
        ImageView block = new ImageView();
        block.setX(x);
        block.setY(y);
        block.setImage(BOX_TRAIL);
        solvePane.getChildren().add(block);
        block.toBack();
    }
    private void animateMazeSolution(int move, int x , int y) {
       int locX = 0;
       int locY = 0;

        if(move < moves.length && start.getScreenManager().isSolveScreenActive())
        {
            //Size of every box used in the maze which is 25X25
            int cubeSize = 25;

            start.getSoundManager().playSound(CustomSoundID.WALK);
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(animationSpeed));
            transition.setNode(player);

            switch (moves[move])
            {
                case EAST: transition.setToX(player.getTranslateX() + cubeSize); locX = 25; break;
                case WEST: transition.setToX(player.getTranslateX() - cubeSize); locX = -25; break;
                case NORTH: transition.setToY(player.getTranslateY() - cubeSize); locY = -25; break;
                case SOUTH: transition.setToY(player.getTranslateY() + cubeSize); locY = 25; break;
            }

            if(isAnimationTrailed)
            {
                updateTrail(x, y);
            }

            transition.play();

            int newMove = move + 1;
            int newX = x + locX;
            int newY = y + locY;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(animationSpeed), event -> animateMazeSolution(newMove , newX , newY)));
            timeline.play();
        }
        else if(move >= moves.length)
        {
            start.getSoundManager().playSound(CustomSoundID.QUACK);
        }
    }

    @Override
    public Pane getPane()
    {
        return solvePane;
    }
}
