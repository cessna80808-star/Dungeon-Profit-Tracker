package com.dungeon.tracker;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonProfitTrackerMod implements ModInitializer {
    public static final String MOD_ID = "dungeon-profit-tracker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Dungeon Profit Tracker initialized!");
    }
}
