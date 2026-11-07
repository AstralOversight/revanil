package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;TORCHFLOWER:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;of()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;", ordinal = 0))
    private static BlockBehaviour.Properties revanil$torchflowerGlow(Operation<BlockBehaviour.Properties> original) {
        return original.call().lightLevel(state -> 15);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/BlockIds;POTTED_TORCHFLOWER:Lnet/minecraft/resources/ResourceKey;", opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;flowerPotProperties()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;", ordinal = 0))
    private static BlockBehaviour.Properties revanil$pottedTorchflowerGlow(Operation<BlockBehaviour.Properties> original) {
        return original.call().lightLevel(state -> 15);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/BlockIds;CAVE_VINES_PLANT:Lnet/minecraft/resources/ResourceKey;", opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;of()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;", ordinal = 0))
    private static BlockBehaviour.Properties revanil$caveVineRandomTicks(Operation<BlockBehaviour.Properties> original) {
        return original.call().randomTicks();
    }

}
