package com.daichan.firstPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class FirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("FirstPlugin 已啟動!");
        EventManager();
        Schedule();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("FirstPlugin 已關閉!");
    }

    private void EventManager(){
        getServer()
                .getPluginManager()
                .registerEvents(
                        new PlayerListener(),
                        this
                );
        getLogger().info("EventManager 已啟動!");
    }

    private void Schedule() {

        new Collector(this)
                .runTaskTimer(
                        this,
                        0L,
                        20L
                );
        getLogger().info("Schedule 已啟動!");
    }
}
