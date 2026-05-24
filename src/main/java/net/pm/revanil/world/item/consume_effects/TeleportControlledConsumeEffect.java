package net.pm.revanil.world.item.consume_effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public record TeleportControlledConsumeEffect(float distance) implements ConsumeEffect {
    private static final float DEFAULT_DISTANCE = 64.0F;
    public static final MapCodec<TeleportControlledConsumeEffect> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("distance", 64.0F).forGetter(TeleportControlledConsumeEffect::distance)).apply(i, TeleportControlledConsumeEffect::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TeleportControlledConsumeEffect> STREAM_CODEC;

    public TeleportControlledConsumeEffect() {
        this(DEFAULT_DISTANCE);
    }

    public ConsumeEffect.Type<TeleportControlledConsumeEffect> getType() {
        return RConsumeEffect.TELEPORT_CONTROLLED;
    }

    public boolean apply(final Level level, final ItemStack stack, final LivingEntity user) {
        HitResult hit = level.clip(new ClipContext(user.getEyePosition(), user.position().add(user.getHeadLookAngle().multiply(distance, distance, distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, user));//user.(64, 0.0f, true);
        Vec3 pos = hit.getLocation();
        if (user.isPassenger()) {
            user.stopRiding();
        }

        Vec3 oldPos = user.position();
        user.teleportTo(pos.x, pos.y, pos.z);
        level.gameEvent(GameEvent.TELEPORT, oldPos, GameEvent.Context.of(user));
        SoundSource soundSource;
        SoundEvent soundEvent;
        if (user instanceof Fox) {
            soundEvent = SoundEvents.FOX_TELEPORT;
            soundSource = SoundSource.NEUTRAL;
        } else {
            soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
            soundSource = SoundSource.PLAYERS;
        }

        // currently unused particles
//            Vec3 diff = pos.subtract(user.getEyePosition()).multiply(0.01, 0.01, 0.01);
//            Vec3 particlePos = user.getEyePosition();
//            for (int i = 0; i < 100; i++) {
//                world.addParticle(ParticleTypes.PORTAL, particlePos.getX(), particlePos.getY(), particlePos.getZ(), diff.getX() * 10, diff.getY() * 10, diff.getY() * 10);
//                particlePos = particlePos.add(diff);
//            }

        level.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundSource);
        user.resetFallDistance();
        user.resetCurrentImpulseContext();

        return true;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, TeleportControlledConsumeEffect::distance, TeleportControlledConsumeEffect::new);
    }
}
