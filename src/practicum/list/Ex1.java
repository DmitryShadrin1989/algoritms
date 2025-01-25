package practicum.list;

import java.util.Iterator;

public class Ex1 {
    public static void main(String[] args) {
        SingleLinkedList<Contact> contactList = new SingleLinkedList<>();

        contactList.addToTail(new Contact(123, "Васильев Евстахий Борисович", "+129381832"));
        contactList.addToTail(new Contact(151, "Коновалов Степан Петрович", "+234432334"));
        contactList.addToTail(new Contact(332, "Калинин Артём Валериевич", "+2234234423"));
        contactList.addToTail(new Contact(432, "Предыбайло Григорий Анатолиевич", "+2342344234"));
        contactList.addToTail(new Contact(556, "Степанов Мирослав Андреевич", "+6678877777"));

        for(Contact contact: contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for(Contact contact: contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkedList<T> implements Iterable<T> {
        Node<T> head;
        Node<T> tail;

        private static class Node<T> {
            T data;
            Node<T> next;
        }

        public boolean isEmpty() {
            return this.head == null;
        }

        public void addToTail(T data) {
            Node<T> newNode = new Node<>();
            newNode.data = data;
            if (isEmpty()) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                Node<T> current = head.next;
                head.next = null;
                while (current != null) {
                    Node<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }

        public void reverse1() {
            Node<T> prev = null;
            Node<T> current = head;


            while (current != null) {
                Node<T> tmp = current.next;
                current.next = prev;

                prev = current;
                head = current;
                current = tmp;
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }
    }
}


