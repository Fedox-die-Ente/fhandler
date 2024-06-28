package net.fedustria.handler.test.commands;

import net.fedustria.handler.annotations.argument.Name;
import net.fedustria.handler.annotations.argument.Sender;
import net.fedustria.handler.annotations.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand {

    // /test subcommand1 <player>
    // /test subcommand2 <player> <text>
    // /test

    @Command("test subcommand1")
    public void subcommand1(@Sender CommandSender sender, @Name("player") String player, @Name("text") String text) {
        Player p = Bukkit.getPlayer(player);
        p.sendMessage(text);
        sender.sendMessage("Message sent to " + player + "!");
    }

    @Command("test subcommand2")
    public void subcommand2(@Sender CommandSender sender, @Name("player") String player) {
        Player p = Bukkit.getPlayer(player);
        p.sendMessage("Hello!");
        sender.sendMessage("Message sent to " + player + "!");
    }

    @Command("test")
    public void test() {
        Bukkit.broadcastMessage("Test command executed!");
    }

    @Command("test subcommand subcommand2")
    public void subcommandSubcommand2(@Sender CommandSender sender) {
        sender.sendMessage("Subcommand 2 executed!");
    }

}
