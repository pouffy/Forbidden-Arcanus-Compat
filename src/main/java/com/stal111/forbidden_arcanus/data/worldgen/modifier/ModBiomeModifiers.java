package com.stal111.forbidden_arcanus.data.worldgen.modifier;

import com.stal111.forbidden_arcanus.core.init.ModEntities;
import com.stal111.forbidden_arcanus.data.worldgen.placement.ModCavePlacements;
import com.stal111.forbidden_arcanus.data.worldgen.placement.ModOrePlacements;
import com.stal111.forbidden_arcanus.data.worldgen.placement.ModTreePlacements;
import com.stal111.forbidden_arcanus.data.worldgen.placement.ModVegetationPlacements;
import com.stal111.forbidden_arcanus.util.ModTags;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.data.worldgen.ValhelsiaBiomeModifierProvider;

/**
 * @author stal111
 * @since 2022-06-15
 */
public class ModBiomeModifiers extends ValhelsiaBiomeModifierProvider {

    public ModBiomeModifiers(DataProviderInfo info, BootstapContext<BiomeModifier> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<BiomeModifier> context) {
        this.addFeature("add_arcane_crystal_ore", this.isOverworld, this.directFeature(ModOrePlacements.ARCANE_CRYSTAL_ORE), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_runic_stone", this.isOverworld, this.directFeature(ModOrePlacements.RUNIC_STONE), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_darkstone", this.isOverworld, this.directFeature(ModOrePlacements.DARKSTONE), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_arcane_gilded_darkstone", this.isOverworld, this.directFeature(ModOrePlacements.ARCANE_GILDED_DARKSTONE), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_stella_arcanum", this.isOverworld, this.directFeature(ModOrePlacements.STELLA_ARCANUM), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_xpetrified_ore", this.isOverworld, this.directFeature(ModOrePlacements.XPETRIFIED_ORE), GenerationStep.Decoration.UNDERGROUND_ORES);
        this.addFeature("add_petrified_roots", this.isOverworld, this.directFeature(ModCavePlacements.PETRIFIED_ROOT), GenerationStep.Decoration.UNDERGROUND_ORES);

        this.addFeature("add_cherry_trees", this.directBiome(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS), this.directFeature(ModVegetationPlacements.CHERRY_TREES_PLAINS), GenerationStep.Decoration.VEGETAL_DECORATION);
        this.addFeature("add_edelwood_trees", this.directBiome(Biomes.DARK_FOREST), this.directFeature(ModTreePlacements.EDELWOOD_TREES), GenerationStep.Decoration.VEGETAL_DECORATION);
        this.addFeature("add_aurum_trees", this.directBiome(Biomes.FLOWER_FOREST), this.directFeature(ModTreePlacements.AURUM_TREES), GenerationStep.Decoration.VEGETAL_DECORATION);
        this.addFeature("add_yellow_orchids", this.directBiome(Biomes.FLOWER_FOREST), this.directFeature(ModVegetationPlacements.YELLOW_ORCHID), GenerationStep.Decoration.VEGETAL_DECORATION);

        this.addSpawn("add_lost_soul_overworld", this.isOverworld, new MobSpawnSettings.SpawnerData(ModEntities.LOST_SOUL.get(), 35, 1, 3));
        this.addNetherSpawn("add_lost_soul_nether", this.namedBiome(ModTags.Biomes.SPAWNS_CORRUPT_LOST_SOUL), MobCategory.MONSTER, 1.1D, 1.0D, new MobSpawnSettings.SpawnerData(ModEntities.LOST_SOUL.get(), 60, 1, 4));
    }
}
