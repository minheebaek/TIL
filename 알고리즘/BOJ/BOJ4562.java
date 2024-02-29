package 알고리즘.BOJ;

import java.util.Scanner;

public class BOJ4562 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();

        for(int i=0;i<n;i++){
            int x =scan.nextInt();
            int y =scan.nextInt();

            if(x>=y) System.out.println("MMM BRAINS");
            if(x<y) System.out.println("NO BRAINS");
        }
    }
}
