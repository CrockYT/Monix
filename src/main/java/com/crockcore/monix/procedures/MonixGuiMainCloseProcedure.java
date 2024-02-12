package com.crockcore.monix.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.crockcore.monix.network.MonixModVariables;

public class MonixGuiMainCloseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		{
			double _setval = 1;
			entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.page = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
