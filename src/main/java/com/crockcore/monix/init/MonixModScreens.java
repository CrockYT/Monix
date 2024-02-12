
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.crockcore.monix.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import com.crockcore.monix.client.gui.MonixGuiPage1Screen;
import com.crockcore.monix.client.gui.MonixGuiMainScreen;
import com.crockcore.monix.client.gui.MonixGuiGamemodeScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MonixModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MonixModMenus.MONIX_GUI_PAGE_1, MonixGuiPage1Screen::new);
			MenuScreens.register(MonixModMenus.MONIX_GUI_MAIN, MonixGuiMainScreen::new);
			MenuScreens.register(MonixModMenus.MONIX_GUI_GAMEMODE, MonixGuiGamemodeScreen::new);
		});
	}
}
