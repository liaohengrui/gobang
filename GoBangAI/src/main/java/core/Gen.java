package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Demo class
 *
 * @author HengruiLiao
 * @date 2019/1/3
 */
public class Gen {

    public List<int[]> gen(int[][] board, int deep) {
        List<int[]> neighbors = new ArrayList<>();
        List<int[]> nextNeighbors = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Role.empty) {
                    int[] temp = new int[]{i, j};
                    if (hasNeighbor(board, temp, 1, 1)) {
                        neighbors.add(temp);
                    } else if (deep >= 2 && hasNeighbor(board, temp, 2, 2)) {
                        nextNeighbors.add(temp);
                    }
                }
            }
        }
        for (int[] ints : nextNeighbors) {
            neighbors.add(ints);
        }
        return neighbors;
    }

    private boolean hasNeighbor(int[][] board, int[] point, int distance, int count) {
        int length = board.length;
        for (int i = point[0] - distance; i <= point[0] + distance; i++) {
            if (i < 0 || i >= length) {
                continue;
            }
            for (int j = point[1] - distance; j <= point[1] + distance; j++) {
                if (j < 0 || j >= length) {
                    continue;
                }
                if (i == point[0] && j == point[1]) {
                    continue;
                }
                if (board[i][j] != Role.empty) {
                    count--;
                    if (count <= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
