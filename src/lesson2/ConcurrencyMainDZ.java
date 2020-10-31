package lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ConcurrencyMainDZ {

  private static final int ARRAY_CAPACITY = 10_000;
  private static final int MAX_VALUE = 10_000;

  public static void main(String[] args) throws InterruptedException {
    Supplier<Array<Integer>> constructor = ArrayImpl::new;
//    Supplier<Array<Integer>> constructor = SortedArrayImpl::new;

    Array<Integer> arr1 = createArray(constructor);
    Array<Integer> arr2 = createArray(constructor);
    Array<Integer> arr3 = createArray(constructor);

    randomInitialize(arr1, arr2, arr3);

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    List<Runnable> tasks = List.of(
        measureTime(arr1::sortBubble, "sortBubble"),
        measureTime(arr2::sortSelect, "sortSelect"),
        measureTime(arr3::sortInsert, "sortInsert")
    );

    tasks.forEach(executorService::execute);

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);

    System.out.println("sortBubble: " + Arrays.toString(arr1.toArray()));
    System.out.println("sortSelect: " + Arrays.toString(arr2.toArray()));
    System.out.println("sortInsert: " + Arrays.toString(arr3.toArray()));
  }

  private static Array<Integer> createArray(Supplier<Array<Integer>> factory) {
    return factory.get();
  }

  private static void randomInitialize(Array<Integer>... arrays) {
    Random random = new Random();
    for (int i = 0; i < ARRAY_CAPACITY; i++) {
      int value = random.nextInt(MAX_VALUE);
      for (Array<Integer> array : arrays) {
        array.add(value);
      }
    }
  }

  private static Runnable measureTime(Runnable action, String actionName) {
    return () -> {
      long startTime = System.nanoTime();
      action.run();
      long finishTime = System.nanoTime();
      long duration = finishTime - startTime;

      System.out.printf("%s duration time: %d millis.%n", actionName,
          TimeUnit.NANOSECONDS.toMillis(duration));
    };
  }
}
