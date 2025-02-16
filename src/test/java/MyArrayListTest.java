import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class MyArrayListTest {

  // Helper method for printing the list contents (debugging/logging).


  /*
  Fault: Given the case of an arraylist without a set capacity, when we need to double the capacity,the elements are not
  copied to the new array. Instead the elements are initialized to null.

  Fault: Given the case of an arraylist with a set capacity, The actual set capacity is one less than the input capacity.

  Fault: Given the case of an arraylist with a set capacity, when we need to double the capacity, our program is
  unable to double the capacity. Instead the capacity remains the same.


  Fault: Remove method returns false when we want to remove the first element from the list.

  Fault: When we remove an element from the list, the remove method is called, it would return true if the element is
  in the list. However, the size of the list is not updated and the element is not removed from the list.

  Fault: When we want to sort the elements with getEls() method, the elements are not sorted in ascending order. They
   are not sorted at all.


   */
  public static void print(MyArrayList<Integer> list) {
    for (int i = 0; i < list.getSize(); i++) {
      Object[] els = list.getEls();
      Integer val = (Integer) els[i];
      System.out.print(val + " ");
    }
    System.out.println();
  }

  @Test
  @DisplayName("Test default constructor and initial size/capacity")
  public void testDefaultConstructor() {
    MyArrayList<Integer> list = new MyArrayList<>();
    Assertions.assertEquals(0, list.getSize(), "Size should be 0 initially");
    Assertions.assertEquals(2, list.getCap(), "Default capacity should be 2");
  }

  @Test
  @DisplayName("Test custom constructor with positive capacity")
  public void testCustomConstructorPositive() {
    MyArrayList<Integer> list = new MyArrayList<>(5);
    Assertions.assertEquals(0, list.getSize(), "Size should be 0 initially");
    Assertions.assertEquals(5, list.getCap(), "Capacity should be 5");
  }


  // ------------------------------------------------------------------------
  // Testing add(T el)

  @Test
  @DisplayName("Test add single element (using default capacity)")
  public void testAddSingle() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(10);

    Assertions.assertEquals(1, list.getSize(), "Size should be 1 after adding one element");
    Assertions.assertEquals(2, list.getCap(), "Capacity should still be 2 (no expansion yet)");

    Object[] els = list.getEls();
    Assertions.assertEquals(10, (int) els[0], "Element should be 10");
  }


  @Test
  @DisplayName("Test add up to capacity and then trigger expansion")
  public void testAddUntilExpansionNoCap() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(1);
    list.add(2);
    print(list);
    // Capacity now should be 2, size is 2
    Assertions.assertEquals(2, list.getSize());
    Assertions.assertEquals(2, list.getCap(), "Capacity still 2, no expansion yet");

    // Next add should double capacity to 4
    list.add(3);
    print(list);
    Assertions.assertEquals(3, list.getSize());
    Assertions.assertEquals(4, list.getCap(), "Capacity should have doubled to 4");

    // Validate contents
    Object[] els = list.getEls();
    Assertions.assertEquals(1, (int) els[0]);
    Assertions.assertEquals(2, (int) els[1]);
    Assertions.assertEquals(3, (int) els[2]);
  }



  @Test
  @DisplayName("Test add up to capacity and then trigger expansion")
  public void testAddUntilExpansion() {
    MyArrayList<Integer> list = new MyArrayList<>(2);
    list.add(1);
    list.add(2);
    // Capacity now should be 2, size is 2
    Assertions.assertEquals(2, list.getSize());
    Assertions.assertEquals(2, list.getCap(), "Capacity still 2, no expansion yet");

    // Next add should double capacity to 4
    list.add(3);
    Assertions.assertEquals(3, list.getSize());
    Assertions.assertEquals(4, list.getCap(), "Capacity should have doubled to 4");

    // Validate contents
    Object[] els = list.getEls();
    Assertions.assertEquals(1, (int) els[0]);
    Assertions.assertEquals(2, (int) els[1]);
    Assertions.assertEquals(3, (int) els[2]);
  }

  @Test
  @DisplayName("Test multiple expansions with add()")
  public void testMultipleExpansionsAdd() {
    MyArrayList<Integer> list = new MyArrayList<>(2);

    // Insert enough elements to trigger multiple expansions:
    // capacity path: 2 -> 4 -> 8 -> 16
    for (int i = 1; i <= 12; i++) {
      list.add(i);
    }

    Assertions.assertEquals(12, list.getSize(), "Size should be 12");
    Assertions.assertTrue(list.getCap() >= 12, "Capacity should be at least 16 after expansions");

    // Validate the sequence
    Object[] els = list.getEls();
    for (int i = 0; i < 12; i++) {
      Assertions.assertEquals(i + 1, (int) els[i], "Elements must match the inserted sequence");
    }
  }

  @Test
  @DisplayName("Test add duplicates")
  public void testAddDuplicates() {
    MyArrayList<Integer> list = new MyArrayList<>(9);
    list.add(5);
    list.add(5);
    list.add(5);

    Assertions.assertEquals(3, list.getSize(), "Size should be 3 after adding three duplicates");
    Object[] els = list.getEls();
    Assertions.assertEquals(5, (int) els[0]);
    Assertions.assertEquals(5, (int) els[1]);
    Assertions.assertEquals(5, (int) els[2]);
  }

  // ------------------------------------------------------------------------
  // Testing remove(T el)

  @Test
  @DisplayName("Test remove existing element (middle element)")
  public void testRemoveExisting() {
    MyArrayList<Integer> list = new MyArrayList<>(4);
    list.add(10);
    list.add(20);
    list.add(30);
    print(list);

    boolean result = list.remove( 30);
    Assertions.assertTrue(result, "Should return true when removing an existing element");
    print(list);
//    Assertions.assertEquals(2, list.getSize(), "Size should decrease by 1");

    Object[] els = list.getEls();
    Assertions.assertEquals(10, (int) els[0]);
    Assertions.assertEquals(30, (int) els[1]);
  }

  @Test
  @DisplayName("Test remove first element")
  public void testRemoveFirstElement() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(1);
    list.add(2);

    boolean removed = list.remove(1);
    print(list);
    Assertions.assertTrue(removed, "Should successfully remove the first element (1)");
    Assertions.assertEquals(2, list.getSize(), "Size should now be 2");

    Object[] els = list.getEls();
    Assertions.assertEquals(2, (int) els[0]);
    Assertions.assertEquals(3, (int) els[1]);
  }

  @Test
  @DisplayName("Test remove last element")
  public void testRemoveLastElement() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    boolean removed = list.remove(30);
    Assertions.assertTrue(removed, "Should successfully remove the last element (30)");
    Assertions.assertEquals(2, list.getSize(), "Size should now be 2");

    Object[] els = list.getEls();
    Assertions.assertEquals(10, (int) els[0]);
    Assertions.assertEquals(20, (int) els[1]);
  }

  @Test
  @DisplayName("Test remove duplicates (removes only first occurrence)")
  public void testRemoveDuplicates() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(5);
    list.add(7);
    list.add(5);
    list.add(7);

    boolean removed = list.remove(5);
    Assertions.assertTrue(removed, "Should remove the first occurrence of 5");
    Assertions.assertEquals(3, list.getSize(), "Size should be 3 after removing one occurrence of 5");

    // The list should now contain [7, 5, 7]
    Object[] els = list.getEls();
    Assertions.assertEquals(7, (int) els[0]);
    Assertions.assertEquals(5, (int) els[1]);
    Assertions.assertEquals(7, (int) els[2]);
  }

  @Test
  @DisplayName("Test remove non-existing element")
  public void testRemoveNonExisting() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(1);
    list.add(2);

    boolean result = list.remove(3);
    Assertions.assertFalse(result, "Should return false when removing a non-existing element");
    Assertions.assertEquals(2, list.getSize(), "Size should remain the same");

    Object[] els = list.getEls();
    Assertions.assertEquals(1, (int) els[0]);
    Assertions.assertEquals(2, (int) els[1]);
  }

  @Test
  @DisplayName("Test remove from empty list")
  public void testRemoveFromEmpty() {
    MyArrayList<Integer> list = new MyArrayList<>();
    boolean result = list.remove(1);
    Assertions.assertFalse(result, "Removing from an empty list should return false");
    Assertions.assertEquals(0, list.getSize(), "Size remains 0");
  }

  // ------------------------------------------------------------------------
  // Testing addAll(T[] elsToAdd)

  @Test
  @DisplayName("Test addAll with empty array (no changes)")
  public void testAddAllEmpty() {
    MyArrayList<Integer> list = new MyArrayList<>();
    Integer[] emptyArr = {};
    list.addAll(emptyArr);

    Assertions.assertEquals(0, list.getSize(), "Size should remain 0");
    Assertions.assertEquals(2, list.getCap(), "Capacity should remain 2 (no expansions)");
  }

  @Test
  @DisplayName("Test addAll with small array (no capacity doubling needed)")
  public void testAddAllNoExpansion() {
    MyArrayList<Integer> list = new MyArrayList<>(5);
    Integer[] arr = {1, 2};
    list.addAll(arr);

    Assertions.assertEquals(2, list.getSize(), "Size should be 2");
    Assertions.assertEquals(5, list.getCap(), "Capacity should remain 5");

    Object[] els = list.getEls();
    Assertions.assertEquals(1, (int) els[0]);
    Assertions.assertEquals(2, (int) els[1]);
  }

  @Test
  @DisplayName("Test addAll triggers multiple capacity expansions")
  public void testAddAllMultipleExpansions() {
    MyArrayList<Integer> list = new MyArrayList<>(2);
    Integer[] arr = {1, 2, 3, 4, 5};
    list.addAll(arr);

    Assertions.assertEquals(5, list.getSize(), "Size should be 5");
    // expansions: 2 -> 4 -> 8
    Assertions.assertEquals(8, list.getCap(), "Capacity should be 8 after expansions");

    Object[] els = list.getEls();
    for (int i = 0; i < 5; i++) {
      Assertions.assertEquals(i + 1, (int) els[i]);
    }
  }

  @Test
  @DisplayName("Test addAll with duplicates")
  public void testAddAllDuplicates() {
    MyArrayList<Integer> list = new MyArrayList<>(6);
    Integer[] arr = {5, 5, 5};
    list.addAll(arr);

    Assertions.assertEquals(3, list.getSize(), "Size should be 3 after adding three duplicates");
    Assertions.assertTrue(list.getCap() >= 3, "Capacity should be at least 4 after expansions if needed");

    Object[] els = list.getEls();
    Assertions.assertEquals(5, (int) els[0]);
    Assertions.assertEquals(5, (int) els[1]);
    Assertions.assertEquals(5, (int) els[2]);
  }

  @Test
  @DisplayName("Test addAll with large array to ensure multiple expansions")
  public void testAddAllLargeArray() {
    MyArrayList<Integer> list = new MyArrayList<>(100);
    Integer[] largeArr = new Integer[10];
    for (int i = 0; i < 10; i++) {
      largeArr[i] = i + 1;  // e.g., 1,2,3,...,10
    }
    list.addAll(largeArr);

    Assertions.assertEquals(10, list.getSize(), "Should have 10 elements added");
    // expansions: 2 -> 4 -> 8 -> 16 (so final capacity expected is 16)
    Assertions.assertTrue(list.getCap() >= 10, "Capacity should be at least 16 now");

    Object[] els = list.getEls();
    for (int i = 0; i < 10; i++) {
      Assertions.assertEquals(i + 1, (int) els[i], "Elements should match the inserted array");
    }
  }

  // ------------------------------------------------------------------------

  @Test
  @DisplayName("Test getEls returns correct array contents")
  public void testGetEls() {
    MyArrayList<Integer> list = new MyArrayList<>(4);

    // Add some elements
    list.add(30);
    list.add(20);
    list.add(10);

    print(list);

    // Call getEls()
    Object[] els = list.getEls();

    for (int i = 0; i < els.length; i++) {
      System.out.println(els[i]);
    }

    print(list);


    // Basic checks
    Assertions.assertEquals(3, els.length, "Returned array length should match the current size.");
    Assertions.assertEquals(10, (int) els[0], "First element should be 10.");
    Assertions.assertEquals(20, (int) els[1], "Second element should be 20.");
    Assertions.assertEquals(30, (int) els[2], "Third element should be 30.");

  }

  @Test
  @DisplayName("Test getSize and getCap correctness after multiple operations")
  public void testSizeCapAfterMultipleOps() {
    MyArrayList<Integer> list = new MyArrayList<>(3);

    // Initially empty
    Assertions.assertEquals(0, list.getSize());
    Assertions.assertEquals(3, list.getCap());

    // Add 3 items
    list.add(10);
    list.add(20);
    list.add(30);
    Assertions.assertEquals(3, list.getSize(), "Added 3 elements");
    Assertions.assertEquals(3, list.getCap(), "Capacity is exactly 3; no expansion yet");

    // Add one more => expansion to 6 (double from 3 to 6)
    list.add(40);
    Assertions.assertEquals(4, list.getSize());
    Assertions.assertEquals(6, list.getCap(), "Should have expanded to 6");

    // Remove an element => size is 3, capacity remains 6
    list.remove(20);
    Assertions.assertEquals(3, list.getSize());
    Assertions.assertEquals(6, list.getCap(), "Capacity does not shrink upon removal");
  }

  @Test
  @DisplayName("Test getEls after removing some elements")
  public void testGetElsAfterRemove() {
    MyArrayList<Integer> list = new MyArrayList<>(4);
    list.add(100);
    list.add(200);
    list.add(300);
    list.remove(200);

    // Now should have [100, 300]
    Object[] els = list.getEls();
    Assertions.assertEquals(2, els.length, "Length of returned array should match the size");
    Assertions.assertEquals(100, (int) els[0]);
    Assertions.assertEquals(300, (int) els[1]);
  }

  @Test
  @DisplayName("Test repeated remove until empty")
  public void testRemoveAll() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    list.remove(10);
    list.remove(20);
    list.remove(30);

    Assertions.assertEquals(0, list.getSize(), "All elements removed, size should be 0");
    Assertions.assertFalse(list.remove(30), "No elements left, removing again should be false");
  }

}
