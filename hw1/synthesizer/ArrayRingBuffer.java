package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    public class RingIterator implements Iterator<T> {
        private int ptr;
        private int f;

        public RingIterator() {
            ptr = 0;
            f = first;
        }

        public boolean hasNext() {

            return (ptr != fillCount());
        }

        public T next() {

            ptr++;
            T result = rb[f];
            if (f == capacity - 1) {
                f = 0;
            } else {
                f++;
            }
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;

    }

    /** Implements fillCount from the interface. */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /** Implements capacity from the interface. */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount++;
        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first];
        fillCount--;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first++;
        }
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
        }


}


