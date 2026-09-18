package dev.anvilcraft.ccb;

import dev.anvilcraft.lib.v2.registrum.Registrum;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(AnvilCraftCCB.MOD_ID)
public class AnvilCraftCCB {
    public static final String MOD_ID = "anvilcraft_chachaba";
    public static final String MOD_NAME = "AnvilCraft: ChaChaBa";
    public static final Registrum REGISTRUM = Registrum.create(AnvilCraftCCB.MOD_ID);

    public AnvilCraftCCB(IEventBus modEventBus, ModContainer container) {
    }
}
