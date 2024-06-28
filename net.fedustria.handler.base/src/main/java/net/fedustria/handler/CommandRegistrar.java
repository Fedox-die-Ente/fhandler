package net.fedustria.handler;

import net.fedustria.handler.platform.SpigotRegistrar;
import net.fedustria.handler.scopes.FScope;

public class CommandRegistrar {

    Object instance;

    public CommandRegistrar(FScope scope, Object instance) {
        this.instance = instance;
    }

    public SpigotRegistrar getSpigotRegistrar() {
        return new SpigotRegistrar(instance);
    }


}
