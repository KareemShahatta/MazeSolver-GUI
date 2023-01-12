package assets.screen.screens;

import application.Start;
import assets.button.CustomButton;
import assets.button.CustomButtonID;
import assets.screen.CustomScreen;
import assets.sound.CustomSoundID;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static assets.image.Images.TITLE;
import static assets.settings.ThemeManager.HINT_TEXT_FONT;
/**
 * Menu Screen of the application
 * Contain all four button view, solve, load, settings
 * NOTE: Load button has its own clicking functionality embedded in this class rather than MouseInput
 * NOTE: Load Animation is broken into three methods to make it easier to understand what is going on
 * */
public class MenuScreen extends CustomScreen
{
    //Reference to the main application class and the screen pane
    private final Pane menuPane;
    private final Start start;

    //Private fields that help us edit the variables throughout the class
    private final ImageView spinner;
    private final Text hintMessage;
    private final CustomButton loadButton;
    private final CustomButton solveButton;
    private final CustomButton viewButton;
    private SequentialTransition sequenceTransition;

    public MenuScreen(Start start) {
        this.start = start;
        menuPane = new Pane();

        ImageView logo = new ImageView(TITLE);
        logo.setFitWidth(400);
        logo.setFitHeight(80);
        logo.setX(50);
        logo.setY(40);

        solveButton = new CustomButton(start, new Label("Solve")  , CustomButtonID.SOLVE);
        viewButton = new CustomButton(start, new Label("View") , CustomButtonID.VIEW);
        loadButton = new CustomButton(start, new Label("Load") , CustomButtonID.LOAD);
        CustomButton settingButton = new CustomButton(start, new Label("Settings") , CustomButtonID.SETTINGS);
        loadButton.getButtonLabel().setOnMouseClicked(event -> loadButtonClicked());

        if(!start.getMazeManager().isFileValid())
        {
            solveButton.setDisabled(true);
            viewButton.setDisabled(true);
        }

        VBox labelsVB = new VBox();
        labelsVB.getChildren().addAll(solveButton.getButtonLabel() , viewButton.getButtonLabel(), loadButton.getButtonLabel(), settingButton.getButtonLabel() );
        labelsVB.setLayoutX(150);
        labelsVB.setLayoutY(145);
        labelsVB.setSpacing(38);

        VBox framesVB = new VBox();
        framesVB.getChildren().addAll(solveButton.getButtonFrame() , viewButton.getButtonFrame() , loadButton.getButtonFrame(), settingButton.getButtonFrame());
        framesVB.setLayoutX(145);
        framesVB.setLayoutY(145);
        framesVB.setSpacing(40);

        hintMessage = new Text("");
        hintMessage.setFont(HINT_TEXT_FONT);
        hintMessage.setX(5);
        hintMessage.setY(490);

        spinner = new ImageView(start.getThemeManager().getSpinnerImage());
        spinner.setFitWidth(30);
        spinner.setFitHeight(30);
        spinner.setX(460);
        spinner.setY(460);
        spinner.setVisible(false);

        menuPane.setBackground(new Background(new BackgroundFill(start.getThemeManager().getBackgroundColor(), null , null)));
        menuPane.getChildren().addAll(logo, framesVB , labelsVB, hintMessage, spinner);
    }

    @Override public Pane getPane() {
        return menuPane;
    }

    //Method for handling the loadButton being clicked
    private void loadButtonClicked() {
        if(!loadButton.isDisabled())
        {
            loadButton.setDisabled(true);
            start.getSoundManager().playSound(CustomSoundID.CLICK);

            switch (start.getMazeManager().loadMazeFile())
            {
                case 0 : sendHintMessage(start.getMazeManager().getMazeFileName() + " is now loaded", true); break;
                case 1 : sendHintMessage("File extension is not supported" , false); break;
                case 2 : sendHintMessage("Something went wrong" , false); break;
            }
        }
    }

    //Method for sending a hint message to the user either an error or a notification broken into three parts
    private void sendHintMessage(String message, boolean success) {
        hintMessage.setText("[!] " + message);
        hintMessage.setVisible(false);
        spinner.setVisible(true);

        solveButton.setDisabled(true);
        viewButton.setDisabled(true);

        Timeline animationDelay = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(sequenceTransition.getStatus().equals(Animation.Status.RUNNING))
            {
                if(success)
                {
                    start.getSoundManager().playSound(CustomSoundID.SUCCESS);
                    hintMessage.setFill(start.getThemeManager().getTextColor());
                    solveButton.setDisabled(false);
                    viewButton.setDisabled(false);
                }
                else
                {
                    start.getSoundManager().playSound(CustomSoundID.FAIL);
                    hintMessage.setFill(Color.RED);
                    solveButton.setDisabled(true);
                    viewButton.setDisabled(true);
                }
                spinner.setVisible(false);
                hintMessage.setVisible(true);
                loadButton.setDisabled(false);
            }
        }));
        animationDelay.play();
        startAnimation();
    }

    //Plays the animation of the spinner and the hint message
    private void startAnimation() {
        if(sequenceTransition != null)
        {
            sequenceTransition.stop();;
        }

        RotateTransition RT = new RotateTransition();
        RT.setDuration(Duration.seconds(1));
        RT.setNode(spinner);
        RT.setFromAngle(0);
        RT.setToAngle(360*2);
        RT.play();

        FadeTransition FT = new FadeTransition();
        FT.setFromValue(1);
        FT.setToValue(0);
        FT.setDuration(Duration.seconds(3));
        FT.setNode(hintMessage);

        sequenceTransition = new SequentialTransition();

        sequenceTransition.getChildren().addAll(RT,FT);
        sequenceTransition.play();
    }
}
