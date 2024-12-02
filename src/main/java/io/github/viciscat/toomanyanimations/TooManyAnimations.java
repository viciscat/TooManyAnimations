package io.github.viciscat.toomanyanimations;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class TooManyAnimations implements ClientModInitializer {

    public static boolean enabled = false;
    public static final KeyBinding TOGGLE_KEY = new KeyBinding("toomanyanimations.key.enable", GLFW.GLFW_KEY_F7, KeyBinding.MISC_CATEGORY);

    @Override
    public void onInitializeClient() {
    }
}
