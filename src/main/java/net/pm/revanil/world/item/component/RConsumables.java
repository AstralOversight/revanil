package net.pm.revanil.world.item.component;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.pm.revanil.world.item.consume_effects.TeleportControlledConsumeEffect;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class RConsumables {
    public static final Consumable BEETROOT = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 1F)).build();
    public static final Consumable BEETROOT_SOUP = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 150, 1), 1F)).build();
    public static final Consumable GLISTERING_MELON_SLICE = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 250, 0), 1F)).build();
    public static final Consumable GLOW_BERRIES = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0), 1F)).build();
    public static final Consumable GOLDEN_CARROT = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 1F)).build();
    public static final Consumable POPPED_CHORUS_FRUIT = defaultFood().onConsume(new TeleportControlledConsumeEffect()).build();
}
