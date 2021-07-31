package me.GuitarXpress.BetterMagic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class VillagerTrader implements Listener {
	Main main;
	
	static List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
	static Villager trader;
	
	public VillagerTrader(Main main) {
		this.main = main;
	}
	
	public static void init() {
		createTrades();
	}
	
	private static void createTrades() {
		recipes.add(getSpellbook());
		recipes.add(getUtilityUnlock());
		recipes.add(getAncientUnlock());
	}
	
	private static MerchantRecipe getSpellbook() {
		MerchantRecipe trade = new MerchantRecipe(ItemManager.spellbook, 1);
		trade.addIngredient(ItemManager.balanceCrystalTrade);
		return trade;
	}
	
	private static MerchantRecipe getUtilityUnlock() {
		MerchantRecipe trade = new MerchantRecipe(ItemManager.tomeOfBalance, 1);
		trade.addIngredient(ItemManager.balanceCrystalTrade);
		trade.addIngredient(new ItemStack(Material.DIAMOND_PICKAXE, 1));
		return trade;
	}
	
	private static MerchantRecipe getAncientUnlock() {
		MerchantRecipe trade = new MerchantRecipe(ItemManager.ancientTome, 1);
		trade.addIngredient(ItemManager.ancientShard);
		return trade;
	}
	
	static void summonTrader(Location loc) {
		World world = loc.getWorld();
		trader = (Villager) world.spawnEntity(loc, EntityType.VILLAGER);
		trader.setVillagerLevel(5);
		trader.setProfession(Profession.CLERIC);
		trader.setCustomName("§6Wizard");
		trader.setInvulnerable(true);
		trader.setAI(false);
		trader.setRecipes(recipes);
	}
	
	static void killTrader(Player player) {
		for (Entity e : player.getNearbyEntities(5, 5, 5)) {
			if (e.getName().equals("§6Wizard") && e.getType() == EntityType.VILLAGER) {
				trader = (Villager) e;
			}
		}
		if (trader != null)
			trader.setHealth(0);
	}
	
	@EventHandler
	public void onInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getName().equals("§6Wizard") && event.getRightClicked().getType() == EntityType.VILLAGER) {
			event.getPlayer().sendMessage("§6Wizard§e: How may I assist you?");
		}
	}
	
}
