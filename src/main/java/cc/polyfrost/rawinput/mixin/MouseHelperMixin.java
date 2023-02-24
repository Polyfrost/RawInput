package cc.polyfrost.rawinput.mixin;

import cc.polyfrost.rawinput.RawInputThread;
import cc.polyfrost.rawinput.config.RawInputConfig;
import net.minecraft.util.MouseHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHelper.class)
public class MouseHelperMixin {
    @Shadow public int deltaX;
    @Shadow public int deltaY;
    @Unique
    private final RawInputThread polyfrost$rawInput = new RawInputThread();
    @Inject(method = "<init>", at = @At("RETURN"))
    private void startRawInputThread(CallbackInfo ci) {
        polyfrost$rawInput.start();
    }

    @Inject(method = "grabMouseCursor", at = @At(value = "FIELD", target = "Lnet/minecraft/util/MouseHelper;deltaX:I"))
    private void grabRawInputCursor(CallbackInfo ci) {
        polyfrost$rawInput.setDx(0);
        polyfrost$rawInput.setDy(0);
    }

    @Inject(method = "mouseXYChange", at = @At("HEAD"), cancellable = true)
    private void rawInputMouseXYChange(CallbackInfo ci) {
        if (RawInputConfig.rawInput && RawInputConfig.INSTANCE.enabled) {
            deltaX = (int) polyfrost$rawInput.getDx();
            deltaY = -((int) polyfrost$rawInput.getDy());
            ci.cancel();
        }
    }
}
