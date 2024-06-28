package net.fedustria.handler.platform;

import net.fedustria.handler.annotations.argument.Name;
import net.fedustria.handler.annotations.argument.Sender;
import net.fedustria.handler.annotations.command.Command;
import net.fedustria.handler.interfaces.IRegister;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

public class SpigotRegistrar implements IRegister, Listener {

    Object instance;
    JavaPlugin plugin;

    private record SubCommand(String name, Method method, Set<SubCommand> subCommands) {}

    public SpigotRegistrar(Object instance) {
        this.instance = instance;
        this.plugin = (JavaPlugin) instance;
    }

    @Override
    public void register(@NotNull Class<?> clazz) {
        Map<String, Set<SubCommand>> commands = new HashMap<>();

        Arrays.stream(clazz.getMethods())
            .filter(method -> method.isAnnotationPresent(Command.class))
            .forEach(method -> {
                var cmd = method.getAnnotation(Command.class);
                var cmdName = cmd.value()[0];
                var splittedCmdName = cmdName.split(" ");
                var isSubCmd = splittedCmdName.length > 1;

                var subCmds = commands.getOrDefault(splittedCmdName[0], new HashSet<>());
                var subCmdArray = Arrays.copyOfRange(splittedCmdName, 1, splittedCmdName.length);

                if(isSubCmd) {
                    var subCmd = subCmds.stream().filter(subCommand -> subCommand.name().equals(subCmdArray[0])).findFirst().orElse(null);

                    if(subCmd == null) {
                        subCmd = new SubCommand(subCmdArray[0], method, new HashSet<>());
                        subCmds.add(subCmd);
                    }

                    for(int i = 1; i < subCmdArray.length; i++) {
                        var subCmdName = subCmdArray[i];
                        var subCmdTempOpt = subCmd.subCommands().stream().filter(subCommand -> subCommand.name().equals(subCmdName)).findFirst();

                        SubCommand subCmdTemp = null;
                        if (subCmdTempOpt.isPresent()) {
                            subCmdTemp = new SubCommand(subCmdName, method, new HashSet<>());
                            subCmd.subCommands().add(subCmdTemp);
                        }

                        subCmd = subCmdTemp;
                    }
                } else {
                    subCmds.add(new SubCommand(splittedCmdName[0], method, new HashSet<>()));
                }


                commands.put(splittedCmdName[0], subCmds);
            });

        commands.forEach((cmdName, subCmds) -> plugin.getCommand(cmdName).setExecutor((commandSender, command, s, args) -> {
            if (args.length > 0 && !subCmds.isEmpty()) {
                SubCommand finalSubCmd = null;
                int argIndex = 0;

                for(String subCmd : args) {
                    // Loop through subcommands and then loop through subcommands of subcommands
                    finalSubCmd = subCmds.stream().filter(subCommand -> subCommand.name().equals(subCmd)).findFirst().orElse(null);
                }

                if(nonNull(finalSubCmd)) {
                    commandSender.sendMessage(finalSubCmd.name());

                    /*var method = finalSubCmd.method();

                    var params = method.getParameters();

                    try {
                        if (params.length == 0) {
                            method.invoke(instance);
                            return false;
                        }

                        Object[] methodParams = new Object[params.length];
                        IntStream.range(0, params.length).forEach(i -> {
                            var param = params[i];
                            if(param.isAnnotationPresent(Sender.class)) {
                                methodParams[i] = commandSender;
                            } else if(param.isAnnotationPresent(Name.class)) {
                                var annotation = param.getAnnotation(Name.class);

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
               }
            }

            return false;
        }));
    }

    @Override
    public void registerPackage(@NotNull Class<?> clazz, @NotNull String packageName) {

    }

}