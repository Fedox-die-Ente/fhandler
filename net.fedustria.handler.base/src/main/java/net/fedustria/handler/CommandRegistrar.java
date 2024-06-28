package net.fedustria.handler;

import net.fedustria.handler.platform.SpigotRegistrar;
import net.fedustria.handler.scopes.FScope;

public class CommandRegistrar {

    /**
     * The instance
     */
    Object instance;

    /**
     * Constructor for CommandRegistrar.
     * @param scope the scope
     * @param instance the instance
     */
    public CommandRegistrar(FScope scope, Object instance) {
        this.instance = instance;
    }

    /**
     * Get the SpigotRegistrar.
     * @return the SpigotRegistrar {@link SpigotRegistrar}
     */
    public SpigotRegistrar getSpigotRegistrar() {
        return new SpigotRegistrar(instance);
    }


}
