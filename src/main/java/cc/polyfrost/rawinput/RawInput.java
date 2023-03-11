package cc.polyfrost.rawinput;

import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import cc.polyfrost.rawinput.command.RawInputCommand;
import cc.polyfrost.rawinput.config.RawInputConfig;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@net.minecraftforge.fml.common.Mod(modid = RawInput.MODID, name = RawInput.NAME, version = RawInput.VERSION)
public class RawInput {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    public static RawInputConfig config;

    // Register the config and commands.
    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new RawInputConfig();
        CommandManager.INSTANCE.registerCommand(new RawInputCommand());
    }
}
