package com.jm1133.scrappedfools.entity.client;

import com.jm1133.scrappedfools.entity.custom.RedstoneBug;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedstoneBugRenderer extends MobRenderer<RedstoneBug, RedstoneBugModel<RedstoneBug>> {

    public RedstoneBugRenderer(EntityRendererProvider.Context context) {
        super(context,
                new RedstoneBugModel<>(context.bakeLayer(RedstoneBugModel.LAYER_LOCATION)),
                0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(RedstoneBug entity) {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/redstone_bug.png");
    }
}