import com.zeroc.Ice.Current;
import home.LightFixture;
import home.UnsupportedMode;
import home.lightConfig;
import home.lightMode;

public class CeilingLampImpl implements LightFixture {
    lightConfig config = new lightConfig();

    public CeilingLampImpl() {
        super();
        this.config.mode = lightMode.SOLID;
        this.config.intensity = 1;
        this.config.durationUntilOff = -1;
    }
    @Override
    public void setConfig(lightConfig config, Current current) throws UnsupportedMode {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Received request to set config: " + config);
        System.out.println("Mode: " + config.mode);
        System.out.println("Mode int: " + config.mode.value());
        System.out.println("Intensity: " + config.intensity);
        System.out.println("Duration: " + config.durationUntilOff);
        System.out.println("Color: " + config.color.red + " " + config.color.green + " " + config.color.blue);

        if (config.mode == lightMode.OFF) {
            System.out.println("Turning off the light");
        } else if (config.mode == lightMode.RAINBOW) {
            System.out.println("ERROR: Rainbow mode is not supported");
            throw new UnsupportedMode();
        }
        System.out.println("The config will be set");
        this.config = config;
    }

    @Override
    public lightConfig getConfig(Current current) {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Returning config");
        return this.config;
    }
}
