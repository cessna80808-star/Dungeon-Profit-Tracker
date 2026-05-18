package com.dungeon.tracker.hud;

import com.dungeon.tracker.config.RNGConfig;
import com.dungeon.tracker.rng.M7RNGTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HUD renderer for displaying M7 RNG statistics.
 */
public class RNGHudRenderer {
    private final M7RNGTracker tracker;
    private final RNGConfig config;
    private boolean isInventoryOpen = false;
    
    public RNGHudRenderer(M7RNGTracker tracker, RNGConfig config) {
        this.tracker = tracker;
        this.config = config;
    }
    
    /**
     * Render HUD based on current scope setting
     */
    public void render(float partialTicks) {
        RNGConfig.HUDScope scope = config.getHudScope();
        
        // Check visibility based on scope
        boolean shouldRender = switch (scope) {
            case INVENTORY_ONLY -> isInventoryOpen;
            case WORLD_ONLY -> !isInventoryOpen;
            case BOTH -> true;
        };
        
        if (shouldRender) {
            renderHudDisplay();
        }
    }
    
    /**
     * Internal HUD rendering
     */
    private void renderHudDisplay() {
        List<String> lines = new ArrayList<>();
        lines.add("§6M7 RNG Tracker");
        lines.add("§7─────────────");
        
        Map<String, Integer> counts = tracker.getAllDropCounts();
        int totalRNG = 0;
        
        // Display core drops
        lines.add("§eCore Drops:");
        for (String drop : M7RNGTracker.getCoreDrops()) {
            int count = counts.getOrDefault(drop, 0);
            totalRNG += count;
            lines.add(String.format("  §7%s: §a%d", drop, count));
        }
        
        // Display enabled optional drops
        if (!tracker.getEnabledOptionals().isEmpty()) {
            lines.add("§eOptional Drops:");
            for (String drop : tracker.getEnabledOptionals()) {
                int count = counts.getOrDefault(drop, 0);
                totalRNG += count;
                lines.add(String.format("  §7%s: §a%d", drop, count));
            }
        }
        
        lines.add("§7─────────────");
        lines.add(String.format("§6Total RNG: §a%d", totalRNG));
        
        // Render to screen (coordinates can be adjusted)
        renderText(lines, 10, 10);
    }
    
    /**
     * Render text lines to screen
     */
    private void renderText(List<String> lines, int x, int y) {
        // This method would integrate with Minecraft's rendering system
        // Implementation depends on Fabric version and rendering framework
        for (int i = 0; i < lines.size(); i++) {
            // drawString(lines.get(i), x, y + (i * 10));
            System.out.println(lines.get(i));
        }
    }
    
    /**
     * Set inventory state
     */
    public void setInventoryOpen(boolean open) {
        this.isInventoryOpen = open;
    }
}
