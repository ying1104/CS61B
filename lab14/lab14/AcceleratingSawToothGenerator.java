package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {

    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int p, double f) {
        this.period = p;
        state = 0;
        factor = f;
    }

    @Override
    public double next() {
        state = state + 1;
        if (state % period == 0) {
            state = 0;
            period = (int) (period * factor);
            return -1.0;
        }
        return normalize(state) ;
    }

    public double normalize(int state) {
        return -1.0 + ((double) state / (double) period) * 2.0;
    }
}

