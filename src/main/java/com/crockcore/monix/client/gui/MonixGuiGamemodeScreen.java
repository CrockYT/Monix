
package com.crockcore.monix.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import com.crockcore.monix.world.inventory.MonixGuiGamemodeMenu;
import com.crockcore.monix.network.MonixGuiGamemodeButtonMessage;
import com.crockcore.monix.MonixMod;

public class MonixGuiGamemodeScreen extends AbstractContainerScreen<MonixGuiGamemodeMenu> {
	private final static HashMap<String, Object> guistate = MonixGuiGamemodeMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_survival;
	Button button_creative;
	Button button_spectator;
	Button button_adventure;
	ImageButton imagebutton_back_1;

	public MonixGuiGamemodeScreen(MonixGuiGamemodeMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 207;
		this.imageHeight = 166;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix_bg_1.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 207, 166, 207, 166);

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 207, 166, 207, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		button_survival = new Button(this.leftPos + 8, this.topPos + 8, 67, 20, new TranslatableComponent("gui.monix.monix_gui_gamemode.button_survival"), e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiGamemodeButtonMessage(0, x, y, z));
				MonixGuiGamemodeButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_survival", button_survival);
		this.addRenderableWidget(button_survival);
		button_creative = new Button(this.leftPos + 132, this.topPos + 8, 67, 20, new TranslatableComponent("gui.monix.monix_gui_gamemode.button_creative"), e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiGamemodeButtonMessage(1, x, y, z));
				MonixGuiGamemodeButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_creative", button_creative);
		this.addRenderableWidget(button_creative);
		button_spectator = new Button(this.leftPos + 8, this.topPos + 29, 72, 20, new TranslatableComponent("gui.monix.monix_gui_gamemode.button_spectator"), e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiGamemodeButtonMessage(2, x, y, z));
				MonixGuiGamemodeButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_spectator", button_spectator);
		this.addRenderableWidget(button_spectator);
		button_adventure = new Button(this.leftPos + 127, this.topPos + 29, 72, 20, new TranslatableComponent("gui.monix.monix_gui_gamemode.button_adventure"), e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiGamemodeButtonMessage(3, x, y, z));
				MonixGuiGamemodeButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_adventure", button_adventure);
		this.addRenderableWidget(button_adventure);
		imagebutton_back_1 = new ImageButton(this.leftPos + 0, this.topPos + 118, 48, 48, 0, 0, 48, new ResourceLocation("monix:textures/screens/atlas/imagebutton_back_1.png"), 48, 96, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiGamemodeButtonMessage(4, x, y, z));
				MonixGuiGamemodeButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:imagebutton_back_1", imagebutton_back_1);
		this.addRenderableWidget(imagebutton_back_1);
	}
}
