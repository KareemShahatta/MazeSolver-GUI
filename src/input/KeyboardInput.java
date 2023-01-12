package input;

import assets.screen.CustomScreenID;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import application.Start;
/**
 * Main class for handling keyboard events
 * */
public class KeyboardInput implements EventHandler<KeyEvent>
{
    //Reference to the main application class
    private final Start start;

    public KeyboardInput(Start start)
    {
        this.start = start;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE))
        {
            start.getScreenManager().setScreen(CustomScreenID.MENU);
        }
    }
}
