package 알고리즘.BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ5717 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int male = Integer.parseInt(st.nextToken());
            int female = Integer.parseInt(st.nextToken());
            if (male != 0 || female != 0) {
                System.out.println(male + female);
            }
            if (male == 0 && female == 0) {
                break;
            }
        }
    }
}