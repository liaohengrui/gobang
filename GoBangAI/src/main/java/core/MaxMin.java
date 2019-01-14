package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author HengruiLiao
 * @date 2019/1/3
 */
public class MaxMin {
    static int MAX = Score.FIVE * 10;
    static int MIN = -1 * MAX;
    int total = 0;
    int cut = 0;
    Gen gen = new Gen();
    ValuationFunction evaluate = new ValuationFunction();


    //电脑下
    public int[] maxmin(int[][] board, int deep) {
        int best = MIN;
        List<int[]> points = gen.gen(board, deep);
        List<int[]> bestPoints = new ArrayList<>();
        if (evaluate.win(board) == Role.hum) {
            return new int[]{-1, -1, -1};
        }

        for (int i = 0; i < points.size(); i++) {
            int[] p = points.get(i);
            board[p[0]][p[1]] = Role.com;
            if (evaluate.win(board) == Role.com) {
                return new int[]{p[0], p[1], -2};
            }
            int v = min(board, deep - 1, MAX, best > MIN ? best : MIN);
            board[p[0]][p[1]] = Role.empty;
            if (v == best) {
                bestPoints.add(p);
            }
            if (v > best) {
                best = v;
                bestPoints.clear();
                bestPoints.add(p);
            }
        }
        int[] result = bestPoints.get((int) Math.floor(bestPoints.size() * Math.random()));
        System.out.println("当前局面分数：" + best);
        System.out.println("搜索节点数:" + total + " 剪枝掉的节点数:" + cut);

        return new int[]{result[0], result[1], 0};
    }

    //人类下棋
    private int min(int[][] board, int deep, int alpha, int beta) {
        int v = evaluate.calculateSituation(board);
        total++;
        if (deep <= 0 || evaluate.win(board) != Role.empty) {
            return v;
        }
        int best = MAX;
        List<int[]> points = gen.gen(board, deep);

        for (int i = 0; i < points.size(); i++) {
            int[] p = points.get(i);
            board[p[0]][p[1]] = Role.hum;
            v = max(board, deep - 1, best < alpha ? best : alpha, beta);
            board[p[0]][p[1]] = Role.empty;
            if (v < best) {
                best = v;
            }
            if (v < beta) {
                cut++;
                break;
            }
        }
        return best;
    }

    //电脑下棋
    private int max(int[][] board, int deep, int alpha, int beta) {
        int v = evaluate.calculateSituation(board);
        total++;
        if (deep <= 0 || evaluate.win(board) != Role.empty) {
            return v;
        }
        int best = MIN;
        List<int[]> points = gen.gen(board, deep);
        for (int i = 0; i < points.size(); i++) {
            int[] p = points.get(i);
            board[p[0]][p[1]] = Role.com;
            v = min(board, deep - 1, alpha, best > beta ? best : beta);
            board[p[0]][p[1]] = Role.empty;
            if (v > best) {
                best = v;
            }
            if (v > alpha) {
                cut++;
                break;
            }
        }
        return best;
    }
}
