
#ifndef CALC_ICE
#define CALC_ICE

module Demo
{
  sequence<long> LongSeq;

  enum operation { MIN, MAX, AVG };
  
  exception NoInput {};
  exception EmptySequence {};

  struct A
  {
    short a;
    long b;
    float c;
    string d;
  };

  interface Calc
  {
    long add(int a, int b);
    long subtract(int a, int b);
    void op(A a1, short b1);
    double avg(LongSeq a) throws EmptySequence;
  };

};

#endif
