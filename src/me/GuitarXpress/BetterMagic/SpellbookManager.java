package me.GuitarXpress.BetterMagic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SpellbookManager implements Listener {
	Main main;

	public static Inventory spells1;
	public static Inventory spells2;

	public static Inventory utilitySpells;
	public static Inventory ancientSpells;

	public static Inventory spellbookSelector;

	public static Map<String, ItemStack[]> spellbookPage1 = new HashMap<String, ItemStack[]>();
	public static Map<String, ItemStack[]> spellbookPage2 = new HashMap<String, ItemStack[]>();
	public static Map<String, ItemStack[]> utilitySpellbooks = new HashMap<String, ItemStack[]>();
	public static Map<String, ItemStack[]> ancientSpellbooks = new HashMap<String, ItemStack[]>();

	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("BetterMagic");

	public SpellbookManager(Main main) {
		this.main = main;
	}

	public static void createSpellbookSelector() {
		spellbookSelector = Bukkit.createInventory(null, 27, "§6Spellbook Selector");

		for (int i = 0; i < 27; i++) {
			switch (i) {
			case 11:
				spellbookSelector.setItem(i, ItemManager.combatSpellbookItem);
				break;
			case 13:
				spellbookSelector.setItem(i, ItemManager.utilitySpellbookItem);
				break;
			case 15:
				spellbookSelector.setItem(i, ItemManager.ancientSpellbookItem);
				break;
			default:
				spellbookSelector.setItem(i, ItemManager.whitePane);
				break;
			}
		}
	}

	public static void createAncientSpellbook(Player player) {
		ancientSpells = Bukkit.createInventory(null, 54, "§5" + player.getName() + "'s Ancient Spellbook");
		for (int i = 0; i < 54; i++) {
			if (i == 10)
				ancientSpells.setItem(i, ItemManager.iceSurge);
			else if (i == 12)
				ancientSpells.setItem(i, ItemManager.poisonSurge);
			else if (i == 14)
				ancientSpells.setItem(i, ItemManager.darkSurge);
			else if (i == 16)
				ancientSpells.setItem(i, ItemManager.bloodSurge);
			else if (i == 49)
				ancientSpells.setItem(i, ItemManager.selectSpellbookItem);
			else if (i == 28 || i == 30 || i == 32 || i == 34)
				ancientSpells.setItem(i, ItemManager.lockedSpell);
			else
				ancientSpells.setItem(i, ItemManager.whitePane);
		}

		if (ancientSpellbooks.containsKey(player.getUniqueId().toString()))
			ancientSpells.setContents(ancientSpellbooks.get(player.getUniqueId().toString()));
		else if (!ancientSpellbooks.containsKey(player.getUniqueId().toString()))
			ancientSpellbooks.put(player.getUniqueId().toString(), ancientSpells.getContents());
	}

	public static void createUtilitySpellbook(Player player) {
		utilitySpells = Bukkit.createInventory(null, 54, "§a" + player.getName() + "'s Utility Spellbook");
		for (int i = 0; i < 54; i++) {
			if (i == 10)
				utilitySpells.setItem(i, ItemManager.regen);
			else if (i == 12)
				utilitySpells.setItem(i, ItemManager.strength);
			else if (i == 14)
				utilitySpells.setItem(i, ItemManager.haste);
			else if (i == 16)
				utilitySpells.setItem(i, ItemManager.defence);
			else if (i == 28)
				utilitySpells.setItem(i, ItemManager.weakness);
			else if (i == 30)
				utilitySpells.setItem(i, ItemManager.underwaterBreathing);
			else if (i == 32)
				utilitySpells.setItem(i, ItemManager.fireRes);
			else if (i == 34)
				utilitySpells.setItem(i, ItemManager.jump);
			else if (i == 49)
				utilitySpells.setItem(i, ItemManager.selectSpellbookItem);
			else
				utilitySpells.setItem(i, ItemManager.whitePane);
		}

		if (utilitySpellbooks.containsKey(player.getUniqueId().toString()))
			utilitySpells.setContents(utilitySpellbooks.get(player.getUniqueId().toString()));
		else if (!utilitySpellbooks.containsKey(player.getUniqueId().toString()))
			utilitySpellbooks.put(player.getUniqueId().toString(), utilitySpells.getContents());

	}

	public static void createSpellbookPage2(Player player) {
		spells2 = Bukkit.createInventory(null, 54, "§c" + player.getName() + "'s Combat Spellbook");

		for (int i = 0; i < 54; i++) {
			if (i == 10 || i == 12 || i == 14 || i == 16 || i == 28 || i == 30 || i == 32 || i == 34)
				spells2.setItem(i, ItemManager.lockedSpell);
			else if (i == 49)
				spells2.setItem(i, ItemManager.selectSpellbookItem);
			else if (i == 53)
				spells2.setItem(i, ItemManager.whitePane);
			else if (i == 45)
				spells2.setItem(i, ItemManager.prevPageItem);
			else
				spells2.setItem(i, ItemManager.whitePane);
		}

		if (spellbookPage2.containsKey(player.getUniqueId().toString()))
			spells2.setContents(spellbookPage2.get(player.getUniqueId().toString()));
		else if (!spellbookPage2.containsKey(player.getUniqueId().toString()))
			spellbookPage2.put(player.getUniqueId().toString(), spells2.getContents());

	}

	public static void createSpellbookPage1(Player player) {
		spells1 = Bukkit.createInventory(null, 54, "§c" + player.getName() + "'s Combat Spellbook");

		for (int i = 0; i < 54; i++) {
			if (i == 10 || i == 12 || i == 14 || i == 16 || i == 28 || i == 30 || i == 32 || i == 34)
				spells1.setItem(i, ItemManager.lockedSpell);
			else if (i == 49)
				spells1.setItem(i, ItemManager.selectSpellbookItem);
			else if (i == 53)
				spells1.setItem(i, ItemManager.nextPageItem);
			else if (i == 45)
				spells1.setItem(i, ItemManager.whitePane);
			else
				spells1.setItem(i, ItemManager.whitePane);
		}

		spells1.setItem(10, ItemManager.airBolt);

		if (spellbookPage1.containsKey(player.getUniqueId().toString()))
			spells1.setContents(spellbookPage1.get(player.getUniqueId().toString()));
		else if (!spellbookPage1.containsKey(player.getUniqueId().toString()))
			spellbookPage1.put(player.getUniqueId().toString(), spells1.getContents());

	}

	public static void openSpellbookPage1(Player player) {
		createSpellbookPage1(player);
		player.openInventory(spells1);
	}

	public static void openSpellbookPage2(Player player) {
		createSpellbookPage2(player);
		player.openInventory(spells2);
	}

	public static void openUtilitySpellbook(Player player) {
		createUtilitySpellbook(player);
		player.openInventory(utilitySpells);
	}

	public static void openAncientSpellbook(Player player) {
		createAncientSpellbook(player);
		player.openInventory(ancientSpells);
	}

	public static void changeSpellbook(ItemStack spellbook, Player p) {
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		ItemStack offHand = p.getInventory().getItemInOffHand();
		if (mainHand.hasItemMeta() && mainHand.getItemMeta().hasLore()) {
			if (mainHand.getItemMeta().getLore().get(0).equals("§7A magical spellbook.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.utilitySpellbook);
					p.sendMessage("§eNow using the §aUtility Spellbook§e.");
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.ancientSpellbook);
					p.sendMessage("§eNow using the §5Ancient Spellbook§e.");
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
			}
			if (mainHand.getItemMeta().getLore().get(0).equals("§7A magical spellbook with Utility spells.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.ancientSpellbook);
					p.sendMessage("§eNow using the §5Ancient Spellbook§e.");
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.spellbook);
					p.sendMessage("§eNow using the §cCombat Spellbook§e.");
					return;
				}
			}
			if (mainHand.getItemMeta().getLore().get(0).equals("§7An Ancient magic Spellbook.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.utilitySpellbook);
					p.sendMessage("§eNow using the §aUtility Spellbook§e.");
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.getInventory().setItemInMainHand(ItemManager.spellbook);
					p.sendMessage("§eNow using the §cCombat Spellbook§e.");
					return;
				}
			}
		}

		if (offHand.hasItemMeta() && offHand.getItemMeta().hasLore()) {
			if (offHand.getItemMeta().getLore().get(0).equals("§7A magical spellbook.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.utilitySpellbook);
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.ancientSpellbook);
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
			}
			if (offHand.getItemMeta().getLore().get(0).equals("§7A magical spellbook with Utility spells.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.ancientSpellbook);
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.spellbook);
					return;
				}
			}
			if (offHand.getItemMeta().getLore().get(0).equals("§7An Ancient magic Spellbook.")) {
				if (spellbook.equals(ItemManager.utilitySpellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.utilitySpellbook);
					return;
				}
				if (spellbook.equals(ItemManager.ancientSpellbook)) {
					p.sendMessage("§eYou're already using that spellbook.");
					return;
				}
				if (spellbook.equals(ItemManager.spellbook)) {
					p.getInventory().setItemInOffHand(ItemManager.spellbook);
					return;
				}
			}
		}

	}

	public static void saveSpellbooks(String UUID) {
		for (Map.Entry<String, ItemStack[]> entry : spellbookPage1.entrySet()) {
			plugin.getConfig().set("data." + UUID + ".spellbookPage1." + entry.getKey(), entry.getValue());
		}
		plugin.saveConfig();
		for (Map.Entry<String, ItemStack[]> entry : spellbookPage2.entrySet()) {
			plugin.getConfig().set("data." + UUID + ".spellbookPage2." + entry.getKey(), entry.getValue());
		}
		plugin.saveConfig();
		for (Map.Entry<String, ItemStack[]> entry : utilitySpellbooks.entrySet()) {
			plugin.getConfig().set("data." + UUID + ".utilitySpellbooks." + entry.getKey(), entry.getValue());
		}
		plugin.saveConfig();
		for (Map.Entry<String, ItemStack[]> entry : ancientSpellbooks.entrySet()) {
			plugin.getConfig().set("data." + UUID + ".ancientSpellbooks." + entry.getKey(), entry.getValue());
		}
		plugin.saveConfig();
		System.out.println("[§9BetterMagic§r] §aSaved Spellbooks.");
	}

	public static void restoreSpellbooks(String UUID) {
		plugin.getConfig().getConfigurationSection("data." + UUID + ".spellbookPage1").getKeys(false).forEach(key -> {
			@SuppressWarnings("unchecked")
			ItemStack[] content = ((List<ItemStack>) plugin.getConfig().get("data." + UUID + ".spellbookPage1." + key))
					.toArray(new ItemStack[0]);
			spellbookPage1.put(key, content);
		});

		plugin.getConfig().getConfigurationSection("data." + UUID + ".spellbookPage2").getKeys(false).forEach(key -> {
			@SuppressWarnings("unchecked")
			ItemStack[] content = ((List<ItemStack>) plugin.getConfig().get("data." + UUID + ".spellbookPage2." + key))
					.toArray(new ItemStack[0]);
			spellbookPage2.put(key, content);
		});

		plugin.getConfig().getConfigurationSection("data." + UUID + ".utilitySpellbooks").getKeys(false).forEach(key -> {
			@SuppressWarnings("unchecked")
			ItemStack[] content = ((List<ItemStack>) plugin.getConfig().get("data." + UUID + ".utilitySpellbooks." + key))
					.toArray(new ItemStack[0]);
			utilitySpellbooks.put(key, content);
		});

		plugin.getConfig().getConfigurationSection("data." + UUID + ".ancientSpellbooks").getKeys(false).forEach(key -> {
			@SuppressWarnings("unchecked")
			ItemStack[] content = ((List<ItemStack>) plugin.getConfig().get("data." + UUID + ".ancientSpellbooks." + key))
					.toArray(new ItemStack[0]);
			ancientSpellbooks.put(key, content);
		});

		System.out.println("[§9BetterMagic§r] §aRestored Spellbooks.");
	}

	public static void addSpell(ItemStack spell, Player player) {
		ArrayList<ItemStack> regSpells1 = ItemManager.getRegSpells1();
		ArrayList<ItemStack> regSpells2 = ItemManager.getRegSpells2();

		if (regSpells1.contains(spell)) {
			createSpellbookPage1(player);
			for (int i = 0; i < 54; i++) {
				if (spells1.getItem(i).equals(ItemManager.lockedSpell)) {
					spells1.setItem(i, spell);
					spellbookPage1.put(player.getUniqueId().toString(), spells1.getContents());
					return;
				}
			}
		}

		if (regSpells2.contains(spell)) {
			createSpellbookPage2(player);
			for (int i = 0; i < 54; i++) {
				if (spells2.getItem(i).equals(ItemManager.lockedSpell)) {
					spells2.setItem(i, spell);
					spellbookPage2.put(player.getUniqueId().toString(), spells2.getContents());
					return;
				}
			}
		}
	}

	public static void removeSpell(ItemStack spell, Player player) {
		ArrayList<ItemStack> regSpells1 = ItemManager.getRegSpells1();
		ArrayList<ItemStack> regSpells2 = ItemManager.getRegSpells2();

		if (regSpells1.contains(spell)) {
			createSpellbookPage1(player);
			for (int i = 0; i < 54; i++) {
				if (spells1.getItem(i).equals(spell)) {
					spells1.setItem(i, ItemManager.lockedSpell);
					spellbookPage1.put(player.getUniqueId().toString(), spells1.getContents());
					return;
				}
			}
		}

		if (regSpells2.contains(spell)) {
			createSpellbookPage2(player);
			for (int i = 0; i < 54; i++) {
				if (spells2.getItem(i).equals(spell)) {
					spells2.setItem(i, ItemManager.lockedSpell);
					spellbookPage2.put(player.getUniqueId().toString(), spells2.getContents());
					return;
				}
			}
		}
	}

	public void changeSpell(Player p, String spell) {
		Inventory inv = p.getInventory();
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			if (inv.getItem(i) != null) {
				if (inv.getItem(i).hasItemMeta()) {
					if (inv.getItem(i).getItemMeta().hasLore()) {
						if (inv.getItem(i).getItemMeta().getLore().get(0).equals("§7A magical spellbook.")) {
							ItemStack item = inv.getItem(i);
							ItemMeta meta = item.getItemMeta();
							List<String> lore = item.getItemMeta().getLore();
							if (lore.size() == 2)
								lore.add(" ");
							// Bolt
							if (spell.equals("airBolt")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §fAir Bolt");
								else
									lore.set(3, "§7Current Spell: §fAir Bolt");
							}
							if (spell.equals("waterBolt")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §3Water Bolt");
								else
									lore.set(3, "§7Current Spell: §3Water Bolt");
							}
							if (spell.equals("fireBolt")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §6Fire Bolt");
								else
									lore.set(3, "§7Current Spell: §6Fire Bolt");
							}
							if (spell.equals("earthBolt")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §2Earth Bolt");
								else
									lore.set(3, "§7Current Spell: §2Earth Bolt");
							}
							// Blast
							if (spell.equals("airBlast")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §fAir Blast");
								else
									lore.set(3, "§7Current Spell: §fAir Blast");
							}
							if (spell.equals("waterBlast")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §3Water Blast");
								else
									lore.set(3, "§7Current Spell: §3Water Blast");
							}
							if (spell.equals("fireBlast")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §6Fire Blast");
								else
									lore.set(3, "§7Current Spell: §6Fire Blast");
							}
							if (spell.equals("earthBlast")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §2Earth Blast");
								else
									lore.set(3, "§7Current Spell: §2Earth Blast");
							}
							// Surge
							if (spell.equals("airSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §fAir Surge");
								else
									lore.set(3, "§7Current Spell: §fAir Surge");
							}
							if (spell.equals("waterSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §3Water Surge");
								else
									lore.set(3, "§7Current Spell: §3Water Surge");
							}
							if (spell.equals("fireSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §6Fire Surge");
								else
									lore.set(3, "§7Current Spell: §6Fire Surge");
							}
							if (spell.equals("earthSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §2Earth Surge");
								else
									lore.set(3, "§7Current Spell: §2Earth Surge");
							}

							meta.setLore(lore);
							item.setItemMeta(meta);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 100.0f);
							return;

						} else if (inv.getItem(i).getItemMeta().getLore().get(0)
								.equals("§7An Ancient magic Spellbook.")) {
							ItemStack item = inv.getItem(i);
							ItemMeta meta = item.getItemMeta();
							List<String> lore = item.getItemMeta().getLore();
							if (lore.size() == 2)
								lore.add(" ");
							// Ancient
							if (spell.equals("iceSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §bIce Surge");
								else
									lore.set(3, "§7Current Spell: §bIce Surge");
							}
							if (spell.equals("poisonSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §aPoison Surge");
								else
									lore.set(3, "§7Current Spell: §aPoison Surge");
							}
							if (spell.equals("darkSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §8Dark Surge");
								else
									lore.set(3, "§7Current Spell: §8Dark Surge");
							}
							if (spell.equals("bloodSurge")) {
								if (lore.size() <= 3)
									lore.add(3, "§7Current Spell: §4Blood Surge");
								else
									lore.set(3, "§7Current Spell: §4Blood Surge");
							}

							meta.setLore(lore);
							item.setItemMeta(meta);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 100.0f);
							return;
						}
					}
				}
			}
		}
	}

	public static void unbindSpell(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = item.getItemMeta().getLore();
		lore.remove(3);
		lore.remove(2);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		ArrayList<Inventory> relevantInvs = new ArrayList<Inventory>(
				Arrays.asList(spells1, spells2, utilitySpells, ancientSpells, spellbookSelector));
		createSpellbookSelector();

		if (!relevantInvs.contains(event.getInventory()))
			return;

		if (relevantInvs.contains(event.getInventory()))
			event.setCancelled(true);

		ItemStack clickedItem = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();
		if (clickedItem == null)
			return;

		if (!clickedItem.hasItemMeta())
			return;

		if (!clickedItem.getItemMeta().hasLore())
			return;

		// Change page
		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Current Page: 1")) {
			p.closeInventory();
			openSpellbookPage2(p);
			return;
		}

		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Current Page: 2")) {
			p.closeInventory();
			openSpellbookPage1(p);
			return;
		}

		// Change spellbook
		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Open Spellbook selector")) {
			p.closeInventory();
			p.openInventory(spellbookSelector);
			return;
		}

		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Switch to Combat Spellbook")) {
			p.closeInventory();
			openSpellbookPage1(p);
			changeSpellbook(ItemManager.spellbook, p);
			return;
		}

		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Switch to Utility Spellbook")) {
			p.closeInventory();
			if (Events.utilityUnlocked.get(p.getUniqueId().toString())) {
				openUtilitySpellbook(p);
				changeSpellbook(ItemManager.utilitySpellbook, p);
			} else {
				p.sendMessage("§cYou don't have this kind of knowledge yet.");
			}
			return;
		}

		if (clickedItem.getItemMeta().getLore().get(0).equals("§7Switch to Ancient Spellbook")) {
			p.closeInventory();
			if (Events.ancientUnlocked.get(p.getUniqueId().toString())) {
				openAncientSpellbook(p);
				changeSpellbook(ItemManager.ancientSpellbook, p);
			} else {
				p.sendMessage("§cYou don't have this kind of knowledge yet.");
			}
			return;
		}

		// Bolt
		if (clickedItem.getItemMeta().equals(ItemManager.airBolt.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §fAir Bolt§e.");
			changeSpell(p, "airBolt");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.waterBolt.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §3Water Bolt§e.");
			changeSpell(p, "waterBolt");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.earthBolt.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §2Earth Bolt§e.");
			changeSpell(p, "earthBolt");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.fireBolt.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §6Fire Bolt§e.");
			changeSpell(p, "fireBolt");
			p.closeInventory();
			return;
		}

		// Blast
		if (clickedItem.getItemMeta().equals(ItemManager.airBlast.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §fAir Blast§e.");
			changeSpell(p, "airBlast");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.waterBlast.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §3Water Blast§e.");
			changeSpell(p, "waterBlast");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.earthBlast.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §2Earth Blast§e.");
			changeSpell(p, "earthBlast");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.fireBlast.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §6Fire Blast§e.");
			changeSpell(p, "fireBlast");
			p.closeInventory();
			return;
		}

		// Surge
		if (clickedItem.getItemMeta().equals(ItemManager.airSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §fAir Surge§e.");
			changeSpell(p, "airSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.waterSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §3Water Surge§e.");
			changeSpell(p, "waterSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.earthSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §2Earth Surge§e.");
			changeSpell(p, "earthSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.fireSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §6Fire Surge§e.");
			changeSpell(p, "fireSurge");
			p.closeInventory();
			return;
		}

		// Ancient
		if (clickedItem.getItemMeta().equals(ItemManager.iceSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §bIce Surge§e.");
			changeSpell(p, "iceSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.poisonSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §aPoison Surge§e.");
			changeSpell(p, "poisonSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.darkSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §8Dark Surge§e.");
			changeSpell(p, "darkSurge");
			p.closeInventory();
			return;
		}

		if (clickedItem.getItemMeta().equals(ItemManager.bloodSurge.getItemMeta())) {
			p.sendMessage("§eSpellbook bound to §4Blood Surge§e.");
			changeSpell(p, "bloodSurge");
			p.closeInventory();
			return;
		}
	}

	public static boolean checkForSpellbook(Player p) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			if (p.getInventory().getItem(i) == null)
				continue;

			if (!p.getInventory().getItem(i).hasItemMeta())
				continue;

			if (!p.getInventory().getItem(i).getItemMeta().hasLore())
				continue;

			if (p.getInventory().getItem(i).getItemMeta().getLore().get(0).equals("§7A magical spellbook."))
				return true;

			if (p.getInventory().getItem(i).getItemMeta().getLore().get(0)
					.equals("§7A magical spellbook with Utility spells."))
				return true;

			if (p.getInventory().getItem(i).getItemMeta().getLore().get(0).equals("§7An Ancient magic Spellbook."))
				return true;
		}
		return false;
	}
}
