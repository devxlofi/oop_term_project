import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private int numberOfNodes = 0; 
    private ListNode<T> front = null;
    
    public boolean isEmpty() {
        return (front == null);
    }

    public void makeEmpty() {
        front = null;
        numberOfNodes = 0;
    }
    
    public int size() {
        return numberOfNodes;
    }
    
    public void addFront(T element) {
        front = new ListNode<>(element, front);
        numberOfNodes++;
    }
    
    public T peek() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }
    
    @SuppressWarnings("unchecked")
    public T removeFront() {
        if (isEmpty()) 
            return null;
        
        T tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    @SuppressWarnings("unchecked")
    public void removeEnd(T element) {
        ListNode<T> node = front;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new ListNode<>((T)element, null));
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> getArray() {
        ArrayList<T> shapeArray = new ArrayList<>();
        ListNode<T> node = front;
        while (node != null) {
            shapeArray.add(node.getData());
            node = node.getNext();
        }
        return shapeArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListNode<T> current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.getData();
                current = current.getNext();
                return value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        ListNode<T> node = front;
        while (node != null) {
            data.append(node.getData().toString()).append(";");
            node = node.getNext();
        }
        return data.toString();
    }
}
