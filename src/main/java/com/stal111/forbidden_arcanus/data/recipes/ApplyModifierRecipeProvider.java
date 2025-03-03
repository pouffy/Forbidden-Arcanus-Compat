package com.stal111.forbidden_arcanus.data.recipes;

import com.stal111.forbidden_arcanus.common.item.modifier.ItemModifier;
import com.stal111.forbidden_arcanus.core.init.ModItems;
import com.stal111.forbidden_arcanus.core.init.other.ModItemModifiers;
import com.stal111.forbidden_arcanus.data.recipes.builder.ApplyModifierRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.valhelsia.valhelsia_core.data.recipes.RecipeSubProvider;
import net.valhelsia.valhelsia_core.data.recipes.ValhelsiaRecipeProvider;

/**
 * @author stal111
 * @since 2022-10-21
 */
public class ApplyModifierRecipeProvider extends RecipeSubProvider {


    public ApplyModifierRecipeProvider(ValhelsiaRecipeProvider provider) {
        super(provider);
    }

    @Override
    protected void registerRecipes() {
        this.modifier(ModItems.ETERNAL_STELLA.get(), ModItemModifiers.ETERNAL.get());
        this.modifier(ModItems.SMELTER_PRISM.get(), ModItemModifiers.FIERY.get());
        this.modifier(ModItems.FERROGNETIC_MIXTURE.get(), ModItemModifiers.MAGNETIZED.get());
        this.modifier(ModItems.TERRASTOMP_PRISM.get(), ModItemModifiers.DEMOLISHING.get());
        this.modifier(ModItems.SEA_PRISM.get(), ModItemModifiers.AQUATIC.get());
    }

    private void modifier(ItemLike addition, ItemModifier modifier) {
        this.add(ApplyModifierRecipeBuilder.of(addition, modifier));
    }
}
