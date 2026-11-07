package net.pm.revanil.mixin.surfaceRules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.material.MaterialRuleContext;
import net.minecraft.world.level.levelgen.material.condition.ConditionEvaluator;
import net.minecraft.world.level.levelgen.material.condition.VerticalGradientCondition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VerticalGradientCondition.class)
public class VerticalGradientConditionMixin {
	@WrapMethod(method = "compile")
	private ConditionEvaluator revanil$chunked(MaterialRuleContext ruleContext, Operation<ConditionEvaluator> original) {
		final int trueAtAndBelow = ruleContext.resolveAnchorY(((VerticalGradientCondition) (Object) this).trueAtAndBelow());
		final int falseAtAndAbove = ruleContext.resolveAnchorY(((VerticalGradientCondition) (Object) this).falseAtAndAbove());
		final PositionalRandomFactory randomFactory = ruleContext.getOrCreateRandomFactory(((VerticalGradientCondition) (Object) this).randomName());
		return new MaterialRuleContext.LazyYCondition(ruleContext) {
			protected boolean compute() {
				int blockY = this.context.blockY();
				if (blockY <= trueAtAndBelow) {
					return true;
				} else if (blockY >= falseAtAndAbove) {
					return false;
				} else {
					double probability = Mth.map(blockY, trueAtAndBelow, falseAtAndAbove, 1.0F, 0.0F);
					//get random number from chunk pos & also bottomInc since we need a y
					//could change this to use some other stat later if we want it to be smooth
					ChunkPos chunk = ChunkPos.containing(new BlockPos(this.context.blockX(), this.context.blockY(), this.context.blockZ()));
					RandomSource random = randomFactory.at(chunk.x(), trueAtAndBelow, chunk.z());
					return random.nextFloat() < probability;
				}
			}
		};
	}
}