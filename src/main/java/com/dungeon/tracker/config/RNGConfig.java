package com.dungeon.tracker.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dungeon.tracker.DungeonProfitTrackerMod;

/**
 * Configuration manager for M7 RNG Tracker settings and persistence.
 */
public class RNGConfig {
    private static final String CONFIG_DIR = "dungeon-profit-tracker";
    private static final String CONFIG_FILE = "rng-config.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Logger LOGGER = LoggerFactory.getLogger(DungeonProfitTrackerMod.MOD_ID);
    
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
                RNGConfig loaded = GSON.fromJson(reader, RNGConfig.class);
                LOGGER.info("RNG config loaded successfully");
                return loaded != null ? loaded : new RNGConfig();
            } catch (IOException e) {
                LOGGER.error("Failed to load RNG config: {}", e.getMessage());
            }
        }
        
        RNGConfig config = new RNGConfig();
        config.save();
        return config;
    }
    
    /**
     * Save configuration to file
     */
    public void save() {
        File configFile = getConfigFile();
        configFile.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(this, writer);
            LOGGER.info("RNG config saved successfully");
        } catch (IOException e) {
            LOGGER.error("Failed to save RNG config: {}", e.getMessage());
        }
    }
    
    /**
     * Get or create config directory
     */
    private static File getConfigFile() {
        File dir = new File(System.getProperty("user.home") + File.separator + 
                          ".minecraft" + File.separator + CONFIG_DIR);
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
