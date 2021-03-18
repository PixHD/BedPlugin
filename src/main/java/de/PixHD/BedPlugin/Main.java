package de.PixHD.BedPlugin;

import de.PixHD.BedPlugin.Commands.reloadCommand;
import de.PixHD.BedPlugin.Listener.BedLeaveListener;
import de.PixHD.BedPlugin.Listener.SleepListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    PluginDescriptionFile pdf = this.getDescription();

    @Override
    public void onEnable() {
        //int pluginId = ; <-- Is used for Bstats 
        //Metrics metrics = new Metrics(this, pluginId);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + " Enabled - BedPlugin V " + pdf.getVersion());
        if(!configManager.getFile().exists()) {
            saveDefaultConfig();
            configManager.reloadConfigFile();
        }
        getCommand("bedplugin").setExecutor(new reloadCommand());
        loadListener();
    }

    private void loadListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new SleepListener(), this);
        pm.registerEvents(new BedLeaveListener(), this);
    }
}
