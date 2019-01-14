package core;

import java.util.Scanner;

/**
 * Demo class
 *
 * @author HengruiLiao
 * @date 2019/1/3
 */
public class Test {


    public static void main(String[] args) {
        int[][] board = new int[15][15];
        MaxMin maxMin = new MaxMin();

        System.out.println("人类先手  用空格隔开坐标");
        System.out.println("----------------------------");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("人类下棋:");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            board[x][y] = 1;
            int[] comPoint = maxMin.maxmin(board, 4);
            showCom(comPoint);
            board[comPoint[0]][comPoint[1]] = 2;
        }

    }

    private static void showCom(int[] comPoint) {
        System.out.println("电脑下棋:");
        System.out.println("x==>" + comPoint[0]);
        System.out.println("y==>" + comPoint[1]);
    }

}
