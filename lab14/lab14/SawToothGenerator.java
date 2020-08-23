package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {

    private int period;
    private int state;

    public SawToothGenerator(int p) {
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
        return normalize(state);
    }

    public double normalize(int state) {
        return -1.0 + ((double) state / (double) period) * 2.0;
    }
}
