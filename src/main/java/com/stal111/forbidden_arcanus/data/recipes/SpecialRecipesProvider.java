package com.stal111.forbidden_arcanus.data.recipes;

import com.stal111.forbidden_arcanus.common.recipe.CombineAurealTankRecipe;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.valhelsia.valhelsia_core.datagen.recipes.RecipeSubProvider;
import net.valhelsia.valhelsia_core.datagen.recipes.ValhelsiaRecipeProvider;

/**
 * @author stal111
 * @since 24.09.2023
 */
public class SpecialRecipesProvider extends RecipeSubProvider {

    public SpecialRecipesProvider(ValhelsiaRecipeProvider provider) {
        super(provider);
    }

    @Override
    protected void registerRecipes() {
        SpecialRecipeBuilder.special(CombineAurealTankRecipe::new).save(this.getRecipeOutput(), "combine_aureal_tank");
    }
}
