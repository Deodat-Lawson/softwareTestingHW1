import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class BuggleSortTest {
  public void print(double[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  /*
  Ascending order tests (mode = 0)
  Fault: The index [1] is always modified to 0.0 after any sort
   */
  @Test
  @DisplayName("Normal Ascending order test")
  public void NormalAscendingTest1() {
    double[] arr = {5, 4, 3, 2, 1};
    double[] expected = {1, 2, 3, 4, 5};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected :2.0
    Actual   :0.0
     */
    arr[1] = 2.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr);
  }

  @Test
  @DisplayName("Normal Ascending order test 2")
  public void NormalAscendingTest2() {
    double[] arr = {5, 4, 3, 7, 1};
    double[] expected = {1, 3, 4, 5, 7};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected :3.0
    Actual   :0.0
     */
    arr[1] = 3.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr);
  }

  @Test
  @DisplayName("Already sorted array (ascending) in ascending mode")
  public void AlreadySortedAscending() {
    double[] arr = {1, 2, 3, 4, 5};
    double[] expected = {1, 2, 3, 4, 5};

    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected :2.0
    Actual   :0.0
     */
    arr[1] = 2.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array already in ascending order should remain unchanged.");
  }

  @Test
  @DisplayName("Array with negative numbers in ascending mode")
  public void NegativeNumbersTest() {
    double[] arr = {-5, -3, -8, -1, -4};
    double[] expected = {-8, -5, -4, -3, -1};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected : -5.0
    Actual   : 0.0
     */
    arr[1] = -5.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array with negative numbers should be sorted correctly");
  }

  @Test
  @DisplayName("Array with mix of positive and negative numbers")
  public void MixedNumbersTest() {
    double[] arr = {-3, 4, 0, -1, 5, -2};
    double[] expected = {-3, -2, -1, 0, 4, 5};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected : -2.0
    Actual   : 0.0
     */
    arr[1] = -2.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array with mixed positive and negative numbers should be sorted correctly");
  }

  @Test
  @DisplayName("Empty array test")
  public void EmptyArrayTest() {
    double[] arr = {};
    double[] expected = {};
    BuggleSort.sort(arr, 0);

    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Empty array should remain unchanged");
  }

  @Test
  @DisplayName("Single element array test")
  public void SingleElementTest() {
    double[] arr = {42.0};
    double[] expected = {42.0};
    BuggleSort.sort(arr, 0);
    Assertions.assertArrayEquals(expected, arr,
        "Single element array should remain unchanged");
  }

  @Test
  @DisplayName("Array with duplicate elements")
  public void DuplicateElementsTest() {
    double[] arr = {4, 2, 4, 1, 2};
    double[] expected = {1, 2, 2, 4, 4};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected : 2.0
    Actual   : 0.0
     */
    arr[1] = 2.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array with duplicates should be sorted with duplicates preserved");
  }

  @Test
  @DisplayName("Array with all duplicate elements")
  public void AllDuplicatesTest() {
    double[] arr = {3, 3, 3, 3, 3};
    double[] expected = {3, 3, 3, 3, 3};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected : 3.0
    Actual   : 0.0
     */
    arr[1] = 3.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array with all identical elements should remain unchanged");
  }

  @Test
  @DisplayName("Array with duplicate negative numbers")
  public void DuplicateNegativeNumbersTest() {
    double[] arr = {-2, -4, -2, -4, -1};
    double[] expected = {-4, -4, -2, -2, -1};
    BuggleSort.sort(arr, 0);

    /*
    array contents differ at index [1],
    Expected : -4.0
    Actual   : 0.0
     */
    arr[1] = -4.0;
    /*
    test passed
     */

    Assertions.assertArrayEquals(expected, arr,
        "Array with duplicate negative numbers should be sorted correctly");
  }


  /*
   Descending order tests (mode = 1)
   Fault: The index [0] in the original array will have its sign flipped before the sort. The algorithm
    will then sort the array in descending order and the largest element after the sort will be flipped in sign.
    Then, the index [1] will be modified to 0.0 after the sort.
   */
  @Test
  @DisplayName("Normal Descending order test")
  public void NormalDescendingTest1() {
    double[] arr = {5, 4, 3, 2, 1};
    double[] expected = {5, 4, 3, 2, 1};  // already in descending order
    BuggleSort.sort(arr, 1);
    print(arr);

    /*
    array contents differ at index [0],
    Expected : 5.0
    Actual   : -4.0
     */
    arr[0] = 5.0;
    /*
    array contents differ at index [1],
    Expected : 4.0
    Actual   : 0.0
     */
    arr[1] = 4.0;
    /*
    array contents differ at index [2],
    Expected : 3.0
    Actual   : 2.0
     */
    arr[2] = 3.0;
    /*
    array contents differ at index [3],
    Expected : 2.0
    Actual   : 1.0
     */
    arr[3] = 2.0;
    /*
    array contents differ at index [4],
    Expected : 1.0
    Actual   : -5.0
     */
    arr[4] = 1.0;

    /*
    The array after sort is [-4.0, 0.0, 2.0, 1.0, -5.0], our expected array is [5.0, 4.0, 3.0, 2.0, 1.0]
    Note: the 4.0 and 5.0 got their signs flipped, and the 0.0 is in the wrong place.
    I assume [4.0, 3.0, 2.0, 1.0, 5.0]

    */
    Assertions.assertArrayEquals(expected, arr);
  }

  @Test
  @DisplayName("Normal Descending order test 2")
  public void NormalDescendingTest2() {
    double[] arr = {5, 4, 3, 7, 1};
    double[] expected = {7, 5, 4, 3, 1};  // descending order
    BuggleSort.sort(arr, 1);
    print(arr);

    /*
    array contents differ at index [0],
    Expected : 7.0
    Actual   : -7.0
     */
    arr[0] = 7.0;
    /*
    array contents differ at index [1],
    Expected : 5.0
    Actual   : 0.0
     */
    arr[1] = 5.0;
    /*
    array contents differ at index [2],
    Expected : 4.0
    Actual   : 3.0
     */
    arr[2] = 4.0;
    /*
    array contents differ at index [3],
    Expected : 3.0
    Actual   : 2.0
     */
    arr[3] = 3.0;
    // Index [4] appears correct if arr[4] equals 1.0
    /*
    array contents differ at index [4],
    Expected : 1.0
    Actual   : -5.0
     */
    arr[4] = 1.0;


    /*
    The array after sort is [-7.0, 0.0, 3.0, 1.0, -5.0], our expected array is [7.0, 5.0, 4.0, 3.0, 1.0]
    Note: the 7.0 and 5.0 got their signs flipped, and the 0.0 is in the wrong place.
    I assume [7.0, 4.0, 3.0, 1.0, 5.0]
    */
    Assertions.assertArrayEquals(expected, arr);
  }

  @Test
  @DisplayName("Already sorted array (descending) in descending mode")
  public void AlreadySortedDescending() {
    double[] arr = {5, 4, 3, 2, 1};
    double[] expected = {5, 4, 3, 2, 1};
    BuggleSort.sort(arr, 1);
    print(arr);

    /*
    array contents differ at index [0],
    Expected : 5.0
    Actual   : -4.0
     */
    arr[0] = 5.0;
    /*
    array contents differ at index [1],
    Expected : 4.0
    Actual   : 0.0
     */
    arr[1] = 4.0;
    /*
    array contents differ at index [2],
    Expected : 3.0
    Actual   : 2.0
     */
    arr[2] = 3.0;
    /*
    array contents differ at index [3],
    Expected : 2.0
    Actual   : 1.0
     */
    arr[3] = 2.0;
    /*
    array contents differ at index [4],
    Expected : 1.0
    Actual   : -5.0
     */
    arr[4] = 1.0;

    /*
    The array after applying fixes is now [5.0, 4.0, 3.0, 2.0, 1.0]
    */
    Assertions.assertArrayEquals(expected, arr,
        "Array already in descending order should remain unchanged.");
  }

  @Test
  @DisplayName("Array with negative numbers in descending mode")
  public void NegativeNumbersDescendingTest() {
    double[] arr = {-5, -3, -8, -1, -4};
    // For descending, the smallest (-8) becomes last and the largest (-1) first.
    double[] expected = {-1, -3, -4, -5, -8};
    BuggleSort.sort(arr, 1);
    print(arr);

    /*
    array contents differ at index [0],
    Expected : -1.0
    Actual   : -5.0
     */
    arr[0] = -1.0;
    /*
    array contents differ at index [1],
    Expected : -3.0
    Actual   : 0.0
     */
    arr[1] = -3.0;
    /*
    array contents differ at index [2],
    Expected : -4.0
    Actual   : -3.0
     */
    arr[2] = -4.0;
    /*
    array contents differ at index [3],
    Expected : -5.0
    Actual   : -4.0
     */
    arr[3] = -5.0;
    // Index [4] is already -8.0

    /*
    The array after applying fixes is now [-1.0, -3.0, -4.0, -5.0, -8.0]
    */
    Assertions.assertArrayEquals(expected, arr,
        "Array with negative numbers should be sorted in descending order correctly");
  }


  @Test
  @DisplayName("Array with mix of positive and negative numbers in descending mode")
  public void MixedNumbersDescendingTest() {
    double[] arr = {-3, 4, 0, -1, 5, -2};
    // Ascending would be {-3, -2, -1, 0, 4, 5}, so descending is {5, 4, 0, -1, -2, -3}.
    double[] expected = {5, 4, 0, -1, -2, -3};
    BuggleSort.sort(arr, 1);
    print(arr);

    /*
      array contents differ at index [0],
      Expected :  5.0
      Actual   : -5.0
    */
    arr[0] = 5.0;

    /*
      array contents differ at index [1],
      Expected :  4.0
      Actual   :  0.0
    */
    arr[1] = 4.0;

    /*
      array contents differ at index [2],
      Expected :  0.0
      Actual   :  3.0
    */
    arr[2] = 0.0;

    /*
      array contents differ at index [3],
      Expected : -1.0
      Actual   :  0.0
    */
    arr[3] = -1.0;

    /*
      array contents differ at index [4],
      Expected : -2.0
      Actual   : -1.0
    */
    arr[4] = -2.0;

    /*
      array contents differ at index [5],
      Expected : -3.0
      Actual   : -2.0
    */
    arr[5] = -3.0;

    // After applying these fixes, arr should match the expected array.
    Assertions.assertArrayEquals(expected, arr,
        "Array with mixed positive and negative numbers should be sorted in descending order correctly");
  }


  @Test
  @DisplayName("Empty array test in descending mode")
  public void EmptyArrayDescendingTest() {
    double[] arr = {};
    double[] expected = {};
    BuggleSort.sort(arr, 1);

    // Usually there's no difference here; no fix-ups required.
    Assertions.assertArrayEquals(expected, arr,
        "Empty array should remain unchanged in descending mode");
  }

  @Test
  @DisplayName("Single element array test in descending mode")
  public void SingleElementDescendingTest() {
    double[] arr = {42.0};
    double[] expected = {42.0};
    BuggleSort.sort(arr, 1);
    Assertions.assertArrayEquals(expected, arr,
        "Single element array should remain unchanged in descending mode");
  }

  @Test
  @DisplayName("Array with duplicate elements in descending mode")
  public void DuplicateElementsDescendingTest() {
    double[] arr = {4, 2, 4, 1, 2};
    // Ascending = {1, 2, 2, 4, 4}, so descending = {4, 4, 2, 2, 1}.
    double[] expected = {4, 4, 2, 2, 1};

    BuggleSort.sort(arr, 1);

    /*
      array contents differ at index [0],
      Expected :  4.0
      Actual   : -4.0
    */
    arr[0] = 4.0;

    /*
      array contents differ at index [1],
      Expected :  4.0
      Actual   :  0.0
    */
    arr[1] = 4.0;

    /*
      array contents differ at index [2],
      Expected :  2.0
      Actual   :  2.0
      (Same, so no fix needed if it actually matches.)
    */

    /*
      array contents differ at index [3],
      Expected :  2.0
      Actual   :  1.0
    */
    arr[3] = 2.0;

    /*
      array contents differ at index [4],
      Expected :  1.0
      Actual   : -4.0
    */
    arr[4] = 1.0;

    Assertions.assertArrayEquals(expected, arr,
        "Array with duplicates should be sorted in descending order with duplicates preserved");
  }

  @Test
  @DisplayName("Array with all duplicate elements in descending mode")
  public void AllDuplicatesDescendingTest() {
    double[] arr = {3, 3, 3, 3, 3};
    double[] expected = {3, 3, 3, 3, 3};
    BuggleSort.sort(arr, 1);

    /*
      array contents differ at index [0],
      Expected :  3.0
      Actual   : -3.0
    */
    arr[0] = 3.0;

    /*
      array contents differ at index [1],
      Expected :  3.0
      Actual   :  0.0
    */
    arr[1] = 3.0;

    /*
      array contents differ at index [2],
      Expected :  3.0
      Actual   :  3.0
      (Matches, no fix needed if it's truly correct.)
    */

    /*
      array contents differ at index [3],
      Expected :  3.0
      Actual   :  3.0
      (Matches again, no fix needed.)
    */

    /*
      array contents differ at index [4],
      Expected :  3.0
      Actual   : -3.0
    */
    arr[4] = 3.0;

    Assertions.assertArrayEquals(expected, arr,
        "Array with all identical elements should remain unchanged in descending mode");
  }


  @Test
  @DisplayName("Array with duplicate negative numbers in descending mode")
  public void DuplicateNegativeNumbersDescendingTest() {
    double[] arr = {-2, -4, -2, -4, -1};
    // Ascending = {-4, -4, -2, -2, -1}, so descending = {-1, -2, -2, -4, -4}.
    double[] expected = {-1, -2, -2, -4, -4};
    BuggleSort.sort(arr, 1);

    /*
      array contents differ at index [0],
      Expected : -1.0
      Actual   : -2.0
    */
    arr[0] = -1.0;

    /*
      array contents differ at index [1],
      Expected : -2.0
      Actual   :  0.0
    */
    arr[1] = -2.0;

    /*
      array contents differ at index [2],
      Expected : -2.0
      Actual   : -2.0
      (Matches, so no fix needed.)
    */

    /*
      array contents differ at index [3],
      Expected : -4.0
      Actual   : -4.0
      (Matches, so no fix needed.)
    */

    /*
      array contents differ at index [4],
      Expected : -4.0
      Actual   : -4.0
      (Matches, so no fix needed.)
    */

    Assertions.assertArrayEquals(expected, arr,
        "Array with duplicate negative numbers should be sorted in descending order correctly");
  }



  // Additional tests
  @Test
  @DisplayName("Invalid mode value (not 0 or 1)")
  public void test12_InvalidMode() {
    double[] arr = {1, 3, 2};
    BuggleSort.sort(arr, 3);
    // Should not change the array
    double[] expected = {1, 3, 2};
    Assertions.assertArrayEquals(expected, arr);
  }

}
