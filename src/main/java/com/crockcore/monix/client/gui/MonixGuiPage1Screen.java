
package com.crockcore.monix.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import com.crockcore.monix.world.inventory.MonixGuiPage1Menu;
import com.crockcore.monix.network.MonixGuiPage1ButtonMessage;
import com.crockcore.monix.MonixMod;

public class MonixGuiPage1Screen extends AbstractContainerScreen<MonixGuiPage1Menu> {
	private final static HashMap<String, Object> guistate = MonixGuiPage1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox Field_fly;
	ImageButton imagebutton_close;
	ImageButton imagebutton_gui_right_1;
	ImageButton imagebutton_center_1;
	ImageButton imagebutton_gui_left_1;
	ImageButton imagebutton_fly_feather_1;
	ImageButton imagebutton_gamemode_1;

	public MonixGuiPage1Screen(MonixGuiPage1Menu container, Inventory inventory, Component text) {
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
		Field_fly.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		if (mouseX > leftPos + 6 && mouseX < leftPos + 30 && mouseY > topPos + 6 && mouseY < topPos + 30)
			this.renderTooltip(ms, new TranslatableComponent("gui.monix.monix_gui_page_1.tooltip_fei_xing_ke_bu_ke_neng"), mouseX, mouseY);
		if (mouseX > leftPos + 40 && mouseX < leftPos + 64 && mouseY > topPos + 6 && mouseY < topPos + 30)
			this.renderTooltip(ms, new TranslatableComponent("gui.monix.monix_gui_page_1.tooltip_gamemode_changer"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix_bg_1.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 207, 166, 207, 166);

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + -1, 0, 0, 207, 166, 207, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Field_fly.isFocused())
			return Field_fly.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Field_fly.tick();
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
		Field_fly = new EditBox(this.font, this.leftPos + 39, this.topPos + 71, 120, 20, new TranslatableComponent("gui.monix.monix_gui_page_1.Field_fly"));
		Field_fly.setMaxLength(32767);
		guistate.put("text:Field_fly", Field_fly);
		this.addWidget(this.Field_fly);
		imagebutton_close = new ImageButton(this.leftPos + 156, this.topPos + 139, 48, 24, 0, 0, 24, new ResourceLocation("monix:textures/screens/atlas/imagebutton_close.png"), 48, 48, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiPage1ButtonMessage(0, x, y, z));
				MonixGuiPage1ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_close", imagebutton_close);
		this.addRenderableWidget(imagebutton_close);
		imagebutton_gui_right_1 = new ImageButton(this.leftPos + 128, this.topPos + 138, 28, 28, 0, 0, 28, new ResourceLocation("monix:textures/screens/atlas/imagebutton_gui_right_1.png"), 28, 56, e -> {
		});
		guistate.put("button:imagebutton_gui_right_1", imagebutton_gui_right_1);
		this.addRenderableWidget(imagebutton_gui_right_1);
		imagebutton_center_1 = new ImageButton(this.leftPos + 104, this.topPos + 138, 28, 28, 0, 0, 28, new ResourceLocation("monix:textures/screens/atlas/imagebutton_center_1.png"), 28, 56, e -> {
		});
		guistate.put("button:imagebutton_center_1", imagebutton_center_1);
		this.addRenderableWidget(imagebutton_center_1);
		imagebutton_gui_left_1 = new ImageButton(this.leftPos + 81, this.topPos + 138, 28, 28, 0, 0, 28, new ResourceLocation("monix:textures/screens/atlas/imagebutton_gui_left_1.png"), 28, 56, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiPage1ButtonMessage(3, x, y, z));
				MonixGuiPage1ButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:imagebutton_gui_left_1", imagebutton_gui_left_1);
		this.addRenderableWidget(imagebutton_gui_left_1);
		imagebutton_fly_feather_1 = new ImageButton(this.leftPos + 3, this.topPos + 3, 32, 32, 0, 0, 32, new ResourceLocation("monix:textures/screens/atlas/imagebutton_fly_feather_1.png"), 32, 64, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiPage1ButtonMessage(4, x, y, z));
				MonixGuiPage1ButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:imagebutton_fly_feather_1", imagebutton_fly_feather_1);
		this.addRenderableWidget(imagebutton_fly_feather_1);
		imagebutton_gamemode_1 = new ImageButton(this.leftPos + 36, this.topPos + 3, 32, 32, 0, 0, 32, new ResourceLocation("monix:textures/screens/atlas/imagebutton_gamemode_1.png"), 32, 64, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiPage1ButtonMessage(5, x, y, z));
				MonixGuiPage1ButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		guistate.put("button:imagebutton_gamemode_1", imagebutton_gamemode_1);
		this.addRenderableWidget(imagebutton_gamemode_1);
	}
}
