package net.fedustria.handler.interfaces;

/**
 * The register interface
 */
public interface IRegister {

    /**
     * Register a class
     * @param clazz the class
     */
    void register(Class<?> clazz);

    /**
     * Register a package
     * @param clazz the class
     * @param packageName the package name
     */
    void registerPackage(Class<?> clazz, String packageName);

}
