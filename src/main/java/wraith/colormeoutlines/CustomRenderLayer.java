package wraith.colormeoutlines;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import wraith.colormeoutlines.mixin.RenderLayerInvoker;

@Environment(EnvType.CLIENT)
public class CustomRenderLayer extends RenderLayer {

    public static final RenderLayer OUTLINE = RenderLayerInvoker.of("colormeoutlines_outline", VertexFormats.LINES, VertexFormat.DrawMode.LINES, 256, true, true,
            RenderLayer.MultiPhaseParameters.builder()
                    .shader(LINES_SHADER)
                    .layering(VIEW_OFFSET_Z_LAYERING)
                    .lineWidth(new CustomLineWidth())
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .target(ITEM_TARGET)
                    .writeMaskState(ALL_MASK)
                    .cull(DISABLE_CULLING)
                    .build(true));

    private CustomRenderLayer() {
        super("colormeoutlines", VertexFormats.POSITION, VertexFormat.DrawMode.LINES, 0, false, false, null, null);
    }

}
