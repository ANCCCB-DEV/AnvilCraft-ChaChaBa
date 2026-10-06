package dev.anvilcraft.ccb.block;

import net.minecraft.world.level.block.Block;

/**
 * 易碎混凝土。
 *
 * <p>硬度 0.4（同下界岩），需用镐类工具挖掘，任意材质等级均可；
 * 但必须精准采集（Silk Touch）才会掉落自身，否则不掉落任何物品。
 * 具体属性与掉落表在 {@code CCBBlocks} 中注册时指定。
 */
public class FragileConcreteBlock extends Block {
    public FragileConcreteBlock(Properties properties) {
        super(properties);
    }
}
