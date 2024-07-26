# This project is still wip!
<h1 align="center" id="title">FHandler</h1>

<p align="center"><img src="https://socialify.git.ci/Fedox-die-Ente/fhandler/image?font=Jost&issues=1&language=1&name=1&owner=1&pattern=Brick%20Wall&stargazers=1&theme=Dark" alt="project-image"></p>
<p id="description">FHandler is a simple annotation based command handler for Minecraft plugins. The library has support for bungeecord and spigot.</p>

<p align="center"><img src="https://img.shields.io/badge/License-MIT-green" alt="shields">   <img src="https://img.shields.io/github/release/Fedox-die-Ente/fhandler?include_prereleases=&amp;sort=semver&amp;color=green" alt="shields"></p>

<h2>💻 Built with</h2>

Technologies used in the project:

* [Java](https://www.java.com/de/)
* [Spigot](https://www.spigotmc.org/wiki/spigot-installation/)
* [BungeeCord](https://github.com/SpigotMC/BungeeCord)

<h2>🧑‍💻 Examples</h2>

1. Create a instance of the FHandler
```java
@Override
public void onEnable() {
    FConfiguration config = null;
    try {
        config = FHandler.getFConfiguration()
                .setInstance(this)
                .setScope(FScope.SPIGOT)
                .build();
    } catch (FHandlerException e) {
        e.printStackTrace();
    }
}
```

2. Register commands via the instance
```java
@Override
    public void onEnable() {
        FConfiguration config = null;
        try {
            config = FHandler.getFConfiguration()
                    .setInstance(this)
                    .setScope(FScope.SPIGOT)
                    .build();
        } catch (FHandlerException e) {
            e.printStackTrace();
        }

        if (config != null) {
            CommandRegistrar commandRegistrar = config.getCommandRegistrar();
            commandRegistrar.getSpigotRegistrar().register(TestCommand.class);
        }
    }
```

3. Create a command:
```java
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
```

<h2>🤵 Credits</h2>

- [Fedox-die-Ente](https://github.com/Fedox-die-Ente) - Creator of the project
- [Austria7](https://github.com/Austria7) - Contributor and helper of the project

<h2>🛡️ License:</h2>

This project is licensed under the [AGPL-3.0](LICENSE)

<h2>💖Like my work?</h2>

Leave a ⭐ on this repository :D
