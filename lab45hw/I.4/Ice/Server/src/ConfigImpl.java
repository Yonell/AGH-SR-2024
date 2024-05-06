import com.zeroc.Ice.Current;
import home.Config;
import home.c1;

import java.util.OptionalInt;
import java.util.Random;

public class ConfigImpl implements Config {
    @Override
    public OptionalInt test1(Current current) {
        Random rng = new Random();
        System.out.println("test1 called");
        if (rng.nextBoolean()) {
            int result = rng.nextInt();
            System.out.println("Returning: " + result);
            return OptionalInt.of(result);
        }
        System.out.println("Returning: empty");
        return OptionalInt.empty();
    }

    @Override
    public void test2(OptionalInt arg, Current current) {
        System.out.println("test2 called");
        if (arg.isPresent()) {
            System.out.println("Received: " + arg.getAsInt());
        } else {
            System.out.println("Received: empty");
        }
    }

    @Override
    public void test3(c1 arg, Current current) {
        System.out.println("test3 called");
        System.out.println("Received: " + arg);
        System.out.println("val1: " + arg.val1);
        System.out.println("hasVal2: " + arg.hasVal2());
        if(arg.hasVal2()){
            System.out.println("val2: " + arg.getVal2());
        }
        System.out.println("Optional val2: " + arg.optionalVal2());
        System.out.println("Optional isPresent: " + arg.optionalVal2().isPresent());
        if(arg.optionalVal2().isPresent()){
            System.out.println("Optional val2: " + arg.optionalVal2().getAsInt());
        }
    }

    @Override
    public void test4(Current current) throws home.e1{
        System.out.println("test4 called");
        Random rng = new Random();
        if(rng.nextBoolean()){
            int result = rng.nextInt();
            System.out.println("Throwing home.e1(" + result + ")");
            throw new home.e1(result);
        }
        System.out.println("Throwing home.e1()");
        throw new home.e1();
    }
}
