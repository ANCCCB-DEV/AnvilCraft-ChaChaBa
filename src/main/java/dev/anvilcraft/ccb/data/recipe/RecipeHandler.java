package dev.anvilcraft.ccb.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumRecipeProvider;

public class RecipeHandler {
    public static void init(RegistrumRecipeProvider provider) {
        FastCookingRecipeLoader.init(provider);
        BlockCrushRecipeLoader.init(provider);
        ItemInjectRecipeLoader.init(provider);
        ProceduralProcessRecipeLoader.init(provider);
        SolidLiquidRecipeLoader.init(provider);
        StampingRecipeLoader.init(provider);
    }
}
