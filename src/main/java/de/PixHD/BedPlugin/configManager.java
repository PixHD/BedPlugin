package de.PixHD.BedPlugin;

import de.PixHD.BedPlugin.Listener.SleepListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class configManager {

    public static File file = new File("plugins/BedPlugin/", "config.yml");
    public static FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

    public static File getFile() {
        return file;
    }
    public static void reloadConfigFile() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public static void saveConfigurationFile() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean isGerman() {
        String lang = fileConfiguration.getString("lang");
        assert lang != null;
        return lang.equalsIgnoreCase("de");
    }

    public static boolean isEnglish() {
        String lang = fileConfiguration.getString("lang");
        assert lang != null;
        return lang.equalsIgnoreCase("en");
    }

    public static String getGermanMessage(Player player) {
        String msg = ChatColor.translateAlternateColorCodes('&' , Objects.requireNonNull(configManager.getFileConfiguration().getString("Messages.sleep.de")));
        msg = msg.replaceAll( "%PLAYER%" , player.getDisplayName());
        msg = msg.replaceAll( "%SLEEPPLAYER%" , String.valueOf(SleepListener.PlayerList.size()));
        return msg;
    }

    public static String getEnglishMessage(Player player) {
        String msg = ChatColor.translateAlternateColorCodes('&' , Objects.requireNonNull(configManager.getFileConfiguration().getString("Messages.sleep.en")));
        msg = msg.replaceAll( "%PLAYER%" , player.getDisplayName());
        msg = msg.replaceAll( "%SLEEPPLAYER%" , String.valueOf(SleepListener.PlayerList.size()));
        return msg;
    }



}
