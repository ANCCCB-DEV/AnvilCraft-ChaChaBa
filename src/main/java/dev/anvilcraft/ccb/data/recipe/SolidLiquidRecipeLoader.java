package dev.anvilcraft.ccb.data.recipe;

import dev.anvilcraft.ccb.AnvilCraftCCB;
import dev.anvilcraft.ccb.init.CCBBlocks;
import dev.anvilcraft.ccb.init.CCBFluids;
import dev.anvilcraft.lib.v2.registrum.providers.RegistrumRecipeProvider;
import dev.dubhe.anvilcraft.block.state.Color;
import dev.dubhe.anvilcraft.init.block.ModBlocks;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.SolidLiquidRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class SolidLiquidRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        SolidLiquidRecipe.builder()
            .cauldron(CCBFluids.GENETIC_OOZE.get())
            .consume(250)
            .requires(Tags.Items.FOODS, 1)
            .requires(Items.DIRT)
            .transform(CCBFluids.GENETIC_OOZE, 500)
            .save(provider, AnvilCraftCCB.of("solid_liquid/genetic_ooze"));

        for (Color color : Color.values()) {
            Item result = CCBBlocks.FRAGILE_CONCRETES.get(color).asItem();
            String colorName = color.getSerializedName();
            SolidLiquidRecipe.builder()
                .cauldron(ModBlocks.CEMENT_CAULDRONS.get(color).get())
                .requires(Tags.Items.SANDS, 4)
                .result(result, 16)
                .save(provider, AnvilCraftCCB.of("solid_liquid/fragile_concrete/%s_from_sand".formatted(colorName)));
            SolidLiquidRecipe.builder()
                .cauldron(ModBlocks.CEMENT_CAULDRONS.get(color).get())
                .requires(Tags.Items.GRAVELS, 4)
                .result(result, 16)
                .save(provider, AnvilCraftCCB.of("solid_liquid/fragile_concrete/%s_from_gravel".formatted(colorName)));
        }
    }
}
