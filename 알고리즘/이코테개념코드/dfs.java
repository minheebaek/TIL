package 알고리즘.이코테개념코드;
import java.util.*;

public class dfs {
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> gragh = new ArrayList<ArrayList<Integer>>();

    //dfs 함수 정의
    public static void dfs(int x){
        //현재 노드를 방문 처리
        visited[x] = true;
        System.out.println(x+" ");
        //현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for(int i = 0; i<gragh.get(x).size(); i++){
            int y = gragh.get(x).get(i);
            if(!visited[y]) dfs(y);
        }
    }

    public static void main(String[] args){
        /*
         그래프 연결된 내용 생략
         */
    }
}
