package cc.polyfrost.rawinput;

import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.utils.Notifications;
import cc.polyfrost.rawinput.config.RawInputConfig;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;

import java.net.URI;

public class RawInputThread extends Thread {
    private float dx, dy = 0f;
    private ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();

    public RawInputThread() {
        super("RawInputThread");
    }

    @Override
    public void run() {
        try {
            updateControllerEnvironment();
            while (true) {
                boolean isGrabbed = org.lwjgl.input.Mouse.isGrabbed();
                for (Controller controller : env.getControllers()) {
                    if (controller.getType() == Controller.Type.MOUSE) {
                        Mouse mouse = ((Mouse) controller);
                        mouse.poll();
                        if (isGrabbed) {
                            dx += mouse.getX().getPollData();
                            dy += mouse.getY().getPollData();
                        }
                    }
                }

                Thread.sleep(1);
            }
        } catch (InterruptedException ex) {
            //noinspection CallToThreadRun
            run();
        } catch (Exception e) {
            e.printStackTrace();
            RawInputConfig.rawInput = false;
            RawInputConfig.INSTANCE.save();
            Notifications.INSTANCE.send("Raw Input", "Raw Input has been disabled due to an error. Please report this to Polyfrost by clicking here!", 10000f, () -> UDesktop.browse(URI.create("https://polyfrost.cc/discord/")));
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
        this.env = ControllerEnvironment.getDefaultEnvironment();
    }
}
