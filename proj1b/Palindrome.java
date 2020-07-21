/** the Palindrome class.
 * @author Ying
 */
public class Palindrome {

    /** Returns a deque where the chars appears
     * in the same order as in the String.
     * @param word a string to input.
     * @return a deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    /** Returns whether string is palindrome.
     * @param word the string to input
     * @return a boolean
     */
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        for (int i = 0; i <= word.length() / 2 - 1; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /** adds a new method that overloads isPalindrome.
     * @param word the word to be verified
     * @param cc offByOne class
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {

        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        for (int i = 0; i <= word.length() / 2 - 1; i++) {
            if (!cc.equalChars(word.charAt(i),
                    word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;


    }
}
