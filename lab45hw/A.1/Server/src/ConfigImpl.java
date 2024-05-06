import com.zeroc.Ice.Current;
import home.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigImpl implements Config {
    List<String> Lamps = new ArrayList<>();
    List<String> CoffeeMachines = new ArrayList<>();

    public ConfigImpl(List<String> Lamps, List<String> CoffeeMachines) {
        super();
        this.Lamps = new ArrayList<>(Lamps);
        this.CoffeeMachines = new ArrayList<>(CoffeeMachines);
    }

    @Override
    public String[] listLamps(Current current) {
        return Lamps.toArray(new String[0]);
    }

    @Override
    public String[] listCoffeeMachines(Current current) {
        return CoffeeMachines.toArray(new String[0]);
    }
}
