package de.PixHD.BedPlugin.Listener;

import de.PixHD.BedPlugin.configManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SleepListener implements Listener {

    public static ArrayList<UUID> PlayerList = new ArrayList<>();

    @EventHandler
    public void handlePlayerSleep(PlayerBedEnterEvent e) {
        int players = e.getPlayer().getWorld().getPlayers().size();
        if(players > 1) {
            if(e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
                if (!PlayerList.contains(e.getPlayer().getUniqueId())) {
                    PlayerList.add(e.getPlayer().getUniqueId());
                    if(configManager.isGerman()) {
                        if(isEven(e.getPlayer().getWorld().getPlayers().size())) {
                            String message = configManager.getGermanMessage(e.getPlayer());
                            message = message.replaceAll("%MAXPLAYER%", String.valueOf(players/2));
                            Bukkit.broadcastMessage(message);
                        }else {
                            String message = configManager.getGermanMessage(e.getPlayer());
                            message = message.replaceAll("%MAXPLAYER%", String.valueOf((players-1)/2));
                            Bukkit.broadcastMessage(message);
                        }
                    }else if(configManager.isEnglish()) {
                        if(isEven(e.getPlayer().getWorld().getPlayers().size())) {
                            String message = configManager.getEnglishMessage(e.getPlayer());
                            message = message.replaceAll("%MAXPLAYER%", String.valueOf(players/2));
                            Bukkit.broadcastMessage(message);
                        }else {
                            String message = configManager.getEnglishMessage(e.getPlayer());
                            message = message.replaceAll("%MAXPLAYER%", String.valueOf((players-1)/2));
                            Bukkit.broadcastMessage(message);
                        }
                    }else {
                        Bukkit.broadcastMessage("This language is not Supported!");
                    }
                    if(isEven(Bukkit.getOnlinePlayers().size())) {
                        if (PlayerList.size() >= ((players / 2)) && e.getPlayer().getWorld().getTime() >= 13000) {
                            e.getPlayer().getWorld().setTime(0L);
                            e.getPlayer().getWorld().setStorm(false);
                            e.getPlayer().getWorld().setThundering(false);
                            e.getPlayer().setStatistic(Statistic.TIME_SINCE_REST, 0);
                            resetPhantom();
                            PlayerList.clear();
                        }
                    }else {
                        if (PlayerList.size() >= ((players-1) / 2) && e.getPlayer().getWorld().getTime() >= 13000) {
                            e.getPlayer().getWorld().setTime(0L);
                            e.getPlayer().getWorld().setStorm(false);
                            e.getPlayer().getWorld().setThundering(false);
                            resetPhantom();
                            PlayerList.clear();
                        }
                    }
                }
            }
        }
    }

    private static void resetPhantom() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setStatistic(Statistic.TIME_SINCE_REST, 0);
        }
    }


    public static boolean isEven(int i) {
        return (i % 2) == 0;
    }
}
