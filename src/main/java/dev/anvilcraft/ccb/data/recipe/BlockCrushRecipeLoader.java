package dev.anvilcraft.ccb.data.recipe;

import dev.anvilcraft.ccb.AnvilCraftCCB;
import dev.anvilcraft.ccb.init.CCBBlocks;
import dev.anvilcraft.lib.v2.registrum.providers.RegistrumRecipeProvider;
import dev.dubhe.anvilcraft.block.state.Color;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.BlockCrushRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockCrushRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        for (Color color : Color.values()) {
            Block concrete = BuiltInRegistries.BLOCK.get(
                ResourceLocation.withDefaultNamespace("%s_concrete".formatted(color.getSerializedName()))
            );
            BlockCrushRecipe.builder()
                .input(concrete)
                .result(CCBBlocks.FRAGILE_CONCRETES.get(color).get())
                .save(provider, AnvilCraftCCB.of("block_crush/fragile_concrete/%s".formatted(color.getSerializedName())));
        }
    }
}
