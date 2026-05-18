package com.dungeon.tracker.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration manager for M7 RNG Tracker settings and persistence.
 */
public class RNGConfig {
    private static final String CONFIG_DIR = "dungeon-profit-tracker";
    private static final String CONFIG_FILE = "rng-config.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private HUDScope hudScope = HUDScope.BOTH;
    private Set<String> enabledOptionalDrops = new HashSet<>();
    private long totalStats = 0;
    
    public enum HUDScope {
        INVENTORY_ONLY,
        WORLD_ONLY,
        BOTH
    }
    
    /**
     * Load configuration from file
     */
    public static RNGConfig load() {
        File configFile = getConfigFile();
        
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                return GSON.fromJson(reader, RNGConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to load RNG config: " + e.getMessage());
            }
        }
        
        return new RNGConfig();
    }
    
    /**
     * Save configuration to file
     */
    public void save() {
        File configFile = getConfigFile();
        configFile.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            System.err.println("Failed to save RNG config: " + e.getMessage());
        }
    }
    
    /**
     * Get or create config directory
     */
    private static File getConfigFile() {
        File dir = new File(System.getProperty("user.home") + File.separator + ".minecraft" + File.separator + CONFIG_DIR);
        return new File(dir, CONFIG_FILE);
    }
    
    // Getters and setters
    public HUDScope getHudScope() {
        return hudScope;
    }
    
    public void setHudScope(HUDScope scope) {
        this.hudScope = scope;
        save();
    }
    
    public Set<String> getEnabledOptionalDrops() {
        return new HashSet<>(enabledOptionalDrops);
    }
    
    public void enableOptionalDrop(String dropName) {
        if (enabledOptionalDrops.size() < 4) {
            enabledOptionalDrops.add(dropName);
            save();
        }
    }
    
    public void disableOptionalDrop(String dropName) {
        enabledOptionalDrops.remove(dropName);
        save();
    }
    
    public void resetConfig() {
        hudScope = HUDScope.BOTH;
        enabledOptionalDrops.clear();
        totalStats = 0;
        save();
    }
}
