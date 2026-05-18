package com.dungeon.tracker.integration;

/**
 * Interface for mod integrations (SkyHanni, Odin, etc.)
 * Provides abstraction layer for different mod APIs
 */
public interface ModIntegration {
    
    /**
     * Check if the mod is loaded and available
     */
    boolean isModLoaded();
    
    /**
     * Get the mod name
     */
    String getModName();
    
    /**
     * Register event handlers for dungeon tracking
     */
    void registerEventHandlers();
    
    /**
     * Unregister event handlers
     */
    void unregisterEventHandlers();
    
    /**
     * Get current dungeon floor if in a dungeon
     */
    Integer getCurrentFloor();
    
    /**
     * Get player's current balance/coins
     */
    Long getCurrentBalance();
    
    /**
     * Parse dungeon completion message and extract profit
     */
    Long parseDungeonProfit(String message);
    
    /**
     * Parse RNG type from completion message
     */
    String parseRngType(String message);
    
    /**
     * Parse kismet count from completion message
     */
    Integer parseKismetCount(String message);
}
