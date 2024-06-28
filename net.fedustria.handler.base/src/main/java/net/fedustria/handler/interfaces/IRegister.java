package net.fedustria.handler.interfaces;

public interface IRegister {

    void register(Class<?> clazz);
    void registerPackage(Class<?> clazz, String packageName);

}
