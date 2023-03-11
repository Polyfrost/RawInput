package cc.polyfrost.rawinput.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.NonProfileSpecific;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.libs.universal.UDesktop;

public class RawInputConfig extends Config {

    @Switch(
            name = "Raw Input"
    )
    public static boolean rawInput = true; // The default value for the boolean Switch.

    @NonProfileSpecific
    public static boolean osWarned = false;

    public RawInputConfig() {
        super(new Mod("RawInput", ModType.UTIL_QOL), "rawinput.json", true, UDesktop.isWindows());
        initialize();
    }
}

