package cc.polyfrost.rawinput.command;

import cc.polyfrost.oneconfig.libs.universal.UMinecraft;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;
import cc.polyfrost.rawinput.RawInput;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.rawinput.config.RawInputConfig;
import cc.polyfrost.rawinput.hook.MouseHelperHook;

@Command(value = RawInput.MODID, description = "Access the " + RawInput.NAME + " GUI.")
public class RawInputCommand {
    @Main
    private void handle() {
        RawInputConfig.INSTANCE.openGui();
    }

    @SubCommand(description = "Rescan for devices.")
    private void rescan() {
        MouseHelperHook mouseHelper = ((MouseHelperHook) UMinecraft.getMinecraft().mouseHelper);
        mouseHelper.getPolyfrost$rawInput().updateControllerEnvironment();
    }
}