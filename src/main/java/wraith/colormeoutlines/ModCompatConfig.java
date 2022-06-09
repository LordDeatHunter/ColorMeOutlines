package wraith.colormeoutlines;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "colormeoutlines")
public class ModCompatConfig implements ConfigData, IColorMeOutlinesConfig {

    @ConfigEntry.Gui.Excluded
    private static ModCompatConfig INSTANCE = null;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    private int red = 0;
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    private int green = 0;
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    private int blue = 0;
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    private int alpha = 102;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    private int width = 5;

    private ModCompatConfig() {
    }

    public static ModCompatConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = AutoConfig.getConfigHolder(ModCompatConfig.class).getConfig();
        }
        return INSTANCE;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getWidth() {
        return width;
    }

}
