package dev.anvilcraft.ccb.init;

import dev.anvilcraft.ccb.AnvilCraftCCB;
import dev.anvilcraft.ccb.block.CementWormBlock;
import dev.anvilcraft.ccb.block.ChaAnvilBlock;
import dev.anvilcraft.ccb.block.FragileConcreteBlock;
import dev.anvilcraft.ccb.block.GeneticOozeBlock;
import dev.anvilcraft.ccb.block.GeneticOozeCauldronBlock;
import dev.anvilcraft.ccb.block.item.WormBlockItem;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.dubhe.anvilcraft.block.state.Color;
import dev.dubhe.anvilcraft.data.AnvilCraftDatagen;
import dev.dubhe.anvilcraft.init.block.ModBlockTags;
import dev.dubhe.anvilcraft.init.item.ModItemTags;
import dev.dubhe.anvilcraft.util.DataGenUtil;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.client.model.generators.ModelFile;

import static dev.anvilcraft.ccb.AnvilCraftCCB.REGISTRUM;

public class CCBBlocks {
    static {
        REGISTRUM.defaultCreativeTab(CCBItemGroups.CHACHABA_ITEMS.getKey());
    }

    @SuppressWarnings("unused")
    public static BlockEntry<ChaAnvilBlock> CHA_ANVIL = REGISTRUM
        .block("cha_anvil", ChaAnvilBlock::new)
        .blockstate(DataGenUtil::noExtraModelOrState)
        .initialProperties(() -> Blocks.SPRUCE_PLANKS)
        .simpleItem()
        .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
            .pattern("AAA")
            .pattern(" M ")
            .pattern("BBB")
            .define('A', Items.BROWN_WOOL)
            .define('M', Items.MELON)
            .define('B', Items.SPRUCE_PLANKS)
            .unlockedBy(AnvilCraftDatagen.hasItem(Items.MELON), AnvilCraftDatagen.has(Items.MELON))
            .save(provider))
        .register();

    public static BlockEntry<GeneticOozeBlock> GENETIC_OOZE_BLOCK = REGISTRUM
        .block("genetic_ooze_block", GeneticOozeBlock::new)
        .lang("Genetic Ooze")
        .simpleItem()
        .initialProperties(() -> Blocks.MUD)
        .properties(BlockBehaviour.Properties::noLootTable)
        .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry()))
        .register();

    public static final BlockEntry<GeneticOozeCauldronBlock> GENETIC_OOZE_CAULDRON = REGISTRUM
        .block("genetic_ooze_cauldron", GeneticOozeCauldronBlock::new)
        .initialProperties(() -> Blocks.CAULDRON)
        .blockstate(DataGenUtil::noExtraModelOrState)
        .loot((tables, block) -> tables.dropOther(block, Items.CAULDRON))
        .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.CAULDRONS)
        .onRegister(block -> Item.BY_BLOCK.put(block, Items.CAULDRON))
        .register();

    @SuppressWarnings("unused")
    public static final BlockEntry<CementWormBlock>  CEMENT_WORM_BLOCK = REGISTRUM
        .block("cement_worm_block", CementWormBlock::new)
        .lang("Cement Worm")
        .initialProperties(() -> Blocks.MUD)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate(DataGenUtil::noExtraModelOrState)
        .item(WormBlockItem::new)
        .properties(properties -> properties.component(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY))
        .model((ctx, prov) -> prov.getBuilder(ctx.getName())
            .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
            .texture("particle", AnvilCraftCCB.of("block/cement_worm_side"))
            .transforms()
            .transform(ItemDisplayContext.GUI)
            .rotation(30, 225, 0).scale(0.625F).end()
            .transform(ItemDisplayContext.GROUND)
            .translation(0, 3, 0).scale(0.25F).end()
            .transform(ItemDisplayContext.FIXED)
            .scale(0.5F).end()
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(75, 45, 0).translation(0, 2.5F, 0).scale(0.375F).end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .rotation(0, 45, 0).scale(0.4F).end()
            .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
            .rotation(0, 225, 0).scale(0.4F).end()
            .end())
        .build()
        .register();

    /**
     * 易碎混凝土，16 色。
     *
     * <p>硬度 0.4（同下界岩），任意镐子均可挖掘，但必须精准采集才会掉落自身。
     */
    public static final Object2ObjectMap<Color, BlockEntry<FragileConcreteBlock>> FRAGILE_CONCRETES
        = registerFragileConcretes();

    private static Object2ObjectMap<Color, BlockEntry<FragileConcreteBlock>> registerFragileConcretes() {
        Object2ObjectMap<Color, BlockEntry<FragileConcreteBlock>> map = new Object2ObjectLinkedOpenHashMap<>();
        for (Color color : Color.values()) {
            map.put(color, registerFragileConcreteBlock(color));
        }
        return map;
    }

    private static BlockEntry<FragileConcreteBlock> registerFragileConcreteBlock(Color color) {
        return REGISTRUM.block("fragile_concrete_" + color, FragileConcreteBlock::new)
            .lang("Fragile " + toDisplayName(color) + " Concrete")
            .initialProperties(() -> Blocks.NETHERRACK)
            .properties(properties -> properties
                .strength(0.4F)
                .requiresCorrectToolForDrops())
            .item()
            .tag(ModItemTags.DYED_COLORS.get(color))
            .build()
            .blockstate((ctx, provider) -> provider.simpleBlock(
                ctx.getEntry(),
                provider.models()
                    .cubeAll(ctx.getName(), AnvilCraftCCB.of("block/fragile_concrete_" + color))
            ))
            .loot((tables, block) -> tables.add(block, tables.createSilkTouchOnlyTable(block)))
            .tag(
                BlockTags.MINEABLE_WITH_PICKAXE,
                ModBlockTags.DYED_COLORS.get(color)
            )
            .register();
    }

    /**
     * 把 {@link Color} 的序列化名转成显示名，例如 {@code light_blue} → {@code Light Blue}。
     *
     * @param color 颜色枚举
     * @return 首字母大写的空格分隔显示名
     */
    private static String toDisplayName(Color color) {
        StringBuilder sb = new StringBuilder();
        for (String word : color.getSerializedName().split("_")) {
            if (word.isEmpty()) continue;
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return sb.toString();
    }

    public static void register() {}
}
