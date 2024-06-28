package net.fedustria.handler;

import lombok.Getter;
import net.fedustria.handler.logger.exceptions.FHandlerException;
import net.fedustria.handler.scopes.FScope;

@Getter
public class FConfiguration {

    /**
     * The instance
     */
    Object instance;
    /**
     * The scope
     */
    FScope scope;

    /**
     * @param instance the instance
     * @param scope the scope
     */
    public FConfiguration(Object instance, FScope scope) {
        this.instance = instance;
        this.scope = scope;
    }

    /**
     * Default constructor
     */
    public FConfiguration() {}

    /**
     * @param instance the instance to set
     * @return the FConfiguration
     */
    public FConfiguration setInstance(Object instance) {
        this.instance = instance;
        return this;
    }

    /**
     * @param scope the scope to set
     * @return the FConfiguration
     */
    public FConfiguration setScope(FScope scope) {
        this.scope = scope;
        return this;
    }

    /**
     * @return the instance
     */
    public FConfiguration build() throws FHandlerException {
        validate();

        return new FConfiguration(instance, scope);
    }

    /**
     * @return the command registrar
     */
    public CommandRegistrar getCommandRegistrar() {
        return new CommandRegistrar(scope, instance);
    }

    /**
     * @throws FHandlerException if the instance is null
     */
    private void validate() throws FHandlerException {
        if (instance == null) {
            throw new FHandlerException("Instance cannot be null. Please set the instance before building the configuration.");
        }
        if (scope == null) {
            throw new FHandlerException("Scope cannot be null. Please set the scope before building the configuration.");
        }
    }

}
