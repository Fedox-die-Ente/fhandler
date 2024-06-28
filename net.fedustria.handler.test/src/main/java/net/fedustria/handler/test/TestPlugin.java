package net.fedustria.handler.test;

import lombok.Getter;
import net.fedustria.handler.CommandRegistrar;
import net.fedustria.handler.FConfiguration;
import net.fedustria.handler.FHandler;
import net.fedustria.handler.logger.exceptions.FHandlerException;
import net.fedustria.handler.scopes.FScope;
import net.fedustria.handler.test.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

    @Getter
    static TestPlugin instance;

    @Override
    public void onEnable() {

        instance = this;

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
}
