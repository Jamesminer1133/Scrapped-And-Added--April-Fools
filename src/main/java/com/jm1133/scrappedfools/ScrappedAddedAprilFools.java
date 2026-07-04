package com.jm1133.scrappedfools;

import com.jm1133.scrappedfools.block.ModBlocks;
import com.jm1133.scrappedfools.entity.ModEntities;
import com.jm1133.scrappedfools.entity.client.RedstoneBugRenderer;
import com.jm1133.scrappedfools.event.ModEventBusEvents;
import com.jm1133.scrappedfools.event.RedstoneBugEvent;
import com.jm1133.scrappedfools.item.ModCreativeModeTabs;
import com.jm1133.scrappedfools.item.ModItems;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;



// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ScrappedAddedAprilFools.MODID)
public class ScrappedAddedAprilFools {

    public static final String MODID = "jm1133_fool";

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.REDSTONEBUG.get(),
                RedstoneBugRenderer::new
        );
    }

    public ScrappedAddedAprilFools(IEventBus modEventBus, ModContainer modContainer){
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEventBusEvents.register(modEventBus);
        NeoForge.EVENT_BUS.register(RedstoneBugEvent.class);
        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(this::registerRenderers);
        }

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(
                    ModBlocks.TNT_SLAB.get(),
                    DispenserBlock.DISPENSER_REGISTRY.get(Items.TNT)
            );
        });
    }
}