package dev.anvilcraft.ccb.init;

import dev.anvilcraft.ccb.mixin.ModItemGroupsAccessor;
import dev.anvilcraft.lib.v2.registrum.util.CreativeTabSection;
import dev.anvilcraft.lib.v2.registrum.util.CreativeTabSections;
import dev.dubhe.anvilcraft.block.state.Color;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class CCBItemTabContents {
    private CCBItemTabContents() {}

    public static void addItems(
        CreativeModeTab.ItemDisplayParameters parameters,
        CreativeModeTab.Output output
    ) {
        if (ModItemGroupsAccessor.anvilcraft$isLegacyCreativeTabEnabled()) {
            addAll(output);
            return;
        }
        CreativeTabSections.build(
            CCBItemGroups.CHACHABA_ITEMS.getId(),
            parameters,
            output,
            sections -> {
                sections.section(
                    createSection("items/tools", "anvilcraft.creative.section.items.tools"),
                    CCBItemTabContents::addTools
                );
                sections.section(
                    createSection("functional_blocks/functional", "anvilcraft.creative.section.functional_blocks.functional"),
                    CCBItemTabContents::addFunctionalBlocks
                );
                sections.section(
                    createSection("items/foods", "anvilcraft.creative.section.items.foods"),
                    CCBItemTabContents::addFoods
                );
                sections.section(
                    createSection("items/fluids", "anvilcraft.creative.section.items.fluids"),
                    CCBItemTabContents::addFluids
                );
                sections.section(
                    createSection("building_blocks/concrete", "anvilcraft.creative.section.building_blocks.concrete"),
                    CCBItemTabContents::addConcretes
                );
            }
        );
    }

    private static CreativeTabSection createSection(String texturePath, String titleKey) {
        return CreativeTabSection.builder(
                ResourceLocation.fromNamespaceAndPath(
                    "anvilcraft",
                    "textures/gui/creative_inventory/section/" + texturePath + ".png"
                )
            )
            .textAlignment(CreativeTabSection.TextAlignment.RIGHT)
            .textRange(16, 49)
            .text(Component.translatable(titleKey))
            .tooltip(Component.translatable(titleKey))
            .build();
    }

    /**
     * 旧版标签页不支持分区，按分区顺序平铺全部物品。
     */
    private static void addAll(CreativeModeTab.Output output) {
        addTools(output);
        addFunctionalBlocks(output);
        addFoods(output);
        addFluids(output);
        addConcretes(output);
    }

    private static void addTools(CreativeModeTab.Output output) {
        output.accept(CCBItems.TUNING_FORK);
    }

    private static void addFunctionalBlocks(CreativeModeTab.Output output) {
        output.accept(CCBBlocks.CHA_ANVIL);
        output.accept(CCBBlocks.GENETIC_OOZE_BLOCK);
        output.accept(CCBBlocks.CEMENT_WORM_BLOCK);
    }

    private static void addFoods(CreativeModeTab.Output output) {
        output.accept(CCBItems.MUSH_BAR);
        output.accept(CCBItems.MUSH_BAR_BOWL);
        output.accept(CCBItems.MUSH_FRY);
    }

    private static void addFluids(CreativeModeTab.Output output) {
        output.accept(CCBItems.GENETIC_OOZE_BUCKET);
    }

    private static void addConcretes(CreativeModeTab.Output output) {
        for (Color color : Color.values()) {
            output.accept(CCBBlocks.FRAGILE_CONCRETES.get(color).asStack());
        }
    }
}
