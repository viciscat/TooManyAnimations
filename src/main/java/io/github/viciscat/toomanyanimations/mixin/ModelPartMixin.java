package io.github.viciscat.toomanyanimations.mixin;

import io.github.viciscat.toomanyanimations.TheSilliesOfInterfaces;
import io.github.viciscat.toomanyanimations.TooManyAnimations;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPart.class)
public abstract class ModelPartMixin implements TheSilliesOfInterfaces {

    @Unique
    private boolean silly = true;

    @Override
    public void tooManyAnimations$setNotSillySadFace() {
        silly = false;
    }

    @Shadow public abstract void resetTransform();

    @Inject(method = {
            "rotate(Lorg/joml/Quaternionf;)V",
            "rotate(Lorg/joml/Vector3f;)V",
            "rotate(Lnet/minecraft/client/util/math/MatrixStack;)V",
            "translate",
            "scale"
    }, at = @At("HEAD"))

    private void animationsGoByeBye(CallbackInfo ci) {
        if (TooManyAnimations.enabled && silly) resetTransform();
    }

    @Mixin(ModelPartBuilder.class)
    private static class BuilderMixin implements TheSilliesOfInterfaces.WithGetter {
        @Unique
        private boolean silly = true;

        @Override
        public boolean tooManyAnimations$isSilly() {
            return silly;
        }

        @Override
        public void tooManyAnimations$setNotSillySadFace() {
            silly = false;
        }
    }
}
