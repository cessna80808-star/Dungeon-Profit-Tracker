package com.cessna.m7tracker.integration;

import com.cessna.m7tracker.DungeonRun;
import com.cessna.m7tracker.StatisticsTracker;

public class SkyHanniIntegration implements ModIntegration {
    private boolean initialized = false;

    @Override
    public boolean isLoaded() {
        try {
            Class.forName("at.hannibal2.skyhanni.SkyHanniMod");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void initialize() {
        if (!isLoaded()) {
            System.out.println("[Dungeon Tracker] SkyHanni not found, skipping integration");
            return;
        }

        try {
            // Hook into SkyHanni's event bus
            // TODO: Implement actual SkyHanni event listeners
            // This would listen for:
            // - DungeonStartEvent
            // - DungeonCompleteEvent
            // - ProfitEvent
            // - RngEvent
            initialized = true;
            System.out.println("[Dungeon Tracker] SkyHanni integration initialized");
        } catch (Exception e) {
            System.err.println("[Dungeon Tracker] Failed to initialize SkyHanni integration: " + e.getMessage());
        }
    }

    @Override
    public void shutdown() {
        if (initialized) {
            // Unregister event listeners
            initialized = false;
            System.out.println("[Dungeon Tracker] SkyHanni integration shutdown");
        }
    }

    @Override
    public String getModName() {
        return "SkyHanni";
    }
}
