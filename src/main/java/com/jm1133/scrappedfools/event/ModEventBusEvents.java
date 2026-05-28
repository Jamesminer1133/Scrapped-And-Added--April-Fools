package com.jm1133.scrappedfools.event;

import com.jm1133.scrappedfools.entity.ModEntities;
import com.jm1133.scrappedfools.entity.client.RedstoneBugModel;
import com.jm1133.scrappedfools.entity.custom.RedstoneBug;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModEventBusEvents {

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModEventBusEvents::registerLayers);
        modEventBus.addListener(ModEventBusEvents::registerAttributes);
    }

    private static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                RedstoneBugModel.LAYER_LOCATION,
                RedstoneBugModel::createBodyLayer
        );
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.REDSTONEBUG.get(),
                RedstoneBug.createAttributes().build());
    }
}