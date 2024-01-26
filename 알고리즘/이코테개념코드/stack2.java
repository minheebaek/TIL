package 알고리즘.이코테개념코드;
import java.util.*;
public class stack2 {
    public static void main(String[] args){
        Queue<Integer> q = new LinkedList<>();

        q.offer(5);
        q.offer(2);
        q.offer(3);
        q.offer(7);
        q.poll();
        q.offer(1);
        q.offer(4);
        q.poll();
        //먼저 들어온 원소부터 추출
        while (!q.isEmpty()){
            System.out.println(q.poll()+" ");
        }
    }
}
