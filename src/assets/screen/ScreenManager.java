package assets.screen;

import assets.screen.screens.*;
import assets.sound.CustomSoundID;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import application.Start;

import static assets.screen.CustomScreenID.*;
/**
 * Main class for managing screen
 * Responsible for switching from on screen to another
 * */
public class ScreenManager
{
    //Reference to the main application class
    private Start start;
    //boolean to show if the Solve screen is active or not
    private boolean isSolveScreenActive = false;

    public ScreenManager(Start start)
    {
        this.start = start;
    }

    //Public methods available for changing the screen
    //NOTE: For Solve we are updating the value before creating the screen or else it won't work
    public void setScreen(CustomScreenID screenID) {
        switch (screenID)
        {
            case START : updateScreen(screenID, new StartScreen(start).getPane()); break;
            case MENU : updateScreen(screenID, new MenuScreen(start).getPane()); break;
            case SOLVE : isSolveScreenActive = true; updateScreen(screenID, new SolveScreen(start).getPane()); break;
            case VIEW : updateScreen(screenID, new ViewScreen(start).getPane()); break;
            case SETTING: updateScreen(screenID, new SettingsScreen(start).getPane()); break;
        }
    }

    //Helper methods for switching screens and playing any special effect while doing so
    //NOTE: Handles stopping the animation for the solve screen
    private void updateScreen(CustomScreenID id, Pane pane) {
        start.getScene().setRoot(pane);
        isSolveScreenActive = id.equals(SOLVE);

        if(id.equals(START))
        {
            start.getSoundManager().playSound(CustomSoundID.START);
            Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(2.2), event -> setScreen(MENU)));
            loadingDelay.play();
        }
    }
    public boolean isSolveScreenActive(){return isSolveScreenActive;}
}
