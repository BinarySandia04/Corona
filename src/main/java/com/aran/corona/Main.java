package com.aran.corona;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static String jsonCorona;
    public static Main instance;
    public static TitleManagerAPI api;
    public static List<Player> connectedPlayers = new ArrayList<Player>();

    public static String formatted;


    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Bon dia!!!");

        api = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");

        Bukkit.getServer().getPluginManager().registerEvents(new Liseners(this), this);

        this.getCommand("corona").setExecutor(new CoronaCommand(this, api));

        UpdateCorona();

        new Runavel().runTaskTimer(this, 20, 20);

        new BukkitRunnable() {

            @Override
            public void run() {
                UpdateCorona();
            }

        }.runTaskTimer(this, 100, 2400);

        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("Bona nit!!!");
    }

    public static void UpdateCorona(){
        jsonCorona = CoronaCommand.executePost("https://coronavirus-19-api.herokuapp.com/all");

        JSONObject obj = new JSONObject(Main.jsonCorona);

        formatted = ChatColor.DARK_AQUA + "\nBENVINGUT A CONFINACRAFT SERVER!\n-------------------------------------------" + ChatColor.WHITE + "\nInfectats: " + ChatColor.YELLOW + obj.getInt("cases") +
                ChatColor.RESET + "\nMorts: " + ChatColor.RED + obj.getInt("deaths") + ChatColor.RESET + "\nCurats: " + ChatColor.GREEN + obj.getInt("recovered") + "\n";
    }
}
