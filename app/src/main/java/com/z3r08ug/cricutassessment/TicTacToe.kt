package com.z3r08ug.cricutassessment


/* This function assumes that the board is represented by a 2D array of characters
('X', 'O', and space for empty). The function will check all rows, columns, and diagonals
for three consecutive identical symbols ('X' or 'O'), which indicates a winner.

Time Complexity: Since all operations involve a fixed number of element comparisons
(3 rows, 3 columns, 2 diagonals), and none of these operations depend on the size of the
input beyond the fixed size of the Tic Tac Toe board (3x3), the time complexity is
O(1), constant time.

Space Complexity: The function only uses a few additional variables (i in the loop and the
temporary comparisons in the if statements). These do not depend on the size of the input
and occupy a constant amount of space.
Input Storage: The board itself is passed as an argument, and the function does not create
any additional data structures that scale with input size. It operates directly on the given
Array<CharArray>.
Thus, the space complexity is also
O(1), as it requires a constant amount of space regardless of the input.

 */
fun checkWinner(board: Array<CharArray>): Char? {
    // Check rows and columns
    for (i in 0..2) {
        if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
            return board[i][0]
        }
        if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
            return board[0][i]
        }
    }

    // Check diagonals
    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        return board[0][0]
    }
    if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        return board[0][2]
    }

    // No winner
    return null
}
