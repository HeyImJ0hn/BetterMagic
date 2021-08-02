package me.GuitarXpress.BetterMagic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Events implements Listener {
	Main plugin;

	private boolean joinedWithinRestart = false;

	public static HashMap<String, SkillManager> skillManagerHashMap = new HashMap<String, SkillManager>();

	public static HashMap<String, Boolean> gotFirstShard = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> canGetShards = new HashMap<String, Boolean>();

	public static HashMap<String, Boolean> boughtSpellbook = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> boughtUtilityTome = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> boughtAncientTome = new HashMap<String, Boolean>();

	public static HashMap<String, Boolean> utilityUnlocked = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> ancientUnlocked = new HashMap<String, Boolean>();

	public static List<Location> altarLocations = new ArrayList<Location>();

	public static ArrayList<Player> negateFall = new ArrayList<>();

	public static double shardDropChance;

	Map<Snowball, Integer> snowballPartTaskIDs = new HashMap<Snowball, Integer>();
	static Map<Player, Long> cooldowns = new HashMap<Player, Long>();
	static Map<Player, Long> utilityCooldowns = new HashMap<Player, Long>();

	Map<Player, Boolean> earthBonus = new HashMap<Player, Boolean>();

	Particle.DustOptions dustPurple = new Particle.DustOptions(Color.PURPLE, (float) 1.0);
	Particle.DustOptions dustBlack = new Particle.DustOptions(Color.BLACK, (float) 1.0);
	Particle.DustOptions dustWhite = new Particle.DustOptions(Color.WHITE, (float) 1.0);
	Particle.DustOptions dustOrange = new Particle.DustOptions(Color.ORANGE, (float) 1.0);
	Particle.DustOptions dustBlue = new Particle.DustOptions(Color.BLUE, (float) 1.0);
	Particle.DustOptions dustAqua = new Particle.DustOptions(Color.AQUA, (float) 1.0);
	Particle.DustOptions dustGreen = new Particle.DustOptions(Color.GREEN, (float) 1.0);
	Particle.DustOptions dustLime = new Particle.DustOptions(Color.LIME, (float) 1.0);
	Particle.DustOptions dustRed = new Particle.DustOptions(Color.RED, (float) 1.0);
	Particle.DustOptions dustTeal = new Particle.DustOptions(Color.TEAL, (float) 1.0);

	ArrayList<Material> earthBlocks = new ArrayList<Material>(Arrays.asList(Material.GRASS_BLOCK, Material.STONE,
			Material.DIRT, Material.COARSE_DIRT, Material.PODZOL, Material.MYCELIUM, Material.HAY_BLOCK,
			Material.MOSSY_COBBLESTONE, Material.MOSSY_STONE_BRICKS, Material.COBBLESTONE, Material.DIRT_PATH,
			Material.GRANITE, Material.DIORITE, Material.ANDESITE, Material.GRAVEL));

	Vector eVector;
	Vector pVector;

	int damage = 0;

	Snowball earthBolt;
	Snowball earthBlast;
	Snowball earthSurge;

	Snowball bloodSurge;

	int airBoltProjID;
	int waterBoltProjID;
	int earthBoltProjID;
	int fireBoltProjID;

	int airBlastProjID;
	int waterBlastProjID;
	int earthBlastProjID;
	int fireBlastProjID;

	int airSurgeProjID;
	int waterSurgeProjID;
	int earthSurgeProjID;
	int fireSurgeProjID;

	int iceSurgeProjID;
	int poisonSurgeProjID;
	int darkSurgeProjID;
	int bloodSurgeProjID;

	int curseProjID;

	public Events(Main plugin) {
		this.plugin = plugin;
	}

	// =========================== onPlayerJoin/Leave ===========================
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();

		if (!plugin.UUIDs.contains(p.getUniqueId().toString())) {
			plugin.UUIDs.add(p.getUniqueId().toString());
			skillManagerHashMap.put(p.getUniqueId().toString(), new SkillManager(1, 7));
			utilityUnlocked.put(p.getUniqueId().toString(), false);
			ancientUnlocked.put(p.getUniqueId().toString(), false);
			gotFirstShard.put(p.getUniqueId().toString(), false);
			canGetShards.put(p.getUniqueId().toString(), true);
			boughtSpellbook.put(p.getUniqueId().toString(), false);
			boughtUtilityTome.put(p.getUniqueId().toString(), false);
			boughtAncientTome.put(p.getUniqueId().toString(), false);
			System.out.println("=============================================================");
			System.out.println("[§9BetterMagic§r] §eAdded " + p.getName() + " to BetterMagic data.");
			System.out.println("UUID: " + p.getUniqueId().toString());
			System.out.println("Level: 1\nXP: 7");
			System.out.println("=============================================================");
		}

		SpellbookManager.createSpellbookPage1(p);
		SpellbookManager.createSpellbookPage2(p);
		SpellbookManager.createUtilitySpellbook(p);
		SpellbookManager.createAncientSpellbook(p);

		if (!joinedWithinRestart) {
			skillManagerHashMap.get(p.getUniqueId().toString()).restoreSpellbook(p);
			joinedWithinRestart = true;
		}
	}

	// ============================= PlayerInteraction =============================
	@EventHandler
	public void onPlayerInteraction(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();

		if (event.getItem() == null)
			return;

		if (!event.getItem().hasItemMeta())
			return;

		if (!event.getItem().getItemMeta().hasLore())
			return;

		if (event.getClickedBlock() != null)
			if (event.getClickedBlock().getType().isInteractable())
				return;

		// ============ Right Click ============
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getItem().getItemMeta().getLore().get(0).equals("§7A magical spellbook.")
					|| event.getItem().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook with Utility spells.")
					|| event.getItem().getItemMeta().getLore().get(0).equals("§7An Ancient magic Spellbook.")) {

				if (p.isSneaking()) {
					if (event.getItem().getItemMeta().getLore().get(0).equals("§7A magical spellbook.")) {
						SpellbookManager.openSpellbookPage1(p);
					} else if (event.getItem().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook with Utility spells.")) {
						if (utilityUnlocked.get(p.getUniqueId().toString())) {
							SpellbookManager.openUtilitySpellbook(p);
						} else {
							p.sendMessage("§cYou don't have this kind of knowledge yet.");
						}
					} else if (event.getItem().getItemMeta().getLore().get(0).equals("§7An Ancient magic Spellbook.")) {
						if (ancientUnlocked.get(p.getUniqueId().toString())) {
							SpellbookManager.openAncientSpellbook(p);
						} else {
							p.sendMessage("§cYou don't have this kind of knowledge yet.");
						}
					}
					p.sendMessage("§eYou open your spellbook...");
					return;
				}

				if (cooldowns.containsKey(p)) {
					if (cooldowns.get(p) > System.currentTimeMillis()) {
//						long timeleft = (cooldowns.get(p) - System.currentTimeMillis()) / 1000;
						return;
					}
				}
				cooldowns.put(p, System.currentTimeMillis() + (1 * 1100));
				p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 5.0f, 100.0f);
				for (int degree = 0; degree < 360; degree++) {
					double radians = Math.toRadians(degree);
					double x = Math.cos(radians);
					double z = Math.sin(radians);
					Location location = p.getLocation();
					location.add(x, 1, z);
					p.getWorld().spawnParticle(Particle.REDSTONE, location.clone(), 1, 0, 0, 0, 0, dustWhite);
					location.subtract(x, 1, z);
				}

				for (Entity e : p.getNearbyEntities(6, 4, 6)) {
					if (!(e instanceof Player)) {
						eVector = e.getLocation().getDirection();
						pVector = p.getLocation().getDirection();
						Vector vector = pVector.subtract(eVector);
						vector = vector.normalize();
						e.setVelocity(vector.multiply(1.5).setY(0.5));
					}

				}
			}

			if (event.getItem().getItemMeta().equals(ItemManager.tomeOfBalance.getItemMeta())) {
				if (!utilityUnlocked.get(p.getUniqueId().toString())) {
					if (skillManagerHashMap.get(p.getUniqueId().toString()).getLevel() >= 40) {
						utilityUnlocked.put(p.getUniqueId().toString(), true);
						p.sendMessage("§eUnlocked the §aUtility Spellbook§e.");
						p.getInventory().remove(event.getItem());
						return;
					} else {
						p.sendMessage("§cYou need at least level §640 §cto unlock this spellbook.");
					}
				} else {
					p.sendMessage("§cYou already unlocked this spellbook.");
				}
			}

			if (event.getItem().getItemMeta().equals(ItemManager.ancientTome.getItemMeta())) {
				if (!ancientUnlocked.get(p.getUniqueId().toString())) {
					if (skillManagerHashMap.get(p.getUniqueId().toString()).getLevel() >= 75) {
						ancientUnlocked.put(p.getUniqueId().toString(), true);
						p.sendMessage("§eUnlocked the §5Ancient Spellbook§e.");
						p.getInventory().remove(event.getItem());
						return;
					} else {
						p.sendMessage("§cYou need at least level §675 §cto unlock this spellbook.");
					}
				} else {
					p.sendMessage("§cYou already unlocked this spellbook.");
				}
			}
		}
		// ============ Left Click ============
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if (event.getItem().getItemMeta().getLore().get(0).equals("§7A magical spellbook with Utility spells.")) {
				if (!event.getItem().getItemMeta().getLore().get(event.getItem().getItemMeta().getLore().size()-1).contains("Leap of Faith")) {
					if (utilityCooldowns.containsKey(p)) {
						if (utilityCooldowns.get(p) > System.currentTimeMillis()) {
							long timeleft = (utilityCooldowns.get(p) - System.currentTimeMillis()) / 1000;
							p.sendMessage("§cYou need to wait §6" + timeleft + " §cmore seconds to cast this spell again!");
							return;
						}
					}
				}
			}
			for (int i = 0; i < event.getItem().getItemMeta().getLore().size(); i++) {
				// ===== Air Bolt =====
				if (event.getItem().getItemMeta().getLore().get(i).contains("Air Bolt")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						airBoltProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 10, 0.1, 0.1,
										0.1, 1, dustWhite);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 0);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Water Bolt =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Water Bolt")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						waterBoltProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 10, 0.1, 0.1,
										0.1, 1, dustTeal);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.5f, 1.0f);
						if (p.getFireTicks() > 0) {
							p.setFireTicks(0);
						}
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Earth Bolt =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Earth Bolt")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						earthBonus.put(p, false);
						Location loc = p.getLocation();
						Block block = p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
						if (earthBlocks.contains(block.getType()))
							earthBonus.put(p, true);
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						earthBoltProjID = proj.getEntityId();
						proj.setShooter(p);
						earthBolt = proj;
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 10, 0.1, 0.1,
										0.1, 1, dustGreen);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Fire Bolt =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Fire Bolt")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						fireBoltProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 10, 0.1, 0.1,
										0.1, 1, dustOrange);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
				}
				// ===== Air Blast =====
				if (event.getItem().getItemMeta().getLore().get(i).contains("Air Blast")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						airBlastProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 50, 0.1, 0.1,
										0.1, 1, dustWhite);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Water Blast =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Water Blast")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						waterBlastProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 50, 0.1, 0.1,
										0.1, 1, dustTeal);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.5f, 1.0f);
						if (p.getFireTicks() > 0) {
							p.setFireTicks(0);
						}
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Earth Blast =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Earth Blast")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						earthBonus.put(p, false);
						Location loc = p.getLocation();
						Block block = p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
						if (earthBlocks.contains(block.getType()))
							earthBonus.put(p, true);
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						earthBlastProjID = proj.getEntityId();
						proj.setShooter(p);
						earthBlast = proj;
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 50, 0.1, 0.1,
										0.1, 1, dustGreen);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Fire Blast =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Fire Blast")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						fireBlastProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 50, 0.1, 0.1,
										0.1, 1, dustOrange);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
				}

				// ===== Air Surge =====
				if (event.getItem().getItemMeta().getLore().get(i).contains("Air Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						airSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustWhite);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.SPELL_INSTANT, proj.getLocation().clone(), 10, 0, 0,
										0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Water Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Water Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						waterSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustTeal);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.NAUTILUS, proj.getLocation().clone(), 5, 0, 0, 0,
										1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.5f, 1.0f);
						if (p.getFireTicks() > 0) {
							p.setFireTicks(0);
						}
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Earth Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Earth Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						earthBonus.put(p, false);
						Location loc = p.getLocation();
						Block block = p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
						if (earthBlocks.contains(block.getType()))
							earthBonus.put(p, true);
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						earthSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						earthSurge = proj;
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustGreen);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										-0.1, -0.1, -0.1, 1);
								p.getWorld().spawnParticle(Particle.COMPOSTER, proj.getLocation().clone(), 20, 0.2, 0.2,
										0.2, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Fire Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Fire Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						fireSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustOrange);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										-0.1, -0.1, -0.1, 1);
								p.getWorld().spawnParticle(Particle.LAVA, proj.getLocation().clone(), 2, 0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
				}

				// ===== Ice Surge =====
				if (event.getItem().getItemMeta().getLore().get(i).contains("Ice Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						iceSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustAqua);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.SPELL_INSTANT, proj.getLocation().clone(), 10, 0, 0,
										0, 1);
								p.getWorld().spawnParticle(Particle.SNOWBALL, proj.getLocation().clone(), 10, 0, 0, 0,
										1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.ICE));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Poison Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Poison Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						poisonSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustLime);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.SNEEZE, proj.getLocation().clone(), 2, 0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.CRIT_MAGIC, proj.getLocation().clone(), 2, 0, 0, 0,
										1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.GHAST_TEAR));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SPIDER_HURT, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Dark Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Dark Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						darkSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustBlack);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, proj.getLocation().clone(), 1, 0, 0,
										0, 1);
								p.getWorld().spawnParticle(Particle.CRIT, proj.getLocation().clone(), 2, 0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.WITHER_SKELETON_SKULL));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ===== Blood Surge =====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Blood Surge")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						bloodSurgeProjID = proj.getEntityId();
						proj.setShooter(p);
						bloodSurge = proj;
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustRed);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
								p.getWorld().spawnParticle(Particle.HEART, proj.getLocation().clone(), 1, 0.4, 0.4, 0.4,
										1);
								p.getWorld().spawnParticle(Particle.CRIT, proj.getLocation().clone(), 5, 0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.NETHER_WART_BLOCK));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHER_WART_BREAK, 100.0f, 1.0f);
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1100);
					}
					// ==== Band-Aid ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Band-Aid")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 2, true));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHER_WART_BREAK, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Iron Fist ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Iron Fist")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 2, true));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Quick Hands ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Quick Hands")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30 * 20, 1, true));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BREAK, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Iron Skin ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Iron Skin")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 20, 3, true));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Curse ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Curse")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						Snowball proj = (Snowball) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 1.4, 0),
								EntityType.SNOWBALL);
						proj.setVelocity(p.getLocation().getDirection().normalize().multiply(4.2));
						curseProjID = proj.getEntityId();
						proj.setShooter(p);
						int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
							if (proj.isValid()) {
								p.getWorld().spawnParticle(Particle.REDSTONE, proj.getLocation().clone(), 100, 0.1, 0.1,
										0.1, 1, dustBlack);
								p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, proj.getLocation().clone(), 10,
										0, 0, 0, 1);
							} else {
								Bukkit.getScheduler().cancelTask(this.snowballPartTaskIDs.get(proj));
								this.snowballPartTaskIDs.remove(proj);
							}
						}, 2, 1);
						this.snowballPartTaskIDs.put(proj, taskID);
						proj.setItem(new ItemStack(Material.FLINT));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Help from the Depths ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Help from the Depths")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40 * 20, 10, true));
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 40 * 20, 10, true));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40 * 20, 10, true));
						p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Magma Skin ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Magma Skin")) {
					if (!utilityCooldowns.containsKey(p) || System.currentTimeMillis() >= utilityCooldowns.get(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40 * 20, 10, true));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 100.0f, 1.0f);
						utilityCooldowns.put(p, System.currentTimeMillis() + 60 * 1000);
					}
					// ==== Leap of Faith ====
				} else if (event.getItem().getItemMeta().getLore().get(i).contains("Leap of Faith")) {
					if (!cooldowns.containsKey(p) || System.currentTimeMillis() >= cooldowns.get(p)) {
						p.setVelocity(p.getLocation().getDirection().add(new Vector(0, .5, 0)));
						p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 5.0f, 100.0f);
						p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation().clone(), 30, 1, 1, 1, 1,
								dustWhite);
						if (negateFall.contains(p)) {
						} else {
							negateFall.add(p);
						}
						cooldowns.put(p, System.currentTimeMillis() + 1 * 1500);
					}
				}
			}
		}

	}

	// ========================= PlayerInteractEntityEvent =========================
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		if (event.getRightClicked() instanceof Villager) {
			if (p.getInventory().getItemInMainHand().hasItemMeta()) {
				if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
					if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook.")) {
						if (p.getInventory().getItemInMainHand().getItemMeta().getLore().size() > 3) {
							SpellbookManager.unbindSpell(p.getInventory().getItemInMainHand());
							p.sendMessage("§eYou unbind your spellbook to talk to the villager.");
						}
					} else if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook with Utility spells.")) {
						if (p.getInventory().getItemInMainHand().getItemMeta().getLore().size() > 3) {
							SpellbookManager.unbindSpell(p.getInventory().getItemInMainHand());
							p.sendMessage("§eYou unbind your spellbook to talk to the villager.");
						}
					} else if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7An Ancient magic Spellbook.")) {
						if (p.getInventory().getItemInMainHand().getItemMeta().getLore().size() > 3) {
							SpellbookManager.unbindSpell(p.getInventory().getItemInMainHand());
							p.sendMessage("§eYou unbind your spellbook to talk to the villager.");
						}
					}
				}
			}

		}
	}

	// ========================= EntityDamageEvent =========================
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (negateFall.contains(player)) {
				if (event.getCause() == DamageCause.FALL) {
					negateFall.remove(player);
					event.setCancelled(true);
				}
			}
		}
	}

	// ========================= PlayerMoveEvent =========================
	@EventHandler
	public void playerMove(PlayerMoveEvent event) {
		if (negateFall.contains(event.getPlayer())) {
			if (event.getPlayer().getLocation().getBlock().getType().equals(Material.WATER)
					|| event.getPlayer().getLocation().getBlock().getType().equals(Material.LAVA)) {
				negateFall.remove(event.getPlayer());
			}
		}
	}

	// =========================== onMobDeath ===========================
	@EventHandler
	public void onMobDeath(EntityDeathEvent event) {
		if (!(event.getEntity().getKiller() instanceof Player))
			return;

		Player p = (Player) event.getEntity().getKiller();
		if (!SpellbookManager.checkForSpellbook(p))
			return;

		if (event.getEntity() instanceof EnderDragon) {
			skillManagerHashMap.get(p.getUniqueId().toString()).addXp(p, 50);
			return;
		}

		if (event.getEntity() instanceof Mob)
			skillManagerHashMap.get(p.getUniqueId().toString()).addXp(p, event.getDroppedExp());

	}

	// =========================== EntityDamageByEntity ===========================
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		// Player Hits Enemy with Spellbook on hand
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if (p.getInventory().getItemInMainHand().hasItemMeta()) {
				if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
					if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook.")) {
						event.setCancelled(true);
					} else if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7A magical spellbook with Utility spells.")) {
						event.setCancelled(true);
					} else if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
							.equals("§7An Ancient magic Spellbook.")) {
						event.setCancelled(true);
					}
				}
			}
		}
		// =========== BOLT ===========
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == airBoltProjID) { // Air
			event.setDamage(5 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 80, 0.5, 0.5, 0.5, 1, dustWhite);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == waterBoltProjID) { // Water
			event.setDamage(5 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 80, 0.5, 0.5, 0.5, 1, dustAqua);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == earthBoltProjID) { // Earth
			if (earthBonus.get(earthBolt.getShooter())) {
				event.setDamage(5 + damage + 2);
				Player p = (Player) earthBolt.getShooter();
				earthBonus.put(p, false);
			} else {
				event.setDamage(5 + damage);
			}
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 80, 0.5, 0.5, 0.5, 1, dustGreen);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == fireBoltProjID) { // Fire
			event.getEntity().setFireTicks(4 * 20);
			event.setDamage(5 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 80, 0.5, 0.5, 0.5, 1, dustOrange);
		}

		// =========== BLAST ===========
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == airBlastProjID) { // Air
			event.setDamage(8 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 1, dustWhite);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == waterBlastProjID) { // Water
			event.setDamage(8 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 1, dustAqua);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == earthBlastProjID) { // Earth
			if (earthBonus.get(earthBlast.getShooter())) {
				event.setDamage(8 + damage + 2);
				Player p = (Player) earthBlast.getShooter();
				earthBonus.put(p, false);
			} else {
				event.setDamage(8 + damage);
			}
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 1, dustGreen);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == fireBlastProjID) { // Fire
			event.getEntity().setFireTicks(4 * 20);
			event.setDamage(8 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 1, dustOrange);
		}

		// =========== SURGE ===========
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == airSurgeProjID) { // Air
			event.setDamage(14 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustWhite);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == waterSurgeProjID) { // Water
			event.setDamage(14 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustAqua);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == earthSurgeProjID) { // Earth
			if (earthBonus.get(earthSurge.getShooter())) {
				event.setDamage(14 + damage + 2);
				Player p = (Player) earthSurge.getShooter();
				earthBonus.put(p, false);
			} else {
				event.setDamage(14 + damage);
			}
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustGreen);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == fireSurgeProjID) { // Fire
			event.getEntity().setFireTicks(4 * 20);
			event.setDamage(14 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustOrange);
		}

		// =========== ANCIENT ===========
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == iceSurgeProjID) { // Ice
			LivingEntity le = (LivingEntity) event.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 100));
			event.setDamage(20 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(),
					Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustAqua);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == poisonSurgeProjID) { // Poison
			LivingEntity le = (LivingEntity) event.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 2));
			event.setDamage(20 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(),
					Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustLime);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == darkSurgeProjID) { // Dark
			LivingEntity le = (LivingEntity) event.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5 * 20, 1));
			event.setDamage(20 + damage);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(),
					Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustBlack);
		}
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == bloodSurgeProjID) { // Blood
			event.setDamage(20 + damage);
			Player player = (Player) bloodSurge.getShooter();
			if ((player.getHealth() + ((int) (20 + damage) / 6)) > 20) {
				player.setHealth(20);
			} else {
				player.setHealth(player.getHealth() + ((int) (20 + damage) / 6));
			}
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(),
					Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustRed);
		}

		// =========== UTILITY ===========
		if (event.getDamager() instanceof Snowball && event.getDamager().getEntityId() == curseProjID) { // CURSE
			LivingEntity le = (LivingEntity) event.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 20, 0));
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,
					100.0f, 1.0f);
			event.getEntity().getWorld().spawnParticle(Particle.REDSTONE,
					event.getEntity().getLocation().clone().add(0, 1, 0), 120, 0.5, 0.5, 0.5, 1, dustBlack);
		}

	}

	// ============================= BlockPlaceEvent =============================
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getItemInHand() != null) {
			if (event.getItemInHand().hasItemMeta()) {
				if (event.getItemInHand().getItemMeta().hasLore()) {
					if (event.getItemInHand().getItemMeta().getLore().get(0).equals("§7Used for Altar Crafting")) {
						altarLocations.add(event.getBlockPlaced().getLocation());
					} else if (event.getItemInHand().getItemMeta().getLore().get(0)
							.equals("§7A powerful Magical Orb.")) {
						event.setCancelled(true);
					}
				}
			}
		}
	}

	// =========================== BlockBreakEvent ===========================
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();

		if (p.getInventory().getItemInMainHand().hasItemMeta()) {
			if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)
						.equals("§7A magical spellbook.")) {
					event.setCancelled(true);
				}
			}
		}

		if (altarLocations.contains(event.getBlock().getLocation())) {
			altarLocations.remove(event.getBlock().getLocation());
		}

		if (p.getGameMode() == GameMode.SURVIVAL) {
			Location loc = event.getBlock().getLocation();

			if (p.getInventory().getItemInMainHand().hasItemMeta())
				if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH))
					return;

			if (canGetShards.get(p.getUniqueId().toString())) {
				if (event.getBlock().getType().equals(Material.COAL_ORE)
						|| event.getBlock().getType().equals(Material.COPPER_ORE)
						|| event.getBlock().getType().equals(Material.IRON_ORE)
						|| event.getBlock().getType().equals(Material.GOLD_ORE)
						|| event.getBlock().getType().equals(Material.REDSTONE_ORE)
						|| event.getBlock().getType().equals(Material.LAPIS_ORE)
						|| event.getBlock().getType().equals(Material.DIAMOND_ORE)
						|| event.getBlock().getType().equals(Material.EMERALD_ORE)) {

					if (Math.random() < shardDropChance) {
						if (!gotFirstShard.get(p.getUniqueId().toString())) {
							p.sendMessage(
									"§eYou find a strange shard.\nType §6/magic toggle §eif you'd like to stop receiving shards.");
							gotFirstShard.put(p.getUniqueId().toString(), true);
						}
						event.getBlock().getLocation().getWorld().dropItemNaturally(loc, ItemManager.airShard);
						return;
					}
					if (Math.random() < shardDropChance) {
						if (!gotFirstShard.get(p.getUniqueId().toString())) {
							p.sendMessage(
									"§eYou find a strange shard.\nType §6/magic toggle §eif you'd like to stop receiving shards.");
							gotFirstShard.put(p.getUniqueId().toString(), true);
						}
						event.getBlock().getLocation().getWorld().dropItemNaturally(loc, ItemManager.waterShard);
						return;
					}
					if (Math.random() < shardDropChance) {
						if (!gotFirstShard.get(p.getUniqueId().toString())) {
							p.sendMessage(
									"§eYou find a strange shard.\nType §6/magic toggle §eif you'd like to stop receiving shards.");
							gotFirstShard.put(p.getUniqueId().toString(), true);
						}
						event.getBlock().getLocation().getWorld().dropItemNaturally(loc, ItemManager.fireShard);
						return;
					}
					if (Math.random() < shardDropChance) {
						if (!gotFirstShard.get(p.getUniqueId().toString())) {
							p.sendMessage(
									"§eYou find a strange shard.\nType §6/magic toggle §eif you'd like to stop receiving shards.");
							gotFirstShard.put(p.getUniqueId().toString(), true);
						}
						event.getBlock().getLocation().getWorld().dropItemNaturally(loc, ItemManager.earthShard);
						return;
					}
				}
			}
		}

	}

}
