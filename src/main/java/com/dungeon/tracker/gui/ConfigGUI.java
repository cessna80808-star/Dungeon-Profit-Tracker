package com.dungeon.tracker.gui;

import com.dungeon.tracker.config.RNGConfig;
import com.dungeon.tracker.rng.M7RNGTracker;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration GUI for /dprof config command.
 * Displays HUD scope options and optional drop toggles.
 */
public class ConfigGUI {
    private final RNGConfig config;
    private final M7RNGTracker tracker;
    
    public ConfigGUI(RNGConfig config, M7RNGTracker tracker) {
        this.config = config;
        this.tracker = tracker;
    }
    
    /**
     * Display the main config menu
     */
    public void displayMainMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n");
        menu.append("§6=== Dungeon Profit Tracker Config ===").append("\n");
        menu.append("\n");
        menu.append("§eHUD Display Settings:").append("\n");
        menu.append("  §a1. Inventory Only").append("\n");
        menu.append("  §a2. World Only").append("\n");
        menu.append("  §a3. Both (Current: ").append(getHudScopeString()).append(")\n");
        menu.append("\n");
        menu.append("§eOptional Drop Tracking:").append("\n");
        menu.append("  §a4. Toggle Optional Drops (").append(tracker.getEnabledOptionals().size()).append("/4)\n");
        menu.append("\n");
        menu.append("§eManagement:").append("\n");
        menu.append("  §a5. Reset Statistics").append("\n");
        menu.append("  §a6. Close Menu").append("\n");
        menu.append("\n");
        menu.append("§7Type the number of your choice in chat.");
        
        System.out.println(menu.toString());
    }
    
    /**
     * Display optional drops menu
     */
    public void displayOptionalDropsMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n");
        menu.append("§6=== Optional Drop Tracking ===").append("\n");
        menu.append("§7(Select up to 4 additional drops to track)\n");
        menu.append("\n");
        
        List<String> optionals = new ArrayList<>(M7RNGTracker.getAvailableOptionals());
        int index = 1;
        for (String drop : optionals) {
            boolean enabled = tracker.getEnabledOptionals().contains(drop);
            String status = enabled ? "§a✓" : "§c✗";
            menu.append("  §a").append(index).append(". ").append(status).append(" §e").append(drop).append("\n");
            index++;
        }
        
        menu.append("\n");
        menu.append("  §a0. Back to Main Menu");
        
        System.out.println(menu.toString());
    }
    
    /**
     * Display core drops info
     */
    public void displayCoreDropsInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n");
        info.append("§6=== Core M7 RNG Drops (Always Tracked) ===").append("\n");
        info.append("\n");
        
        for (String drop : M7RNGTracker.getCoreDrops()) {
            int count = tracker.getDropCount(drop);
            info.append("  §e").append(drop).append(": §a").append(count).append("\n");
        }
        
        System.out.println(info.toString());
    }
    
    /**
     * Get current HUD scope as string
     */
    private String getHudScopeString() {
        return switch (config.getHudScope()) {
            case INVENTORY_ONLY -> "Inventory Only";
            case WORLD_ONLY -> "World Only";
            case BOTH -> "Both";
        };
    }
    
    /**
     * Set HUD scope from menu selection
     */
    public void setHudScopeFromSelection(int choice) {
        RNGConfig.HUDScope scope = switch (choice) {
            case 1 -> RNGConfig.HUDScope.INVENTORY_ONLY;
            case 2 -> RNGConfig.HUDScope.WORLD_ONLY;
            case 3 -> RNGConfig.HUDScope.BOTH;
            default -> config.getHudScope();
        };
        config.setHudScope(scope);
        System.out.println("§aHUD scope set to: " + getHudScopeString());
    }
}
