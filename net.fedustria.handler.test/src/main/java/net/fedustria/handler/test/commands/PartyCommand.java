package net.fedustria.handler.test.commands;

import net.fedustria.handler.annotations.argument.Name;
import net.fedustria.handler.annotations.argument.Sender;
import net.fedustria.handler.annotations.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand {

    @Command("party create")
    public void create(@Sender CommandSender sender) {
        sender.sendMessage("Party created!");
    }

    @Command("party invite")
    public void invite(@Sender CommandSender sender, @Name("player") String player) {
        Player p = (Player) sender;
        Player t = p.getServer().getPlayer(player);

        if (t == null) {
            sender.sendMessage("Player not found!");
            return;
        }

        t.sendMessage("You have been invited to a party!");
        sender.sendMessage("Invitation sent to " + player + "!");
    }

    @Command("party kick")
    public void kick(@Sender CommandSender sender, @Name("player") String player, @Name("reason") String reason) {
        Player p = (Player) sender;
        Player t = p.getServer().getPlayer(player);

        if (t == null) {
            sender.sendMessage("Player not found!");
            return;
        }

        t.kickPlayer("You got kicked from the party :( \n Reason: " + reason + "!");
        sender.sendMessage("Player kicked!");
    }

    @Command("party")
    public void party(@Sender CommandSender sender) {
        sender.sendMessage("Party command executed!");
        sender.sendMessage("Let's party!");
        sender.sendMessage("PARTY WHOOSH");
    }
}
