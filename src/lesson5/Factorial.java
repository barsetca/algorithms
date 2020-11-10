package lesson5;

public class Factorial {

  public static void main(String[] args) {
    System.out.println(factorial(8L));
    System.out.println();
    System.out.println(tailFactorial(1L, 8L));
  }

  private static long tailFactorial(long acc, long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n <= 1) {
      return acc;
    }
    acc = acc * n;
    return tailFactorial(acc, n - 1);
  }

  private static long factorial(long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n <= 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }

}
