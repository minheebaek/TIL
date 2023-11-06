package 알고리즘.이코테개념코드;

public class dp3 { //Bottom-top으로 구현한 피보나치 수열
    public static long[] d = new long[100];

    public static void main(String[] args) {
        d[1] = 1;
        d[2] = 1;
        int n = 50;

        for(int i=3; i<=n; i++){
            d[i] = d[i-1]+d[i-2];
        }
        System.out.println(d[n]);
    }

}
