# Dungeon Profit Tracker - M7 RNG Edition

## Overview

A focused Minecraft Fabric mod for tracking Master Mode VII (M7) RNG drops with a clean, configurable HUD and persistent statistics.

**MC Version:** 1.21.1  
**Fabric Version:** 0.19.2  
**Language:** Java

## Quick Start

1. Download the latest JAR from releases
2. Place in `.minecraft/mods` folder
3. Launch Minecraft with Fabric
4. Type `/dprof config` in-game to configure

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
- View core drop statistics
- Reset all statistics

### `/dprof stats`
Displays current core drop statistics in chat

### `/dprof reset`
Resets all statistics and config to defaults

## Configuration

Configs are stored in: `~/.minecraft/dungeon-profit-tracker/rng-config.json`

Example config:
```json
{
  "hudScope": "BOTH",
  "enabledOptionalDrops": ["Dark Claymore", "Auto Recombobulator"],
  "totalStats": 1337
}
```

## Project Structure

```
src/main/java/com/dungeon/tracker/
├── DungeonProfitTrackerMod.java      # Main mod entry point
├── client/
│   └── DungeonProfitTrackerClient.java # Client initialization & commands
├── rng/
│   └── M7RNGTracker.java              # Core tracking logic
├── config/
│   └── RNGConfig.java                 # Persistent configuration
├── gui/
│   └── ConfigGUI.java                 # Menu system
├── hud/
│   └── RNGHudRenderer.java             # HUD display
├── listener/
│   └── ChatEventListener.java          # Chat event parsing
└── commands/
    └── DProfCommand.java               # Command orchestration
```

## Building from Source

```bash
./gradlew build
```

JAR output: `build/libs/dungeon-profit-tracker-1.0.0.jar`

## Requirements

- Minecraft 1.21.1
- Fabric Loader 0.19.2+
- Fabric API 0.100.0+
- Java 21+

## License

MIT License - See LICENSE file

## Changelog

### v1.0.0 (2026-05-18)
- Initial release with focused M7 RNG tracking
- 5 core drops tracked by default
- Optional drops system (select up to 4)
- Configurable HUD display
- Persistent JSON configuration
- Full command system

## Support

For issues or suggestions, open an issue on [GitHub](https://github.com/cessna80808-star/Dungeon-Profit-Tracker/issues)
