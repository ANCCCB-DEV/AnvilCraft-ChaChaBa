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
            addConcretes(output);
            return;
        }
        CreativeTabSections.build(
            CCBItemGroups.CHACHABA_ITEMS.getId(),
            parameters,
            output,
            sections -> {
                sections.section(
                    createSection(
                        "building_blocks/concrete",
                        "anvilcraft.creative.section.building_blocks.concrete"
                    ),
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

    private static void addConcretes(CreativeModeTab.Output output) {
        for (Color color : Color.values()) {
            output.accept(CCBBlocks.FRAGILE_CONCRETES.get(color).asStack());
        }
    }
}
