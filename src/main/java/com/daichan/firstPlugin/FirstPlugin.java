package com.daichan.firstPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class FirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("FirstPlugin 已启动!");
        getServer()
                .getPluginManager()
                .registerEvents(
                        new PlayerListener(),
                        this
                );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("FirstPlugin 已关闭!");
    }
}
