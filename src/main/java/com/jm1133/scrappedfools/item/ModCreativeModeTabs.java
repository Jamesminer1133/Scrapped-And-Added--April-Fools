package com.jm1133.scrappedfools.item;

import com.jm1133.scrappedfools.ScrappedAddedAprilFools;
import com.jm1133.scrappedfools.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScrappedAddedAprilFools.MODID);

    public static final Supplier<CreativeModeTab> SCRAPPED_FOOLS_TAB =
            CREATIVE_MODE_TAB.register("scrapped_fools_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModBlocks.WHITE_TINTED_GLASS.get()))
                            .title(Component.translatable("creativetab.scrappedfools.tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModBlocks.WHITE_TINTED_GLASS.get());
                                output.accept(ModBlocks.RED_TINTED_GLASS.get());
                                output.accept(ModBlocks.CYAN_TINTED_GLASS.get());
                                output.accept(ModBlocks.ORANGE_TINTED_GLASS.get());
                                output.accept(ModBlocks.PURPLE_TINTED_GLASS.get());
                                output.accept(ModBlocks.LIGHT_PURPLE_TINTED_GLASS.get());
                                output.accept(ModBlocks.BLUE_TINTED_GLASS.get());
                                output.accept(ModBlocks.LIGHT_BLUE_TINTED_GLASS.get());
                                output.accept(ModBlocks.BROWN_TINTED_GLASS.get());
                                output.accept(ModBlocks.YELLOW_TINTED_GLASS.get());
                                output.accept(ModBlocks.GREEN_TINTED_GLASS.get());
                                output.accept(ModBlocks.LIGHT_GREEN_TINTED_GLASS.get());
                                output.accept(ModBlocks.PINK_TINTED_GLASS.get());
                                output.accept(ModBlocks.BLACK_TINTED_GLASS.get());
                                output.accept(ModBlocks.GREY_TINTED_GLASS.get());
                                output.accept(ModBlocks.LIGHT_GREY_TINTED_GLASS.get());
                                output.accept(ModBlocks.TNT_SLAB.get());
                                output.accept(ModBlocks.TORCH_OFF.get());
                                output.accept(ModItems.REDSTONE_BUG_SPAWN_EGG.get());
                                output.accept(Blocks.COAL_BLOCK);
                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}