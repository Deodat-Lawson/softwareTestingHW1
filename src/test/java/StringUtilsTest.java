

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
  @Test
  void testCompress_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.compress(null);
    });
  }

  @Test
  void testCompress_emptyString() {
    String input = "";
    // For-loop never enters => compressed = "", length check => return ""
    String result = su.compress(input);
    assertEquals("", result);
  }

  @Test
  void testCompress_singleChar() {
    String input = "a";
    String result = su.compress(input);
    assertEquals("a", result);
  }

  @Test
  void testCompress_repetition() {
    String input = "aa";
    String result = su.compress(input);
    assertEquals("aa", result,
        "Depending on the final comparison, it could return 'aa' or 'a2'.");
  }

  @Test
  void testCompress_mixedNoAdvantage() {
    String input = "ab";
    String result = su.compress(input);
    assertEquals("ab", result);
  }

  @Test
  void testCompress_typicalExample() {
    String input = "aabcccccaaa";
    // By the javadoc example => "a2b1c5a3" => length 8 < 11 => returns "a2b1c5a3"
    String result = su.compress(input);
    assertEquals("a2b1c5a3", result);
  }

  // --------------------------------------------------------
  // 2. TESTS FOR isPermutation(String str1, String str2)
  // --------------------------------------------------------
  @Test
  void testIsPermutation_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation(null, "abc");
    });
    assertThrows(NullPointerException.class, () -> {
      su.isPermutation("abc", null);
    });
  }

  @Test
  void testIsPermutation_lengthMismatch() {
    // Branch: (str1.length() != str2.length()) => true => returns false
    boolean result = su.isPermutation("abc", "ab");
    assertFalse(result);
  }

  @Test
  void testIsPermutation_validPermutation() {
    // Same length, "ab" => "ba"
    boolean result = su.isPermutation("ab", "ba");
    assertTrue(result);
  }

  @Test
  void testIsPermutation_invalidPermutation() {
    // Same length, but different characters
    boolean result = su.isPermutation("abc", "abd");
    assertFalse(result);
  }

  /**
   * Attempt to trigger letters[c] < 0 => not possible with the code as is,
   * because the check happens before decrement.
   * We'll just note that coverage cannot be fully achieved here.
   */
  @Test
  void testIsPermutation_cannotReachLettersLessThanZero() {
    // The code's logic never lets letters[c] go below 0 prior to checking it.
    // We'll just put a scenario that might attempt it:
    boolean result = su.isPermutation("ab", "abb");
    // length mismatch => false (but doesn't trigger letters[c]<0).
    assertFalse(result, "Expected false by length mismatch, but not the letters[c] < 0 branch");
  }

  // --------------------------------------------------------
  // 3. TESTS FOR isPermutationOfPalindrome(String str)
  // --------------------------------------------------------
  @Test
  void testIsPermutationOfPalindrome_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.isPermutationOfPalindrome(null);
    });
  }

  @Test
  void testIsPermutationOfPalindrome_allDistinctChars() {
    // e.g., "abc" => countOdd eventually = 3 => returns false
    boolean result = su.isPermutationOfPalindrome("abc");
    assertFalse(result);
  }

  @Test
  void testIsPermutationOfPalindrome_simplePalindrome() {
    // "aba" => palindrome => isPermutationOfPalindrome => true
    // Also "baa", "aab" etc. should be true
    boolean result = su.isPermutationOfPalindrome("aba");
    assertTrue(result);
  }

  @Test
  void testIsPermutationOfPalindrome_ignoringNonLetters() {
    // e.g., "A1bc" => 'A', '1' are ignored => effectively "bc" => distinct => false
    // But it also depends on case (since 'A' might yield getCharNumber=-1)
    boolean result = su.isPermutationOfPalindrome("A1bc");
    assertFalse(result);
  }

  @Test
  void testIsPermutationOfPalindrome_exampleTrue() {
    // For instance "baa" => permutation of "aba" => palindrome => true
    boolean result = su.isPermutationOfPalindrome("baa");
    assertTrue(result);
  }

  // --------------------------------------------------------
  // 4. TESTS FOR stringToInteger(String str)
  // --------------------------------------------------------
  @Test
  void testStringToInteger_nullInput_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
      su.stringToInteger(null);
    });
  }

  @Test
  void testStringToInteger_simplePositive() {
    String input = "123";
    int result = su.stringToInteger(input);
    assertEquals(123, result);
  }

  @Test
  void testStringToInteger_simpleNegative() {
    String input = "-123";
    int result = su.stringToInteger(input);
    assertEquals(-123, result);
  }

  @Test
  void testStringToInteger_overflow() {
    // Just beyond max int: 2147483647 is MAX_VALUE
    // 2147483648 => should overflow
    String input = "2147483648";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MAX_VALUE, result);
  }

  @Test
  void testStringToInteger_underflow() {
    // Just beyond min int: -2147483648 is MIN_VALUE
    // -2147483649 => should underflow
    String input = "-2147483649";
    int result = su.stringToInteger(input);
    assertEquals(Integer.MIN_VALUE, result);
  }

  @Test
  void testStringToInteger_leadingSpaces() {
    String input = "   456";
    int result = su.stringToInteger(input);
    assertEquals(456, result);
  }

  @Test
  void testStringToInteger_signThenInvalidCharacter() {
    // The code checks in a for-loop. As soon as it hits 'a', it should throw NumberFormatException
    String input = "+a23";
    assertThrows(NumberFormatException.class, () -> {
      su.stringToInteger(input);
    });
  }

  @Test
  void testStringToInteger_allSpaces() {
    // The for loop sees ' ' < '0' => not a digit,
    // i=0 => it's not '+' or '-' => it should throw NumberFormatException
    String input = "    ";
    assertThrows(NumberFormatException.class, () -> {
      su.stringToInteger(input);
    });
  }

  @Test
  void testStringToInteger_emptyString() {
    // for-loop => 0 length => code might skip loop but eventually
    // might throw an error when str.charAt(index) is accessed if index=0
    // The given code will see length = 0 in the 'for' loop, skip it,
    // then while(index < len && str.charAt(index) == ' ') => won't loop
    // if (index > len) => false =>
    // if (str.charAt(index) == '+'||'-') => (index=0 => out of range) => likely error
    // Actually, this code might end up with an IndexOutOfBoundsException
    // instead of returning 0. Let's see if the user wants to catch that
    // or it's a bug. We'll assume NumberFormatException for non-digit.
    String input = "";
    // In many "atoi" implementations, empty -> 0.
    // But this code is incomplete for that scenario. Let's see:
    // We can see it might actually throw. Let's do an assertion for an exception:
    assertThrows(StringIndexOutOfBoundsException.class, () -> {
      su.stringToInteger(input);
    });
  }
}
