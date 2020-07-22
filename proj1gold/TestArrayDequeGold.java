import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {

    public void testToPrint(StudentArrayDeque<Integer> sad1,
                            ArrayDequeSolution<Integer> happy1,
                            String toPrint) {
        Integer expected = happy1.size();
        Integer actual = sad1.size();
        assertEquals(toPrint + "size()\n", expected, actual);

        for (int i = 0; i < happy1.size(); i++) {
            Integer expected1 = happy1.get(i);
            Integer actual1 = sad1.get(i);
            assertEquals(toPrint + "get()\n", expected1, actual1);
        }


    }

    @Test
    public void testArrayDequeGold() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> happy1 = new ArrayDequeSolution<>();

        String toPrint = "";

        for (int i = 0; i < 500; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                toPrint += "addLast(" + i + ")\n";
                sad1.addLast(i);
                happy1.addLast(i);
                testToPrint(sad1, happy1, toPrint);
            } else if (numberBetweenZeroAndOne < 0.5) {
                toPrint += "addFirst(" + i + ")\n";
                sad1.addFirst(i);
                happy1.addFirst(i);
                testToPrint(sad1, happy1, toPrint);
            } else if (numberBetweenZeroAndOne < 0.75
                    && sad1.size() != 0
                    && happy1.size() != 0) {
                toPrint += "removeFirst()\n";
                Integer expected = happy1.removeFirst();
                Integer actual = sad1.removeFirst();
                assertEquals(toPrint, expected, actual);
                testToPrint(sad1, happy1, toPrint);
            } else if (numberBetweenZeroAndOne < 1
                    && sad1.size() != 0
                    && happy1.size() != 0) {
                toPrint += "removeLast()\n";
                Integer expected = happy1.removeLast();
                Integer actual = sad1.removeLast();
                assertEquals(toPrint, expected, actual);
                testToPrint(sad1, happy1, toPrint);
            }
        }
        sad1.printDeque();
        happy1.printDeque();

    }

    public static void main(String[] args) {
        TestArrayDequeGold a = new TestArrayDequeGold();
        a.testArrayDequeGold();
    }

}
