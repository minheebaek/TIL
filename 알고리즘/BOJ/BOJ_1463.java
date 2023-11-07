package 알고리즘.BOJ;

import java.util.*;

public class BOJ_1463 {

    public static void main(String[] agrs) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int[] d = new int[x + 1];
        d[1] = 0;

        for (int i = 2; i <= x; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }
            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
        }
        System.out.println(d[x]);
    }
}


