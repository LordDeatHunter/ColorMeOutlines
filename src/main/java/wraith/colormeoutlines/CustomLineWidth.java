package wraith.colormeoutlines;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderPhase;

import java.util.OptionalDouble;

@Environment(EnvType.CLIENT)
public class CustomLineWidth extends RenderPhase.LineWidth {

    public CustomLineWidth() {
        super(OptionalDouble.empty());
    }

}
