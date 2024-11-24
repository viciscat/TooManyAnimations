package io.github.viciscat.toomanyanimations.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.viciscat.toomanyanimations.TheSilliesOfInterfaces;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModelPartData.class)
public class ModelPartDataMixin implements TheSilliesOfInterfaces {

    @Unique
    private boolean silly = true;

    @Override
    public void tooManyAnimations$setNotSillySadFace() {
        silly = false;
    }

    @ModifyArg(
            method = "addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartData;)Lnet/minecraft/client/model/ModelPartData;"),
            index = 1
    )
    private ModelPartData thing(ModelPartData data, @Local(argsOnly = true) ModelPartBuilder builder) {
        if (!((TheSilliesOfInterfaces.WithGetter) builder).tooManyAnimations$isSilly()) ((TheSilliesOfInterfaces) data).tooManyAnimations$setNotSillySadFace();
        return data;
    }

    @ModifyReturnValue(method = "createPart", at = @At("RETURN"))
    private ModelPart thing(ModelPart original) {
        if (!silly) ((TheSilliesOfInterfaces) (Object) original).tooManyAnimations$setNotSillySadFace();
        return original;
    }

    @ModifyReturnValue(method = "applyTransformer", at = @At("RETURN"))
    private ModelPartData change(ModelPartData original) {
        if (!silly) ((TheSilliesOfInterfaces) original).tooManyAnimations$setNotSillySadFace();
        return original;
    }
}
