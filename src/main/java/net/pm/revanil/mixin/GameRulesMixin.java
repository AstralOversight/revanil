package net.pm.revanil.mixin;

import net.minecraft.world.level.gamerules.GameRules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GameRules.class)
public class GameRulesMixin {
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/world/level/gamerules/GameRules;PLAYERS_SLEEPING_PERCENTAGE:Lnet/minecraft/world/level/gamerules/GameRule;", opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/gamerules/GameRules;registerInteger(Ljava/lang/String;Lnet/minecraft/world/level/gamerules/GameRuleCategory;II)Lnet/minecraft/world/level/gamerules/GameRule;"), index = 2)
    private static int revanil$noSleepingThroughNight(int defaultValue) {
        return 999;
    }
}
