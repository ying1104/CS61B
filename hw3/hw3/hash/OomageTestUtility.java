package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = 0;
        }
        int N = oomages.size();
        double upperLimit = N / 50.0;
        double lowerLimit = N / 2.5;

        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] = buckets[bucketNum] + 1;
        }
        for (int b : buckets) {
            if (b < upperLimit || b > lowerLimit) {
                return false;
            }
        }
        return true;
    }
}
