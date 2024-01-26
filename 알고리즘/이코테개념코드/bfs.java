package 알고리즘.이코테개념코드;
import java.util.*;

public class bfs {
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> gragh = new ArrayList<ArrayList<Integer>>();

    //BFS 함수 정의
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        //현재 노드를 방문처리
        visited[start] =true;
        //큐가 빌때까지
        while(!q.isEmpty()){
            //큐에서 하나의 원소를 뽑아 출력
            int x = q.poll();
            System.out.println(x+" ");
            //해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
            for(int i=0; i<gragh.get(x).size(); i++){
                int y = gragh.get(x).get(i);
                if(!visited[y]){
                    q.offer(y);
                    visited[y] = true;
                }
            }
        }
    }
    // 메인 함수 생략
}
