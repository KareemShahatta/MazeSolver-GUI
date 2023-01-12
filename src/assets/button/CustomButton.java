package assets.button;

import application.Start;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

import static assets.image.Images.DISABLED_FRAME;
import static assets.settings.ThemeManager.BUTTON_FONT;
/**
 * Class for creating a custom button
 * All buttons in the application are created using this class, instead of the default JavaFX Button
 * */
public class CustomButton
{
    //Static list to keep track of button that have been created
    static List<CustomButton> buttonList = new ArrayList<>();

    //Field for getting/changing information related to the button
    private final CustomButtonID buttonID;
    private final Label button;
    private final ImageView buttonFrame;
    private final Start start;
    private boolean isDisabled;

    /**
     * Constructor for CustomButton
     * @param start reference to the Start class
     * @param button Label of the button
     * @param buttonID id of the button
     * */
    public CustomButton(Start start, Label button , CustomButtonID buttonID) {
        this.start = start;
        this.button = button;
        this.buttonID = buttonID;

        this.buttonFrame = new ImageView(start.getThemeManager().getUnselectedFrame());
        button.setTextFill(start.getThemeManager().getTextColor());
        button.setFont(BUTTON_FONT);
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(190);

        buttonFrame.setFitWidth(200);
        buttonFrame.setFitHeight(50);

        button.setOnMouseEntered(start.getMouseInput());
        button.setOnMouseExited(start.getMouseInput());
        button.setOnMouseClicked(start.getMouseInput());

        buttonList.add(this);
    }

    //Helper methods for getting information about the button label, id, frame
    public Label getButtonLabel() {
        return button;
    }
    public ImageView getButtonFrame() {
        return buttonFrame;
    }
    public CustomButtonID getButtonID() {
        return buttonID;
    }

    //Helper methods for selecting/unselecting a button
    public void selectedButton() {
        buttonFrame.setImage(start.getThemeManager().getSelectedFrame());
        button.setTextFill(start.getThemeManager().getTextColor());
    }
    public void unSelectedButton() {
        buttonFrame.setImage(start.getThemeManager().getUnselectedFrame());
        button.setTextFill(start.getThemeManager().getTextColor());
    }

    //Helper methods for disabling/enabling a button
    public void setDisabled(boolean disabled) {
        this.isDisabled = disabled;

        if(isDisabled)
        {
            buttonFrame.setImage(DISABLED_FRAME);
            button.setTextFill(start.getThemeManager().getDisableTextColor());
        }
        else
        {
            buttonFrame.setImage(start.getThemeManager().getUnselectedFrame());
            button.setTextFill(start.getThemeManager().getTextColor());
        }
    }
    public boolean isDisabled(){return isDisabled;}

    //Static Methods to help figure out if a label is related to a button or not and get its instance
    public static boolean isButton(Label button) {
        for(CustomButton gameButton : CustomButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(button))
            {
                return true;
            }
        }

        return false;
    }
    public static CustomButton getButton(Label buttonLabel) {
        for(CustomButton gameButton : CustomButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(buttonLabel))
            {
                return gameButton;
            }
        }

        return null;
    }
}
