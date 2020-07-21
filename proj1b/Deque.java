/** the deque interface.
 * @param <T>
 * @author Ying
 */
public interface Deque<T> {
    /** addFirst method.
     * @param item the first item to add.
     */
    void addFirst(T item);

    /** addLast method.
     * @param item the last item to add.
     */
    void addLast(T item);

    /** isEmpty method.
     * @return
     */
    boolean isEmpty();

    /** size method.
     * @return
     */
    int size();

    /** printDeque method. */
    void printDeque();

    /** removeFirst method.
     * @return
     */
    T removeFirst();

    /** removeLast method.
     * @return
     */
    T removeLast();

    /** get method.
     * @param index the index where to get the item.
     * @return
     */
    T get(int index);
}
