package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{

    /** Iterator method. */
    Iterator<T> iterator();

    /** Returns size of the buffer. */
    int capacity();

    /** Returns number of items currently in the buffers. */
    int fillCount();

    /** Adds item x to the end. */
    void enqueue(T x);

    /** Deletes and returns item from the front. */
    T dequeue();

    /** Return (but do not delete) item from the front. */
    T peek();

    /** is the buffer empty. (fillCount = 0). */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** is the buffer full (fullCount = capacity). */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
