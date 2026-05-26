package com.dungeon.tracker.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.dungeon.tracker.DungeonProfitTrackerMod;
import com.dungeon.tracker.config.RNGConfig;
import com.dungeon.tracker.rng.M7RNGTracker;
import com.dungeon.tracker.gui.ConfigGUI;
import com.dungeon.tracker.hud.RNGHudRenderer;
import com.dungeon.tracker.listener.ChatEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class DungeonProfitTrackerClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DungeonProfitTrackerMod.MOD_ID);
    private static RNGConfig config;
    private static M7RNGTracker tracker;
    private static RNGHudRenderer hudRenderer;
    private static ConfigGUI configGUI;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Dungeon Profit Tracker client...");
        
        // Initialize config and tracker
        config = RNGConfig.load();
        tracker = new M7RNGTracker();
        hudRenderer = new RNGHudRenderer(tracker, config);
        configGUI = new ConfigGUI(config, tracker);
        
        // Register chat event listener
        ChatEventListener chatListener = new ChatEventListener(tracker);
        chatListener.register();
        
        // Register HUD render callback
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            hudRenderer.render(tickDelta);
        });
        
        // Register client tick for inventory detection
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                boolean inventoryOpen = client.currentScreen != null && client.currentScreen.passEvents == false;
                hudRenderer.setInventoryOpen(inventoryOpen);
            }
        });
        
        // Register /dprof command
        registerCommands();
        
        LOGGER.info("Dungeon Profit Tracker client initialized!");
    }
    
    private void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            // /dprof config command
            dispatcher.register(
                com.mojang.brigadier.builder.LiteralArgumentBuilder.literal("dprof")
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.literal("config")
                        .executes(context -> {
                            configGUI.displayMainMenu();
                            return 1;
                        }))
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.literal("stats")
                        .executes(context -> {
                            configGUI.displayCoreDropsInfo();
                            return 1;
                        }))
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.literal("reset")
                        .executes(context -> {
                            tracker.resetStats();
                            config.resetConfig();
                            LOGGER.info("[Dungeon Profit Tracker] All statistics and config reset!");
                            return 1;
                        }))
            );
        });
    }
    
    public static RNGConfig getConfig() {
        return config;
    }
    
    public static M7RNGTracker getTracker() {
        return tracker;
    }
}
