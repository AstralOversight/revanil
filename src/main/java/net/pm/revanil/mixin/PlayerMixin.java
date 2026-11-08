package net.pm.revanil.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    // TODO: I probably need to make this give the sleeping achievement.
    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;sleepCounter:I", shift = At.Shift.AFTER, ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void revanil$rejuvenatingSleep(CallbackInfo ci) {
        if (!((Player)((Object)this)).level().isClientSide() && !((Player)((Object)this)).hasEffect(MobEffects.REGENERATION)) {
                ((Player)((Object)this)).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50));
            }
    }
}
