package 알고리즘.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        String t = st.nextToken();
        int time = Integer.parseInt(t);
        String s = st.nextToken();
        int drink = Integer.parseInt(s);

        if (time >= 12 && time <= 16) {
            if (drink == 0) {
                System.out.println("320");
            } else {
                System.out.println("280");
            }
        } else {
            System.out.println("280");
        }
    }
}