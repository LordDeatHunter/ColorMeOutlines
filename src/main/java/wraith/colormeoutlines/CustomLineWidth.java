package wraith.colormeoutlines;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderPhase;

import java.util.OptionalDouble;

@Environment(EnvType.CLIENT)
public class CustomLineWidth extends RenderPhase.LineWidth {

    public CustomLineWidth() {
        super(OptionalDouble.empty());
    }

    @Override
    public void startDrawing() {
        super.startDrawing();
        if (ColorMeOutlinesClient.isClothMode()) {
            RenderSystem.lineWidth(ModCompatConfig.getInstance().width * 0.5F);
        } else {
            RenderSystem.lineWidth(ModConfig.getInstance().getWidth() * 0.5F);
        }
    }

    @Override
    public void endDrawing() {
        super.endDrawing();
        RenderSystem.lineWidth(1.0F);
    }
}
