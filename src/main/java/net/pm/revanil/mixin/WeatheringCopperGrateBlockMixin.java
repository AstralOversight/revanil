package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.WeatheringCopperGrateBlock;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WeatheringCopperGrateBlock.class)
public abstract class WeatheringCopperGrateBlockMixin extends BlockBehaviourMixin {
    // FIXME: Make also apply to waxed.
    public void revanil$modifyShape(CallbackInfoReturnable<VoxelShape> cir, @Local(argsOnly = true) CollisionContext collisionContext) {
        if (collisionContext instanceof EntityCollisionContext && ((EntityCollisionContext) collisionContext).getEntity() != null && ((EntityCollisionContext) collisionContext).getEntity().getBoundingBox().getSize() <= 0.8) {
            cir.setReturnValue(Shapes.empty());
        }
    }

}
