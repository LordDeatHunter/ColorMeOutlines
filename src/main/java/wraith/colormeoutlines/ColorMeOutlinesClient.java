package wraith.colormeoutlines;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class ColorMeOutlinesClient implements ClientModInitializer {

    private static boolean cloth_mode = false;

    public static final Logger LOGGER = LogManager.getLogger();

    public static IColorMeOutlinesConfig getConfig() {
        return isClothMode() ? ModCompatConfig.getInstance() : ModConfig.getInstance();
    }

    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            AutoConfigIntegration.load();
            cloth_mode = true;
        } else {
            ModConfig.getInstance().load();
        }
        LOGGER.info("[Color Me Outlines] has successfully been loaded!");
    }

    public static boolean isClothMode() {
        return cloth_mode;
    }

}
