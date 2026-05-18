package com.cessna.m7tracker.integration;

public interface ModIntegration {
    /**
     * Check if the mod is currently loaded
     */
    boolean isLoaded();

    /**
     * Initialize the integration and hook into mod events
     */
    void initialize();

    /**
     * Clean up and remove event listeners
     */
    void shutdown();

    /**
     * Get the name of the integrated mod
     */
    String getModName();
}
