package net.pm.revanil.world.item.consume_effects;

import net.minecraft.world.item.consume_effects.ConsumeEffect;

public interface RConsumeEffect extends ConsumeEffect {
    public static final Type<TeleportControlledConsumeEffect> TELEPORT_CONTROLLED = Type.register("teleport_controlled",TeleportControlledConsumeEffect.CODEC, TeleportControlledConsumeEffect.STREAM_CODEC);
}
