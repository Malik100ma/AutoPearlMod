package com.autopearl;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

public class AutoPearlClient implements ClientModInitializer {
    public static final String MOD_ID = "autopearl";

    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    /** Throws the pearl. Its key is changed from the AutoPearl settings screen (or from Controls). */
    public static KeyMapping THROW_KEY;
    /** Opens the AutoPearl settings screen. Default: [ (this is the "х" key on a Russian layout). */
    public static KeyMapping SETTINGS_KEY;

    @Override
    public void onInitializeClient() {
        THROW_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.autopearl.throw",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                CATEGORY));

        SETTINGS_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.autopearl.settings",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(AutoPearlClient::onTick);
    }

    private static void onTick(Minecraft mc) {
        // Open settings
        while (SETTINGS_KEY.consumeClick()) {
            if (mc.screen == null) {
                mc.setScreen(new AutoPearlScreen(null));
            }
        }

        // Throw pearl
        boolean pressed = false;
        while (THROW_KEY.consumeClick()) {
            pressed = true;
        }
        if (pressed && mc.screen == null) {
            throwPearl(mc);
        }
    }

    private static void throwPearl(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.gameMode == null || player.isSpectator()) {
            return;
        }

        Inventory inventory = player.getInventory();

        // Hotbar only: slots 0..8
        int pearlSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (inventory.getItem(i).is(Items.ENDER_PEARL)) {
                pearlSlot = i;
                break;
            }
        }
        if (pearlSlot == -1) {
            return; // no pearl in hotbar -> do nothing
        }

        int previousSlot = inventory.getSelectedSlot();
        if (pearlSlot != previousSlot) {
            inventory.setSelectedSlot(pearlSlot);
        }

        // Right click with the pearl in hand (sends the slot change + use packet in order)
        InteractionResult result = mc.gameMode.useItem(player, InteractionHand.MAIN_HAND);
        if (result.consumesAction()) {
            player.swing(InteractionHand.MAIN_HAND);
        }

        // Return to the slot we were on; the client syncs it to the server on the next tick
        if (pearlSlot != previousSlot) {
            inventory.setSelectedSlot(previousSlot);
        }
    }
}
