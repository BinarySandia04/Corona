package com.aran.corona;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import io.puharesource.mc.titlemanager.api.v2.animation.Animation;
import io.puharesource.mc.titlemanager.api.v2.animation.AnimationFrame;
import io.puharesource.mc.titlemanager.api.v2.animation.AnimationPart;
import io.puharesource.mc.titlemanager.api.v2.animation.SendableAnimation;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoronaCommand implements CommandExecutor {

    private Main main;
    private TitleManagerAPI api;

    public CoronaCommand(Main main, TitleManagerAPI api){
        this.main = main;
        this.api = api;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("corona")) {
            sender.sendMessage("Getting coronavirus info...");

            String req = executePost("https://coronavirus-19-api.herokuapp.com/all");

            sender.sendMessage(req);

            api.setHeader((Player) sender, req);
        }


        return false;
    }

    public static String executePost (String url) {
        //Creating a HttpClient object

        //Creating a HttpGet object
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);

            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(
                            response.getEntity().getContent()));
            String o = "";
            String line = "";
            while ((line = rd.readLine()) != null) {
                o += line;
            }

            return o;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }

        return "Ey";
    }
}

