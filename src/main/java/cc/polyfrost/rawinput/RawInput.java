package cc.polyfrost.rawinput;

import cc.polyfrost.rawinput.command.RawInputCommand;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;

@net.minecraftforge.fml.common.Mod(modid = RawInput.MODID, name = RawInput.NAME, version = RawInput.VERSION)
public class RawInput {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";

    // Register the config and commands.
    @Subscribe
    public void onInit(InitializationEvent event) {
        CommandManager.INSTANCE.registerCommand(new RawInputCommand());
    }
}
