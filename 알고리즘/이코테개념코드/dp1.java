package 알고리즘.이코테개념코드;

public class dp1 { //비효율적인 피보나치 수열 구현
    public static int fibo(int x){
        if(x == 1 || x == 2){
            return 1;
        }
        return fibo(x-1)+fibo(x-2);
    }

    public static void main(String[] args){
        System.out.println(fibo(4));
    }
}
