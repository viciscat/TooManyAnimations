package io.github.viciscat.toomanyanimations.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.viciscat.toomanyanimations.TheSilliesOfInterfaces;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SkullEntityModel.class)
public class SkullEntityModelMixin {

    @WrapOperation(method = {"getHeadTexturedModelData", "getModelData"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartBuilder;create()Lnet/minecraft/client/model/ModelPartBuilder;"))
    private static ModelPartBuilder getTexturedModelData(Operation<ModelPartBuilder> original) {
        ModelPartBuilder call = original.call();
        ((TheSilliesOfInterfaces) call).tooManyAnimations$setNotSillySadFace();
        return call;
    }
}
