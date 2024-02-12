
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.crockcore.monix.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import com.crockcore.monix.network.MonixGuiMainKeyMessage;
import com.crockcore.monix.MonixMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MonixModKeyMappings {
	public static final KeyMapping MONIX_GUI_MAIN_KEY = new KeyMapping("key.monix.monix_gui_main_key", GLFW.GLFW_KEY_F8, "key.categories.misc");
	private static long MONIX_GUI_MAIN_KEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(MONIX_GUI_MAIN_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == MONIX_GUI_MAIN_KEY.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						MONIX_GUI_MAIN_KEY_LASTPRESS = System.currentTimeMillis();
					} else if (event.getAction() == GLFW.GLFW_RELEASE) {
						int dt = (int) (System.currentTimeMillis() - MONIX_GUI_MAIN_KEY_LASTPRESS);
						MonixMod.PACKET_HANDLER.sendToServer(new MonixGuiMainKeyMessage(1, dt));
						MonixGuiMainKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
					}
				}
			}
		}
	}
}
