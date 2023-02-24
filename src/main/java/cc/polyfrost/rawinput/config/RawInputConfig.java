package cc.polyfrost.rawinput.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class RawInputConfig extends Config {

    @Switch(
            name = "Raw Input"
    )
    public static boolean rawInput = true; // The default value for the boolean Switch.

    public static final RawInputConfig INSTANCE = new RawInputConfig();

    public RawInputConfig() {
        super(new Mod("Raw Input", ModType.UTIL_QOL), "rawinput.json");
        initialize();
    }
}
