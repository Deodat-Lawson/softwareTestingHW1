import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.example.Sudoku;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestIsValidSudoku {
  @ParameterizedTest
  @MethodSource("provideSudokuBoards")
  void testIsValidSudoku(char[][] board, boolean expectedResult) {
    boolean actualResult = Sudoku.isValidSudoku(board);
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Provide multiple test cases (boards and their expected validity).
   */
  private static Stream<Arguments> provideSudokuBoards() {
    return Stream.of(
        // Test Case 1: Completely empty board
        Arguments.of(
            new char[][] {
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            true
        ),

        // Test Case 2: Duplicate in the same row
        // Row 0 contains two '1's
        Arguments.of(
            new char[][] {
                {'1','1','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),

        // Test Case 3: Duplicate in the same column
        // Column 0 contains two '2's
        Arguments.of(
            new char[][] {
                {'2','.','.','.','.','.','.','.','.'},
                {'2','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),

        // Test Case 4: Duplicate in the same 3×3 box
        // Top-left box has two '3's (positions (0,0) and (1,1))
        Arguments.of(
            new char[][] {
                {'3','.','.','.','.','.','.','.','.'},
                {'.','3','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),

        // Test Case 5: A valid partially filled board
        // (No duplicates; each filled cell is unique in row, column, box)
        Arguments.of(
            new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
            },
            true
        ),

        // Test Case 6: A valid completed Sudoku
         Arguments.of(
            new char[][] {
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}
            },
            true
         ),

        // Test Case 7: Multiple duplicates in the same row
        // Row 0 has '1' repeated 3 times
        Arguments.of(
            new char[][] {
                {'1','1','1','.','.','.','.','.','.'},  // Multiple '1's in row 0
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),


        // Test Case 8: Duplicates in different rows
        // Row 0 has two '4's, row 1 has two '5's
        Arguments.of(
            new char[][] {
                {'4','4','.','.','.','.','.','.','.'},  // Row 0 has duplicate '4'
                {'5','5','.','.','.','.','.','.','.'},  // Row 1 has duplicate '5'
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),
        // Test Case 9: Duplicates in both a row and a box
        //   - Row 0 has two '1's
        //   - Also, the top-left box would have the same '1' repeated
        Arguments.of(
            new char[][] {
                {'1','1','.','.','.','.','.','.','.'},
                {'1','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),

        // Test Case 10: A violation in the bottom-right 3×3
        Arguments.of(
            new char[][] {
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','8','.','.'},
                {'.','.','.','.','.','.','.','8','.'}, // Duplicate '8' in bottom-right box
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),
        // Test Case 11: One row is perfect (1..9), but another row has duplicates
        Arguments.of(
            new char[][] {
                // Row 0 is a full valid row from 1..9
                {'1','2','3','4','5','6','7','8','9'},
                {'.','3','3','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        ),

        // Test Case 12: A valid board
        Arguments.of(
            new char[][] {
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','8','.','.','.'},
                {'.','.','.','.','.','.','8','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            true
        ),

        // Test Case 13: Non integer board
        Arguments.of(
            new char[][] {
                {'1','a','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
            },
            false
        )




    );
  }


}
