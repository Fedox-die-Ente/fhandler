package net.fedustria.handler.instance;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotInstance {

    @Getter
    @Setter
    private static JavaPlugin instance;

}
