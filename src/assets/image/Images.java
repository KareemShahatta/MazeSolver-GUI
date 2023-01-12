package assets.image;

import javafx.scene.image.Image;
/**
 * Enum class containing all image used by the application
 * */
public class Images
{
    public static final Image LOGO = new Image(ClassLoader.getSystemResource("textures/logo.png").toString());
    public static final Image TITLE = new Image(ClassLoader.getSystemResource("textures/title.png").toString());
    public static final Image ICON = new Image(ClassLoader.getSystemResource("textures/icon_image.png").toString());
    public static final Image DISABLED_FRAME = new Image(ClassLoader.getSystemResource("textures/disabled_frame.png").toString());
    public static final Image SETTINGS = new Image(ClassLoader.getSystemResource("textures/settings.png").toString());
    public static final Image BOX_START = new Image(ClassLoader.getSystemResource("textures/box_start.png").toString());
    public static final Image BOX_GOAL = new Image(ClassLoader.getSystemResource("textures/box_goal.png").toString());
    public static final Image BOX_TRAIL = new Image(ClassLoader.getSystemResource("textures/box_trail.png").toString());

    public static final Image SELECTED_FRAME_LIGHT = new Image(ClassLoader.getSystemResource("textures/light/selected_frame_light.png").toString());
    public static final Image UNSELECTED_FRAME_LIGHT = new Image(ClassLoader.getSystemResource("textures/light/unselected_frame_light.png").toString());
    public static final Image BOX_WALL_LIGHT = new Image(ClassLoader.getSystemResource("textures/light/box_wall_light.png").toString());
    public static final Image SPINNER_LIGHT = new Image(ClassLoader.getSystemResource("textures/light/spinner_light.png").toString());

    public static final Image SELECTED_FRAME_DARK = new Image(ClassLoader.getSystemResource("textures/dark/selected_frame_dark.png").toString());
    public static final Image UNSELECTED_FRAME_DARK = new Image(ClassLoader.getSystemResource("textures/dark/unselected_frame_dark.png").toString());
    public static final Image BOX_WALL_DARK = new Image(ClassLoader.getSystemResource("textures/dark/box_wall_dark.png").toString());
    public static final Image SPINNER_DARK = new Image(ClassLoader.getSystemResource("textures/dark/spinner_dark.png").toString());
}
