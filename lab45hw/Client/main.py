import Ice, sys

import home


lamps_objects = [
    "LivingRoomLamp",
    "KitchenLamp",
    "BedroomLamp",
    "LivingRoomAmbientLamp",
    "BedroomAmbientLamp"
]

coffee_machines_objects = [
    "KitchenCoffeeMachine"
]

with Ice.initialize(sys.argv) as communicator:
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
                        lc.mode = home.lightMode.ON
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
                        mode = input("Enter mode: ")
                        lc = lamp.getConfig()
                        lc.mode = home.lightMode.from_str(mode)
                        lamp.setConfig(lc)
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
                    elif command == "exit":
                        break
            elif object_name in coffee_machines_objects:
                base  = communicator.propertyToProxy(object_name+".Proxy")
                coffee_machine = home.CoffeeMachinePrx.checkedCast(base)
                if not coffee_machine:
                    raise RuntimeError("Invalid proxy")
                while True:
                    command = input(object_name + " > ")
                    if command == "makeCoffee":
                        coffee_type = input("Enter coffee type: ")
                        ct = home.coffeeType.from_str(coffee_type)
                        coffee_machine.makeCoffee(coffee_type)
                    elif command == "makeCoffees":
                        coffee_types = ["ESPRESSO", "AMERICANO", "CAPPUCCINO", "LATTE", "ESPRESSO"]
                        coffee_types = [home.coffeeType.from_str(ct) for ct in coffee_types]
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
            else:
                print("Object not found")
        elif command == "exit":
            sys.exit(0)
        else:
            print("Unknown command")
