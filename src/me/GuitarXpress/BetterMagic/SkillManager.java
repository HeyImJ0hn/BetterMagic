package me.GuitarXpress.BetterMagic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SkillManager {

	private int MAXLEVEL = 99;

	private int level;
	private double xp;

	public SkillManager(int level, double xp) {
		this.level = level;
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public void addXp(Player p, double xp) {
		setXp(getXp() + xp);
		checkLevel(p);
	}

	public void removeXp(double xp) {
		setXp(getXp() - xp);
	}

	public void addLevel(int level) {
		setLevel(getLevel() + level);
	}

	public void checkLevel(Player p) {
		for (int i = 0; i < MAXLEVEL; i++) {
			if (getLevel() <= 16) {
				double xpToLevel = (Math.pow(getLevel() + 1, 2) + 6 * (getLevel() + 1));
				if (getXp() >= xpToLevel) {
					addLevel(1);
					p.sendMessage("§eCongratulations! You've reached level §6" + getLevel() + "§e!");
					if (getLevel() == 7) {
						SpellbookManager.addSpell(ItemManager.waterBolt, p);
						p.sendMessage("§eUnlocked Spell: §3Water Bolt");
					} else if (getLevel() == 13) {
						SpellbookManager.addSpell(ItemManager.earthBolt, p);
						p.sendMessage("§eUnlocked Spell: §2Earth Bolt");
					}
				}
			} else if (getLevel() >= 17 && getLevel() <= 31) {
				double xpToLevel = (2.5 * Math.pow(getLevel() + 1, 2) - 40.5 * (getLevel() + 1) + 360);
				if (getXp() >= xpToLevel) {
					addLevel(1);
					p.sendMessage("§eCongratulations! You've reached level §6" + getLevel() + "§e!");
					if (getLevel() == 20) {
						SpellbookManager.addSpell(ItemManager.fireBolt, p);
						p.sendMessage("§eUnlocked Spell: §6Fire Bolt");
					} else if (getLevel() == 27) {
						SpellbookManager.addSpell(ItemManager.airBlast, p);
						p.sendMessage("§eUnlocked Spell: §fAir Blast");
					}
				}
			} else if (getLevel() >= 32 && getLevel() <= 99) {
				double xpToLevel = (4.5 * Math.pow((getLevel() + 1), 2) - 162.5 * (getLevel() + 1) + 2220);
				if (getXp() >= xpToLevel) {
					addLevel(1);
					p.sendMessage("§eCongratulations! You've reached level §6" + getLevel() + "§e!");
					if (getLevel() == 33) {
						SpellbookManager.addSpell(ItemManager.waterBlast, p);
						p.sendMessage("§eUnlocked Spell: §3Water Blast");
					} else if (getLevel() == 40) {
						SpellbookManager.addSpell(ItemManager.earthBlast, p);
						p.sendMessage("§eUnlocked Spell: §2Earth Blast");
					} else if (getLevel() == 48) {
						SpellbookManager.addSpell(ItemManager.fireBlast, p);
						p.sendMessage("§eUnlocked Spell: §6Fire Blast");
					} else if (getLevel() == 55) {
						SpellbookManager.addSpell(ItemManager.airSurge, p);
						p.sendMessage("§eUnlocked Spell: §fAir Surge");
					} else if (getLevel() == 61) {
						SpellbookManager.addSpell(ItemManager.waterSurge, p);
						p.sendMessage("§eUnlocked Spell: §3Water Surge");
					} else if (getLevel() == 67) {
						SpellbookManager.addSpell(ItemManager.earthSurge, p);
						p.sendMessage("§eUnlocked Spell: §2Earth Surge");
					} else if (getLevel() == 74) {
						SpellbookManager.addSpell(ItemManager.fireSurge, p);
						p.sendMessage("§eUnlocked Spell: §6Fire Surge");
					}
				}
			}
		}

	}

	public void restoreSpellbook(Player p) {
		for (int i = 0; i < getLevel() + 1; i++) {
			switch (i) {
			case 7:
				SpellbookManager.addSpell(ItemManager.waterBolt, p);
				break;
			case 13:
				SpellbookManager.addSpell(ItemManager.earthBolt, p);
				break;
			case 20:
				SpellbookManager.addSpell(ItemManager.fireBolt, p);
				break;
			case 27:
				SpellbookManager.addSpell(ItemManager.airBlast, p);
				break;
			case 33:
				SpellbookManager.addSpell(ItemManager.waterBlast, p);
				break;
			case 40:
				SpellbookManager.addSpell(ItemManager.earthBlast, p);
				break;
			case 48:
				SpellbookManager.addSpell(ItemManager.fireBlast, p);
				break;
			case 55:
				SpellbookManager.addSpell(ItemManager.airSurge, p);
				break;
			case 61:
				SpellbookManager.addSpell(ItemManager.waterSurge, p);
				break;
			case 67:
				SpellbookManager.addSpell(ItemManager.earthSurge, p);
				break;
			case 74:
				SpellbookManager.addSpell(ItemManager.fireSurge, p);
				break;
			}
		}
	}

	public void createBoard(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("SkillScoreboard", "dummy", "§9Magic Info");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score = obj.getScore("§6" + p.getName());
		score.setScore(3);
		Score score1 = obj.getScore("-----------------");
		score1.setScore(2);
		Score score2 = obj.getScore("Level: §6" + String.valueOf(level));
		score2.setScore(1);
		Score score3 = obj.getScore("Current XP: §6" + String.valueOf(xp));
		score3.setScore(0);
		p.setScoreboard(board);

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("BetterMagic"), () -> {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}, 6 * 20);
	}

}
