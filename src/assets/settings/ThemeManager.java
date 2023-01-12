package assets.settings;

import application.Start;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static assets.image.Images.*;
/**
 * Class for manging the theme of the application
 * Contain static field that hold the general style of the application
 * Contain private field that are called generally throughout the application
 * The private field get updated using the updateTheme() and this way we avoid doing constant check for dark or light
 * */
public class ThemeManager
{
    //Final fields for storing important information about the style of the application
    private final static String font = "Helvetica";
    public final static Font BUTTON_FONT = Font.font(font, FontWeight.BOLD, 35);
    public final static Font HINT_TEXT_FONT = Font.font(font, FontWeight.BOLD, 18);

    public final static Font REGULAR_LABEL_FONT = Font.font(font, FontWeight.BOLD, 18);
    public final static Font SLIDER_LABEL_FONT = Font.font(font, FontWeight.BOLD, 30);
    public final static Font RADIO_BUTTON_FONT = Font.font(font, FontWeight.BOLD, 15);

    private final Color BACKGROUND_DARK = Color.valueOf("#121212");
    private final Color BACKGROUND_LIGHT = Color.valueOf("#fafafa");

    private final Color TEXT_COLOR_DARK = Color.valueOf("#FDB827");
    private final Color TEXT_COLOR_LIGHT = Color.valueOf("#37007A");
    private final Color TEXT_COLOR_DISABLE = Color.valueOf("#808080");

    private final Color MAZE_BACKGROUND_COLOR_DARK = Color.valueOf("#4C4C4C");
    private final Color MAZE_BACKGROUND_COLOR_LIGHT = Color.valueOf("#333333");

    //Fields used to get the style of the application
    private Color textColor;
    private Color backgroundColor;
    private Color mazeBackgroundColor;
    private Image unselectedFrame;
    private Image selectedFrame;
    private Image spinnerImage;
    private Image wallBoxImage;

    //Reference to the main application class
    private final Start start;

    //Constructor for ThemeManager
    //NOTE: It calls updateTheme() when it starts so that the application and load the start screen with proper theme
    public ThemeManager(Start start) {
        this.start = start;
        updateTheme();
    }

    //Method for updating the style field to either be light mode or dark mode
    public void updateTheme() {
        if(start.getSettingsManager().isDark())
        {
            textColor = TEXT_COLOR_DARK;
            backgroundColor = BACKGROUND_DARK;
            mazeBackgroundColor= MAZE_BACKGROUND_COLOR_DARK;
            unselectedFrame = UNSELECTED_FRAME_DARK;
            selectedFrame = SELECTED_FRAME_DARK;
            spinnerImage = SPINNER_DARK;
            wallBoxImage = BOX_WALL_DARK;
        }
        else
        {
            textColor = TEXT_COLOR_LIGHT;
            backgroundColor = BACKGROUND_LIGHT;
            mazeBackgroundColor= MAZE_BACKGROUND_COLOR_LIGHT;
            unselectedFrame = UNSELECTED_FRAME_LIGHT;
            selectedFrame = SELECTED_FRAME_LIGHT;
            spinnerImage = SPINNER_LIGHT;
            wallBoxImage = BOX_WALL_LIGHT;
        }
    }

    //Methods used to get the style of the application
    public Color getTextColor() {
        return textColor;
    }
    public Color getDisableTextColor() {
        return TEXT_COLOR_DISABLE;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public Color getMazeBackgroundColor() {
        return mazeBackgroundColor;
    }
    public Image getUnselectedFrame() {
        return unselectedFrame;
    }
    public Image getSelectedFrame() {
        return selectedFrame;
    }
    public Image getSpinnerImage() {
        return spinnerImage;
    }
    public Image getWallboxImage() {
        return wallBoxImage;
    }
}
