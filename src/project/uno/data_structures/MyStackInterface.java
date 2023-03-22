package project.uno.data_structures;

public interface MyStackInterface<T> {
    void push(T value);
    T pop();
    T peek();
    T search(int index);
    boolean isEmpty();
    boolean isFull();
    void show();
    int size();
}
