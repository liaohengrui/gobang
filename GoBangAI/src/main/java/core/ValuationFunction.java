package core;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * Demo class
 *
 * @author HengruiLiao
 * @date 2019/1/3
 */
public class ValuationFunction {
    /**
     * 读取棋盘的棋子排列情况
     */
    private List<String> readBoard(int[][] board) {
        List<String> result = new ArrayList<>();
        int length = board.length;
        //横向读取
        for (int i = 0; i < length; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < length; j++) {
                buffer.append(board[i][j]);
            }
            result.add(buffer.toString());
        }
        //纵向读取
        for (int i = 0; i < length; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < length; j++) {
                buffer.append(board[j][i]);
            }
            result.add(buffer.toString());
        }
        //  左下读取
        for (int i = 0; i < length * 2 - 1; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j <= i && j < length; j++) {
                if (i - j < length) {
                    buffer.append(board[j][i - j]);
                }
            }
            result.add(buffer.toString());
        }
        // 右下读取
        for (int i = -length + 1; i < length; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < length; j++) {
                if (i + j >= 0 && i + j < length) {
                    buffer.append(board[j][i + j]);
                }
            }
            result.add(buffer.toString());
        }
        return result;
    }

    public int calculateSituation(int[][] board) {
        List<String> chessLine = readBoard(board);
        int humScore = eRows(chessLine, Role.hum);
        int comScore = eRows(chessLine, Role.com);
        return comScore - humScore;
    }

    private int eRows(List<String> rows, int role) {
        int r = 0;
        for (int i = 0; i < rows.size(); i++) {
            r += eRow(rows.get(i), role);
        }
        return r;
    }

    private int eRow(String s, int role) {
        // 连子数
        int count = 0;
        // 封闭数
        int block = 0;
        //分数
        int value = 0;

        for (int i = 0; i < s.length(); i++) {
            // 发现第一个己方棋子
            if ((s.charAt(i) - 48) == role) {
                count = 1;
                block = 0;
                if (i == 0) {
                    block = 1;
                } else if ((s.charAt(i - 1) - 48) != Role.empty) {
                    block = 1;
                }
                for (i = i + 1; i < s.length(); i++) {
                    if ((s.charAt(i) - 48) == role) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (i == s.length() || (s.charAt(i) - 48) != Role.empty) {
                    block++;
                }
                value += score(count, block);
            }
        }
        return value;
    }

    private int score(int count, int block) {

        if (count >= 5) {
            return Score.FIVE;
        }

        if (block == 0) {
            switch (count) {
                case 1:
                    return Score.ONE;
                case 2:
                    return Score.TWO;
                case 3:
                    return Score.THREE;
                case 4:
                    return Score.FOUR;
                default:
            }
        }

        if (block == 1) {
            switch (count) {
                case 1:
                    return Score.BLOCKED_ONE;
                case 2:
                    return Score.BLOCKED_TWO;
                case 3:
                    return Score.BLOCKED_THREE;
                case 4:
                    return Score.BLOCKED_FOUR;
                default:
            }
        }

        return 0;
    }

    public int win(int[][] board) {
        List<String> rows = readBoard(board);
        for (int i = 0; i < rows.size(); i++) {
            int value = eRow(rows.get(i), Role.com);
            if (value >= Score.FIVE) {
                return Role.com;
            }
            value = eRow(rows.get(i), Role.hum);
            if (value >= Score.FIVE) {
                return Role.hum;
            }
        }
        return Role.empty;
    }

}
