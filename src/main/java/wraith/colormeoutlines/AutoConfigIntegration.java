package wraith.colormeoutlines;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AutoConfigIntegration {

    public static void load() {
        AutoConfig.register(ModCompatConfig.class, GsonConfigSerializer::new);
    }

}
