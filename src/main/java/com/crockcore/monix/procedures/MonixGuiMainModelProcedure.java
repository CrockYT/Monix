package com.crockcore.monix.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

public class MonixGuiMainModelProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? new Slime(EntityType.SLIME, _level) : null;
	}
}
