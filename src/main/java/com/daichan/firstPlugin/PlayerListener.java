package com.daichan.firstPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        String name = event.getPlayer().getName();

        event.getPlayer().sendMessage(
                "歡迎 " + name + " 進入伺服器！"
        );
    }
}