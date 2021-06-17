package com.CupidonSauce173.AutoBroadcaster;

import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Timer;

public final class AutoBroadcaster extends Plugin {

    private void createDefaultConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceFile("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        //create config
        createDefaultConfig();
        Configuration config = getConfig();
        //start broadcaster task
        int delay = config.getInt("delay") * 1000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new BroadcasterTask(config.getList("messages")), 0, delay);
    }

}
