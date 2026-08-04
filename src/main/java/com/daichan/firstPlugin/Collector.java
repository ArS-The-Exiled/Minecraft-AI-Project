package com.daichan.firstPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.FileWriter;
import java.io.IOException;


public class Collector extends BukkitRunnable {

    private final FirstPlugin plugin;

    private FileWriter writer;


    public Collector(FirstPlugin plugin){

        this.plugin = plugin;

        try {
            writer = new FileWriter(
                    "player_data.csv",
                    true
            );
            writer.write(
                    "time,player,locX,locY,locZ,vX,vY,vZ,yaw,pitch,handType,blockType\n"
            );
        } catch(IOException e){
            plugin.getLogger().severe(
                    "data collect failed: "
                            + e.getMessage()
            );
        }
        //建立csv檔
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            Location loc = player.getLocation();

            double locX= loc.getX();
            double locY = loc.getY();
            double locZ = loc.getZ();

            Vector velocity = player.getVelocity();

            double velocityX = velocity.getX();
            double velocityY = velocity.getY();
            double velocityZ = velocity.getZ();


            float yaw = loc.getYaw();
            float pitch = loc.getPitch();

            Material type = player.getInventory()
                            .getItemInMainHand()
                            .getType();

            Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

            Material blockType = block.getType();

            try {

                writer.write(
                        System.currentTimeMillis() + ","
                                + player.getName() + ","
                                + locX + "," + locY + "," + locZ + ","
                                + velocityX + ","  + velocityY + "," + velocityZ + ","
                                + yaw + ","
                                + pitch + ","
                                + type + ","
                                + blockType
                                + "\n"
                );

                writer.flush();

            } catch(IOException e){
                plugin.getLogger().severe(
                        "data collect failed: "
                                + e.getMessage()
                );
            }

        }

    }
}