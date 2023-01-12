package assets.screen.screens;

import assets.button.CustomButton;
import assets.button.CustomButtonID;
import assets.sound.CustomSoundID;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import application.Start;
import assets.screen.CustomScreen;

import static assets.image.Images.SETTINGS;
import static assets.screen.CustomScreenID.MENU;
import static assets.settings.ThemeManager.*;
/**
 * Settings screen is the main screen controlling everything with the player settings
 * Controls speed, volume, style, and trail option
 * */
public class SettingsScreen extends CustomScreen
{
    //Reference to the main application class and the screen pane
    private final Pane registerPane;
    private final Start start;

    //Field for getting/changing information related to the maze
    private final Slider soundSlider;
    private final Slider speedSlider;
    private final RadioButton darkModeButton;
    private final CheckBox trailButton;

    public SettingsScreen(Start start) {
        this.start = start;
        registerPane = new Pane();

        ImageView logo = new ImageView(SETTINGS);
        logo.setFitWidth(150);
        logo.setFitHeight(150);
        logo.setX(170);
        logo.setY(20);

        Label soundTextLabel = new Label("Sound:");
        soundTextLabel.setTextFill(start.getThemeManager().getTextColor());
        soundTextLabel.setFont(SLIDER_LABEL_FONT);

        soundSlider = new Slider(1,10,start.getSettingsManager().getVolume());
        soundSlider.setBlockIncrement(1);

        Label soundTextValue = new Label((int) soundSlider.getValue() + "");
        soundTextValue.setTextFill(start.getThemeManager().getTextColor());
        soundTextValue.setFont(REGULAR_LABEL_FONT);

        soundSlider.valueProperty().addListener(event -> soundTextValue.setText((int) soundSlider.getValue() + " "));

        HBox soundSliderHBox = new HBox();
        soundSliderHBox.getChildren().addAll(soundTextLabel, soundSlider, soundTextValue);
        soundSliderHBox.setSpacing(20);
        soundSliderHBox.setAlignment(Pos.CENTER);

        Label speedTextLabel = new Label("Speed:");
        speedTextLabel.setTextFill(start.getThemeManager().getTextColor());
        speedTextLabel.setFont(SLIDER_LABEL_FONT);

        speedSlider = new Slider(1,3,start.getSettingsManager().getSpeed());
        speedSlider.setBlockIncrement(1);

        Label speedTextValue = new Label((int) speedSlider.getValue() + "");
        speedTextValue.setTextFill(start.getThemeManager().getTextColor());
        speedTextValue.setFont(REGULAR_LABEL_FONT);

        speedSlider.valueProperty().addListener(event -> speedTextValue.setText((int) speedSlider.getValue() + ""));

        HBox speedSliderHBox = new HBox();
        speedSliderHBox.getChildren().addAll(speedTextLabel, speedSlider, speedTextValue);
        speedSliderHBox.setSpacing(20);
        speedSliderHBox.setAlignment(Pos.CENTER);

        Label themeText = new Label("Theme:");
        themeText.setTextFill(start.getThemeManager().getTextColor());
        themeText.setFont(REGULAR_LABEL_FONT);

        ToggleGroup group = new ToggleGroup();

        darkModeButton = new RadioButton("Dark");
        darkModeButton.setFont(RADIO_BUTTON_FONT);
        darkModeButton.setTextFill(start.getThemeManager().getTextColor());
        darkModeButton.setToggleGroup(group);

        RadioButton lightModeButton = new RadioButton("Light");
        lightModeButton.setFont(RADIO_BUTTON_FONT);
        lightModeButton.setTextFill(start.getThemeManager().getTextColor());
        lightModeButton.setToggleGroup(group);

        if(start.getSettingsManager().isDark())
        {
            darkModeButton.setSelected(true);
        }
        else
        {
            lightModeButton.setSelected(true);
        }

        HBox ThemeHBox = new HBox();
        ThemeHBox.getChildren().addAll(themeText, darkModeButton, lightModeButton);
        ThemeHBox.setSpacing(20);
        ThemeHBox.setAlignment(Pos.CENTER);

        trailButton = new CheckBox();
        trailButton.setText("Draw a trail while solving the maze");
        trailButton.setFont(REGULAR_LABEL_FONT);
        trailButton.setTextFill(start.getThemeManager().getTextColor());
        trailButton.setSelected(start.getSettingsManager().isTrailed());
        
        VBox fieldsVB = new VBox();
        fieldsVB.getChildren().addAll(logo, soundSliderHBox, speedSliderHBox, ThemeHBox, trailButton);
        fieldsVB.setLayoutX(80);
        fieldsVB.setLayoutY(200);
        fieldsVB.setSpacing(20);
        fieldsVB.setAlignment(Pos.CENTER);

        CustomButton saveButton = new CustomButton(start, new Label("Save/Exit") , CustomButtonID.SAVE);
        saveButton.getButtonLabel().setLayoutX(150);
        saveButton.getButtonLabel().setLayoutY(430);
        saveButton.getButtonFrame().setLayoutX(145);
        saveButton.getButtonFrame().setLayoutY(430);
        saveButton.getButtonLabel().setOnMouseClicked(event -> saveButtonClicked());

        registerPane.setBackground(new Background(new BackgroundFill(start.getThemeManager().getBackgroundColor() , null , null)));
        registerPane.getChildren().addAll(logo, fieldsVB, saveButton.getButtonLabel(), saveButton.getButtonFrame());
    }

    @Override public Pane getPane() {
        return registerPane;
    }

    //Method for handling the save button being clicked
    public void saveButtonClicked() {
        start.getSoundManager().playSound(CustomSoundID.CLICK);
        start.getSettingsManager().updateSetting((int) soundSlider.getValue(), (int) speedSlider.getValue(), darkModeButton.isSelected(), trailButton.isSelected());
        start.getThemeManager().updateTheme();
        start.getScreenManager().setScreen(MENU);
    }
}
