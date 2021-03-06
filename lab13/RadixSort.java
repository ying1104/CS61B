/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        if (asciis.length == 0) {
            return asciis;
        }
        int stringMaxLen = 0;
        String[] asciis2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            asciis2[i] = asciis[i];
            if (asciis[i].length() > stringMaxLen) {
                stringMaxLen = asciis[i].length();

            }
        }

        for (int i = stringMaxLen - 1; i >= 0; i--) {
            sortHelperLSD(asciis2, i);
        }
        return asciis2;

    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[256];
        String[] sorted2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted2[i] = asciis[i];
            if (asciis[i].length() >= index + 1) {
                counts[(int) asciis[i].charAt(index)]++;
            } else {
                counts[0]++;
            }

        }

        int[] starts = new int[256];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {

            starts[i] = pos;
            pos += counts[i];

        }


        for (int i = 0; i < sorted2.length; i += 1) {
            String item = sorted2[i];
            int itemPlace;
            if (item.length() >= index + 1) {
                itemPlace = starts[(int) item.charAt(index)];
                starts[(int) item.charAt(index)]++;
            } else {
                itemPlace = starts[0];
                starts[0]++;
            }
            asciis[itemPlace] = item;
        }

        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] original = {"banana", "dog", "cat", "apple", "app"};
        String[] expected = RadixSort.sort(original);
        for (String i : original) {
            System.out.print(i);
        }
        System.out.println();
        for (String i : expected) {
            System.out.print(i);
        }
    }
}
