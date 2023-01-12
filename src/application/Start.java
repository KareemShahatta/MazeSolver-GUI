package application;

import assets.maze.MazeManager;
import assets.screen.ScreenManager;
import assets.settings.SettingsManager;
import assets.settings.ThemeManager;
import assets.sound.SoundManager;
import input.KeyboardInput;
import input.MouseInput;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static assets.image.Images.ICON;
import static assets.screen.CustomScreenID.MENU;
import static assets.screen.CustomScreenID.START;

/**
 * Main class for the entire program
 * Contains instances for all the manager classes that can be used in the application
 * */
public class Start extends Application
{
    //Final instances to be sent and used by other classes if needed
    private final SettingsManager settingsManager = new SettingsManager();
    private final ThemeManager themeManager = new ThemeManager(this);
    private final ScreenManager screenManager = new ScreenManager(this);
    private final SoundManager soundManager = new SoundManager(this);
    private final MouseInput mouseInput = new MouseInput(this);
    private final MazeManager mazeManager = new MazeManager(this);

    //Field for changing details to the scene or stage
    private Scene scene;
    private Stage stage;

    //Starting point of application
    @Override public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene(new Pane() , 500 , 500);
        scene.setOnKeyPressed(new KeyboardInput(this));

        stage.setOnHiding(event -> Platform.runLater(() -> System.exit(0))); //Terminating the application once the window is closed
        stage.getIcons().add(ICON);
        stage.setTitle("CSC 240 Maze Solver");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        screenManager.setScreen(START);
    }
    public static void main(String[] args)
    {
        launch(args);
    }

    //Helper methods for getting instances for different manager classes
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
    public ThemeManager getThemeManager() {
        return themeManager;
    }
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    public SoundManager getSoundManager() {
        return soundManager;
    }
    public MouseInput getMouseInput() {
        return mouseInput;
    }
    public MazeManager getMazeManager() {
        return mazeManager;
    }
    public Scene getScene() {
        return scene;
    }
    public Stage getStage(){return stage;}
}
