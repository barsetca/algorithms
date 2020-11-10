package lesson5.homework;

public class Exponentiation {

  public static void main(String[] args) {
    System.out.println(exponent(-0.15 , -4));
    System.out.println(exponentRecursion(-0.15 , -4));
  }

  private static double exponent(double base, int exponent) {
    return Math.pow(base, exponent);
  }

  private static double exponentRecursion(double base, int exponent) {
    if (base == 0){
      return 0;
    }
    if (exponent == 0){
      return 1;
    }
    if(exponent < 0){
      base = 1/base;
      exponent = -exponent;
    }
    return base * exponentRecursion(base, exponent-1);
  }


}
