package com.dungeon.tracker.rng;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * M7 RNG Drop Tracker - Focused tracking for high-value Master Mode VII drops.
 */
public class M7RNGTracker {
    private static final Set<String> CORE_DROPS = new HashSet<>();
    private static final Set<String> OPTIONAL_DROPS = new HashSet<>();
    
    // Drop statistics
    private final Map<String, Integer> dropCounts = new HashMap<>();
    private final Map<String, Long> dropTimestamps = new HashMap<>();
    private final Set<String> enabledOptionals = new HashSet<>();
    
    static {
        // Core M7 RNG drops (always tracked)
        CORE_DROPS.add("Shadow Warp");
        CORE_DROPS.add("Wither Scroll");
        CORE_DROPS.add("Wither Shield");
        CORE_DROPS.add("Implosion");
        CORE_DROPS.add("Necron's Handle");
        CORE_DROPS.add("Fifth Master Star");
        
        // Optional drops (configurable via GUI)
        OPTIONAL_DROPS.add("Dark Claymore");
        OPTIONAL_DROPS.add("Auto Recombobulator");
        OPTIONAL_DROPS.add("Recombobulator 3000");
        OPTIONAL_DROPS.add("Master Skull - Tier 5");
        OPTIONAL_DROPS.add("Enchanted Book (One For All I)");
        OPTIONAL_DROPS.add("Enchanted Book (Soul Eater I)");
        OPTIONAL_DROPS.add("Necron Dye");
        OPTIONAL_DROPS.add("Sadan Dye");
    }
    
    public M7RNGTracker() {
        // Initialize drop counts for all tracked drops
        for (String drop : CORE_DROPS) {
            dropCounts.put(drop, 0);
        }
    }
    
    /**
     * Track a drop if it matches core or enabled optional drops
     */
    public boolean trackDrop(String dropName) {
        String normalized = normalizeDropName(dropName);
        
        // Check if it's a core drop
        if (matchesDropList(normalized, CORE_DROPS)) {
            recordDrop(dropName);
            return true;
        }
        
        // Check if it's an enabled optional drop
        if (isOptionalEnabled(normalized)) {
            recordDrop(dropName);
            return true;
        }
        
        return false;
    }
    
    /**
     * Record a drop in statistics
     */
    private void recordDrop(String dropName) {
        dropCounts.merge(dropName, 1, Integer::sum);
        dropTimestamps.put(dropName, System.currentTimeMillis());
    }
    
    /**
     * Enable an optional drop for tracking (max 4)
     */
    public boolean enableOptionalDrop(String dropName) {
        if (enabledOptionals.size() >= 4 && !enabledOptionals.contains(dropName)) {
            return false; // Max 4 optional drops
        }
        
        String normalized = normalizeDropName(dropName);
        if (OPTIONAL_DROPS.contains(normalized)) {
            enabledOptionals.add(normalized);
            if (!dropCounts.containsKey(dropName)) {
                dropCounts.put(dropName, 0);
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * Disable an optional drop
     */
    public boolean disableOptionalDrop(String dropName) {
        String normalized = normalizeDropName(dropName);
        return enabledOptionals.remove(normalized);
    }
    
    /**
     * Check if an optional drop is enabled
     */
    private boolean isOptionalEnabled(String normalizedName) {
        return enabledOptionals.contains(normalizedName);
    }
    
    /**
     * Match drop name against a set (case-insensitive, partial match)
     */
    private boolean matchesDropList(String dropName, Set<String> dropList) {
        return dropList.stream()
            .anyMatch(drop -> dropName.equalsIgnoreCase(drop) || 
                            dropName.contains(drop.toLowerCase()));
    }
    
    /**
     * Normalize drop name for matching
     */
    private String normalizeDropName(String dropName) {
        return dropName.trim().toLowerCase();
    }
    
    /**
     * Get drop count for a specific drop
     */
    public int getDropCount(String dropName) {
        return dropCounts.getOrDefault(dropName, 0);
    }
    
    /**
     * Get all tracked drops with counts
     */
    public Map<String, Integer> getAllDropCounts() {
        return new HashMap<>(dropCounts);
    }
    
    /**
     * Get core drops
     */
    public Set<String> getTrackedDrops() {
        Set<String> tracked = new HashSet<>(CORE_DROPS);
        tracked.addAll(enabledOptionals);
        return tracked;
    }
    
    /**
     * Reset all statistics
     */
    public void resetStats() {
        dropCounts.replaceAll((k, v) -> 0);
        dropTimestamps.clear();
    }
    
    /**
     * Get enabled optional drops
     */
    public Set<String> getEnabledOptionals() {
        return new HashSet<>(enabledOptionals);
    }
    
    /**
     * Get available optional drops
     */
    public static Set<String> getAvailableOptionals() {
        return new HashSet<>(OPTIONAL_DROPS);
    }
    
    /**
     * Get core drops
     */
    public static Set<String> getCoreDrops() {
        return new HashSet<>(CORE_DROPS);
    }
}
