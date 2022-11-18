package mc.server.survival.commands;

import mc.server.Logger;
import mc.server.survival.libraries.InventoryLib;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Sklep
implements CommandExecutor, TabCompleter
{
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args)
	{
		if (sender instanceof Player)
		{
			final Player player = (Player) sender;

			final String rank = DataManager.getInstance().getLocal(player).getRank();

			if (rank.equalsIgnoreCase("administrator") & !Commands.getInstance().getReader("sklep").isForAdministrators() ||
					rank.equalsIgnoreCase("moderator") & !Commands.getInstance().getReader("sklep").isForModerators() ||
					rank.equalsIgnoreCase("gracz") & !Commands.getInstance().getReader("sklep").isForPlayers())
			{
				ChatManager.sendNotification(player, "Nie masz uprawnien do uzycia tej komendy!", ChatManager.NotificationType.ERROR);
				return true;
			}
			
			if (args.length == 0)
				InventoryLib.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
			else
				ChatManager.sendNotification(player, "Umiesz pisac?! Wystarczy napisac #ffc936/sklep!", ChatManager.NotificationType.ERROR);

			return true;
		}
		else if (sender instanceof ConsoleCommandSender)
			Logger.log("&cJak mam Ci wykonac ta komende w konsoli?!");

		return true;
	}

	@Nullable
	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args)
	{
		return null;
	}
}