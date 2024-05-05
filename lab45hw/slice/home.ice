
#ifndef HOME_ICE
#define HOME_ICE

module home
{

  enum cofeeType { ESPRESSO, AMERICANO, CAPPUCCINO, LATTE };

  sequence <cofeeType> CofeeSeq;

  exception Busy{};

  interface CoffeeMachine
  {
    void makeCoffee(cofeeType type) throws Busy;
    void makeCoffees(CofeeSeq types) throws Busy;
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

};

#endif
