package cc.polyfrost.rawinput.command;

import cc.polyfrost.rawinput.RawInput;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.rawinput.config.RawInputConfig;

@Command(value = RawInput.MODID, description = "Access the " + RawInput.NAME + " GUI.")
public class RawInputCommand {
    @Main
    private void handle() {
        RawInputConfig.INSTANCE.openGui();
    }
}