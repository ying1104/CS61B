 public class LinkedListDeque<T> {

    private class ItemNode {
        public T item;
        public ItemNode prev;
        public ItemNode next;

        public ItemNode(T i, ItemNode p, ItemNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
     private ItemNode sentinel;
     private int size;

     /** Creates an empty LinkedListDeque with sentinel. */
     public LinkedListDeque() {
         sentinel = new ItemNode(null, null, null);
         sentinel.next = sentinel;
         sentinel.prev = sentinel;
         size = 0;
     }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {

        ItemNode toAddFirst = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next = toAddFirst;
        sentinel.next.next.prev = toAddFirst;


        size = size + 1;

    }

    /** Adds an item of type T to the back of the deque */
    public void addLast(T item) {

        ItemNode toAddLast = new ItemNode(item, sentinel.prev, sentinel);
        sentinel.prev = toAddLast;
        sentinel.prev.prev.next = toAddLast;


        size = size + 1;

    }

    /** Returns true if deque is empty */
    public boolean isEmpty() {
        return size == 0;

    }

    /** Returns the number of items in the deque */
    public int size() {
        return size;

    }

     /** Prints the items in the deque from first to last,
      * separated by a space.
      */
     public void printDeque() {

         ItemNode p = sentinel;
         while (p.next != sentinel) {
             p = p.next;
             System.out.print(p.item + " ");
         }

     }

     /** Removes and returns the item at the front of the queue,
      * if no such item exists, returns null
      */
     public T removeFirst() {
         T result = sentinel.next.item;
         sentinel.next = sentinel.next.next;
         sentinel.next.next.prev = sentinel;
         size = size - 1;
         return result;

     }

     /** Removes and returns the item at the back of the queue,
      * if no such item exists, returns null
      */
     public T removeLast() {
         T result = sentinel.prev.item;
         sentinel.prev = sentinel.prev.prev;
         sentinel.prev.next = sentinel;
         size = size - 1;
         return result;

     }

     /** Gets the item at the given index, where 0 is the front,
      * 1 is the next item, and so forth.
      * If no such item exists, returns null.
      * Must not alter the deque!
      * @param index the index of the item to get
      * @return unchanged deque
      */
     public T get(int index) {
         ItemNode p = sentinel.next;
         for (int i = 0; i < index; i++) {
             p = p.next;
             if (p == sentinel) {
                 return null;
             }
         }
         return p.item;

     }

     /** Gets the item at the given index using
      * recursion. If not exists, return null;
      */
     public T getRecursive(int index) {
         ItemNode p = sentinel.next;
         p = getRecursiveHelper(p, index);
         return p.item;
     }

     /** Helper method of getRecursive. */
     public ItemNode getRecursiveHelper(ItemNode p, int index) {
         if (index == 0 || p == sentinel) {
             return p;
         }
         return getRecursiveHelper(p.next, index - 1);
     }

 }