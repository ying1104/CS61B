package synthesizer;

import java.util.Iterator;

public class GuitarString {
    /** Sampling Rate. */
    private static final int SR = 44100;

    /** energy decay factor. */
    private static final double DECAY = .996;

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int cap = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(cap);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while(!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;

            Iterator<Double> theBuffer = buffer.iterator();
            while (theBuffer.hasNext()) {
                if (theBuffer.next() == r) {
                    continue;
                }
            }
            buffer.enqueue(r);
        }


    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        System.out.println("the item to dequeued is: " + sample());
        double firstItem = buffer.dequeue();
        System.out.println("the items to be averaged are: "
                + Double.toString(firstItem) + " and " + sample());
        double toEnqueue = (firstItem + buffer.peek()) * 0.5 * DECAY;
        System.out.println("the item to enqueued is: " + toEnqueue);
        buffer.enqueue(toEnqueue);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
