package com.cessna.m7tracker.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntegrationManager {
    private static final List<ModIntegration> integrations = new ArrayList<>();
    private static final List<ModIntegration> activeIntegrations = new ArrayList<>();

    static {
        // Register all available integrations
        integrations.add(new SkyHanniIntegration());
        integrations.add(new OdinIntegration());
    }

    /**
     * Initialize all detected mod integrations
     */
    public static void initializeAll() {
        System.out.println("[Dungeon Tracker] Scanning for compatible mods...");
        activeIntegrations.clear();

        for (ModIntegration integration : integrations) {
            if (integration.isLoaded()) {
                System.out.println("[Dungeon Tracker] Found " + integration.getModName() + "!");
                integration.initialize();
                activeIntegrations.add(integration);
            }
        }

        if (activeIntegrations.isEmpty()) {
            System.out.println("[Dungeon Tracker] No compatible mods detected. Running in standalone mode.");
        } else {
            System.out.println("[Dungeon Tracker] " + activeIntegrations.size() + " integration(s) active.");
        }
    }

    /**
     * Shutdown all active integrations
     */
    public static void shutdownAll() {
        for (ModIntegration integration : activeIntegrations) {
            integration.shutdown();
        }
        activeIntegrations.clear();
    }

    /**
     * Get a specific integration by name
     */
    public static Optional<ModIntegration> getIntegration(String modName) {
        return activeIntegrations.stream()
                .filter(i -> i.getModName().equalsIgnoreCase(modName))
                .findFirst();
    }

    /**
     * Check if a specific integration is active
     */
    public static boolean isIntegrationActive(String modName) {
        return getIntegration(modName).isPresent();
    }

    /**
     * Get all active integrations
     */
    public static List<ModIntegration> getActiveIntegrations() {
        return new ArrayList<>(activeIntegrations);
    }
}
