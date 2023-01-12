package assets.screen.screens;

import assets.screen.CustomScreen;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import application.Start;

import static assets.image.Images.LOGO;
import static assets.image.Images.TITLE;
/**
 * Starting screen of the application
 * Contain simple animation to greet the user once they open it
 * */
public class StartScreen extends CustomScreen
{
    //Reference to the screen pane
    private final Pane startPane;

    public StartScreen(Start start) {
        startPane = new Pane();

        ImageView logoImage = new ImageView(LOGO);
        logoImage.setX(75);
        logoImage.setY(20);
        logoImage.setFitWidth(350);
        logoImage.setFitHeight(350);

        ImageView titleImage = new ImageView(TITLE);
        titleImage.setX(50);
        titleImage.setY(400);
        titleImage.setFitWidth(420);
        titleImage.setFitHeight(80);

        playAnimations(logoImage, titleImage);

        startPane.getChildren().addAll(logoImage, titleImage);
        startPane.setBackground(new Background(new BackgroundFill(start.getThemeManager().getBackgroundColor(), null , null)));
    }

    @Override public Pane getPane() {
        return startPane;
    }

    //Helper methods to play the animation of the logo and the title
    private void playAnimations(ImageView logo, ImageView title) {
        ScaleTransition logoAnimation = new ScaleTransition();
        logoAnimation.setFromX(0);
        logoAnimation.setToX(1);
        logoAnimation.setDuration(Duration.seconds(1.5));
        logoAnimation.setNode(logo);
        logoAnimation.play();

        FadeTransition titleAnimation = new FadeTransition();
        titleAnimation.setFromValue(0);
        titleAnimation.setToValue(1);
        titleAnimation.setDuration(Duration.seconds(2));
        titleAnimation.setNode(title);
        titleAnimation.play();
    }
}
