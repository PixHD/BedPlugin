package de.PixHD.BedPlugin.Commands;

import de.PixHD.BedPlugin.configManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                configManager.saveConfigurationFile();
                configManager.reloadConfigFile();
                if(configManager.isGerman()) {
                    sender.sendMessage("§aDie Config wurde erfolgreich Reloaded!");
                }else {
                    sender.sendMessage("§aThe Config was reloaded successfully!");
                }
            }else {
                sendWrongArgs(sender);
            }
        }else {
            sendWrongArgs(sender);
        }
        return false;
    }


    public static void sendWrongArgs(CommandSender s) {
        if(configManager.isGerman()) {
            s.sendMessage("§cBitte benutze §4/bedplugin reload§c!");
        }else {
            s.sendMessage("§cPlease use §4/beplugin reload§c!");
        }
    }
}
