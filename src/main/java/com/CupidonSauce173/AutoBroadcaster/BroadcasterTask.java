package com.CupidonSauce173.AutoBroadcaster;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.List;
import java.util.TimerTask;

public class BroadcasterTask extends TimerTask {

    List<String> messages;
    int msgId;

    public BroadcasterTask(List<String> messages){
        this.messages = messages;
    }

    @Override
    public void run() {
        for (ProxiedPlayer player: ProxyServer.getInstance().getPlayers().values()) {
            player.sendMessage(messages.get(msgId));
        }
        msgId++;
        if(msgId >= messages.size()){
            msgId = 0;
        }
    }
}
