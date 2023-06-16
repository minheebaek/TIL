import java.io.*;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum =0;
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
           arr[i]=Integer.parseInt(br.readLine());
            sum+=arr[i];
        }
        Arrays.sort(arr);
        System.out.println(sum/5);
        System.out.println(arr[2]);

    }
}
