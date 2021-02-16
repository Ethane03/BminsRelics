package frostfire.bminsrelics.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import frostfire.bminsrelics.Bminsrelics;

public class Data {
    File folder;
    final YamlConfiguration configFile;

    public Data(File f) {
        configFile = new YamlConfiguration();
        folder = f;
        File yml = new File(folder, "checkpoints.yml");
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Location GetLocation(String name) {
        File file = new File(folder, "checkpoints.yml");
        YamlConfiguration config = new YamlConfiguration();
        double x,y,z;
        try {
            config.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        x=config.getDouble(name+".x");
        y=config.getDouble(name+".y");
        z=config.getDouble(name+".z");
        String world = config.getString(name+".w");
        World w = Bukkit.getServer().getWorld(world);
        return new Location(w, x, y, z);
    }
    public void AddLocation(String name, Location location) {
        File file = new File(folder, "checkpoints.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(name+".x",location.getX());
        config.set(name+".z",location.getZ());
        config.set(name+".y",location.getY());
        config.set(name+".w",location.getWorld().getName());
        try {
            config.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
