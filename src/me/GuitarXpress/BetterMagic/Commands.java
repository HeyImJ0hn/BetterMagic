package me.GuitarXpress.BetterMagic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	Main plugin;

	public Commands(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!(sender instanceof Player))
			return true;

		Player player = (Player) sender;

		if (!cmd.getName().equalsIgnoreCase("magic"))
			return true;

		if (args.length == 0) {
			player.sendMessage(prefix() + "§ePlugin being developed by §4GuitarXpress§e.\n- Version 1.0.0 BETA\nPlease to submit any feedback on discord.\nFor help use §6/magic help§e.");
		}

		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("help")) {
				player.sendMessage(prefix() + "§eHelpful Commands: ");
				player.sendMessage("§6/magic level §e- Displays player's level and xp.");
				player.sendMessage("§6/magic toggle §e- Toggles shard drops (Default is ON).");
				player.sendMessage("§6/magic guide §e- Adds §9BetterMagic Guide Book §eto inventory.");
				if (player.hasPermission("magic.spawn")) {
					player.sendMessage(prefix() + "§eSpawn Item Commands: ");
					player.sendMessage("§6/magic spellbook §e- Gives player the combat spellbook.");
					player.sendMessage("§6/magic altar §e- Gives player the crafting altar.");
					player.sendMessage("§6/magic shards §e- Gives player one of each shard.");
					player.sendMessage("§6/magic unlocks §e- Gives player items to unlock content (tomes).");
//					player.sendMessage("§6/magic orb §e- Gives player the ancient orb (WIP).");
					player.sendMessage("§6/magic unreleased §e- Gives player WIP items.");
				}
				if (player.hasPermission("magic.xp")) {
					player.sendMessage(prefix() + "§eXP Commands: ");
					player.sendMessage("§6/magic addxp «xp» «username» §e- Adds «xp» to specified player.");
					player.sendMessage(
							"§6/magic removexp «xp» «username» §e- Removes «xp» from specified player. §4(WIP)");
				}
				if (player.hasPermission("magic.summon")) {
					player.sendMessage(prefix() + "§eSummon Commands: ");
					player.sendMessage("§6/magic summon/kill wizard §e- Spawns/Kills Wizard Villager.");
				}
				return true;
			}

			if (args[0].equalsIgnoreCase("level")) {
				if (args.length == 1) {
					SkillManager manager = new SkillManager(
							Events.skillManagerHashMap.get(player.getUniqueId().toString()).getLevel(),
							Events.skillManagerHashMap.get(player.getUniqueId().toString()).getXp());
					manager.createBoard(player);
					return true;
				} else if (args.length == 2) {
					Player p = plugin.getServer().getPlayer(args[1]);
					if (p == null) {
						player.sendMessage(prefix() + "§ePlayer is not in BetterMagic Data or is offline.");
						return true;
					}
					SkillManager manager = new SkillManager(
							Events.skillManagerHashMap.get(p.getUniqueId().toString()).getLevel(),
							Events.skillManagerHashMap.get(p.getUniqueId().toString()).getXp());
					manager.createBoard(player);
					player.sendMessage(prefix() + "§eDisplaying Skill Info for §6" + p.getName());
					return true;

				}
			}
			
			if (args[0].equalsIgnoreCase("toggle")) {
				if (Events.canGetShards.get(player.getUniqueId().toString())) {
					Events.canGetShards.put(player.getUniqueId().toString(), false);
					player.sendMessage(prefix() + "§eYou will no longer receive shards.");
				} else {
					Events.canGetShards.put(player.getUniqueId().toString(), true);
					player.sendMessage(prefix() + "§eYou will now receive shards.");
				}
				return true;
			}

			if (player.hasPermission("magic.spawn")) {
				if (args[0].equalsIgnoreCase("spellbook")) {
					player.getInventory().addItem(ItemManager.spellbook);
					player.sendMessage(prefix() + "§eAdded Spellbook to inventory.");
					return true;
				}
				if (args[0].equalsIgnoreCase("altar")) {
					player.getInventory().addItem(ItemManager.altarItem);
					player.sendMessage(prefix() + "§eAdded §5Altar §eto inventory.");
					return true;
				}
				if (args[0].equalsIgnoreCase("guide")) {
					player.getInventory().addItem(ItemManager.helpBook);
					player.sendMessage(prefix() + "§eYou received the §9BetterMagic Quick Guide §eto your inventory.");
					return true;
				}
				if (args[0].equalsIgnoreCase("shards")) {
					player.sendMessage(prefix() + "§eAdded shards to inventory.");
					player.getInventory().addItem(ItemManager.airShard);
					player.getInventory().addItem(ItemManager.fireShard);
					player.getInventory().addItem(ItemManager.waterShard);
					player.getInventory().addItem(ItemManager.earthShard);
					player.getInventory().addItem(ItemManager.balanceCrystal);
					player.getInventory().addItem(ItemManager.ancientShard);
					return true;
				}
				if (args[0].equalsIgnoreCase("unlocks")) {
					player.sendMessage(prefix() + "§eAdded unlocks to inventory.");
					player.getInventory().addItem(ItemManager.ancientTome);
					player.getInventory().addItem(ItemManager.tomeOfBalance);
					return true;
				}
//				if (args[0].equalsIgnoreCase("orb")) {
//					player.sendMessage(prefix() + "§eAdded §5Ancient Orb §eto inventory.");
//					player.getInventory().addItem(ItemManager.ancientOrb);
//					return true;
//				}
				if (args[0].equalsIgnoreCase("unreleased")) {
					player.sendMessage(prefix() + "§eAdded &4WIP items §eto inventory.");
					player.getInventory().addItem(ItemManager.unknownItem);
					player.getInventory().addItem(ItemManager.ancientOrb);
					return true;
				}
			} else {
				player.sendMessage(prefix() + "§4You do not have access to that command.");
				return true;
			}
			if (player.hasPermission("magic.xp")) {
				if (args[0].equalsIgnoreCase("addxp")) {
					if (args.length == 1) {
						player.sendMessage(prefix() + "§cUsage: /magic addxp «xp» «username»");
						return true;
					} else if (args.length == 2) {
						Events.skillManagerHashMap.get(player.getUniqueId().toString()).addXp(player,
								Double.valueOf(args[1]));
						return true;
					} else if (args.length == 3) {
						Player p = plugin.getServer().getPlayer(args[2]);
						if (p == null) {
							player.sendMessage(prefix() + "§ePlayer is not in BetterMagic Data or is offline.");
							return true;
						}
						Events.skillManagerHashMap.get(player.getUniqueId().toString()).addXp(p,
								Double.valueOf(args[1]));
						player.sendMessage(prefix() + "§eAdded §6" + args[1] + "§exp to §6" + p.getName());
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("removexp")) {
					if (args.length == 1) {
						player.sendMessage(prefix() + "§cUsage: /magic removexp «xp» «username»");
						return true;
					} else if (args.length == 2) {
						Events.skillManagerHashMap.get(player.getUniqueId().toString())
								.removeXp(Double.valueOf(args[1]));
						return true;
					} else if (args.length == 3) {
						Player p = plugin.getServer().getPlayer(args[2]);
						if (p == null) {
							player.sendMessage(prefix() + "§ePlayer is not in BetterMagic Data or is offline.");
							return true;
						}
						Events.skillManagerHashMap.get(p.getUniqueId().toString()).removeXp(Double.valueOf(args[1]));
						player.sendMessage(prefix() + "§eRemoved §6" + args[1] + "§exp from §6" + p.getName());
						return true;
					}
				}
			} else {
				player.sendMessage(prefix() + "§4You do not have access to that command.");
				return true;
			}

			if (player.hasPermission("magic.summon")) {
				if (args[0].equalsIgnoreCase("summon")) {
					if (args.length < 2) {
						player.sendMessage(prefix() + "§cUsage: /magic summon wizard");
					} else if (args.length == 2) {
						if (args[1].equalsIgnoreCase("wizard")) {
							VillagerTrader.summonTrader(player.getLocation());
						}
					}
				}
				if (args[0].equalsIgnoreCase("kill")) {
					if (args.length < 2) {
						player.sendMessage(prefix() + "§cUsage: /magic kill wizard");
					} else if (args.length == 2) {
						if (args[1].equalsIgnoreCase("wizard")) {
							VillagerTrader.killTrader((Player) sender);
						}
					}
				}
			} else {
				player.sendMessage(prefix() + "§4You do not have access to that command.");
				return true;
			}
		}
		return true;
	}

	public String prefix() {
		return "[§9BetterMagic§r] ";
	}

}
