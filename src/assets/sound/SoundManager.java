package assets.sound;

import application.Start;
import javafx.scene.media.AudioClip;

public class SoundManager
{
    //Reference to the main application class
    private Start start;

    //private field for audioPlayer
    private AudioClip audioPlayer;

    public SoundManager(Start start)
    {
        this.start = start;
    }

    //Main method for playing sound throughout the entire application
    //NOTE: Adjusting the application volume happens in this method
    public void playSound(CustomSoundID sound) {
        switch(sound)
        {
            case START: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/start.wav").toExternalForm()); break;
            case CLICK: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/click.wav").toExternalForm()); break;
            case HOVER: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/hover.wav").toExternalForm()); break;
            case FAIL: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/error.wav").toExternalForm()); break;
            case SUCCESS: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/success.wav").toExternalForm()); break;
            case VIEW: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/view.wav").toExternalForm()); break;
            case WALK: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/walk.wav").toExternalForm()); break;
            case QUACK: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/quack.wav").toExternalForm()); break;
        }

        audioPlayer.setVolume(((float) start.getSettingsManager().getVolume())/10);
        audioPlayer.play();
    }
}
