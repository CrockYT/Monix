
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.crockcore.monix.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;
import java.util.ArrayList;

import com.crockcore.monix.world.inventory.MonixGuiPage1Menu;
import com.crockcore.monix.world.inventory.MonixGuiMainMenu;
import com.crockcore.monix.world.inventory.MonixGuiGamemodeMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MonixModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<MonixGuiPage1Menu> MONIX_GUI_PAGE_1 = register("monix_gui_page_1", (id, inv, extraData) -> new MonixGuiPage1Menu(id, inv, extraData));
	public static final MenuType<MonixGuiMainMenu> MONIX_GUI_MAIN = register("monix_gui_main", (id, inv, extraData) -> new MonixGuiMainMenu(id, inv, extraData));
	public static final MenuType<MonixGuiGamemodeMenu> MONIX_GUI_GAMEMODE = register("monix_gui_gamemode", (id, inv, extraData) -> new MonixGuiGamemodeMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
