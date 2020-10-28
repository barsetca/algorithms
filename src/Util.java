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
}
