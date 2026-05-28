package com.jm1133.scrappedfools.item;

import com.jm1133.scrappedfools.ScrappedAddedAprilFools;
import com.jm1133.scrappedfools.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ScrappedAddedAprilFools.MODID);

    public static final DeferredItem<Item> REDSTONE_BUG_SPAWN_EGG = ITEMS.register("redstone_bug_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.REDSTONEBUG, 0xa80100, 0xff4343,
                    new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
