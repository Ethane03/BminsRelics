package frostfire.bminsrelics.data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import net.minecraft.server.v1_16_R3.World;


public class Data implements Serializable {
    private static transient final long serialVersionUID = -1681012206529286330L;
 
    public final HashMap<String,Location> checkpoints;
 
    // Can be used for saving
    public Data(HashMap<String, Location> checks) {
        this.checkpoints = checks;
    }
    // Can be used for loading
    public Data(Data loadedData) {
        this.checkpoints = loadedData.checkpoints;
    }
 
    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Location getLocationByName(String name) {
        return checkpoints.get(name);
    }
    public void SetCheckpoint(String name, Location loc) {
        checkpoints.put(name, loc);
    }
}
