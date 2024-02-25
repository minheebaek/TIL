package 알고리즘.BOJ;
import java.util.*;
public class BOJ4101{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(true){
            int firstNum=scan.nextInt();
            int secondNum=scan.nextInt();
            if(firstNum==0&&secondNum==0){
                return;
            }
            if(firstNum>secondNum){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }


        }
    }
}
