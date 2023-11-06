package 알고리즘;

import java.io.*;
public class ex_dp1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = new String[n];
        for(int i=0; i<n; i++){
            str[i]= br.readLine();

        }
        int k = Integer.parseInt(br.readLine());

        if(k==1){
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i]);

            }
        }else if(k==2){
            for (int i = 0; i < str.length; i++) {
                StringBuilder sb = new StringBuilder(str[i]);
                String output = sb.reverse().toString();
                System.out.println(output);
            }
        }else{
            for (int i = str.length-1; i >=0; i--) {
                System.out.println(str[i]);
            }
            }
        }
    }
