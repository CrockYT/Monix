
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

import com.crockcore.monix.world.inventory.MonixGuiPage1Menu;
import com.crockcore.monix.procedures.MonixGuiMainPageBackProcedure;
import com.crockcore.monix.procedures.MonixGuiMainFlyProcProcedure;
import com.crockcore.monix.procedures.MonixGuiMainCloseProcedure;
import com.crockcore.monix.procedures.MonixGuiGamemodeOpenProcedure;
import com.crockcore.monix.MonixMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MonixGuiPage1ButtonMessage {
	private final int buttonID, x, y, z;

	public MonixGuiPage1ButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public MonixGuiPage1ButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(MonixGuiPage1ButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(MonixGuiPage1ButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = MonixGuiPage1Menu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			MonixGuiMainCloseProcedure.execute(entity);
		}
		if (buttonID == 3) {

			MonixGuiMainPageBackProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			MonixGuiMainFlyProcProcedure.execute(entity, guistate);
		}
		if (buttonID == 5) {

			MonixGuiGamemodeOpenProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MonixMod.addNetworkMessage(MonixGuiPage1ButtonMessage.class, MonixGuiPage1ButtonMessage::buffer, MonixGuiPage1ButtonMessage::new, MonixGuiPage1ButtonMessage::handler);
	}
}
