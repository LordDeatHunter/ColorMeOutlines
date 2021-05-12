package wraith.colormeoutlines;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "colormeoutlines")
public class ModCompatConfig implements ConfigData {

    private static ModCompatConfig INSTANCE = null;

    @ConfigEntry.BoundedDiscrete(min=0, max=255)
    public int red = 0;

    @ConfigEntry.BoundedDiscrete(min=0, max=255)
    public int green = 0;

    @ConfigEntry.BoundedDiscrete(min=0, max=255)
    public int blue = 0;

    @ConfigEntry.BoundedDiscrete(min=0, max=255)
    public int alpha = 102;

    @ConfigEntry.BoundedDiscrete(min=1, max=10)
    public int width = 5;

    private ModCompatConfig(){}

    public static ModCompatConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = AutoConfig.getConfigHolder(ModCompatConfig.class).getConfig();
        }
        return INSTANCE;
    }

}
