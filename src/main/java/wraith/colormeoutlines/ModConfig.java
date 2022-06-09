package wraith.colormeoutlines;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Environment(EnvType.CLIENT)
public class ModConfig implements IColorMeOutlinesConfig {

    private static ModConfig INSTANCE = null;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int alpha = 102;
    private int width = 5;

    private ModConfig() {}

    public static ModConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModConfig();
        }
        return INSTANCE;
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

    @Override
    public int getRed() {
        return this.red;
    }

    @Override
    public int getGreen() {
        return this.green;
    }

    @Override
    public int getBlue() {
        return this.blue;
    }

    @Override
    public int getAlpha() {
        return this.alpha;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

}
