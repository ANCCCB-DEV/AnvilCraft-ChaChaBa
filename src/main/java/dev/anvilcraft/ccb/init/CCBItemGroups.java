package dev.anvilcraft.ccb.init;

import dev.anvilcraft.ccb.AnvilCraftCCB;
import dev.dubhe.anvilcraft.init.item.ModItemGroups;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.anvilcraft.ccb.AnvilCraftCCB.REGISTRUM;

public class CCBItemGroups {
    private static final DeferredRegister<CreativeModeTab> DEFERRED_REGISTER = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB,
        AnvilCraftCCB.MOD_ID
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CHACHABA_ITEMS = DEFERRED_REGISTER.register(
        "item", () -> CreativeModeTab.builder()
            .icon(CCBItems.TUNING_FORK::asStack)
            .displayItems(CCBItemTabContents::addItems)
            .title(REGISTRUM.addLang("itemGroup", AnvilCraftCCB.of("addon_items"), "AnvilCraft: ChaChaBa"))
            .withTabsBefore(ModItemGroups.ANVILCRAFT_BUILDING_BLOCKS.getId(), ModItemGroups.ANVILCRAFT_ITEMS.getId())
            .build()
    );

    public static void register(IEventBus modEventBus) {
        DEFERRED_REGISTER.register(modEventBus);
    }
}
