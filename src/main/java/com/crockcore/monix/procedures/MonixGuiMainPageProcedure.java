package com.crockcore.monix.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import com.crockcore.monix.world.inventory.MonixGuiPage1Menu;
import com.crockcore.monix.world.inventory.MonixGuiMainMenu;
import com.crockcore.monix.network.MonixModVariables;

public class MonixGuiMainPageProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MonixModVariables.PlayerVariables())).page + 1;
			entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.page = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MonixModVariables.PlayerVariables())).page == 1) {
			{
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = new BlockPos(x, y, z);
					NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return new TextComponent("MonixGuiMain");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new MonixGuiMainMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
		} else {
			if ((entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MonixModVariables.PlayerVariables())).page == 2) {
				{
					if (entity instanceof ServerPlayer _ent) {
						BlockPos _bpos = new BlockPos(x, y, z);
						NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
							@Override
							public Component getDisplayName() {
								return new TextComponent("MonixGuiPage1");
							}

							@Override
							public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
								return new MonixGuiPage1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
							}
						}, _bpos);
					}
				}
			}
		}
	}
}
