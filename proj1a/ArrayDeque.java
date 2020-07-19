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
    /** the First. */
    private int first;
    /** the Last. */
    private int last;
    /** the size. */
    private int size;

    /** Creates an empty array. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 5;
        last = 4;
        size = 0;
    }

    /** Changes the size of array.
     * @param cap the size of the new array
     */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        if (cap > size) {
            System.arraycopy(items, 0, a, 0, last + 1);
            System.arraycopy(items, first, a, cap - size + first,
                    size - first);
            first = cap - size + first;
        } else {
            System.arraycopy(items, 0, a, 0, last + 1);
            System.arraycopy(items, first, a,
                    cap - size + first,
                    items.length - first);
        }

        items = a;
    }

    /** Makes the decision if to resize the array. */
    private void resizeOrNot() {
        if (size == items.length) {
            resize(size * 2);
        } else if (items.length >= 16 && size < 0.25 * items.length) {
            resize(size / 2);
        }
    }

    /** Finds the next index for last and first.
     *
     * @param a the current first or last
     * @param lOrF 1 for first, 0 for last
     * @return the next first or last.
     */
    private int nextIndex(int a, int lOrF) {
        /** lorF == 1 means it's finding next first. */
        if (lOrF == 1) {
            if (a == 0) {
                return items.length - 1;
            }
            return a - 1;
        }
        /** loF == 0 means it's finding the next last. */
        if (lOrF == 0) {
            if (a == items.length - 1) {
                return 0;
            }
            return a + 1;
        }
        return -1;
    }

    /** Adds an item of type T to the front of the deque.
     * @param item the item to add
     */
    public void addFirst(T item) {
        resizeOrNot();

        first = nextIndex(first, 1);

        items[first] = item;
        size++;
    }


    /** Adds an item of type T to the back of the deque.
     *
     * @param item the item to add
     */
    public void addLast(T item) {
        resizeOrNot();

        last = nextIndex(last, 0);

        items[last] = item;
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
        int f1 = first;
        int l1 = last;
        while (nextIndex(f1, 1) != l1) {
            System.out.print(items[f1] + " ");
            f1 = nextIndex(f1, 0);
        }


    }

    /** Removes and returns the item at the front of
     *  the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int p = first;
        first = nextIndex(first, 0);

        size--;
        return items[p];

    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int p = last;
        last = nextIndex(last, 1);

        size--;
        return items[p];

    }

    /** Gets the item at the given index,
     *  where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * @param index the index where to get the item
     */
    public T get(int index) {
        int f1 = first;
        int l1 = last;
        if (size == 1) {
            return items[f1];
        }
        int i = 0;
        while (nextIndex(f1, 1) != l1) {
            if (i == index) {
                return items[f1];
            } else {
                i++;
                f1 = nextIndex(f1, 0);
            }
        }
        return null;


    }

}
