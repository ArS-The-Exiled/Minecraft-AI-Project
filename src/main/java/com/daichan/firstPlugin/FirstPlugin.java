package com.daichan.firstPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class FirstPlugin extends JavaPlugin {

    private Collector collector;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("FirstPlugin 已啟動!");
        EventManager();
        Schedule();
        GetComamd();
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
    }

    private void Schedule() {

        collector = new Collector(this);
        collector.runTaskTimer(
                        this,
                        0L,
                        20L
                );
    }

    public Collector getCollector(){
        return collector;
    }

    private void GetComamd() {
        getCommand("collect").setExecutor(
                new CollectCommand(this)
        );
    }
}
