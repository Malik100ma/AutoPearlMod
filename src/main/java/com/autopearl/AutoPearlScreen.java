package com.autopearl;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class AutoPearlScreen extends Screen {
    private final Screen parent;
    private Button keyButton;
    private boolean listening = false;

    public AutoPearlScreen(Screen parent) {
        super(Component.translatable("autopearl.settings.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int y = this.height / 2 - 30;

        keyButton = Button.builder(buttonText(), btn -> {
            listening = true;
            btn.setMessage(buttonText());
        }).bounds(cx - 100, y, 200, 20).build();
        this.addRenderableWidget(keyButton);

        this.addRenderableWidget(Button.builder(Component.translatable("autopearl.settings.reset"), btn -> {
            listening = false;
            applyKey(AutoPearlClient.THROW_KEY.getDefaultKey());
        }).bounds(cx - 100, y + 24, 200, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("gui.done"), btn -> this.onClose())
                .bounds(cx - 100, y + 60, 200, 20).build());
    }

    private Component buttonText() {
        Component key = AutoPearlClient.THROW_KEY.getTranslatedKeyMessage();
        if (listening) {
            return Component.literal("> ")
                    .append(key.copy().withStyle(ChatFormatting.YELLOW))
                    .append(Component.literal(" <"))
                    .withStyle(ChatFormatting.YELLOW);
        }
        return Component.translatable("autopearl.settings.key", key);
    }

    private void applyKey(InputConstants.Key key) {
        AutoPearlClient.THROW_KEY.setKey(key);
        KeyMapping.resetMapping();
        this.minecraft.options.save();
        keyButton.setMessage(buttonText());
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (listening) {
            listening = false;
            if (event.key() != GLFW.GLFW_KEY_ESCAPE) {
                applyKey(InputConstants.Type.KEYSYM.getOrCreate(event.key()));
            } else {
                keyButton.setMessage(buttonText());
            }
            return true;
        }
        return super.keyPressed(event);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (listening) {
            listening = false;
            applyKey(InputConstants.Type.MOUSE.getOrCreate(event.button()));
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 70, 0xFFFFFFFF);
        graphics.drawCenteredString(this.font, Component.translatable("autopearl.settings.hint"),
                this.width / 2, this.height / 2 - 50, 0xFFAAAAAA);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
