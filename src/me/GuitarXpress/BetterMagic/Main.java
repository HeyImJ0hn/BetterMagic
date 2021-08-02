package me.GuitarXpress.BetterMagic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public List<String> UUIDs = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getServer().getPluginManager().registerEvents(new SpellbookManager(this), this);
		getServer().getPluginManager().registerEvents(new CustomCrafting(this), this);
		getServer().getPluginManager().registerEvents(new VillagerTrader(this), this);
		getCommand("magic").setExecutor(new Commands(this));
		getCommand("magic").setTabCompleter(new TabComplete());
		ItemManager.init();
		VillagerTrader.init();
		getConfig().options().copyDefaults(true);

		if (this.getConfig().get("Variables.shardDropChance") != null) {
			Events.shardDropChance = this.getConfig().getDouble("Variables.shardDropChance");
			System.out.println("[§9BetterMagic§r] §aLoaded Variables.");
		}
		
		if (this.getConfig().get("PlayerLevels") != null) {
			ConfigurationSection cfg = this.getConfig().getConfigurationSection("PlayerLevels");
			for (String key : cfg.getKeys(false)) {
				UUIDs.add(key);
			}
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				int level = this.getConfig().getInt("PlayerLevels." + UUID + ".level");
				double xp = this.getConfig().getDouble("PlayerLevels." + UUID + ".xp");
				Events.skillManagerHashMap.put(UUIDs.get(i), new SkillManager(level, xp));
			}
			System.out.println("[§9BetterMagic§r] §aLoaded Skill Levels.");
		}
		
//		if (this.getConfig().contains("data"))
//			for (int i = 0; i < UUIDs.size(); i++)
//				SpellbookManager.restoreSpellbooks(UUIDs.get(i));
		
		if (this.getConfig().get("Blocks.Altar.Locations") != null) {
			Events.altarLocations = (ArrayList<Location>) this.getConfig().getList("Blocks.Altar.Locations");
			System.out.println("[§9BetterMagic§r] §aLoaded Block Locations.");
		}
		
		if (this.getConfig().get("PlayerUnlockables") != null) {
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				boolean util = this.getConfig().getBoolean("PlayerUnlockables." + UUID + ".Utility");
				Events.utilityUnlocked.put(UUID, util);
				boolean ancient = this.getConfig().getBoolean("PlayerUnlockables." + UUID + ".Ancient");
				Events.ancientUnlocked.put(UUID, ancient);
			}
			System.out.println("[§9BetterMagic§r] §aLoaded Unlockables Status.");
		}
		
		if (this.getConfig().get("Booleans") != null) {
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				boolean firstShard = this.getConfig().getBoolean("Booleans." + UUID + ".gotFirstShard");
				Events.gotFirstShard.put(UUID, firstShard);
				boolean shard = this.getConfig().getBoolean("Booleans." + UUID + ".canGetShards");
				Events.canGetShards.put(UUID, shard);
				boolean boughtSpellbook = this.getConfig().getBoolean("Booleans." + UUID + ".boughtSpellbook");
				Events.boughtSpellbook.put(UUID, boughtSpellbook);
				boolean boughtUtilityTome = this.getConfig().getBoolean("Booleans." + UUID + ".boughtUtilityTome");
				Events.boughtUtilityTome.put(UUID, boughtUtilityTome);
				boolean boughtAncientTome = this.getConfig().getBoolean("Booleans." + UUID + ".boughtAncientTome");
				Events.boughtAncientTome.put(UUID, boughtAncientTome);
			}
		}

//		this.saveDefaultConfig();
		this.saveConfig();
		System.out.println("[§9BetterMagic§r] §2Enabled!");
		Bukkit.broadcastMessage("[§9BetterMagic§r] §eThis plugin is in development and your data may be reset multiple times during this stage.");
	}

	@Override
	public void onDisable() {
//		if (!SpellbookManager.spellbookPage1.isEmpty())
//			for (int i = 0; i < UUIDs.size(); i++)
//				SpellbookManager.saveSpellbooks(UUIDs.get(i));
		
		if (!Events.altarLocations.isEmpty()) {
			this.getConfig().set("Blocks.Altar.Locations", Events.altarLocations);
			System.out.println("[§9BetterMagic§r] §aSaved Block Locations.");
		}
		
		if (!Events.skillManagerHashMap.isEmpty() && !UUIDs.isEmpty()) {
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				int level = Events.skillManagerHashMap.get(UUID).getLevel();
				double xp = Events.skillManagerHashMap.get(UUID).getXp();
				this.getConfig().set("PlayerLevels." + UUID + ".level", level);
				this.getConfig().set("PlayerLevels." + UUID + ".xp", xp);
				this.saveConfig();
			}
			System.out.println("[§9BetterMagic§r] §aSaved Skill Levels.");
		}
		
		if (!Events.utilityUnlocked.isEmpty() && !UUIDs.isEmpty()) {
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				boolean util = Events.utilityUnlocked.get(UUID);
				boolean ancient = Events.ancientUnlocked.get(UUID);
				this.getConfig().set("PlayerUnlockables." + UUID + ".Utility", util);
				this.getConfig().set("PlayerUnlockables." + UUID + ".Ancient", ancient);
				this.saveConfig();
			}
			System.out.println("[§9BetterMagic§r] §aSaved Unlockables Status.");
		}
		
		if (!Events.canGetShards.isEmpty() && !UUIDs.isEmpty()) {
			for (int i = 0; i < UUIDs.size(); i++) {
				String UUID = UUIDs.get(i);
				boolean shards = Events.canGetShards.get(UUID);
				boolean firstShard = Events.gotFirstShard.get(UUID);
				boolean boughtSpellbook = Events.boughtSpellbook.get(UUID);
				boolean boughtUtilityTome = Events.boughtUtilityTome.get(UUID);
				boolean boughtAncientTome = Events.boughtAncientTome.get(UUID);
				this.getConfig().set("Booleans." + UUID + ".gotFirstShard", firstShard);
				this.getConfig().set("Booleans." + UUID + ".canGetShards", shards);
				this.getConfig().set("Booleans." + UUID + ".boughtSpellbook", boughtSpellbook);
				this.getConfig().set("Booleans." + UUID + ".boughtUtilityTome", boughtUtilityTome);
				this.getConfig().set("Booleans." + UUID + ".boughtAncientTome", boughtAncientTome);
				this.saveConfig();
			}
		}
		
		System.out.println("[§9BetterMagic§r] §4Disabled!");
	}

}
