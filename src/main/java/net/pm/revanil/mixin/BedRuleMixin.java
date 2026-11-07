package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BedRule.class)
public class BedRuleMixin {
    // FIXME: Some of this is super broken.
    @WrapMethod(method = "canSleep(Lnet/minecraft/world/level/Level;)Z")
    private boolean revanil$neverAlwaysSwitch(Level level, Operation<Boolean> original) {
        return level.isDarkOutside();
    }

    @WrapMethod(method = "canSetSpawn(Lnet/minecraft/world/level/Level;)Z")
    private boolean revanil$neverSetSpawn(Level level, Operation<Boolean> original) {
        return false;
    }
}
