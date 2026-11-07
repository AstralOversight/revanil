package net.pm.revanil.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.CaveVines.BERRIES;

@Mixin(CaveVinesPlantBlock.class)
public abstract class CaveVinesPlantBlockMixin extends BlockBehaviourMixin {
    @Override
    public void revanil$isRandomlyTickingOverride(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!((Boolean)blockState.getValue(BERRIES)));
    }

    @Override
    public void revanil$randomTickOverride(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        boolean berriesAbove = berriesAtBlock(serverLevel, blockPos.above());
        boolean berriesBelow = berriesAtBlock(serverLevel, blockPos.below());
        if (!(berriesAbove && berriesBelow)) serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(BERRIES, true), 2);
    }

    @Unique
    public boolean berriesAtBlock(ServerLevel level, BlockPos blockPos) {
        return ((level.getBlockState(blockPos)).getBlock() == Blocks.CAVE_VINES || ((level.getBlockState(blockPos)).getBlock() == Blocks.CAVE_VINES_PLANT)) && level.getBlockState(blockPos).getValue(BERRIES);
    }
}
