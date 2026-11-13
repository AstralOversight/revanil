package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Hud.class)
public class HudMixin {
    @WrapOperation(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;getSaturationLevel()F"))
    private static float tooFull(FoodData instance, Operation<Float> original, @Local(argsOnly = true, name = "player") Player player) {
        // Only wiggle if empty? maybe just never wiggle? player.getFoodData().getFoodLevel() > 0
        return player.getFoodData().hasEnoughFood() ? 1 : -1;
    }

    // FIXME: Seemingly doesn't work as I think the timer is server-side only...
    @ModifyArg(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"), index = 3)
    private static int healingWiggle(int y, @Local(name = "yLineBase", argsOnly = true) int yLineBase) {//, @Local(name = "player", argsOnly = true) Player player, @Local(name = "i") int i) {
        return yLineBase;// - (player.getFoodData().tickTimer / 8 == (9-i) && player.getFoodData().tickTimer != 0 ? 3 : 0);
    }
}
