
#ifndef HOME_ICE
#define HOME_ICE

module home
{
  // Nie da sie
  
  /*struct s1 
  {
    int val1;
    optional(2) int val2;
  };*/
  
  class c1
  {
    int val1;
    optional(2) int val2;
  }
  
  exception e1
  {
    optional(3) int m;
  }
  
  interface Config
  {
    optional(0) int test1();
    void test2(optional(1) int arg);
    void test3(c1 arg);
    void test4() throws e1;
  };

};

#endif
