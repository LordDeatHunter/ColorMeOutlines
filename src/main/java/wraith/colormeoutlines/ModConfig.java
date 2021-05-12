package wraith.colormeoutlines;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.MathHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Environment(EnvType.CLIENT)
public class ModConfig {

    private static ModConfig INSTANCE = null;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int alpha = 102;
    private int width = 5;

    private ModConfig(){}

    public static ModConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModConfig();
        }
        return INSTANCE;
    }

    public int getAlpha() {
        return alpha;
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

    public void setRed(int color) {
        this.red = setColor(color);
    }

    public void setGreen(int color) {
        this.green = setColor(color);
    }

    public void setBlue(int color) {
        this.blue = setColor(color);
    }

    public void setAlpha(int color) {
        this.alpha = setColor(alpha);
    }

    public void setWidth(int width) {
        this.width = MathHelper.clamp(width, 1, 10);
    }

    public int getWidth() {
        return this.width;
    }

    private static int setColor(int color) {
        return MathHelper.clamp(color, 0, 255);
    }

    public void save() {
        File file = new File("config/colormeoutlines.json");

        JsonObject json = new JsonObject();
        json.addProperty("red", red);
        json.addProperty("green", green);
        json.addProperty("blue", blue);
        json.addProperty("alpha", alpha);
        json.addProperty("width", width);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        File file = new File("config/colormeoutlines.json");
        if (!file.exists()) {
            save();
            return;
        }
        String contents = "";
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\\Z");
            contents = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject json;
        try {
            json = new JsonParser().parse(contents).getAsJsonObject();
            if (json == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            ColorMeOutlinesClient.LOGGER.error("[Color Me Outlines] Encountered error with config file. Using default values.");
            return;
        }
        if (json.has("red")) {
            this.red = json.get("red").getAsInt();
        }
        if (json.has("green")) {
            this.green = json.get("green").getAsInt();
        }
        if (json.has("blue")) {
            this.blue = json.get("blue").getAsInt();
        }
        if (json.has("alpha")) {
            this.alpha = json.get("alpha").getAsInt();
        }
        if (json.has("width")) {
            this.width = json.get("width").getAsInt();
        }
    }

}
