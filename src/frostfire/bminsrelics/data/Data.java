package frostfire.bminsrelics.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class Data {
    File folder;
    public Data(File f) {
        folder = f;
    }
    public Location GetLocation(String name) {
        File file = new File(folder, "checkpoints.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            double x,y,z;
            World w = Bukkit.getWorld(config.getString(name+".w"));
            x=config.getDouble(name+".x");
            y=config.getDouble(name+".y");
            z=config.getDouble(name+".z");
            return new Location(w, x, y, z,0,0);
        }
        catch(Exception e) {
            return null;
        }
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
