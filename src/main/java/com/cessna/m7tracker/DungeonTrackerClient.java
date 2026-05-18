package com.cessna.m7tracker;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.cessna.m7tracker.event.ChatEventListener;
import com.cessna.m7tracker.hud.DungeonHud;
import com.cessna.m7tracker.integration.IntegrationManager;
import com.cessna.m7tracker.tracker.StatisticsTracker;

public class DungeonTrackerClient implements ClientModInitializer {
    public static final String MOD_ID = "m7-dungeon-tracker";

    @Override
    public void onInitializeClient() {
        System.out.println("[DungeonTracker] Initializing Dungeon Profit Tracker...");
        
        // Initialize statistics tracker
        StatisticsTracker.getInstance();
        
        // Initialize integrations
        IntegrationManager manager = IntegrationManager.getInstance();
        manager.initializeAll();
        
        // Initialize standalone chat listener
        ChatEventListener.init();
        System.out.println("[DungeonTracker] Chat event listener initialized");
        
        // Register HUD renderer
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            DungeonHud.render(matrices, tickDelta);
        });
        System.out.println("[DungeonTracker] HUD renderer registered");
        
        System.out.println("[DungeonTracker] ✓ Initialization complete!");
    }
}
