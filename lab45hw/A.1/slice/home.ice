
#ifndef HOME_ICE
#define HOME_ICE

module home
{

  enum coffeeType { ESPRESSO, AMERICANO, CAPPUCCINO, LATTE };

  sequence <coffeeType> CoffeeSeq;
  sequence <string> StringSeq;

  exception Busy{};

  interface CoffeeMachine
  {
    void makeCoffee(coffeeType type) throws Busy;
    void makeCoffees(CoffeeSeq types) throws Busy;
    idempotent void cancel();
    idempotent bool isBusy();
    idempotent float timeToFinish(); // in seconds
    idempotent void showOnDisplay(string message);
  };

  enum lightMode { BLINK, FADE, SOLID, OFF, RAINBOW };

  exception UnsupportedMode{};

  struct color
  {
    float red;
    float green;
    float blue;
  };

  struct lightConfig
  {
    lightMode mode;
    float intensity;
    color color;
    float durationUntilOff; // -1 to turn off
  };

  interface LightFixture
  {
    void setConfig( lightConfig config ) throws UnsupportedMode;
    idempotent lightConfig getConfig();

  };
  
  interface Config
  {
    idempotent StringSeq listLamps();
    idempotent StringSeq listCoffeeMachines();
  }

};

#endif
