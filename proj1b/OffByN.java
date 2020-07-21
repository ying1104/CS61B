/** The OffByN class.
 * @author Ying
 */
public class OffByN implements CharacterComparator {
    /** Determines off by how much. */
    private int offBy;

    /** constructor.
     * @param N determines off by N.
     */
    public OffByN(int N) {
        offBy = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int result = x - y;
        if (result == offBy || result == -offBy) {
            return true;
        }
        return false;
    }
}
