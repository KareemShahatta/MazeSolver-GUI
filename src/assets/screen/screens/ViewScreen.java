package assets.screen.screens;

import application.Start;
import assets.screen.CustomScreen;
import assets.sound.CustomSoundID;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;

import static assets.image.Images.BOX_GOAL;
import static assets.image.Images.BOX_START;
/**
 * View screen to view the maze without solving it\
 * NOTE: In DisplayMaze r = Rows, c = Columns
 * NOTE: The way we load the maze is by getting every row and populating every column in that row
 * [] [] [] [] []
 * [] => [] [] [] []
 * [] [] [] [] []
 * [] [] [] [] []
 * */
public class ViewScreen extends CustomScreen
{
    //Reference to the main application class and the screen pane
    private final Start start;
    private final Pane viewPane;

    public ViewScreen(Start start) {
        this.start = start;
        viewPane = new Pane();
        viewPane.setBackground(new Background(new BackgroundFill(start.getThemeManager().getMazeBackgroundColor() , null , null)));

        start.getSoundManager().playSound(CustomSoundID.VIEW);
        displayMaze();
    }

    @Override public Pane getPane() {
        return viewPane;
    }

    //Method for display the maze in the view screen
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
                    case 'S' : block.setImage(BOX_START); break;
                    case 'G' : block.setImage(BOX_GOAL); break;
                    case '#' : block.setImage(start.getThemeManager().getWallboxImage()); break;
                }

                viewPane.getChildren().add(block);
            }
        }
    }
}
