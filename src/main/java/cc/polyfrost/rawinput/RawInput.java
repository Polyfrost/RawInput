package cc.polyfrost.rawinput;

import cc.polyfrost.rawinput.command.RawInputCommand;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@net.minecraftforge.fml.common.Mod(modid = RawInput.MODID, name = RawInput.NAME, version = RawInput.VERSION)
public class RawInput {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";

    // Register the config and commands.
    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        CommandManager.INSTANCE.registerCommand(new RawInputCommand());
    }
}
