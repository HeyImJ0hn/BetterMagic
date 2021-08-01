# BetterMagic
BetterMagic Minecraft Plugin for Spigot/Paper 1.17 by GuitarXpress (HeyImJ0hn)

##### Permission nodes:
- "magic.spawn" - Permission to spawn plugin related items
- "magic.xp" - Permission to give and remove xp from players
- "magic.summon" - Permission to summon plugin related mobs

##### Commands:

###### Regular Commands
- /magic help - Displays all available commands if player has permissions for them.
- /magic level - Displays player's level and xp.
- /magic toggle - Toggles shard drops (Default is ON).
- /magic guide - Adds §9BetterMagic Guide Book §eto inventory.

###### "magic.spawn" Permission Required
- /magic spellbook - Gives player the combat spellbook.
- /magic altar - Gives player the crafting altar.
- /magic shards - Gives player one of each shard.
- /magic unlocks - Gives player items to unlock content (tomes).
- /magic unreleased - Gives player WIP items.

###### "magic.xp" Permission Required
- /magic addxp «xp» «username» - Adds «xp» to specified player.
- /magic removexp «xp» «username» - Removes «xp» from specified player. (WIP)

###### "magic.summon" Permission Required
- /magic summon/kill wizard - Spawns/Kills Wizard Villager.

##### Additional Info:
Player information such as their skill level and spellbook are created/loaded when the player joins. 
Please relog after reloading the plugin.
