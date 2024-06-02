import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.awt.Color;
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

    public T removeFront() {
        if (isEmpty())
            return null;
        T tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }

    public void removeEnd(T element) {
        ListNode<T> node = front;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new ListNode<>(element, null));
    }

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

    public String toJson() {
        ObjectMapper mapper = createObjectMapper();
        try {
            ArrayList<T> arrayList = this.getArray();
            ArrayList<ObjectNode> jsonList = new ArrayList<>();
            for (T item : arrayList) {
                ObjectNode node = mapper.valueToTree(item);
                if (item instanceof GcuLine) {
                    node.put("type", "line");
                } else if (item instanceof GcuOval) {
                    node.put("type", "oval");
                } else if (item instanceof GcuRectangle) {
                    node.put("type", "rectangle");
                }
                jsonList.add(node);
            }
            return mapper.writeValueAsString(jsonList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}
