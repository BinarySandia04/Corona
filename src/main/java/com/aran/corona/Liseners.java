package com.aran.corona;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Liseners implements Listener {

    Main main;

    public Liseners(Main main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage(e.getPlayer().getDisplayName() + " ha entrat al servidor");
        Main.connectedPlayers.add(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e){
        e.setQuitMessage(e.getPlayer().getDisplayName() + " ha sortit del servidor");
        Main.connectedPlayers.remove(e.getPlayer());
    }
}
