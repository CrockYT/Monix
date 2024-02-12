
package com.crockcore.monix.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import com.crockcore.monix.world.inventory.MonixGuiMainMenu;
import com.crockcore.monix.procedures.MonixGuiMainModelProcedure;
import com.crockcore.monix.network.MonixGuiMainButtonMessage;
import com.crockcore.monix.MonixMod;

public class MonixGuiMainScreen extends AbstractContainerScreen<MonixGuiMainMenu> {
	private final static HashMap<String, Object> guistate = MonixGuiMainMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_close;
	ImageButton imagebutton_gui_right_1;
	ImageButton imagebutton_gui_left_1;

	public MonixGuiMainScreen(MonixGuiMainMenu container, Inventory inventory, Component text) {
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
		if (MonixGuiMainModelProcedure.execute(world) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventory(this.leftPos + 16, this.topPos + 28, 50, 0, 0, livingEntity);
		}
		this.renderTooltip(ms, mouseX, mouseY);
		if (mouseX > leftPos + 3 && mouseX < leftPos + 27 && mouseY > topPos + 3 && mouseY < topPos + 27)
			this.renderTooltip(ms, new TranslatableComponent("gui.monix.monix_gui_main.tooltip_tadanosuraimudao"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix_bg_1.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 207, 166, 207, 166);

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/monix.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + -23, 0, 0, 207, 166, 207, 166);

		RenderSystem.setShaderTexture(0, new ResourceLocation("monix:textures/screens/center_1.png"));
		this.blit(ms, this.leftPos + 104, this.topPos + 138, 0, 0, 28, 28, 28, 28);

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
		imagebutton_close = new ImageButton(this.leftPos + 156, this.topPos + 139, 48, 24, 0, 0, 24, new ResourceLocation("monix:textures/screens/atlas/imagebutton_close.png"), 48, 48, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiMainButtonMessage(0, x, y, z));
				MonixGuiMainButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_close", imagebutton_close);
		this.addRenderableWidget(imagebutton_close);
		imagebutton_gui_right_1 = new ImageButton(this.leftPos + 128, this.topPos + 138, 28, 28, 0, 0, 28, new ResourceLocation("monix:textures/screens/atlas/imagebutton_gui_right_1.png"), 28, 56, e -> {
			if (true) {
				MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiMainButtonMessage(1, x, y, z));
				MonixGuiMainButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:imagebutton_gui_right_1", imagebutton_gui_right_1);
		this.addRenderableWidget(imagebutton_gui_right_1);
		imagebutton_gui_left_1 = new ImageButton(this.leftPos + 81, this.topPos + 138, 28, 28, 0, 0, 28, new ResourceLocation("monix:textures/screens/atlas/imagebutton_gui_left_1.png"), 28, 56, e -> {
		});
		guistate.put("button:imagebutton_gui_left_1", imagebutton_gui_left_1);
		this.addRenderableWidget(imagebutton_gui_left_1);
	}
}
