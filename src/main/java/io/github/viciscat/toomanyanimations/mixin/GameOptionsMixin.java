package io.github.viciscat.toomanyanimations.mixin;

import io.github.viciscat.toomanyanimations.ExcessiveGameOptionsInjectInterface;
import io.github.viciscat.toomanyanimations.TooManyAnimations;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements ExcessiveGameOptionsInjectInterface {

    @Shadow @Final @Mutable
    public KeyBinding[] allKeys;

    @Unique
    private final SimpleOption<Boolean> toggleOption = SimpleOption.ofBoolean("toomanyanimations.toggled", true);

    @Inject(method = "load", at = @At("HEAD"))
    private void addKeybind(CallbackInfo ci) {
         allKeys = ArrayUtils.addAll(allKeys, TooManyAnimations.TOGGLE_KEY);
    }

    @Inject(method = "accept", at = @At("TAIL"))
    private void addOption(GameOptions.Visitor visitor, CallbackInfo ci) {
        visitor.accept("toomanyanimations.toggled", toggleOption);
        TooManyAnimations.enabled = toggleOption.getValue();
    }

    @Override
    public SimpleOption<Boolean> toomanyanimations$getOption() {
        return toggleOption;
    }
}
