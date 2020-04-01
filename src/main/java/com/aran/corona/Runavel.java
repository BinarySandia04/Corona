package com.aran.corona;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;

public class Runavel extends BukkitRunnable {

    @Override
    public void run() {
        for(Player p : Main.connectedPlayers){
            Main.instance.api.setHeader((Player) p, Main.formatted);
        }
    }
}
