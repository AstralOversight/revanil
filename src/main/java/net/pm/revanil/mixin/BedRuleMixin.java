package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BedRule.class)
public class BedRuleMixin {
    // TODO: See if I can add a message to say "It's too bright to sleep here." or something.
    @WrapMethod(method = "canSleep(Lnet/minecraft/world/level/Level;)Z")
    private boolean revanil$neverAlwaysSwitch(Level level, Operation<Boolean> original) {
        return level.isDarkOutside() || (level.dimensionType().hasFixedTime() && level.getSkyDarken() >= 4);
    }

    @WrapMethod(method = "canSetSpawn(Lnet/minecraft/world/level/Level;)Z")
    private boolean revanil$neverSetSpawn(Level level, Operation<Boolean> original) {
        return false;
    }

    // FIXME: For some reason this also makes Straw Beds not break after leaving???
    @WrapMethod(method = "destroyOnUse")
    private boolean revanil$neverDestroyOnUse(Operation<Boolean> original) {
        return false;//((BedRule)((Object)this)).destroyOnLeave();
    }
}
