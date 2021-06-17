package wraith.colormeoutlines.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import wraith.colormeoutlines.ColorMeOutlinesClient;
import wraith.colormeoutlines.CustomRenderLayer;
import wraith.colormeoutlines.ModCompatConfig;
import wraith.colormeoutlines.ModConfig;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    @Final
    private BufferBuilderStorage bufferBuilders;

    @ModifyArgs(method = "drawBlockOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawShapeOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/shape/VoxelShape;DDDFFFF)V"))
    public void drawBlockOutline(Args args, MatrixStack matrixStack, VertexConsumer vertexConsumer, Entity entity, double d, double e, double f, BlockPos blockPos, BlockState blockState) {
        VertexConsumer buffer = bufferBuilders.getEntityVertexConsumers().getBuffer(CustomRenderLayer.OUTLINE);
        args.set(1, buffer);

        if (ColorMeOutlinesClient.isClothMode()) {
            ModCompatConfig config = ModCompatConfig.getInstance();
            args.set(6, config.red / 255F);
            args.set(7, config.green / 255F);
            args.set(8, config.blue / 255F);
            args.set(9, config.alpha / 255F);
        } else {
            ModConfig config = ModConfig.getInstance();
            args.set(6, config.getRed() / 255F);
            args.set(7, config.getGreen() / 255F);
            args.set(8, config.getBlue() / 255F);
            args.set(9, config.getAlpha() / 255F);
        }

    }

}
