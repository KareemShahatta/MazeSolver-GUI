package input;

import application.Start;
import assets.button.CustomButton;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;

import static assets.screen.CustomScreenID.*;
import static assets.sound.CustomSoundID.CLICK;
import static assets.sound.CustomSoundID.HOVER;
/**
 * Main class for handling keyboard events
 * */
public class MouseInput implements EventHandler<MouseEvent>
{
    //Reference to the main application class
    private final Start start;

    public MouseInput(Start start)
    {
        this.start = start;
    }

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
        {
            mouseClickEvent(event);
        }

        if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
        {
            mouseHoverEnterTargetEvent(event);
        }

        if(event.getEventType().equals(MouseEvent.MOUSE_EXITED))
        {
            mouseHoverExitTargetEvent(event);
        }
    }

    //Helper methods for managing all three mouse events
    private void mouseHoverEnterTargetEvent(MouseEvent event) {
        if(event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();
            if(CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);

               if(!button.isDisabled())
                {
                    button.selectedButton();

                    Glow effect = new Glow();
                    effect.setLevel(0.5);
                    targetedLabel.setEffect(effect);

                    start.getSoundManager().playSound(HOVER);
                }
            }
        }
    }
    private void mouseHoverExitTargetEvent(MouseEvent event) {
        if(event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();
            if(CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);
                if(!button.isDisabled())
                {
                    button.unSelectedButton();

                    targetedLabel.setEffect(null);
                }
            }
        }
    }
    private void mouseClickEvent(MouseEvent event) {
        if (event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();

            if (CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);
               if(!button.isDisabled())
                {
                    start.getSoundManager().playSound(CLICK);
                    switch (button.getButtonID())
                    {
                        case SOLVE : start.getScreenManager().setScreen(SOLVE); break;
                        case VIEW : start.getScreenManager().setScreen(VIEW); break;
                        case SETTINGS : start.getScreenManager().setScreen(SETTING); break;
                    }
                }
            }
        }
    }
}
