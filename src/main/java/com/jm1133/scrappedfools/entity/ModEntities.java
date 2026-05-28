package com.jm1133.scrappedfools.entity;

import com.jm1133.scrappedfools.ScrappedAddedAprilFools;
import com.jm1133.scrappedfools.entity.custom.RedstoneBug;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,
                    ScrappedAddedAprilFools.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<RedstoneBug>> REDSTONEBUG =
            ENTITY_TYPES.register("redstone_bug",
                    () -> EntityType.Builder
                            .of(RedstoneBug::new, MobCategory.CREATURE)
                            .sized(0.75f, 0.35f)
                            .build("redstone_bug"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}