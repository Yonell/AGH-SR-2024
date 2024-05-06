import home.Busy;
import home.CoffeeMachine;
import home.coffeeType;

import java.util.Arrays;

public class CoffeeMachineImpl implements CoffeeMachine{

    // in ms
    long coffeeBusyFor = 0;
    long coffeeLastStarted = 1;

    @Override
    public void makeCoffee(coffeeType type, com.zeroc.Ice.Current current) throws Busy {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Received request for coffee: " + type);
        if (System.currentTimeMillis() - coffeeLastStarted < coffeeBusyFor) {
            System.out.println("ERROR: Busy");
            throw new Busy();
        }
        coffeeLastStarted = System.currentTimeMillis();
        switch (type) {
            case ESPRESSO:
                coffeeBusyFor = 3000;
                break;
            case LATTE:
                coffeeBusyFor = 4000;
                break;
            case CAPPUCCINO:
                coffeeBusyFor = 5000;
                break;
            case AMERICANO:
                coffeeBusyFor = 7000;
                break;
            default:
                coffeeBusyFor = 0;
        }
        System.out.println("Making coffee: " + type);
        System.out.println("Coffee will be ready in " + coffeeBusyFor / 1000 + " seconds");
    }

    @Override
    public void makeCoffees(coffeeType[] types, com.zeroc.Ice.Current current) throws Busy {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Received request for coffees: " + Arrays.toString(types));
        if (System.currentTimeMillis() - coffeeLastStarted < coffeeBusyFor) {
            throw new Busy();
        }
        coffeeLastStarted = System.currentTimeMillis();
        long sum = 0;
        for (coffeeType type : types) {
            switch (type) {
                case ESPRESSO:
                    sum += 3000;
                    break;
                case LATTE:
                    sum += 4000;
                    break;
                case CAPPUCCINO:
                    sum += 5000;
                    break;
                case AMERICANO:
                    sum += 7000;
                    break;
                default:
                    sum += 0;
            }
        }
        coffeeBusyFor = sum;
        System.out.println("Making coffees: " + Arrays.toString(types));
        System.out.println("Coffees will be ready in " + sum / 1000 + " seconds");

    }

    @Override
    public void cancel(com.zeroc.Ice.Current current) {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Cancelling coffees");
        coffeeBusyFor = 0;
    }

    @Override
    public boolean isBusy(com.zeroc.Ice.Current current) {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Someone asked if I'm busy");
        return System.currentTimeMillis() - coffeeLastStarted < coffeeBusyFor;
    }

    @Override
    public float timeToFinish(com.zeroc.Ice.Current current) {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("Someone asked how long I'm busy for");
        if(coffeeBusyFor == 0)
            return 0;
        if(System.currentTimeMillis() - coffeeLastStarted > coffeeBusyFor)
            return 0;
        return (coffeeLastStarted + coffeeBusyFor - System.currentTimeMillis()) / 1000f;
    }

    @Override
    public void showOnDisplay(String message, com.zeroc.Ice.Current current) {
        System.out.println("Object: " + current.id.name.toUpperCase() + " " + current.id.category.toUpperCase());
        System.out.println("SHOWING ON DISPLAY: " + message);
    }
}
