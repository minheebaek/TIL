package 알고리즘.이코테개념코드;

public class dp2 { //Top-bottom으로 구현한 피보나치 수열
    public static long[] d = new long[100];

    public static long fibo(int x){
        if(x == 1 || x == 2){
            return 1;
        }
        if(d[x]!=0){
            return d[x];
        }

        d[x] = fibo(x - 1)+fibo(x - 2);
        return d[x];
    }

    public static void main(String[] args){
        System.out.println(fibo(50));
    }
}
