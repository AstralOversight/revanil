package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(FoodData.class)
public class FoodDataMixin {
    @Unique
    int aLot = 5;
    @Unique
    int healingTime = 80;

    /*
     * The player now heals every 80* ticks when provided with enough food.
     * *tick timer increases twice as fast when food level is below "aLot"
     * *the healing time is divided by two for each level of hunger
     * Healing requires a base of 1 food with it costing 1 more food per level of hunger.
     */
    @WrapMethod(method = "tick")
    public void revanil$tick(ServerPlayer player, Operation<Void> original) {
        ServerLevel level = player.level();
        boolean naturalRegen = level.getGameRules().get(GameRules.NATURAL_HEALTH_REGENERATION);
        int hunger = player.getEffect(MobEffects.HUNGER) == null ? 0: player.getEffect(MobEffects.HUNGER).getAmplifier() + 1;
        if (naturalRegen && player.isHurt() && ((FoodData)(Object)this).getFoodLevel() >= hunger+1) {
            ++((FoodData)(Object)this).tickTimer;

            int timeUntilHeal = healingTime >> hunger > 0 ? healingTime >> hunger : 1;

            if (((FoodData)(Object)this).getFoodLevel() < aLot) {
                ++((FoodData)(Object)this).tickTimer;
            }

            if (((FoodData)(Object)this).tickTimer >= timeUntilHeal) {
                player.heal(1.0F);
                ((FoodData) (Object) this).setFoodLevel(Math.max(((FoodData) (Object) this).getFoodLevel() - (hunger+1), 0));
                ((FoodData)(Object)this).tickTimer = 0;
            }
        } else {
            ((FoodData)(Object)this).tickTimer = 0;
        }
    }

    // Has any food.
    @WrapMethod(method = "hasEnoughFood")
    public boolean revanil$tooMuch(Operation<Boolean> original) {
        return ((FoodData)(Object)this).getFoodLevel() <= 0;
    }
}
