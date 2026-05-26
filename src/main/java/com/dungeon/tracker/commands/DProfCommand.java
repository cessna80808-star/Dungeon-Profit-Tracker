package com.dungeon.tracker.commands;

import com.dungeon.tracker.config.RNGConfig;
import com.dungeon.tracker.gui.ConfigGUI;
import com.dungeon.tracker.rng.M7RNGTracker;

/**
 * Main command handler for /dprof config
 */
public class DProfCommand {
    private final RNGConfig config;
    private final M7RNGTracker tracker;
    private final ConfigGUI gui;
    
    public DProfCommand(RNGConfig config, M7RNGTracker tracker) {
        this.config = config;
        this.tracker = tracker;
        this.gui = new ConfigGUI(config, tracker);
    }
    
    /**
     * Handle /dprof config command
     */
    public void executeConfig() {
        gui.displayMainMenu();
    }
    
    /**
     * Handle /dprof stats command
     */
    public void executeStats() {
        gui.displayCoreDropsInfo();
    }
    
    /**
     * Handle /dprof reset command
     */
    public void executeReset() {
        tracker.resetStats();
        config.resetConfig();
        System.out.println("§a✓ All statistics and config reset!");
    }
    
    /**
     * Process user input from menu selections
     */
    public void handleMenuInput(int choice) {
        switch (choice) {
            case 1, 2, 3 -> gui.setHudScopeFromSelection(choice);
            case 4 -> gui.displayOptionalDropsMenu();
            case 5 -> executeReset();
            case 6 -> System.out.println("§aConfig menu closed.");
            default -> System.out.println("§cInvalid choice.");
        }
    }
}
