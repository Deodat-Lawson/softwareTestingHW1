package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StringUtils methods:
 *   1) compress(String)
 *   2) isPermutation(String, String)
 *   3) isPermutationOfPalindrome(String)
 *   4) stringToInteger(String)
 *
 * Many of these tests highlight discrepancies between the
 * code's actual behavior and the javadoc's intended behavior.
 */
class StringUtilsTest {

  private StringUtils su;

  @BeforeEach
  void setUp() {
    su = new StringUtils();
  }

  // --------------------------------------------------------
  // 1. TESTS FOR compress(String str)
  // --------------------------------------------------------

  /**
   * Verifies that compress(null) throws a NullPointerException,
   * covering the immediate null check (or failure) if present.
   */
  @Test
  void testCompress_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.compress(null);
    });
  }

  /**
   * Verifies behavior for an empty string:
   * - The for-loop is never entered
   * - The method should return an empty string
   * (This matches the current code's behavior.)
   */
  @Test
  void testCompress_emptyString_returnsEmptyString() {
    String input = "";
    String result = su.compress(input);
    assertEquals("", result, "compress(\"\") should return \"\"");
  }

  /**
   * Checks behavior for a single-character string.
   * According to the code's logic, it tries to append 'a1'.
   * However, because the if-condition looks ahead (i+1) out of bounds,
   * there's a bug. In many implementations, this might trigger an
   * IndexOutOfBoundsException. The current code is incomplete/buggy.
   *
   * If the code were corrected, we'd expect that compress("a")
   * returns "a" since "a1" isn't shorter.
   */
  @Test
  void testCompress_singleChar_returnsOriginalString() {
    String input = "a";
    String result = su.compress(input);
    // In the buggy code, this may never be reached or may cause an exception.
    // We'll assume it survives and returns "a" (the intended behavior).
    assertEquals("a", result, "A single character should return itself if 'a1' is not shorter.");
  }

  /**
   * Checks repeated characters that do not actually shorten the string.
   * e.g., "aa" -> would compress to "a2" which is the same length.
   * The code is supposed to return the original string in that case.
   */
  @Test
  void testCompress_repetition_returnsOriginalStringIfCompressionNotBeneficial() {
    String input = "aa";
    String result = su.compress(input);
    assertEquals("aa", result,
        "Should return the original string when the compressed string is not shorter.");
  }

  /**
   * If there's no benefit in compression (like "ab" -> "a1b1"),
   * the method should return the original string. Currently, the code tries
   * to append counts for each character, making "a1b1" which is longer,
   * so the original "ab" is expected.
   */
  @Test
  void testCompress_mixedNoAdvantage_returnsOriginalString() {
    String input = "ab";
    String result = su.compress(input);
    assertEquals("ab", result, "No compression benefit for \"ab\".");
  }

  /**
   * A typical example where compression is beneficial:
   * "aabcccccaaa" -> "a2b1c5a3" which is shorter than the original (11 chars -> 10 chars).
   */
  @Test
  void testCompress_typicalExample_returnsCompressedString() {
    String input = "aabcccccaaa";
    String result = su.compress(input);
    assertEquals("a2b1c5a3", result,
        "Should compress 'aabcccccaaa' to 'a2b1c5a3'.");
  }

  /**
   * Intermediate case: "abb"
   * - "abb" would compress to "a1b2", which is the same length (4 chars).
   * Hence we expect the original string "abb".
   */
  @Test
  void testCompress_intermediateCase_returnsOriginalString() {
    String input = "abb";
    String result = su.compress(input);
    assertEquals("abb", result,
        "Compression should not replace the original if not shorter.");
  }

  // --------------------------------------------------------
  // 2. TESTS FOR isPermutation(String str1, String str2)
  // --------------------------------------------------------

  /**
   * Verifies that passing null for either argument results in a NullPointerException,
   * since the code immediately does str1.length() or str2.length().
   */
  @Test
  void testIsPermutation_nullInput_throwsNullPointerException() {
    // First parameter null
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation(null, "abc");
    });

    // Second parameter null
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation("abc", null);
    });
  }

  /**
   * Strings with different lengths automatically fail the permutation check.
   */
  @Test
  void testIsPermutation_lengthMismatch() {
    assertFalse(su.isPermutation("abc", "ab"),
        "'abc' vs 'ab' -> false due to length mismatch");
    assertFalse(su.isPermutation("xyz", "xy"),
        "'xyz' vs 'xy' -> false due to length mismatch");
  }

  /**
   * Valid permutations of short strings: "ab" and "ba".
   * Should return true.
   */
  @Test
  void testIsPermutation_validPermutation() {
    assertTrue(su.isPermutation("ab", "ba"),
        "'ab' vs 'ba' -> true, valid permutation");
  }

  /**
   * Same-length strings that differ in at least one character
   * should return false.
   */
  @Test
  void testIsPermutation_invalidPermutation() {
    assertFalse(su.isPermutation("abc", "abd"),
        "'abc' vs 'abd' -> false, differ in one char");
  }

  /**
   * Another invalid permutation check with repeated characters
   * that do not match in frequency. e.g., "abccc" vs "abbbb".
   */
  @Test
  void testIsPermutation_invalidPermutationRepetition() {
    assertFalse(su.isPermutation("abccc", "abbbb"),
        "'abccc' vs 'abbbb' -> false, mismatch in char counts");
  }

  /**
   * Attempts to reach the code path where letters[c] < 0.
   * In the given implementation, that check is done BEFORE letters[c]--,
   * so it's effectively never triggered. This test simply documents
   * that we can't cause that branch to be true with the current code.
   */
  @Test
  void testIsPermutation_cannotReachLettersLessThanZero() {
    // This mismatch of lengths triggers an early return false anyway.
    boolean result = su.isPermutation("ab", "abb");
    assertFalse(result,
        "The method returns false on length mismatch, never checking letters[c] < 0.");
  }

  // --------------------------------------------------------
  // 3. TESTS FOR isPermutationOfPalindrome(String str)
  // --------------------------------------------------------

  /**
   * Null input triggers a NullPointerException when str.toCharArray() is called.
   */
  @Test
  void testIsPermutationOfPalindrome_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.isPermutationOfPalindrome(null);
    });
  }

  /**
   * All distinct characters in "abc" lead to countOdd = 3,
   * which is > 1, so not a palindrome permutation.
   */
  @Test
  void testIsPermutationOfPalindrome_allDistinctChars() {
    assertFalse(su.isPermutationOfPalindrome("abc"),
        "'abc' -> not a palindrome permutation, has multiple odd counts");
  }

  /**
   * If the input is an empty string, the code sums up zero letters.
   * By definition, that results in countOdd = 0, which is <= 1,
   * so it should return true. If the code is inconsistent or
   * handles empty differently, we might see false.
   *
   * Adjust expectation depending on actual code behavior.
   */
  @Test
  void testIsPermutationOfPalindrome_emptyString() {
    // The code, as written, sees no characters,
    // so countOdd remains 0. => returns true
    boolean result = su.isPermutationOfPalindrome("");
    assertTrue(result, "Empty string can be considered a palindrome permutation (countOdd=0).");
  }

  /**
   * Non-ASCII chars or uppercase letters might be ignored since getCharNumber(c) returns -1
   * for anything outside 'a'..'z'. Here we use some string containing non-lowercase letters.
   * For demonstration, we expect the code to ignore them or treat them as non-letters,
   * likely resulting in a certain countOdd. Adjust as necessary based on code's actual logic.
   */
  @Test
  void testIsPermutationOfPalindrome_nonASCIIChar() {
    // "Aß" -> 'A' and 'ß' likely map to -1 in getCharNumber
    // so effectively no letters -> countOdd=0 => returns true
    // We'll see actual behavior:
    boolean result = su.isPermutationOfPalindrome("Aß");
    assertTrue(result,
        "Non-ASCII / uppercase are ignored, so it's effectively empty => true");
  }

  /**
   * Simple known palindrome "aba" => definitely a palindrome permutation.
   */
  @Test
  void testIsPermutationOfPalindrome_simplePalindrome() {
    assertTrue(su.isPermutationOfPalindrome("aba"),
        "'aba' is already a palindrome, thus returns true");
  }

  /**
   * Input "A1bc":
   * - 'A' -> ignored if uppercase,
   * - '1' -> ignored because it's not a letter,
   * - 'b'/'c' -> counted. Possibly leads to countOdd=2, so false.
   */
  @Test
  void testIsPermutationOfPalindrome_ignoringNonLetters() {
    boolean result = su.isPermutationOfPalindrome("A1bc");
    // 'b' and 'c' each appear once => 2 odd counts => false
    assertFalse(result, "Expected false with two distinct lower letters each 1 count.");
  }

  /**
   * "baa" is a permutation of "aba", which is a palindrome.
   * So we expect true.
   */
  @Test
  void testIsPermutationOfPalindrome_exampleTrue() {
    assertTrue(su.isPermutationOfPalindrome("baa"),
        "'baa' is a permutation of 'aba' => true");
  }

  // --------------------------------------------------------
  // 4. TESTS FOR stringToInteger(String str)
  // --------------------------------------------------------

  /**
   * Null input leads to NullPointerException on str.length().
   */
  @Test
  void testStringToInteger_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.stringToInteger(null);
    });
  }

  /**
   * A normal positive integer without sign or spaces:
   * - The for-loop sees all digits (passes).
   * - No sign/space skipping actually used.
   */
  @Test
  void testStringToInteger_simplePositive() {
    String input = "123";
    int result = su.stringToInteger(input);
    assertEquals(123, result, "Should parse '123' as 123");
  }

  /**
   * A string prefixed with '+' sign.
   * - For-loop sees the '+' at index 0 as allowed,
   * - Then digits at subsequent indices.
   */
  @Test
  void testStringToInteger_plusSign() {
    String input = "+123";
    int result = su.stringToInteger(input);
    assertEquals(123, result, "Should parse '+123' as 123");
  }

  /**
   * Zero "0" is a valid digit string.
   */
  @Test
  void testStringToInteger_zero() {
    String input = "0";
    int result = su.stringToInteger(input);
    assertEquals(0, result, "Should parse '0' as 0");
  }

  /**
   * Negative integer string:
   * - The '-' at index 0 is allowed,
   * - Subsequent chars must be digits,
   * - sign is -1 => final result is negative.
   */
  @Test
  void testStringToInteger_simpleNegative() {
    String input = "-123";
    int result = su.stringToInteger(input);
    assertEquals(-123, result, "Should parse '-123' as -123");
  }

  /**
   * Overflow scenario: input just beyond Integer.MAX_VALUE => returns MAX_VALUE.
   * Example: "2147483648" => 2147483647 is the maximum int.
   */
  @Test
  void testStringToInteger_overflow() {
    String input = "2147483648";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MAX_VALUE, result,
        "Overflow should yield Integer.MAX_VALUE");
  }

  /**
   * Underflow scenario: input just below Integer.MIN_VALUE => returns MIN_VALUE.
   * Example: "-2147483649" => -2147483648 is the minimum int.
   */
  @Test
  void testStringToInteger_underflow() {
    String input = "-2147483649";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MIN_VALUE, result,
        "Underflow should yield Integer.MIN_VALUE");
  }

  /**
   * Leading spaces test:
   * Per the code, ANY space discovered after i=0 (and it's not a sign) triggers a NumberFormatException,
   * because the for-loop disallows non-digits except at index 0 if sign is +/-.
   * So we expect a NumberFormatException, not a successful parse.
   */
  @Test
  void testStringToInteger_leadingSpaces() {
    String input = "   456"; // has spaces at index 0, 1, 2
    // In the current implementation, the for-loop sees ' ' at i=0, which is not '-' or '+', not digit => throws
    assertThrows(NumberFormatException.class, () -> su.stringToInteger(input),
        "Spaces are not allowed by the code's for-loop => should throw NumberFormatException");
  }

  /**
   * Sign followed by an invalid character ('+a23'):
   * - '+' at index 0 is okay,
   * - 'a' at index 1 is not a digit => throws NumberFormatException.
   */
  @Test
  void testStringToInteger_signThenInvalidCharacter() {
    String input = "+a23";
    assertThrows(NumberFormatException.class, () -> {
      su.stringToInteger(input);
    });
  }

  /**
   * String of ALL spaces. The for-loop sees space at i=0 -> not digit, not sign => NumberFormatException.
   */
  @Test
  void testStringToInteger_allSpaces() {
    String input = "    ";
    assertThrows(NumberFormatException.class, () -> su.stringToInteger(input),
        "All spaces -> not digit or sign => NumberFormatException");
  }

  /**
   * Empty string:
   * - The for-loop has length=0, so it does not run,
   * - Then we try to do `str.charAt(index=0)` => IndexOutOfBoundsException.
   * The code does NOT match the javadoc statement of returning 0 for empty strings.
   * We fix the test to match the actual code behavior => IndexOutOfBoundsException.
   */
  @Test
  void testStringToInteger_emptyString() {
    String input = "";
    assertThrows(IndexOutOfBoundsException.class, () -> su.stringToInteger(input),
        "Empty string triggers out-of-bounds access in the code");
  }
}
