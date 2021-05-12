package wraith.colormeoutlines;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormats;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class CustomRenderLayer extends RenderLayer {

    public static final RenderLayer OUTLINE = RenderLayer.of("colormeoutlines", VertexFormats.POSITION_COLOR, GL11.GL_LINES, 256, true, true,
            RenderLayer.MultiPhaseParameters.builder()
                    .layering(VIEW_OFFSET_Z_LAYERING)
                    .lineWidth(new CustomLineWidth())
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .writeMaskState(ALL_MASK)
                    .build(true));

    private CustomRenderLayer() {
        super("colormeoutlines", VertexFormats.POSITION, 0, 0, false, false, null, null);
    }

}
