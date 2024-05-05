import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import home.CoffeeMachine;
import home.LightFixture;

public class Server {
    public void t1(String[] args) {
        int status = 0;
        Communicator communicator = null;
        try{
            communicator = Util.initialize(args);
            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");
            LightFixture lamp = new CeilingLampImpl();
            adapter.add(lamp, new Identity("LivingRoomLamp", "Lamp"));
            LightFixture lamp2 = new CeilingLampImpl();
            adapter.add(lamp2, new Identity("KitchenLamp", "Lamp"));
            LightFixture lamp3 = new CeilingLampImpl();
            adapter.add(lamp3, new Identity("BedroomLamp", "Lamp"));
            LightFixture lamp4 = new AmbientLightImpl();
            adapter.add(lamp4, new Identity("LivingRoomAmbientLamp", "Lamp"));
            LightFixture lamp5 = new AmbientLightImpl();
            adapter.add(lamp5, new Identity("BedroomAmbientLamp", "Lamp"));
            CoffeeMachine coffeeMachine = new CoffeeMachineImpl();
            adapter.add(coffeeMachine, new Identity("KitchenCoffeeMachine", "CoffeeMachine"));
            adapter.activate();
            System.out.println("Entering event processing loop...");
            communicator.waitForShutdown();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if(communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);

    }
    public static void main(String[] args) {
        Server app = new Server();
        app.t1(args);
    }
}