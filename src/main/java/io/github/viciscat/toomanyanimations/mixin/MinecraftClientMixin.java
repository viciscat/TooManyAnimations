package io.github.viciscat.toomanyanimations.mixin;

import io.github.viciscat.toomanyanimations.ExcessiveGameOptionsInjectInterface;
import io.github.viciscat.toomanyanimations.TooManyAnimations;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final public GameOptions options;

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void handleInputEvents(CallbackInfo ci) {
        while (TooManyAnimations.TOGGLE_KEY.wasPressed()) {
            SimpleOption<Boolean> option = ((ExcessiveGameOptionsInjectInterface) options).toomanyanimations$getOption();
            option.setValue(!option.getValue());
            TooManyAnimations.enabled = option.getValue();
        }
    }
}
