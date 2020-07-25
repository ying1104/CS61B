package synthesizer;

import java.util.Iterator;

/** Bounded Queue Interface.
 * @param <T> generic type
 * @author Ying
 */
public interface BoundedQueue<T> extends Iterable<T> {

    /** Iterator method.
     * @return an iterator
     */
    Iterator<T> iterator();

    /** Returns size of the buffer. */
    int capacity();

    /** Returns number of items currently in the buffers. */
    int fillCount();

    /** Adds item x to the end.
     * @param x the item to be enqueued.
     */
    void enqueue(T x);

    /** Deletes and returns item from the front.
     * @return return the first item from the front.
     */
    T dequeue();

    /** Return (but do not delete) item from the front.
     * @return the first item from the front.
     */
    T peek();

    /** is the buffer empty. (fillCount = 0).
     * @return whether it's empty.
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** is the buffer full (fullCount = capacity).
     * @return whether it's full.
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
