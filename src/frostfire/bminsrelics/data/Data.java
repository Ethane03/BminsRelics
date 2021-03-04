package frostfire.bminsrelics.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
    public List<String> GetLocations(String soFar) {
        List<String> locations = new ArrayList<>();
        File file = new File(folder, "checkpoints.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection sect = config.getRoot();
        String[] things = soFar.split(".");
        for(int i=0;i<things.length-1;i++) {
            sect = sect.getConfigurationSection(things[i]);
        }
        for(String key : sect.getKeys(false)){
            locations.add(key);
          }
          
        return locations;
    }
    public void DeleteLoc(String loc) {
        File file = new File(folder, "checkpoints.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(loc, null);
        try {
            config.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //Glow game info
    public void SetPlayerParticipation(String game, Player player,Boolean in) {
        File file = new File(folder, "playerReg.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(game+"."+player.getUniqueId().toString(),in);
        try {
            config.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public boolean GetParticipating(String game, Player player) {
        File file = new File(folder,  "playerReg.yml");
        YamlConfiguration config = new YamlConfiguration();
        boolean status = false;
        try {
            config.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        status=config.getBoolean(game+"."+player.getUniqueId().toString());
        return status;
    }
    public boolean hasParticipated(String game, Player player) {
        File file = new File(folder,  "playerReg.yml");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config.contains(game+"."+player.getUniqueId().toString());
    }
    public List<String> getPlayerList(String game) {
        File file = new File(folder,  "playerReg.yml");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        List<String> players = new ArrayList<>();
        for(String p : config.getConfigurationSection(game).getKeys(false)) {
            if(config.getBoolean(game+"."+p)) {
                players.add(p);
            }
        }
        return players;
    }
    public void SetGameVariable(String game, String variable, Object value) {
        File file = new File(folder, game+".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(variable,value);
        try {
            config.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public Object GetGameVariable(String game, String variable,Object def) {
        File file = new File(folder,  game+".yml");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Object var = config.get(variable);
        return var==null?def:variable;
    }
    public void SetGameActive(String game,Boolean active) {
        File file = new File(folder, "gameReg.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(active) {
            config.set(game, true);
        }
        else {
            config.set(game, null);
        }
        try {
            config.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public List<String> GetActiveGames() {
        File file = new File(folder, "gameReg.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<String> games = new ArrayList<>();
        for(String s : config.getKeys(false)) {
            games.add(s);
        }
        return games;
    }

}
