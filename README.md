# Dungeon Profit Tracker - M7 RNG Edition

## Overview

A focused Minecraft Fabric mod for tracking Master Mode VII (M7) RNG drops with a clean, configurable HUD and persistent statistics.

## Features

### 🎯 Core M7 RNG Tracking (Always Enabled)
- **Shadow Warp** (Wither Scroll)
- **Wither Shield** (Wither Scroll)
- **Implosion** (Wither Scroll)
- **Necron's Handle**
- **Fifth Master Star**

### ⚙️ Optional Drop Tracking
Enable up to 4 additional drops from this list:
- Dark Claymore
- Auto Recombobulator
- Recombobulator 3000
- Master Skull - Tier 5
- Enchanted Book (One For All I)
- Enchanted Book (Soul Eater I)
- Necron Dye
- Sadan Dye

### 📊 HUD Display
- **Inventory Only** - Show stats when inventory is open
- **World Only** - Show stats while exploring/dungeons
- **Both** - Always display HUD

### 💾 Persistent Storage
- All statistics auto-saved to JSON
- Config settings remembered across sessions
- One-command reset available

## Commands

### `/dprof config`
Opens the configuration GUI with options to:
- Toggle HUD display scope
- Enable/disable optional drops (max 4)
- Reset all statistics

### `/dprof stats`
Displays current core drop statistics

### `/dprof reset`
Resets all statistics and config to defaults

## Installation

1. Download the latest JAR from releases
2. Place in `.minecraft/mods` folder
3. Launch Minecraft with Fabric loader
4. Type `/dprof config` to get started

## Configuration

Configs are stored in: `~/.minecraft/dungeon-profit-tracker/rng-config.json`

```json
{
  "hudScope": "BOTH",
  "enabledOptionalDrops": ["Dark Claymore", "Auto Recombobulator"],
  "totalStats": 1337
}
```

## Architecture

```
M7RNGTracker          - Core tracking logic & statistics
RNGConfig            - Persistent configuration management
ConfigGUI            - Menu system for user interaction
RNGHudRenderer       - HUD display rendering
DProfCommand         - Command handler & orchestration
```

## Version

**Minecraft:** 1.21.1 Fabric  
**Language:** Java  
**License:** MIT
