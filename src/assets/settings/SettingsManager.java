package assets.settings;

import java.io.*;
import java.util.Scanner;
/**
 * Class for manging the settings of the application
 * The class is responsible for reading and writing the setting values to the .csv file to keep them saved
 * Contain private field that are called generally throughout the application
 * The private field get their data from the .csv file or get the default data if the file isn't created yet
 * */
public class SettingsManager
{
    //Final field containing the folder and the file location for the .csv file
    final private File folder = new File(System.getProperty("user.home") + "/Maze Solver");
    final private File file = new File(folder.getPath() + "/settings.csv");

    //Private field used for changing/getting values related to the settings
    private int volume;
    private int speed;
    private boolean isDark;
    private boolean isTrailed;

    //Constructor for SettingManager
    //NOTE: It tries to look for the folder with the .csv file if it can't find one then it creates a new one
    //NOTE: The newly created .csv file will contain the default data
    public SettingsManager() {
        try
        {
            if(folder.exists() && file != null)
            {
                Scanner scanner = new Scanner(file);
                String[] data = scanner.nextLine().split(",");

                String[] volumeData = data[0].split("=");
                volume = Integer.parseInt(volumeData[1]);

                String[] speedData = data[1].split("=");
                speed = Integer.parseInt(speedData[1]);

                String[] themeData = data[2].split("=");
                isDark = Boolean.parseBoolean(themeData[1]);

                String[] trailData = data[3].split("=");
                isTrailed = Boolean.parseBoolean(trailData[1]);
            }
            else
            {
                folder.mkdir();
                file.createNewFile();

                FileWriter write = new FileWriter(file);

                write.append("Volume=5,");
                volume = 5;

                write.append("Speed=2,");
                speed = 1;

                write.append("Theme=Dark,");
                isDark = true;

                write.append("Trail=False");
                isTrailed = false;

                write.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Private field used for changing/getting values related to the settings
    public int getVolume() {
        return volume;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isDark() {
        return isDark;
    }
    public boolean isTrailed() {
        return isTrailed;
    }

    //Method for updating the setting both in the variables value and in the .csv folder
    public void updateSetting(int volume, int speed, boolean isDark, boolean isTrailed) {
        try
        {
            FileWriter write = new FileWriter(file);
            write.flush();

            this.volume = volume;
            write.append("Volume=").append(String.valueOf(volume)).append(",");

            write.append("Speed=").append(String.valueOf(speed)).append(",");
            this.speed = speed;

            write.append("Theme=").append(String.valueOf(isDark)).append(",");
            this.isDark = isDark;

            write.append("Trail=").append(String.valueOf(isTrailed));
            this.isTrailed = isTrailed;

            write.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
