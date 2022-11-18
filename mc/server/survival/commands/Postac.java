package mc.server.survival.commands;

import mc.server.Logger;
import mc.server.survival.libraries.InventoryLib;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DataManager;
import mc.server.survival.managers.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Postac
implements CommandExecutor, TabCompleter
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;

            final String rank = DataManager.getInstance().getLocal(player).getRank();

            if (rank.equalsIgnoreCase("administrator") & !Commands.getInstance().getReader("postac").isForAdministrators() ||
                    rank.equalsIgnoreCase("moderator") & !Commands.getInstance().getReader("postac").isForModerators() ||
                    rank.equalsIgnoreCase("gracz") & !Commands.getInstance().getReader("postac").isForPlayers())
            {
                ChatManager.sendNotification(player, "Nie masz uprawnien do uzycia tej komendy!", ChatManager.NotificationType.ERROR);
                return true;
            }

            if (args.length == 0)
            {
                ChatManager.sendMessage(player, FileManager.getInstance().getConfigValue("global.survival.prefixes.chat") + "#8c8c8cTrwa otwieranie menu postaci... #fff203⌛");
                InventoryLib.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
            }
            else
                ChatManager.sendNotification(player, "Umiesz pisac?! Wystarczy napisac #ffc936/postac!", ChatManager.NotificationType.ERROR);

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