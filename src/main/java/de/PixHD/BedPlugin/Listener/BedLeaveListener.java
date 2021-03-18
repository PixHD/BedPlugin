package de.PixHD.BedPlugin.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedLeaveListener implements Listener {
    
    @EventHandler
    public void handleBedLeave(PlayerBedLeaveEvent e) {
        Player p = e.getPlayer();
        SleepListener.PlayerList.remove(p.getUniqueId());
    }
}
