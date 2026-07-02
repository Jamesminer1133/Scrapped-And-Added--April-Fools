package com.jm1133.scrappedfools.entity.client;

import com.jm1133.scrappedfools.ScrappedAddedAprilFools;
import com.jm1133.scrappedfools.entity.custom.RedstoneBug;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Arrays;

public class RedstoneBugModel<T extends RedstoneBug> extends HierarchicalModel<T> {

    private final ModelPart root;

    private final ModelPart[] bodyParts = new ModelPart[7];
    private final ModelPart[] bodyLayers = new ModelPart[3];

    private static final int[][] BODY_SIZES = new int[][]{
            {3, 2, 2}, {4, 3, 2}, {6, 4, 3},
            {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}
    };

    private static final int[][] BODY_TEXS = new int[][]{
            {0, 0}, {0, 4}, {0, 9},
            {0, 16}, {0, 22}, {11, 0}, {13, 4}
    };

    public RedstoneBugModel(ModelPart root) {
        this.root = root;

        Arrays.setAll(this.bodyParts,
                i -> root.getChild("segment" + i));

        Arrays.setAll(this.bodyLayers,
                i -> root.getChild("layer" + i));
    }

    // IMPORTANT: correct override for HierarchicalModel
    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        float f = -3.5F;
        float[] offsets = new float[7];

        for (int i = 0; i < 7; i++) {

            part.addOrReplaceChild(
                    "segment" + i,
                    CubeListBuilder.create()
                            .texOffs(BODY_TEXS[i][0], BODY_TEXS[i][1])
                            .addBox(
                                    -BODY_SIZES[i][0] * 0.5F,
                                    0.0F,
                                    -BODY_SIZES[i][2] * 0.5F,
                                    BODY_SIZES[i][0],
                                    BODY_SIZES[i][1],
                                    BODY_SIZES[i][2]
                            ),
                    PartPose.offset(0.0F, 24 - BODY_SIZES[i][1], f)
            );

            offsets[i] = f;

            if (i < 6) {
                f += (BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
            }
        }

        part.addOrReplaceChild("layer0",
                CubeListBuilder.create().texOffs(20, 0)
                        .addBox(-5, 0, -BODY_SIZES[2][2] / 2.0F, 10, 8, BODY_SIZES[2][2]),
                PartPose.offset(0, 16, offsets[2]));

        part.addOrReplaceChild("layer1",
                CubeListBuilder.create().texOffs(20, 11)
                        .addBox(-3, 0, -BODY_SIZES[4][2] / 2.0F, 6, 4, BODY_SIZES[4][2]),
                PartPose.offset(0, 20, offsets[4]));

        part.addOrReplaceChild("layer2",
                CubeListBuilder.create().texOffs(20, 18)
                        .addBox(-3, 0, -BODY_SIZES[1][2] / 2.0F, 6, 5, BODY_SIZES[1][2]),
                PartPose.offset(0, 19, offsets[1]));

        return LayerDefinition.create(mesh, 64, 32);
    }

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "redstone_bug"),
                    "main"
            );

    @Override
    public void setupAnim(T entity,
                          float limbSwing,
                          float limbSwingAmount,
                          float ageInTicks,
                          float netHeadYaw,
                          float headPitch) {

        for (int i = 0; i < bodyParts.length; i++) {
            bodyParts[i].yRot =
                    Mth.cos(ageInTicks * 0.9F + i * 0.15F * (float)Math.PI)
                            * 0.15F * (1 + Math.abs(i - 2));

            bodyParts[i].x =
                    Mth.sin(ageInTicks * 0.9F + i * 0.15F * (float)Math.PI)
                            * 0.6F * Math.abs(i - 2);
        }

        bodyLayers[0].yRot = bodyParts[2].yRot;
        bodyLayers[1].yRot = bodyParts[4].yRot;
        bodyLayers[1].x = bodyParts[4].x;
        bodyLayers[2].yRot = bodyParts[1].yRot;
        bodyLayers[2].x = bodyParts[1].x;
    }

    public ResourceLocation getTextureLocation(T entity) {
        return ResourceLocation.fromNamespaceAndPath(
                "minecraft",
                "textures/entity/redstone_bug.png"
        );
    }
}