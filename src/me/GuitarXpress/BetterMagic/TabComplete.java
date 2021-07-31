package me.GuitarXpress.BetterMagic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabComplete implements TabCompleter {

	List<String> arguments = new ArrayList<String>();

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (arguments.isEmpty()) {
			Player player = (Player) sender;
			arguments.add("level");
			arguments.add("help");
			if (player.hasPermission("magic.spawn")) {
				arguments.add("spellbook");
				arguments.add("shards");
				arguments.add("altar");
				arguments.add("unlocks");
				arguments.add("unreleased");
			}
			if (player.hasPermission("magic.xp")) {
				arguments.add("addxp");
				arguments.add("removexp");
			}
			if (player.hasPermission("magic.summon")) {
				arguments.add("summon");
				arguments.add("kill");
			}
		}

		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}

		}
		return result;
	}
}
