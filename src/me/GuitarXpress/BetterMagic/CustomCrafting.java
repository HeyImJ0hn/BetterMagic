package me.GuitarXpress.BetterMagic;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCrafting implements Listener {

	Main main;

	public static Inventory craftMenu;

	public CustomCrafting(Main main) {
		this.main = main;
	}

	public static void createMenu() {
		craftMenu = Bukkit.createInventory(null, 27, "§5Altar Crafting");

		for (int i = 0; i < 27; i++) {
			if (i == 11)
				craftMenu.setItem(i, ItemManager.balanceCrystal);
			else if (i == 13)
				craftMenu.setItem(i, ItemManager.ancientOrb);
			else if (i == 15)
				craftMenu.setItem(i, ItemManager.ancientShard);
			else
				craftMenu.setItem(i, ItemManager.whitePane);
		}
	}

	public static void openCraftMenu(Player player) {
		createMenu();
		player.openInventory(craftMenu);
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (Events.altarLocations.contains(event.getClickedBlock().getLocation())) {
				openCraftMenu(event.getPlayer());
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(craftMenu))
			return;

		event.setCancelled(true);

		ItemStack clickedItem = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();

		if (clickedItem == null)
			return;

		if (!clickedItem.hasItemMeta())
			return;

		if (!clickedItem.getItemMeta().hasLore())
			return;

		if (clickedItem.getItemMeta().getLore().get(0).equals("§7A Crystal of Balance.")) {
			if (!checkMaterials(p, "balancecrystal")) {
				p.sendMessage(
						"§cMissing Ingredients. You need:\n§e1x Fire Shard\n§e1x Water Shard\n§e1x Earth Shard\n§e1x Air Shard");
			} else {
				p.getInventory().addItem(ItemManager.balanceCrystal);
				p.sendMessage("§eCrafted §dCrystal of Balance§e.");
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0f, 1.0f);
			}
		} else if (clickedItem.getItemMeta().getLore().get(0).equals("§7An Ancient Crystal Shard.")) {
			if (!checkMaterials(p, "ancientshard")) {
				p.sendMessage("§cMissing Ingredients. You need:\n§e1x §kunknownItem\n§e4x Balance Crystal");
			} else {
				p.getInventory().addItem(ItemManager.ancientShard);
				p.sendMessage("§eCrafted §5Ancient Shard§e.");
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0f, 1.0f);
			}
		} else if (clickedItem.getItemMeta().getLore().get(0).equals("§7A powerful Magical Orb.")) {
			if (!checkMaterials(p, "ancientorb")) {
				p.sendMessage("§cMissing Ingredients. You need:\n§e1x §kunknownItem\n§e4x Ancient Shard");
			} else {
				p.getInventory().addItem(ItemManager.ancientOrb);
				p.sendMessage("§eCrafted §5Ancient Orb§e.");
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 100.0f, 1.0f);
			}
		}
	}

	private boolean checkMaterials(Player player, String item) {
		if (item.equals("balancecrystal")) {
			if (player.getInventory().containsAtLeast(ItemManager.fireShard, 1)
					&& player.getInventory().containsAtLeast(ItemManager.airShard, 1)
					&& player.getInventory().containsAtLeast(ItemManager.waterShard, 1)
					&& player.getInventory().containsAtLeast(ItemManager.earthShard, 1)) {
				for (int i = 0; i < player.getInventory().getSize(); i++) {
					if (player.getInventory().getContents()[i] != null
							&& (player.getInventory().getContents()[i].isSimilar(ItemManager.fireShard)
									|| player.getInventory().getContents()[i].isSimilar(ItemManager.airShard)
									|| player.getInventory().getContents()[i].isSimilar(ItemManager.earthShard)
									|| player.getInventory().getContents()[i].isSimilar(ItemManager.waterShard))) {
						player.getInventory().getContents()[i]
								.setAmount(player.getInventory().getContents()[i].getAmount() - 1);
					}
				}
				return true;
			}
		} else if (item.equals("ancientshard")) {
			if (player.getInventory().containsAtLeast(ItemManager.balanceCrystal, 4)
					&& player.getInventory().containsAtLeast(ItemManager.unknownItem, 1)) {
				for (int i = 0; i < player.getInventory().getSize(); i++) {
					if (player.getInventory().getContents()[i] != null
							&& (player.getInventory().getContents()[i].isSimilar(ItemManager.balanceCrystal))) {
						player.getInventory().getContents()[i]
								.setAmount(player.getInventory().getContents()[i].getAmount() - 4);
					} else if (player.getInventory().getContents()[i] != null
							&& (player.getInventory().getContents()[i].isSimilar(ItemManager.unknownItem))) {
						player.getInventory().getContents()[i]
								.setAmount(player.getInventory().getContents()[i].getAmount() - 1);
					}
				}
				return true;
			}
		} else if (item.equals("ancientorb")) {
			if (player.getInventory().containsAtLeast(ItemManager.ancientShard, 4)
					&& player.getInventory().containsAtLeast(ItemManager.unknownItem, 1)) {
				for (int i = 0; i < player.getInventory().getSize(); i++) {
					if (player.getInventory().getContents()[i] != null
							&& (player.getInventory().getContents()[i].isSimilar(ItemManager.ancientShard))) {
						player.getInventory().getContents()[i]
								.setAmount(player.getInventory().getContents()[i].getAmount() - 4);
					} else if (player.getInventory().getContents()[i] != null
							&& (player.getInventory().getContents()[i].isSimilar(ItemManager.unknownItem))) {
						player.getInventory().getContents()[i]
								.setAmount(player.getInventory().getContents()[i].getAmount() - 1);
					}
				}
				return true;
			}
		}
		return false;
	}
}
