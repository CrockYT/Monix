
package com.crockcore.monix.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import com.crockcore.monix.world.inventory.MonixGuiGamemodeMenu;
import com.crockcore.monix.procedures.SurvivalProcedure;
import com.crockcore.monix.procedures.SpectatorProcedure;
import com.crockcore.monix.procedures.MonixGuiGamemodebackProcedure;
import com.crockcore.monix.procedures.CreativeProcedure;
import com.crockcore.monix.procedures.AdventureProcedure;
import com.crockcore.monix.MonixMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MonixGuiGamemodeButtonMessage {
	private final int buttonID, x, y, z;

	public MonixGuiGamemodeButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public MonixGuiGamemodeButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(MonixGuiGamemodeButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(MonixGuiGamemodeButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = MonixGuiGamemodeMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			SurvivalProcedure.execute(entity);
		}
		if (buttonID == 1) {

			CreativeProcedure.execute(entity);
		}
		if (buttonID == 2) {

			SpectatorProcedure.execute(entity);
		}
		if (buttonID == 3) {

			AdventureProcedure.execute(entity);
		}
		if (buttonID == 4) {

			MonixGuiGamemodebackProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MonixMod.addNetworkMessage(MonixGuiGamemodeButtonMessage.class, MonixGuiGamemodeButtonMessage::buffer, MonixGuiGamemodeButtonMessage::new, MonixGuiGamemodeButtonMessage::handler);
	}
}
