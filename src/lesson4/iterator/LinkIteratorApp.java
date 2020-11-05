package lesson4.iterator;

public class LinkIteratorApp {

  public static void main(String[] args) {
    LinkedListLearn list = new LinkedListLearn();

    LinkIterator itr = new LinkIterator(list);

    itr.insertAfter("Artem", 20);
    itr.insertBefore("Sergey", 10);
    itr.insertBefore("Sergey", 30);
    itr.insertAfter("Artem", 40);
    itr.insertBefore("Artem", 50);

    list.display();

    itr.deleteCurrent();// delete 50

    list.display();

    System.out.println(itr.atEnd()); //false

    while (!itr.atEnd()) {
      itr.nextLink();
    }

    System.out.println(itr.atEnd()); //true

    System.out.println(itr.getCurrent());//last

    itr.reset();

    System.out.println(itr.getCurrent());//first

  }

}
