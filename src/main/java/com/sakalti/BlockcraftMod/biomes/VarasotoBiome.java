// VarasotoBiome.java
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.block.Blocks;

public class VarasotoBiome {
    public static final Biome VARASOTO = createVarasotoBiome();

    private static Biome createVarasotoBiome() {
        // 地表設定
        TernarySurfaceConfig surfaceConfig = new TernarySurfaceConfig(
            Blocks.RED_CONCRETE.getDefaultState(), // 表層
            Blocks.STONE.getDefaultState(), // 中層
            Blocks.STONE.getDefaultState() // 下層（未使用）
        );
        ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, surfaceConfig);

        // 地形生成設定
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultOres(generationSettings); // 鉱石を追加
        DefaultBiomeFeatures.addForestTrees(generationSettings); // 樹木を追加

        // モブのスポーン設定
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntities.RED, 10, 1, 5)); // エンティティ「red」のスポーン設定

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.PLAINS)
            .temperature(0.5f)
            .downfall(0.0f)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xC0D8FF)
                .skyColor(0x77AFFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .build())
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}
