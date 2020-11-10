package lesson5;

public class Countdown {

  public static void main(String[] args) {
    countDownLoop(5);
    System.out.println("----------------------------");
    countDownRecursion(5);
  }

  private static void countDownRecursion(int n) {

    if (n < 1) {
      return;
    }
    System.out.println(n);
    countDownRecursion(--n);
  }

  private static void countDownLoop(int n) {
    while (n > 0) {
      System.out.println(n);
      n--;
    }
  }

}
