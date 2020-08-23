package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int p) {
        this.period = p;
        state = 0;
    }

    @Override
    public double next() {
        state = state + 1;
        if (state % period == 0) {
            state = 0;

            return -1.0;
        }
        int weirdState = state & (state >>> 3) % period;
        return normalize(weirdState);
    }

    public double normalize(int state) {
        return -1.0 + ((double) state / (double) period) * 2.0;
    }
}
