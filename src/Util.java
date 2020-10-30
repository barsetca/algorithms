public class Util {

  private Util() {
  }

  public static <E extends Comparable<? super E>> Array<E> copy(Array<E> arraySource) {
    Array<E> arrayTarget = new ArrayImpl(arraySource.size());
    for (int i = 0; i < arraySource.size(); i++) {
      arrayTarget.add(arraySource.get(i));
    }
    return arrayTarget;
  }

  public static Array<Integer> fillArray(Array<Integer> arrayEmpty, int size) {
    for (int i = 0; i < size; i++) {
      arrayEmpty.add((int) (Math.random() * arrayEmpty.size()));
    }
    return arrayEmpty;
  }

  public static void bubbleSort(int[] array){
    for (int i = 0; i < array.length-1; i++) {
      for (int j = 0; j < array.length-1-i; j++) {
        if (array[j] > array[j+1]){
          int temp = array[j];
          array[j] = array[j+1];
          array[j+1] = temp;
        }
      }
    }
  }
  public static void selectSort(int[] array){
    for (int i = 0; i < array.length-1; i++) {
      int temp = array[i];
      int index = i;
      for (int j = i+1; j < array.length; j++) {
       if (array[j] < temp){
         index = j;
       }
      }
      if (index != i){
        array[i] = array[index];
        array[index] = temp;
        i--;
      }
    }
  }

  public static void selectSort2(int[] array){
    for (int i = 0; i < array.length-1; i++) {
      int min = array[i];
      int index = i;
      for (int j = i+1; j < array.length; j++) {
        if (array[j] < min){
          min = array[j];
          index = j;
        }
      }
      if (index != i){
        int temp = array[i];
        array[i] = array[index];
        array[index] = temp;
      }
    }
  }


}
