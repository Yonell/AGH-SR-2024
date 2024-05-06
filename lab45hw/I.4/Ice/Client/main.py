import Ice, sys

import home

with Ice.initialize(sys.argv) as communicator:
    base = communicator.propertyToProxy("Config.Proxy")
    config = home.ConfigPrx.checkedCast(base)

    while True:
        command = input("# ")
        if command == "test1":
            res = config.test1()
            if res is Ice.Unset:
                print("No response")
            else:
                print("Returned: ", res)
        elif command == "test2.1":
            config.test2(1)
        elif command == "test2.2":
            config.test2(Ice.Unset)
        elif command == "test3.1":
            arg = home.c1()
            arg.val2 = 42
            config.test3(arg)
        elif command == "test3.2":
            arg = home.c1()
            config.test3(arg)
        elif command == "test4":
            try:
                config.test4()
            except home.e1 as e:
                print("Caught exception: ", e)
                if e.m is not Ice.Unset:
                    print("Returned: ", e.m)
                else:
                    print("No response")
        elif command == "exit":
            sys.exit(0)
        else:
            print("Unknown command")
