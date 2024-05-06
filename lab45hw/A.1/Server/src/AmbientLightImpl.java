import com.zeroc.Ice.Current;
import home.LightFixture;
import home.UnsupportedMode;
import home.lightConfig;
import home.lightMode;

public class AmbientLightImpl implements LightFixture {
    lightConfig config = new lightConfig();

    public AmbientLightImpl() {
        super();
        this.config.mode = lightMode.SOLID;
        this.config.intensity = 1;
        this.config.color.red = 255;
        this.config.color.green = 255;
        this.config.color.blue = 255;
        this.config.durationUntilOff = -1;
    }
    @Override
    public void setConfig(lightConfig config, Current current) throws UnsupportedMode {
        System.out.println("Received request to set config: " + config);
        if (config.mode == lightMode.OFF) {
            System.out.println("Turning off the light");
        }
        System.out.println("The config will be set");
        this.config = config;
    }

    @Override
    public lightConfig getConfig(Current current) {
        System.out.println("Returning config");
        return config;
    }
}
