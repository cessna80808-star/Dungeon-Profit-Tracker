package com.cessna.dungeontracker.events;

import com.cessna.dungeontracker.data.DungeonRun;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

/**
 * Unified event handler for dungeon tracking
 * Supports both SkyHanni and Odin mods through event callbacks
 */
public class DungeonEventHandler {

    /**
     * Event fired when a dungeon run starts
     * Can be triggered by SkyHanni's DungeonStartEvent or Odin's equivalent
     */
    public static final Event<DungeonStartListener> DUNGEON_START = EventFactory.createArrayBacked(
            DungeonStartListener.class,
            (listeners) -> (floor, roomName) -> {
                for (DungeonStartListener listener : listeners) {
                    ActionResult result = listener.onDungeonStart(floor, roomName);
                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            }
    );

    /**
     * Event fired when a dungeon run completes
     * Can be triggered by SkyHanni's DungeonCompleteEvent or Odin's equivalent
     */
    public static final Event<DungeonCompleteListener> DUNGEON_COMPLETE = EventFactory.createArrayBacked(
            DungeonCompleteListener.class,
            (listeners) -> (run) -> {
                for (DungeonCompleteListener listener : listeners) {
                    ActionResult result = listener.onDungeonComplete(run);
                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            }
    );

    /**
     * Event fired when a kismet (guaranteed drop) is obtained
     * Can be triggered by SkyHanni's ItemDropEvent or Odin's equivalent
     */
    public static final Event<KismetObtainedListener> KISMET_OBTAINED = EventFactory.createArrayBacked(
            KismetObtainedListener.class,
            (listeners) -> (rngType) -> {
                for (KismetObtainedListener listener : listeners) {
                    ActionResult result = listener.onKismetObtained(rngType);
                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            }
    );

    /**
     * Event fired when RNG results are announced
     * Can be triggered by SkyHanni's chat parsing or Odin's event system
     */
    public static final Event<RngResultListener> RNG_RESULT = EventFactory.createArrayBacked(
            RngResultListener.class,
            (listeners) -> (rngType, success, itemName) -> {
                for (RngResultListener listener : listeners) {
                    ActionResult result = listener.onRngResult(rngType, success, itemName);
                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            }
    );

    @FunctionalInterface
    public interface DungeonStartListener {
        ActionResult onDungeonStart(String floor, String roomName);
    }

    @FunctionalInterface
    public interface DungeonCompleteListener {
        ActionResult onDungeonComplete(DungeonRun run);
    }

    @FunctionalInterface
    public interface KismetObtainedListener {
        ActionResult onKismetObtained(String rngType);
    }

    @FunctionalInterface
    public interface RngResultListener {
        ActionResult onRngResult(String rngType, boolean success, String itemName);
    }
}
