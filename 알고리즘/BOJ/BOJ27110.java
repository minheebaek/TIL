package 알고리즘.BOJ;
import java.util.Scanner;

public class BOJ27110 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();


        int ans=0;
        for(int i=0;i<3;i++){
            int pepole=scan.nextInt();
            if(n-pepole>=0) ans+=pepole;
            if(n-pepole<0) ans+=n;
        }
        System.out.println(ans);

    }
}

