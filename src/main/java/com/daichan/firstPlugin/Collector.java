package com.daichan.firstPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Collector extends BukkitRunnable {

    private final FirstPlugin plugin;
    private boolean collecting = false;

    private FileWriter writer;
    private String currentLabel;

    public Collector(FirstPlugin plugin){
        this.plugin = plugin;
    }

    public void start(String label)
    {
        File folder = new File(
                plugin.getDataFolder(),
                "records"
        );
        if(!folder.exists()) folder.mkdirs();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        String filename =
                "collect_"
                        + label
                        + "_"
                        + LocalDateTime.now().format(formatter)
                        + ".csv";

        File file = new File(
                folder,
                filename
        );

        try {
            writer = new FileWriter(file);
        } catch(IOException e){
            plugin.getLogger().severe(
                    "Cannot create file : " + e.getMessage()
            );
            collecting = false;
            return;
        }

        try {
            writer.write(
                    "time,player,x,y,z,vx,vy,vz,yaw,pitch,item,block,label\n"
            );
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentLabel = label;

        collecting = true;
    }

    @Override
    public void run() {

        if(!collecting) {

            return;
        }
            for(Player player : Bukkit.getOnlinePlayers()) {
                Location loc = player.getLocation();

                double locX = loc.getX();
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
                                    + velocityX + "," + velocityY + "," + velocityZ + ","
                                    + yaw + ","
                                    + pitch + ","
                                    + type + ","
                                    + blockType + ","
                                    + currentLabel + "\n"
                    );
                    writer.flush();

                } catch (IOException e) {
                    plugin.getLogger().severe(
                            "data collect failed: "
                                    + e.getMessage()
                    );
                }

            }
        }

    public void stop()
    {
        if(!collecting){
            return;
        }
        collecting = false;

        if(writer != null){
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer = null;
        }
    }
}