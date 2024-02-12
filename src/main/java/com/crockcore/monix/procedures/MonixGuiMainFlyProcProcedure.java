package com.crockcore.monix.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

import com.crockcore.monix.network.MonixModVariables;

public class MonixGuiMainFlyProcProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (entity instanceof Player _player) {
			_player.getAbilities().mayfly = ((entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MonixModVariables.PlayerVariables())).fly == 1);
			_player.onUpdateAbilities();
		}
		if ((entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MonixModVariables.PlayerVariables())).fly == 1) {
			{
				Entity _ent = entity;
				if (!_ent.level.isClientSide() && _ent.getServer() != null)
					_ent.getServer().getCommands().performCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
							"tellraw @s [\"\",{\"text\":\"\u98DB\u884C\u3092\u8A31\u53EF\u3057\u307E\u3057\u305F\",\"color\":\"green\"}]");
			}
			if (guistate.get("text:Field_fly") instanceof EditBox _tf)
				_tf.setValue("\u98DB\u884C\u8A31\u53EF");
			{
				double _setval = 2;
				entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.fly = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				Entity _ent = entity;
				if (!_ent.level.isClientSide() && _ent.getServer() != null)
					_ent.getServer().getCommands().performCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
							"tellraw @s [\"\",{\"text\":\"\u98DB\u884C\u3092\u62D2\u5426\u3057\u307E\u3057\u305F\",\"color\":\"green\"}]");
			}
			if (guistate.get("text:Field_fly") instanceof EditBox _tf)
				_tf.setValue("\u98DB\u884C\u62D2\u5426");
			{
				double _setval = 1;
				entity.getCapability(MonixModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.fly = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
