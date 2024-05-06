import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import home.Config;

public class Server {
    public void t1(String[] args) {
        int status = 0;
        Communicator communicator = null;
        try{
            communicator = Util.initialize(args);
            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

            Config config = new ConfigImpl();
            adapter.add(config, new Identity("Config", "Config"));
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