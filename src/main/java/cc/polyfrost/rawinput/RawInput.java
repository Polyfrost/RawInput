package cc.polyfrost.rawinput;

import cc.polyfrost.rawinput.command.RawInputCommand;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import cc.polyfrost.rawinput.hook.MouseHelperHook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@net.minecraftforge.fml.common.Mod(modid = RawInput.MODID, name = RawInput.NAME, version = RawInput.VERSION)
public class RawInput {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";

    // Register the config and commands.
    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        CommandManager.INSTANCE.registerCommand(new RawInputCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            RawInputThread polyfrost$rawInput = ((MouseHelperHook) net.minecraft.client.Minecraft.getMinecraft().mouseHelper).getPolyfrost$rawInput();
            polyfrost$rawInput.updateControllerEnvironment();
        }
    }
}
