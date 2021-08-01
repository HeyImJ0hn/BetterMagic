package me.GuitarXpress.BetterMagic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemManager {
	public static ItemStack spellbook;
	public static ItemStack utilitySpellbook;
	public static ItemStack ancientSpellbook;

	public static ItemStack whitePane;
	public static ItemStack lockedSpell;
	public static ItemStack nextPageItem;
	public static ItemStack prevPageItem;

	// Help Book
	public static ItemStack helpBook;

	// Unknown
	public static ItemStack unknownItem;

	// Trades
	public static ItemStack balanceCrystalTrade;

	// Altar
	public static ItemStack altarItem;

	// Spellbook Items
	public static ItemStack combatSpellbookItem;
	public static ItemStack utilitySpellbookItem;
	public static ItemStack ancientSpellbookItem;
	public static ItemStack selectSpellbookItem;

	// Tomes & Books
	public static ItemStack bookOfBalance;
	public static ItemStack bookOfTheAncients;
	public static ItemStack tomeOfBalance;
	public static ItemStack ancientTome;

	// Spells
	public static ItemStack airBolt;
	public static ItemStack waterBolt;
	public static ItemStack earthBolt;
	public static ItemStack fireBolt;

	public static ItemStack airBlast;
	public static ItemStack waterBlast;
	public static ItemStack earthBlast;
	public static ItemStack fireBlast;

	public static ItemStack airSurge;
	public static ItemStack waterSurge;
	public static ItemStack earthSurge;
	public static ItemStack fireSurge;

	// Utility Spells
	public static ItemStack regen;
	public static ItemStack strength;
	public static ItemStack haste;
	public static ItemStack defence;
	public static ItemStack weakness;
	public static ItemStack underwaterBreathing;
	public static ItemStack fireRes;
	public static ItemStack jump;

	// Ancient Spells
	public static ItemStack iceSurge;
	public static ItemStack poisonSurge;
	public static ItemStack darkSurge;
	public static ItemStack bloodSurge;

	// Shards
	public static ItemStack airShard;
	public static ItemStack waterShard;
	public static ItemStack earthShard;
	public static ItemStack fireShard;
	public static ItemStack ancientShard;

	// Crystal
	public static ItemStack balanceCrystal;

	// Orb
	public static ItemStack ancientOrb;

	public static ArrayList<ItemStack> getRegSpells1() {
		ArrayList<ItemStack> regSpells1 = new ArrayList<ItemStack>(
				Arrays.asList(airBolt, waterBolt, earthBolt, fireBolt, airBlast, waterBlast, earthBlast, fireBlast));
		return regSpells1;
	}

	public static ArrayList<ItemStack> getRegSpells2() {
		ArrayList<ItemStack> regSpells2 = new ArrayList<ItemStack>(
				Arrays.asList(airSurge, waterSurge, earthSurge, fireSurge));
		return regSpells2;
	}

	public static ArrayList<ItemStack> getUtilitySpells() {
		ArrayList<ItemStack> utilitySpells = new ArrayList<ItemStack>(
				Arrays.asList(regen, strength, haste, defence, weakness, underwaterBreathing, fireRes, jump));
		return utilitySpells;
	}

	public static void init() {
		createSpellbook();
		createUtilitySpellbook();
		createAncientSpellbook();

		createWhitePanes(); // White glass Panes inside Spellbook
		createLockedSpell();
		createNextPageItem();
		createPreviousPageItem();

		createHelpBook();

		createUnknownItem();

		// Trades
		createBalanceCrystalTrade();

		createAltarItem();

		// Spellbook Items
		createCombatSpellbookItem();
		createUtilitySpellbookItem();
		createAncientSpellbookItem();
		createSelectSpellbookItem();

		// Tomes & Books
		createBookOfBalance();
		createBookOfTheAncients();
		createTomeOfBalance();
		createAncientTome();

		// Spells
		createAirBolt();
		createWaterBolt();
		createEarthBolt();
		createFireBolt();

		createAirBlast();
		createWaterBlast();
		createEarthBlast();
		createFireBlast();

		createAirSurge();
		createWaterSurge();
		createEarthSurge();
		createFireSurge();

		// UtilitySpells
		createRegen();
		createStrength();
		createHaste();
		createDefense();
		createWeakness();
		createUnderwaterBreathing();
		createFireRes();
		createJump();

		// Ancient Spells
		createIceSurge();
		createPoisonSurge();
		createDarkSurge();
		createBloodSurge();

		// Shards
		createAirShard();
		createWaterShard();
		createEarthShard();
		createFireShard();
		createAncientShard();

		// Crystal
		createBalanceCrystal();

		// Orb
		createAncientOrb();
	}

	private static void createHelpBook() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) item.getItemMeta();
		meta.setTitle("§5BetterMagic Guide");
		meta.setAuthor("GuitarXpress");
		ArrayList<String> pages = new ArrayList<>();
		pages.add("§9§lBetterMagic Guide Book\n\n§r§lQuick Start\n\n"
				+ "§rTo start your adventure you'll need to find one of each elemental shard: §6Fire§r, §2Earth§r, §7Air §rand §3Water§r. "
				+ "These can be found when mining ores.\n");
		pages.add("With your newly obtained shards head to the §5Altar §rto craft a §dCrystal of Balance§r.\n"
				+ "These crystals will be used in most crafting recipes.\n"
				+ "When you have 4 crystals head to the §6Wizard §rand trade them in for your combat spellbook! §4Note that you can only adquire one, so don't lose it!§r\n\n");
		pages.add("§lLevelling Up & Other Spells§r\n\n"
				+ "To unlock other spells you need to level up your magic skill, and you can do so just by using it!\n"
				+ "Using spells will increase your magic skill and using higher level spells will yield more xp.\n");
		meta.setPages(pages);
		item.setItemMeta(meta);
		helpBook = item;
	}

	private static void createUnknownItem() {
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§5UnknownItem");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7§k unknownitem");
		meta.setLore(lore);
		item.setItemMeta(meta);
		unknownItem = item;
	}

	private static void createAltarItem() {
		ItemStack item = new ItemStack(Material.LODESTONE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§5Altar");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Used for Altar Crafting");
		meta.setLore(lore);
		item.setItemMeta(meta);
		altarItem = item;
	}

	private static void createBloodSurge() {
		ItemStack item = new ItemStack(Material.NETHER_WART, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§4Blood Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An ancient Blood Spell.");
		lore.add("§7Steals the enemy's HP and heals the attacker for a small amount.");
		lore.add(" ");
		lore.add("§920 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		bloodSurge = item;
	}

	private static void createDarkSurge() {
		ItemStack item = new ItemStack(Material.WITHER_ROSE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§8Dark Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An ancient Dark Spell.");
		lore.add("§7Withers enemies for 5 seconds.");
		lore.add(" ");
		lore.add("§920 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		darkSurge = item;
	}

	private static void createPoisonSurge() {
		ItemStack item = new ItemStack(Material.SPIDER_EYE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§aPoison Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An ancient Poison Spell.");
		lore.add("§7Poisons enemies for 5 seconds.");
		lore.add(" ");
		lore.add("§920 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		poisonSurge = item;
	}

	private static void createIceSurge() {
		ItemStack item = new ItemStack(Material.ICE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§bIce Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An ancient Ice Spell.");
		lore.add("§7Freezes enemies for 5 seconds.");
		lore.add(" ");
		lore.add("§920 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		iceSurge = item;
	}

	private static void createAncientTome() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§5Ancient Tome");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A tome filled with ancient magical knowledge.");
		lore.add(" ");
		lore.add("§6Unlocks the §5Ancient Spellbook§6.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		ancientTome = item;
	}

	private static void createTomeOfBalance() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§aTome Of Balance");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A tome filled with magical knowledge.");
		lore.add(" ");
		lore.add("§6Unlocks the §aUtility Spellbook§6.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		tomeOfBalance = item;
	}

	private static void createBookOfTheAncients() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§5Book Of The Ancients");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A book inbued with ancient unknown power.");
		lore.add("§7Someone might have a better use for this.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		bookOfTheAncients = item;
	}

	private static void createBookOfBalance() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§aBook Of Balance");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A book inbued with magical power.");
		lore.add("§7Someone might have a better use for this.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		bookOfBalance = item;
	}

	private static void createJump() {
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§fLeap of Faith");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful air spell.");
		lore.add("§7Leaps you into the air in the direction you're facing.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		jump = item;
	}

	private static void createFireRes() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Magma Skin");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful fire spell.");
		lore.add("§7Provides fire immunity for 40 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		fireRes = item;
	}

	private static void createUnderwaterBreathing() {
		ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9Help from the Depths");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful water spell.");
		lore.add("§7Provides several boosts to aid underwater endeavors for 40 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		underwaterBreathing = item;
	}

	private static void createWeakness() {
		ItemStack item = new ItemStack(Material.FLINT, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§8Curse");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful air spell.");
		lore.add("§7Applies weakness on target for 20 seconds on impact.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		weakness = item;
	}

	private static void createDefense() {
		ItemStack item = new ItemStack(Material.SHIELD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§fIron Skin");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful earth spell.");
		lore.add("§7Provides a defence boost for 20 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		defence = item;
	}

	private static void createHaste() {
		ItemStack item = new ItemStack(Material.STONE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§eQuick Hands");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful earth spell.");
		lore.add("§7Provides haste for 30 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		haste = item;
	}

	private static void createStrength() {
		ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§fIron Fist");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful earth spell.");
		lore.add("§7Provides a strength boost for 20 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		strength = item;
	}

	private static void createRegen() {
		ItemStack item = new ItemStack(Material.QUARTZ, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§dBand-Aid");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A helpful air spell");
		lore.add("§7Regenerates health for 10 seconds.");
		lore.add(" ");
		lore.add("§e60 seconds cooldown.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		regen = item;
	}

	private static void createFireSurge() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§6Fire Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A powerful Fire Spell.");
		lore.add("§7Lights enemies on fire on impact.");
		lore.add(" ");
		lore.add("§7Level §e74.");
		lore.add(" ");
		lore.add("§914 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		fireSurge = item;
	}

	private static void createEarthSurge() {
		ItemStack item = new ItemStack(Material.GRASS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§2Earth Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A powerful Earth Spell.");
		lore.add("§7Deals extra damage if standing on earth related blocks.");
		lore.add(" ");
		lore.add("§7Level §e67.");
		lore.add(" ");
		lore.add("§914 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		earthSurge = item;
	}

	private static void createWaterSurge() {
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§3Water Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A powerful Water Spell.");
		lore.add("§7Extinguishes player upon use.");
		lore.add(" ");
		lore.add("§7Level §e61.");
		lore.add(" ");
		lore.add("§914 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		waterSurge = item;
	}

	private static void createAirSurge() {
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§fAir Surge");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A powerful Air Spell.");
		lore.add(" ");
		lore.add("§7Level §e55.");
		lore.add(" ");
		lore.add("§914 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		airSurge = item;
	}

	private static void createFireBlast() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§6Fire Bolt");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A stronger Fire Spell.");
		lore.add("§7Lights enemies on fire on impact.");
		lore.add(" ");
		lore.add("§7Level §e48.");
		lore.add(" ");
		lore.add("§98 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		fireBlast = item;
	}

	private static void createEarthBlast() {
		ItemStack item = new ItemStack(Material.GRASS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§2Earth Blast");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A stronger Earth Spell.");
		lore.add("§7Deals extra damage if standing on earth related blocks.");
		lore.add(" ");
		lore.add("§7Level §e40.");
		lore.add(" ");
		lore.add("§98 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		earthBlast = item;
	}

	private static void createWaterBlast() {
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§3Water Blast");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A stronger Water Spell.");
		lore.add("§7Extinguishes player upon use.");
		lore.add(" ");
		lore.add("§7Level §e33.");
		lore.add(" ");
		lore.add("§98 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		waterBlast = item;
	}

	private static void createAirBlast() {
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§fAir Blast");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A stronger Air Spell.");
		lore.add(" ");
		lore.add("§7Level §e27.");
		lore.add(" ");
		lore.add("§98 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		airBlast = item;
	}

	private static void createFireBolt() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§6Fire Bolt");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A weak Fire Spell.");
		lore.add("§7Lights enemies on fire on impact.");
		lore.add(" ");
		lore.add("§7Level §e20.");
		lore.add(" ");
		lore.add("§95 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		fireBolt = item;
	}

	private static void createEarthBolt() {
		ItemStack item = new ItemStack(Material.GRASS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§2Earth Bolt");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A weak Earth Spell.");
		lore.add("§7Deals extra damage if standing on earth related blocks.");
		lore.add(" ");
		lore.add("§7Level §e13.");
		lore.add(" ");
		lore.add("§95 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		earthBolt = item;
	}

	private static void createWaterBolt() {
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setDisplayName("§3Water Bolt");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A weak Water Spell.");
		lore.add("§7Extinguishes player upon use.");
		lore.add(" ");
		lore.add("§7Level §e7.");
		lore.add(" ");
		lore.add("§95 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		waterBolt = item;
	}

	private static void createAirBolt() {
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§fAir Bolt");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A weak Air Spell.");
		lore.add(" ");
		lore.add("§7Level §e1.");
		lore.add(" ");
		lore.add("§95 Magic Damage");
		meta.setLore(lore);
		item.setItemMeta(meta);
		airBolt = item;
	}

	private static void createBalanceCrystal() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§dCrystal of Balance");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A Crystal of Balance.");
		lore.add("§7Holds the power of the elements.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		balanceCrystal = item;
	}

	private static void createBalanceCrystalTrade() {
		ItemStack item = new ItemStack(Material.COAL, 4);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§dCrystal of Balance");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A Crystal of Balance.");
		lore.add("§7Holds the power of the elements.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		balanceCrystalTrade = item;
	}

	private static void createAncientShard() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§5Ancient Shard");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An Ancient Crystal Shard.");
		lore.add("§7Holds ancient magical powers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		ancientShard = item;
	}

	private static void createFireShard() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§6Fire Shard");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A Fire Crystal Shard.");
		lore.add("§7Holds magical powers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		fireShard = item;
	}

	private static void createEarthShard() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§2Earth Shard");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An Earth Crystal Shard.");
		lore.add("§7Holds magical powers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		earthShard = item;
	}

	private static void createWaterShard() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§3Water Shard");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A Water Crystal Shard.");
		lore.add("§7Holds magical powers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		waterShard = item;
	}

	private static void createAirShard() {
		ItemStack item = new ItemStack(Material.COAL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName("§fAir Shard");
		meta.addEnchant(Enchantment.LUCK, 1, true);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An Air Crystal Shard.");
		lore.add("§7Holds magical powers.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		airShard = item;
	}

	private static void createSpellbook() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§cCombat Spellbook");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A magical spellbook.");
		lore.add("§7I can use this to pratice magic.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		spellbook = item;
	}

	private static void createUtilitySpellbook() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§aUtility Spellbook");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A magical spellbook with Utility spells.");
		lore.add("§7I can use this to pratice magic.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		utilitySpellbook = item;
	}

	private static void createAncientSpellbook() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("§5Ancient Spellbook");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7An Ancient magic Spellbook.");
		lore.add("§7Holds great power.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		ancientSpellbook = item;
	}

	private static void createAncientOrb() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/ff778f72eaca452ee013893e2bc53d8d5b1fca44cfe2703865b054c28a3dd7");
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§5Ancient Orb");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7A powerful Magical Orb.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		ancientOrb = item;
	}

	///////////////////////////////////////////////
	private static void createLockedSpell() {
		ItemStack item = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§4Locked");
		ArrayList<String> lore = new ArrayList<>();
		meta.setLore(lore);
		item.setItemMeta(meta);
		lockedSpell = item;
	}

	private static void createWhitePanes() {
		ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName(" ");
		ArrayList<String> lore = new ArrayList<>();
		meta.setLore(lore);
		item.setItemMeta(meta);
		whitePane = item;
	}

	public static ItemStack getSkull(String url) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		if (url == null || url.isEmpty())
			return skull;
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = skullMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		profileField.setAccessible(true);
		try {
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		skull.setItemMeta(skullMeta);
		return skull;
	}

	private static void createNextPageItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/19bf3292e126a105b54eba713aa1b152d541a1d8938829c56364d178ed22bf");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Current Page: 1");
		meta.setLore(lore);
		meta.setDisplayName("§eNext Page");
		item.setItemMeta(meta);
		nextPageItem = item;
	}

	private static void createPreviousPageItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/bd69e06e5dadfd84e5f3d1c21063f2553b2fa945ee1d4d7152fdc5425bc12a9");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Current Page: 2");
		meta.setLore(lore);
		meta.setDisplayName("§ePrevious Page");
		item.setItemMeta(meta);
		prevPageItem = item;
	}

	private static void createCombatSpellbookItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/93a69c3caa31304e9952328c72cee0b57b2a2bd46ce9c5cb88c07d1266277d6a");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Switch to Combat Spellbook");
		meta.setLore(lore);
		meta.setDisplayName("§cCombat Spellbook");
		item.setItemMeta(meta);
		combatSpellbookItem = item;
	}

	private static void createUtilitySpellbookItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/1c030cc35fde0218ded5ba5d557cca9e03dcbc84f9e672911da54fe07c5fdbbb");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Switch to Utility Spellbook");
		meta.setLore(lore);
		meta.setDisplayName("§aUtility Spellbook");
		item.setItemMeta(meta);
		utilitySpellbookItem = item;
	}

	private static void createAncientSpellbookItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/ac92e5111ea7d7eb3f055833e1f35d651c0da55643c9383e0bce6c23696d58b9");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Switch to Ancient Spellbook");
		meta.setLore(lore);
		meta.setDisplayName("§5Ancient Spellbook");
		item.setItemMeta(meta);
		ancientSpellbookItem = item;
	}

	private static void createSelectSpellbookItem() {
		ItemStack item = getSkull(
				"http://textures.minecraft.net/texture/b33598437e313329eb141a13e92d9b0349aabe5c6482a5dde7b73753634aba");
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Open Spellbook selector");
		meta.setLore(lore);
		meta.setDisplayName("§eSelect Spellbook");
		item.setItemMeta(meta);
		selectSpellbookItem = item;
	}
	//////////////////////////////////////////////

}
