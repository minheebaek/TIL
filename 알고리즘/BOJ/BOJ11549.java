package 알고리즘.BOJ;


import java.io.*;
import java.util.*;

public class BOJ11549 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int ans = 0;
        for (int i = 0; i < 5; i++) {
            if (scan.nextInt() == t) {
                ans++;
            }
            ;

        }
        System.out.println(ans);
    }
}