public class TestArray {

  public static void main(String[] args) {
    testDynamicArray();
  }

  private static void testDynamicArray() {
    Array<Integer> data1 = new ArrayImpl<>(100_000);
    Util.fillArray(data1, 100_000);
    Array<Integer> data2 = Util.copy(data1);
    Array<Integer> data3 = Util.copy(data1);

    //data1.display();
    long startTime1 = System.currentTimeMillis();
    // data1.sortBubble();
    long endTime1 = System.currentTimeMillis();
    //data1.display();
    System.out.println("sortBubble time: " + (endTime1 - startTime1) + "ms"); //51_820 ms

    //data2.display();
    long startTime2 = System.currentTimeMillis();
    data2.sortSelect();
    long endTime2 = System.currentTimeMillis();
    // data2.display();
    System.out.println("sortSelect time: " + (endTime2 - startTime2) + "ms");//10_787 ms

    //data3.display();
    long startTime3 = System.currentTimeMillis();
    data3.sortInsert();
    long endTime3 = System.currentTimeMillis();
    //data3.display();
    System.out.println("sortInsert time: " + (endTime3 - startTime3) + "ms");//5_862 ms

    System.out.println("data1[1] = " + data1.get(1));
    System.out.println("Find 2: " + data1.contains(2));
    System.out.println("Find 222_222: " + data1.contains(222_222));
    System.out.println(data1.max());

  }
}
