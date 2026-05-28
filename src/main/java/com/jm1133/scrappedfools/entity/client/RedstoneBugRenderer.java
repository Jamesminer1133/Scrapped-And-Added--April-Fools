package com.jm1133.scrappedfools.entity.client;

import com.jm1133.scrappedfools.ScrappedAddedAprilFools;
import com.jm1133.scrappedfools.entity.custom.RedstoneBug;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RedstoneBugRenderer extends MobRenderer<RedstoneBug, RedstoneBugModel<RedstoneBug>> {

    public RedstoneBugRenderer(EntityRendererProvider.Context context) {
        super(context,
                new RedstoneBugModel<>(context.bakeLayer(RedstoneBugModel.LAYER_LOCATION)),
                0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(RedstoneBug entity) {
        return ResourceLocation.fromNamespaceAndPath(ScrappedAddedAprilFools.MODID, "textures/entity/redstone_bug.png");
    }
}