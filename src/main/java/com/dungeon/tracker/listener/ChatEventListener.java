package com.dungeon.tracker.listener;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import com.dungeon.tracker.rng.M7RNGTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dungeon.tracker.DungeonProfitTrackerMod;

/**
 * Listens for chat messages and detects M7 RNG drops
 */
public class ChatEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DungeonProfitTrackerMod.MOD_ID);
    private final M7RNGTracker tracker;
    
    public ChatEventListener(M7RNGTracker tracker) {
        this.tracker = tracker;
    }
    
    /**
     * Register chat listener
     */
    public void register() {
        // This is a simplified approach - actual implementation depends on your needs
        LOGGER.info("Chat event listener registered for M7 RNG tracking");
    }
    
    /**
     * Process chat message for RNG drops
     */
    public void onChatMessage(String message) {
        // Parse message for drop names
        String normalizedMessage = message.toLowerCase();
        
        // Check core drops
        for (String drop : M7RNGTracker.getCoreDrops()) {
            if (normalizedMessage.contains(drop.toLowerCase())) {
                tracker.trackDrop(drop);
                LOGGER.info("[Dungeon Profit Tracker] Tracked drop: {}", drop);
                break;
            }
        }
        
        // Check optional drops
        for (String drop : M7RNGTracker.getAvailableOptionals()) {
            if (normalizedMessage.contains(drop.toLowerCase())) {
                tracker.trackDrop(drop);
                LOGGER.info("[Dungeon Profit Tracker] Tracked drop: {}", drop);
                break;
            }
        }
    }
}
