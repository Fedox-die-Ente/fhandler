package net.fedustria.handler.instance;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeInstance {

    @Getter
    @Setter
    private static Plugin instance;

}
