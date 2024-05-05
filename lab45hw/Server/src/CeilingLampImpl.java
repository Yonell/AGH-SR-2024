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
        System.out.println("Received request to set config: " + config);
        if (config.mode == lightMode.OFF) {
            System.out.println("Turning off the light");
        } else if (config.mode == lightMode.RAINBOW) {
            throw new UnsupportedMode();
        } else {
            throw new UnsupportedMode();
        }
        this.config = config;
    }

    @Override
    public lightConfig getConfig(Current current) {
        System.out.println("Returning config");
        return this.config;
    }
}
