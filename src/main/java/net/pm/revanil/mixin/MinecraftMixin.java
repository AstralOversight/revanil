package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.pm.revanil.client.particle.RParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    // TODO: something to do with going into client level to change things from there so that it works with others?
    // TODO: Fix/Turn back on.
//    @Inject(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addBreakingBlockEffect(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)V"))
//    private void revanil$addSparks(boolean bl, CallbackInfo ci, @Local BlockPos pos, @Local Direction direction) {
//        BlockState blockState = ((Minecraft) ((Object )this)).level.getBlockState(pos);
//
//        if (!((Minecraft) ((Object )this)).player.isSpectator() && !((Minecraft) ((Object )this)).player.hasCorrectToolForDrops(blockState)) {
//            Random random = new Random();
//            AABB box = blockState.getBlockSupportShape(((Minecraft) ((Object )this)).level, pos).bounds();
//            double i = (double) pos.getX() + random.nextDouble(box.minX, box.maxX);
//            double j = (double) pos.getY() + random.nextDouble(box.minY, box.maxY);
//            double k = (double) pos.getZ() + random.nextDouble(box.minZ, box.maxZ);
//
//            double baseVel = 0.05;
//            double dirVel = 0.1;
//            double m = random.nextDouble(-baseVel, baseVel);
//            double n = random.nextDouble(-baseVel, baseVel);
//            double o = random.nextDouble(-baseVel, baseVel);
//            switch (direction) {
//                case Direction.UP -> {
//                    j = (double) pos.getY() + box.maxY + (double) 0.1f;
//                    n += dirVel;
//                }
//                case DOWN -> {
//                    j = (double) pos.getY() + box.minY - (double) 0.1f;
//                    n -= dirVel;
//                }
//                case EAST -> {
//                    i = (double) pos.getX() + box.maxX + (double) 0.1f;
//                    m += dirVel;
//                }
//                case WEST -> {
//                    i = (double) pos.getX() + box.minX - (double) 0.1f;
//                    m -= dirVel;
//                }
//                case NORTH -> {
//                    k = (double) pos.getZ() + box.minZ - (double) 0.1f;
//                    o -= dirVel;
//                }
//                case SOUTH -> {
//                    k = (double) pos.getZ() + box.maxZ + (double) 0.1f;
//                    o += dirVel;
//                }
//            }
//            ((Minecraft) ((Object )this)).level.addParticle(RParticles.SPARK, i, j, k, m, n, o);
//        }
//    }
}
