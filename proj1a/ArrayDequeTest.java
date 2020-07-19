
/** Performs some basic Array tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual
                    + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual
                    + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking
     * isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines "
                + "below (and delete this print statement).");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");


        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);

    }

    /** Tests get method */
    public static void getTest() {

        System.out.println("Running get test.");



        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        boolean passed = checkEmpty(true, ad1.isEmpty());


        ad1.addLast(10);
        System.out.println("the 0th item is: " + ad1.get(0));
        ad1.addFirst(10);
        ad1.addFirst(10);
        ad1.addFirst(10);
        ad1.addFirst(10);
        ad1.addFirst(10);
        ad1.addLast(20);
        ad1.addLast(20);
        ad1.addLast(20);
        ad1.addFirst(10);
        ad1.addLast(20);
        System.out.println("The first item removed is: " + ad1.removeFirst());
        System.out.println("The last item removed is: " + ad1.removeLast());

        ad2.addLast(1);
        ad2.addLast(2);

        ad2.addLast(5);
        System.out.println("size 1 is: " + ad2.size());
        ad2.addLast(6);
        ad2.addLast(7);
        ad2.addLast(8);
        ad2.addLast(9);
        ad2.addFirst(10);

        System.out.println("size 2 is: " + ad2.size());
        System.out.println("The first item removed is: " + ad2.removeFirst());
        System.out.println("The last item removed is: " + ad2.removeLast());
        ad2.printDeque();
        System.out.println("The 2nd item is: " + ad2.get(2));
        System.out.println("The 11th item is: " + ad2.get(11));

        passed = checkEmpty(false, ad1.isEmpty()) && passed;
        ad1.printDeque();
        System.out.println("This is the 2nd item: " + ad1.get(2));

        ad1.removeFirst();
        ad1.printDeque();
        System.out.println("This is the 2nd item: " + ad1.get(2));
        System.out.println("This is the 5yh item: " + ad1.get(5));

        passed = checkEmpty(false, ad1.isEmpty()) && passed;


        printTestStatus(passed);

    }

    /** d011) AD-basic: get. (0.0/1.176)。*/
    public static void get1() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        System.out.println("the 3rd item is: " + ad1.get(3));
        ad1.addLast(8);
        ad1.addFirst(9);
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        System.out.println("the 0th item is: " + ad1.get(0));
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");

        get1();
    }
}
