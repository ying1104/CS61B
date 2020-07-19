/**
 * The program is an implementation of linked list.
 *
 * @author Ying
 * @version 1.0
 * @since 2020-7-18
 * @param <T> generic type
 */

public class ArrayDeque<T> {
    /** the item. */
    private T[] items;
    /** the nextFirst. */
    private int nextFirst;
    /** the nextLast. */
    private int nextLast;
    /** the size. */
    private int size;

    /** Creates an empty array. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /** Changes the size of array.
     * @param cap the size of the new array
     */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(items, nextLast, a, cap - size + nextLast,
                size - nextLast);
        items = a;
    }

    /** Makes the decision if to resize the array. */
    private void resizeOrNot() {
        if (size == items.length) {
            resize(size * 2);
            nextFirst += size;
        } else if (items.length >= 16 && size < 0.25 * items.length) {
            resize(size / 2);
        }
    }

    /** Finds the next index for nextLast and nextFirst. */
    private void nextIndex() {
        if (nextLast == items.length) {
            nextLast = 0;
        }
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
    }

    /** Adds an item of type T to the front of the deque.
     *
     * @param item the item to add
     */
    public void addFirst(T item) {
        resizeOrNot();

        nextIndex();

        items[nextFirst] = item;
        nextFirst--;
        size++;
    }


    /** Adds an item of type T to the back of the deque.
     *
     * @param item the item to add
     */
    public void addLast(T item) {
        resizeOrNot();

        nextIndex();

        items[nextLast] = item;
        nextLast++;
        size++;

    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;

    }

    /** Prints the items in the deque from first
     * to last, separated by a space.
     */
    public void printDeque() {
        int f1 = nextFirst;
        int l1;
        if (nextLast == 0) {
            l1 = items.length - 1;
        } else {
            l1 = nextLast;
        }
        while (f1 != l1 - 1) {
            f1++;
            if (f1 == items.length) {
                f1 = 0;
            }
            System.out.print(items[f1] + " ");
        }


    }

    /** Removes and returns the item at the front of
     *  the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextIndex();

        size--;
        nextFirst++;
        return items[nextFirst];

    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nextIndex();

        size--;
        nextLast--;
        return items[nextLast];

    }

    /** Gets the item at the given index,
     *  where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * @param index the index where to get the item
     */
    public T get(int index) {
        int f1 = nextFirst;
        int i = -1;
        while (i != index) {
            f1++;
            i++;
            if (f1 == items.length) {
                f1 = 0;
            }
            if (f1 == nextLast) {
                return null;
            }
        }
        return items[f1];


    }

}
