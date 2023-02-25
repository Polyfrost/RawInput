package cc.polyfrost.rawinput;

import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.utils.Notifications;
import cc.polyfrost.rawinput.config.RawInputConfig;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

public class RawInputThread extends Thread {
    private final Constructor<? extends ControllerEnvironment> envConstructor;
    private float dx, dy = 0f;
    private ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();

    public RawInputThread() {
        super("RawInputThread");
        Constructor<? extends ControllerEnvironment> constructor;
        try {
            constructor = Class.forName("net.java.games.input.DefaultControllerEnvironment").asSubclass(ControllerEnvironment.class).getConstructor();
            constructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
            constructor = null;
        }
        envConstructor = constructor;
    }

    @Override
    public void run() {
        try {
            updateControllerEnvironment();
            while (true) {
                boolean isGrabbed = org.lwjgl.input.Mouse.isGrabbed();
                boolean needsUpdate = false;
                for (Controller controller : env.getControllers()) {
                    if (controller.getType() == Controller.Type.MOUSE) {
                        Mouse mouse = ((Mouse) controller);
                        if (mouse.poll()) {
                            if (isGrabbed) {
                                dx += mouse.getX().getPollData();
                                dy += mouse.getY().getPollData();
                            }
                        } else {
                            needsUpdate = true;
                        }
                    }
                }

                if (needsUpdate) {
                    updateControllerEnvironment();
                }

                Thread.sleep(1);
            }
        } catch (InterruptedException ex) {
            //noinspection CallToThreadRun
            run();
        } catch (Exception e) {
            e.printStackTrace();
            disableRawInput(false);
        }
    }

    public float getDx() {
        float result = dx;
        dx = 0;
        return result;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        float result = dy;
        dy = 0;
        return result;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void updateControllerEnvironment() {
        if (envConstructor == null) {
            env = ControllerEnvironment.getDefaultEnvironment();
        } else {
            try {
                env = envConstructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                disableRawInput(false);
            }
        }
    }

    public void disableRawInput(boolean osNotSupported) {
        RawInputConfig.rawInput = false;
        if (osNotSupported) {
            RawInputConfig.INSTANCE.enabled = false;
        }
        if (osNotSupported && !RawInputConfig.osWarned) {
            Notifications.INSTANCE.send("Raw Input", "Raw Input will not do anything as it is for Windows ONLY. Uninstall Raw Input if needed.", 10000f);
        } else {
            Notifications.INSTANCE.send("Raw Input", "Raw Input has been disabled due to an error. Please report this to Polyfrost by clicking here!", 10000f, () -> UDesktop.browse(URI.create("https://polyfrost.cc/discord/")));
        }
        RawInputConfig.INSTANCE.save();
    }
}
