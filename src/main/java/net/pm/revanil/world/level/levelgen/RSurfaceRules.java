package net.pm.revanil.world.level.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class RSurfaceRules {

    public static SurfaceRules.ConditionSource noise3dCondition(ResourceKey<NormalNoise.NoiseParameters> resourceKey, double min, double max) {
        return new NoiseThresholdConditionSource3d(resourceKey, min, max);
    }

    public record NoiseThresholdConditionSource3d(ResourceKey<NormalNoise.NoiseParameters> noise, double minThreshold, double maxThreshold) implements SurfaceRules.ConditionSource {
        public static final KeyDispatchDataCodec<NoiseThresholdConditionSource3d> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(ResourceKey.codec(Registries.NOISE).fieldOf("noise").forGetter(NoiseThresholdConditionSource3d::noise), Codec.DOUBLE.fieldOf("min_threshold").forGetter(NoiseThresholdConditionSource3d::minThreshold), Codec.DOUBLE.fieldOf("max_threshold").forGetter(NoiseThresholdConditionSource3d::maxThreshold)).apply(instance, NoiseThresholdConditionSource3d::new);
        }));

        public MapCodec<? extends SurfaceRules.ConditionSource> codec() {
            return CODEC.codec();
        }

        public SurfaceRules.Condition apply(final SurfaceRules.Context context) {
            final NormalNoise normalNoise = context.randomState.getOrCreateNoise(this.noise);

            class NoiseThresholdCondition implements SurfaceRules.Condition {
                public boolean test() {
                    double d = normalNoise.getValue(context.blockX, context.blockY, context.blockZ);
                    return d >= NoiseThresholdConditionSource3d.this.minThreshold && d <= NoiseThresholdConditionSource3d.this.maxThreshold;
                }
            }

            return new NoiseThresholdCondition();
        }

        public ResourceKey<NormalNoise.NoiseParameters> noise() {
            return this.noise;
        }

        public double minThreshold() {
            return this.minThreshold;
        }

        public double maxThreshold() {
            return this.maxThreshold;
        }
    }
}
