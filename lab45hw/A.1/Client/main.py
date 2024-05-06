import Ice, sys

import home

with Ice.initialize(sys.argv) as communicator:
    base = communicator.propertyToProxy("Config.Proxy")
    config = home.ConfigPrx.checkedCast(base)
    lamps_objects = config.listLamps()
    coffee_machines_objects = config.listCoffeeMachines()

    while True:
        command = input("# ")
        if command == "list":
            print("Lamps:")
            for lamp in lamps_objects:
                print("\t"+lamp)
            print("Coffee Machines:")
            for coffee_machine in coffee_machines_objects:
                print("\t"+coffee_machine)
        elif command == "connect":
            object_name = input("Enter object name: ")
            if object_name in lamps_objects:
                base = communicator.propertyToProxy(object_name+".Proxy")
                lamp = home.LightFixturePrx.checkedCast(base)
                if not lamp:
                    raise RuntimeError("Invalid proxy")
                while True:
                    command = input(object_name + " > ")
                    if command == "on":
                        lc = home.lightConfig()
                        lc.mode = home.lightMode.SOLID
                        lc.intensity = 100
                        lc.durationUntilOff = -1
                        lc.color.red = 255
                        lc.color.green = 255
                        lc.color.blue = 255
                        lamp.setConfig(lc)
                    elif command == "off":
                        lc = home.lightConfig()
                        lc.mode = home.lightMode.OFF
                        lamp.setConfig(lc)
                    elif command == "setMode":
                        mode = input("Enter mode int: ")
                        lc = lamp.getConfig()
                        lc.mode = None
                        while lc.mode is None:
                            lc.mode = home.lightMode.valueOf(int(mode))
                            if lc.mode is None:
                                print("Invalid mode")
                                mode = input("Enter mode int: ")
                        try:
                            lamp.setConfig(lc)
                        except home.UnsupportedMode as e:
                            print("Error encountered:")
                            print(e)
                    elif command == "setIntensity":
                        intensity = int(input("Enter intensity: "))
                        lc = lamp.getConfig()
                        lc.intensity = intensity
                        lamp.setConfig(lc)
                    elif command == "setColor":
                        red = int(input("Enter red: "))
                        green = int(input("Enter green: "))
                        blue = int(input("Enter blue: "))
                        lc = lamp.getConfig()
                        lc.color.red = red
                        lc.color.green = green
                        lc.color.blue = blue
                        lamp.setConfig(lc)
                    elif command == "getConfig":
                        lc = lamp.getConfig()
                        print("Mode: " + str(lc.mode))
                        print("Intensity: " + str(lc.intensity))
                        print("Duration until off: " + str(lc.durationUntilOff))
                        print("Color: " + str(lc.color.red) + " " + str(lc.color.green) + " " + str(lc.color.blue))
                    elif command == "exit":
                        break
                    else:
                        print("Unknown command")
            elif object_name in coffee_machines_objects:
                base  = communicator.propertyToProxy(object_name+".Proxy")
                coffee_machine = home.CoffeeMachinePrx.checkedCast(base)
                if not coffee_machine:
                    raise RuntimeError("Invalid proxy")
                while True:
                    command = input(object_name + " > ")
                    try:
                        if command == "makeCoffee":
                            coffee_type = input("Enter coffee type int: ")
                            ct = None
                            while ct is None:
                                ct = home.coffeeType.valueOf(int(coffee_type))
                                if ct is None:
                                    print("Invalid coffee type")
                                    coffee_type = input("Enter coffee type int: ")
                            coffee_machine.makeCoffee(ct)
                        elif command == "makeCoffees":
                            coffee_types = [0, 3, 2, 1, 2]
                            coffee_types = [home.coffeeType.valueOf(ct) for ct in coffee_types]
                            coffee_machine.makeCoffees(coffee_types)
                        elif command == "cancel":
                            coffee_machine.cancel()
                        elif command == "isBusy":
                            print(coffee_machine.isBusy())
                        elif command == "timeLeft":
                            print(coffee_machine.timeToFinish())
                        elif command == "showOnDisplay":
                            message = input("Enter message: ")
                            coffee_machine.showOnDisplay(message)
                        elif command == "exit":
                            break
                        else:
                            print("Unknown command")
                    except home.Busy as e:
                        print("Error encountered:")
                        print(e)
            else:
                print("Object not found")
        elif command == "exit":
            sys.exit(0)
        else:
            print("Unknown command")
