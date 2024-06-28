package net.fedustria.handler.test.commands;

import net.fedustria.handler.annotations.argument.Name;
import net.fedustria.handler.annotations.argument.Sender;
import net.fedustria.handler.annotations.command.Command;
import net.fedustria.handler.test.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class MSGCommand {

    @Command("msg")
    public void msg(@Sender CommandSender sender, @Name("player") String player, @Name("message") String message) {
        Player t = Bukkit.getPlayer(player);
        Player p = (Player) sender;

        if (t == null) {
            sender.sendMessage("Player not found!");
            return;
        }

        t.sendMessage(message);
        sender.sendMessage("Message sent to " + player + "!");

        t.setMetadata("replyTo", new FixedMetadataValue(TestPlugin.getInstance(), p.getName()));
        p.setMetadata("replyTo", new FixedMetadataValue(TestPlugin.getInstance(), t.getName()));
    }

    @Command("r")
    public void r(@Sender CommandSender sender, @Name("message") String message) {
        Player p = (Player) sender;
        MetadataValue replyTo = p.getMetadata("replyTo").getFirst();
        Player t = Bukkit.getPlayer(replyTo.asString());

        if (t == null) {
            sender.sendMessage("Player not found!");
            return;
        }

        t.sendMessage(message);
        sender.sendMessage("Message sent to " + t.getName() + "!");
    }

}
