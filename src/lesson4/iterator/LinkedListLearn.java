package lesson4.iterator;

public class LinkedListLearn {

  private Link first;

  public LinkedListLearn(){
    first = null;

  }

  public Link getFirst() {
    return first;
  }

  public void setFirst(Link first) {
    this.first = first;
  }

  public LinkIterator getIterator(){
    return new LinkIterator(this);
  }

  public boolean isEmpty(){
    return (first == null);
  }

  public void display(){
    System.out.println("--------------------------");
    Link current = first;
    while(current != null){
      current.display();
      current = current.next;
    }
    System.out.println("--------------------------");
  }
}
