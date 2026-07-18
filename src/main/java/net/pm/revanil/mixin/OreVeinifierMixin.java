package net.pm.revanil.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.pm.revanil.world.level.block.RBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(OreVeinifier.VeinType.class)
public class OreVeinifierMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/OreVeinifier$VeinType;<init>(Ljava/lang/String;ILnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;II)V"), index = 4)
    private static BlockState revanil$addNewStones(BlockState blockState) {
        BlockState nbs = blockState;
        if (blockState.is(Blocks.GRANITE)) {
            nbs = RBlocks.MALACHITE_BLOCK.defaultBlockState();
        } else if (blockState.is(Blocks.TUFF)) {
            nbs = RBlocks.HEMATITE_BLOCK.defaultBlockState();
        }
        return nbs;
    }
}
