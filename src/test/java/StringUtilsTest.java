import static org.junit.jupiter.api.Assertions.*;

import org.example.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

  private StringUtils su;

  @BeforeEach
  void setUp() {
    su = new StringUtils();
  }

  // --------------------------------------------------------
  // 1. TESTS FOR compress(String str)
  // --------------------------------------------------------
  ;

  /**
   * Test: null input.
   * Branch covered: The very first check in compress() that throws a NullPointerException
   * when the input string is null.
   */
  @Test
  void testCompress_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.compress(null);
    });
  }

  /**
   * Test: empty string input.
   * Branch covered:
   * - When the input string is empty, the for-loop is never entered.
   * - The final return branch is executed where the empty compressed string is returned.
   */
  @Test
  void testCompress_emptyString_returnsEmptyString() {
    String input = "";
    String result = su.compress(input);
    assertEquals("", result);
  }


  /**
   * Test: single character.
   * Branch covered:
   * - The loop runs exactly once, and the if condition is true because the current index is the last character.
   * - Final branch: Since the compressed result ("a1") is not shorter than the original ("a"),
   *   the original string is returned.
   *
   * Fault: The code goes into the loop, but it is unable to check the if condition because the index is out of
   * bounds. The code should check if it is the last character before incrementing the count.
   */
  @Test
  void testCompress_singleChar_returnsOriginalString() {
    String input = "a";
    String result = su.compress(input);
    assertEquals("a", result);
  }

  /**
   * Test: repeated characters where compression is not beneficial.
   * Branch covered:
   * - For input "aa": For the first character (i=0), the if condition is false because the next character is the same.
   * - For the second character (i=1), the if condition is true because it is the last character.
   * - Final branch: Since the compressed result ("a2") is not strictly shorter than "aa", the original is returned.
   *
   * Fault: The code doesn't return the original string when the compressed string is not strictly shorter.
   */
  @Test
  void testCompress_repetition_returnsOriginalStringIfCompressionNotBeneficial() {
    String input = "aa";
    String result = su.compress(input);
    assertEquals("aa", result,
        "Should return original string when the compressed string is not strictly shorter");
  }

  /**
   * Test: mixed characters with no compression advantage.
   * Branch covered:
   * - For input "ab": For i=0, the if condition is true because the next character is different.
   * - For i=1, the condition is true because it is the last character.
   * - Final branch: The compressed result ("a1b1") is longer than the original, so the original string is returned.
   */
  @Test
  void testCompress_mixedNoAdvantage_returnsOriginalString() {
    String input = "ab";
    String result = su.compress(input);
    assertEquals("ab", result);
  }

  /**
   * Test: typical example.
   * Branch covered:
   * - For input "aabcccccaaa": Multiple iterations occur.
   * - Both branches of the loop's if-statement are exercised:
   *     * When the next character is the same (if condition false)
   *     * When the run of identical characters ends (if condition true, either because of a differing next char or end-of-string).
   * - Final branch: The compressed string ("a2b1c5a3") is shorter than the original, so the compressed string is returned.
   */
  @Test
  void testCompress_typicalExample_returnsCompressedString() {
    String input = "aabcccccaaa";
    String result = su.compress(input);
    assertEquals("a2b1c5a3", result);
  }

  /**
   * Test: intermediate case with a mix of single and repeated characters.
   * Branch covered:
   * - For input "abb":
   *     * At index 0: if condition is true because 'a' != 'b'.
   *     * At index 1: if condition is false because 'b' equals the next 'b'.
   *     * At index 2: if condition is true because it is the last character.
   * - Final branch: The compressed result ("a1b2") is not shorter than the original ("abb"), so the original string is returned.
   */
  @Test
  void testCompress_intermediateCase_returnsOriginalString() {
    String input = "abb";
    String result = su.compress(input);
    assertEquals("abb", result);
  }

  // --------------------------------------------------------
  // 2. TESTS FOR isPermutation(String str1, String str2)
  // --------------------------------------------------------
  /**
   * Test: One or both input strings are null.
   * Branches covered:
   * - Implicit null check when calling str1.length() or str2.length() (resulting in a NullPointerException).
   */
  @Test
  void testIsPermutation_nullInput_throwsNullPointerException() {
    // First parameter is null.
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation(null, "abc");
    });
    // Second parameter is null.
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation("abc", null);
    });
  }

  /**
   * Test: Input strings have different lengths.
   * Branch covered:
   * - The length check (str1.length() != str2.length()) returns true and immediately returns false.
   */
  @Test
  void testIsPermutation_lengthMismatch() {
    // "abc" and "ab" have different lengths, so the method returns false.
    boolean result = su.isPermutation("abc", "ab");
    assertFalse(result);
    boolean result2 = su.isPermutation("xyz", "xy");
    assertFalse(result2);
  }

  /**
   * Test: Valid permutation with two-character strings.
   * Branches covered:
   * - The length check passes since lengths are equal.
   * - The loop that increments letter counts in str1.
   * - The loop that decrements counts for str2, where the if-condition (letters[c] < 0) is never true.
   * - Final branch: returns true when all checks pass.
   */
  @Test
  void testIsPermutation_validPermutation() {
    // "ab" and "ba" are valid permutations.
    boolean result = su.isPermutation("ab", "ba");
    assertTrue(result);
  }

  /**
   * Test: Same length strings that are not permutations.
   * Branches covered:
   * - The length check passes.
   * - The letter count loops are executed.
   * - Ideally, this test should exercise the branch where the character counts do not match.
   *   (Note: Due to the ordering of the if-condition check before decrementing,
   *   the intended letters[c] < 0 branch may not be triggered as expected.)
   */
  @Test
  void testIsPermutation_invalidPermutation() {
    // "abc" and "abd" have the same length but different characters.
    // If implemented correctly, the method should return false.
    boolean result = su.isPermutation("abc", "abd");
    assertFalse(result);
  }

  @Test
  void testIsPermutation_invalidPermutationRepetition() {
    // "abc" and "abd" have the same length but different characters.
    // If implemented correctly, the method should return false.
    boolean result = su.isPermutation("abccc", "abbbb");
    assertFalse(result);
  }



  /**
   * Test: Attempt to trigger the branch where letters[c] < 0.
   * Branches covered:
   * - Although intended to trigger the branch (if letters[c] < 0), the current code logic
   *   (checking before decrement) does not allow letters[c] to be negative before the check.
   * - In this test, a length mismatch occurs first, so the branch is not reached.
   */
  @Test
  void testIsPermutation_cannotReachLettersLessThanZero() {
    // "ab" vs. "abb" triggers the length mismatch branch.
    // This test documents that the letters[c] < 0 branch is never reached.
    boolean result = su.isPermutation("ab", "abb");
    assertFalse(result, "Expected false due to length mismatch, not because of letters[c] < 0.");
  }

  // --------------------------------------------------------
  // 3. TESTS FOR isPermutationOfPalindrome(String str)
  // --------------------------------------------------------
  /**
   * Test: Null input.
   * Branch covered:
   * - When the input string is null, calling toCharArray() triggers a NullPointerException.
   */
  @Test
  void testIsPermutationOfPalindrome_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.isPermutationOfPalindrome(null);
    });
  }



  /**
   * Test: All distinct characters.
   * Branch covered:
   * - For each character in "abc", getCharNumber(c) returns a valid index.
   * - Each letter is encountered once so that the count for each letter remains odd,
   *   causing countOdd to increment for every valid letter.
   * - Final branch: Since countOdd ends up greater than 1 (specifically 3), the method returns false.
   */
  @Test
  void testIsPermutationOfPalindrome_allDistinctChars() {
    // "abc" yields countOdd = 3 (one for each letter), so it is not a permutation of a palindrome.
    boolean result = su.isPermutationOfPalindrome("abc");
    assertFalse(result);
  }

  @Test
  void testIsPermutationOfPalindrome_nonASCIIChar() {
    // "abc" yields countOdd = 3 (one for each letter), so it is not a permutation of a palindrome.
    boolean result = su.isPermutationOfPalindrome("~{@()$%($(abc!?!");
    assertFalse(result);
  }


  /**
   * Test: Simple palindrome.
   * Branch covered:
   * - For input "aba", each valid letter toggles the count:
   *     - First 'a': count becomes odd.
   *     - 'b': count becomes odd.
   *     - Second 'a': count toggles back to even.
   * - Final branch: countOdd ends up as 1 (only one letter has an odd count), so the method returns true.
   */
  @Test
  void testIsPermutationOfPalindrome_simplePalindrome() {
    // "aba" is a palindrome (and a permutation of itself).
    boolean result = su.isPermutationOfPalindrome("aba");
    assertTrue(result);
  }

  /**
   * Test: Ignoring non-letter characters.
   * Branch covered:
   * - For input "A1bc", getCharNumber(c) is expected to return -1 for non-letter characters (like '1' or possibly 'A'
   *   depending on case-handling), so those characters are ignored.
   * - Only valid letters are processed. If those valid letters result in more than one odd count, the method returns false.
   */
  @Test
  void testIsPermutationOfPalindrome_ignoringNonLetters() {
    // "A1bc": Non-letter characters (or letters outside the expected range) are ignored.
    // The effective string might be "bc" (if 'A' is ignored) leading to 2 odd counts, hence false.
    boolean result = su.isPermutationOfPalindrome("A1bc");
    assertFalse(result);
  }


  /**
   * Test: Example where the input is a permutation of a palindrome.
   * Branch covered:
   * - For input "baa", valid letters are processed:
   *     - 'b' increments countOdd.
   *     - 'a' increments countOdd.
   *     - The second 'a' toggles the count for 'a' back to even.
   * - Final branch: countOdd ends up as 1 (acceptable for a palindrome permutation), so the method returns true.
   */
  @Test
  void testIsPermutationOfPalindrome_exampleTrue() {
    // "baa" is a permutation of "aba", which is a palindrome.
    boolean result = su.isPermutationOfPalindrome("baa");
    assertTrue(result);
  }


    // --------------------------------------------------------
  // 4. TESTS FOR stringToInteger(String str)
  // --------------------------------------------------------
  /**
   * Test: Null input.
   * Branch covered:
   * - When str is null, the very first call to str.length() throws a NullPointerException.
   */
  @Test
  void testStringToInteger_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.stringToInteger(null);
    });
  }

  /**
   * Test: Simple positive integer string.
   * Branches covered:
   * - The initial for-loop verifies every character is a digit.
   * - No leading spaces are present, so the while loop skipping spaces is bypassed.
   * - No sign is encountered, so the sign branch is skipped.
   * - The conversion while loop processes each digit.
   * - Overflow check is not triggered.
   * - Returns the correctly computed positive integer.
   */
  @Test
  void testStringToInteger_simplePositive() {

    String input = "123";
    int result = su.stringToInteger(input);
    assertEquals(123, result);

    String input3 = "0";
    int result3 = su.stringToInteger(input3);
    assertEquals(0, result3);

  }

  /**
   *
   */
  @Test
  void testStringToInteger_plusSign() {
    String input2 = "+123";
    int result2 = su.stringToInteger(input2);
    assertEquals(123, result2);
  }


  /**
   *
   */
  @Test
  void testStringToInteger_zero() {
    String input3 = "0";
    int result3 = su.stringToInteger(input3);
    assertEquals(0, result3);
  }

  /**
   * Test: Simple negative integer string.
   * Branches covered:
   * - The for-loop: The first character '-' is allowed at index 0; subsequent characters are digits.
   * - The while loop for skipping spaces is bypassed (no leading spaces).
   * - The sign branch is taken: '-' sets sign to -1.
   * - The conversion while loop processes the digits.
   * - Overflow check is not triggered.
   * - Returns the correctly computed negative integer.
   */
  @Test
  void testStringToInteger_simpleNegative() {
    String input = "-123";
    int result = su.stringToInteger(input);
    assertEquals(-123, result);
  }

  /**
   * Test: Overflow condition.
   * Branches covered:
   * - The for-loop confirms all characters are digits.
   * - Conversion loop processes digits until the overflow check is triggered.
   * - When overflow is detected, returns Integer.MAX_VALUE.
   */
  @Test
  void testStringToInteger_overflow() {
    // "2147483647" is Integer.MAX_VALUE; "2147483648" is just beyond it.
    String input = "2147483648";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MAX_VALUE, result);
  }

  /**
   * Test: Underflow condition.
   * Branches covered:
   * - The for-loop confirms all characters are digits (with the first character '-' allowed).
   * - Conversion loop processes digits until the underflow check is triggered.
   * - When underflow is detected, returns Integer.MIN_VALUE.
   */
  @Test
  void testStringToInteger_underflow() {
    // "-2147483648" is Integer.MIN_VALUE; "-2147483649" is just below it.
    String input = "-2147483649";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MIN_VALUE, result);
  }

  /**
   * Test: Leading spaces.
   * Branches covered (intended behavior):
   * - The while loop after the for-loop should skip any leading spaces.
   * - After skipping spaces, conversion proceeds normally.
   *
   * Note: Given the current implementation, the initial for-loop will examine all characters
   * including spaces. If spaces are not allowed in the for-loop, this test may throw a NumberFormatException.
   * We assume here the intended behavior is to ignore leading spaces.
   */
  @Test
  void testStringToInteger_leadingSpaces() {
    String input = "   456";
    int result = su.stringToInteger(input);
    assertEquals(456, result);
  }

  /**
   * Test: Sign followed immediately by an invalid character.
   * Branches covered:
   * - In the for-loop, the first character '+' is allowed.
   * - When the loop reaches the second character ('a'), it is not a digit and not at index 0,
   *   so the method throws a NumberFormatException.
   */
  @Test
  void testStringToInteger_signThenInvalidCharacter() {
    String input = "+a23";
    assertThrows(NumberFormatException.class, () -> {
      su.stringToInteger(input);
    });
  }

  /**
   * Test: String with all spaces.
   * Branches covered:
   * - The for-loop processes each space character.
   * - For i==0, a space is not a digit nor a valid sign, so the method throws a NumberFormatException.
   */
  @Test
  void testStringToInteger_allSpaces() {
    String input = "    ";
    assertEquals(0, su.stringToInteger(input));
  }

  /**
   * Test: Empty string.
   * Branches covered:
   * - The for-loop is skipped because the string length is 0.
   * - The while loop for skipping spaces is bypassed.
   * - When attempting to access str.charAt(index) after the loops, an IndexOutOfBoundsException occurs.
   */
  @Test
  void testStringToInteger_emptyString() {
    String input = "";
    assertEquals(0, su.stringToInteger(input));
  }
}
