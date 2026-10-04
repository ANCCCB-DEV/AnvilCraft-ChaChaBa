package dev.anvilcraft.ccb.block;

import net.minecraft.world.level.block.Block;

/**
 * 易碎混凝土。
 *
 * <p>硬度与下界岩相同（0.4），需铁镐及以上工具才能掉落，
 * 且必须精准采集（Silk Touch）才会掉落自身，否则不掉落任何物品。
 * 具体属性与掉落表在 {@code CCBBlocks} 中注册时指定。
 */
public class FragileConcreteBlock extends Block {
    public FragileConcreteBlock(Properties properties) {
        super(properties);
    }
}
