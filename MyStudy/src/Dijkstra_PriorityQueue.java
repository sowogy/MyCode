import java.io.*;
import java.util.*;

class H_Node implements Comparable<H_Node> {
    private int index;
    private int distance;
    //N번 노드에서 갈 수 있는 노드의 번호와 노드까지의 거리를 저장
    public H_Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
    public int getIndex(){
        return this.index;
    }
    public int getDistance(){
        return this.distance;
    }

    @Override
    public int compareTo(H_Node other){
        if(this.distance < other.distance){
            return -1;
        }
        return 1;
    }
}

public class Dijkstra_PriorityQueue{
    public static final int INF = (int)1e9; //무한을 의미하는 10억 설정
    public static int n, m, start; //노드의 개수, 간선의 개수, 시작 노드
    public static boolean[] visited = new boolean[10001];
    public static int[] d = new int[10001];
    public static ArrayList<ArrayList<H_Node>> graph = new ArrayList<ArrayList<H_Node>>();
    public static int getSmallestNode(){
        int min = INF;
        int index = 0; //가장 간선의 크기가 작은 노드의 인덱스
        for(int i = 1; i <= n; i++){
            if(d[i] < min && !visited[i]){
                min = d[i];
                index = i;
            }
        }
        System.out.println(index);
        return index;
    }

    public static void dijkstra(int start){
        PriorityQueue<H_Node> pq = new PriorityQueue<>();
        //시작 노드에 대해 초기화
        pq.offer(new H_Node(start, 0));
        d[start] = 0;
        //큐가 비어있으면 정지
        while(!pq.isEmpty()){
            //가장 간선의 크기가 작은 노드를 꺼냄
            H_Node node = pq.poll();
            //노드의 거리와 인덱스를 반환
            int dist  = node.getDistance();
            int now = node.getIndex();
            if(d[now] < dist) continue;
            for(int i = 0; i < graph.get(now).size(); i++){
                //현재 인덱스에서 갈 수 있는 노드까지의 거리
                int cost = d[now] + graph.get(now).get(i).getDistance();
                //현재 노드를 거쳐서 가는 것이 거리가 짧은 경우
                if(d[graph.get(now).get(i).getIndex()] > cost){
                    d[graph.get(now).get(i).getIndex()] = cost;
                    //구한 최단거리를 우선순위 큐에 삽입
                    pq.offer(new H_Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        //노드 정보 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk1 = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk1.nextToken());
        m = Integer.parseInt(tk1.nextToken());
        start = Integer.parseInt(tk1.nextToken());
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<H_Node>());
        }
        Arrays.fill(d, INF);
        for(int j = 0; j < m; j++){
            System.out.print("시작 -> 목적지, 거리 : ");
            tk1 = new StringTokenizer(br.readLine());
            int start_idx = Integer.parseInt(tk1.nextToken());
            int idx = Integer.parseInt(tk1.nextToken()); //i번 노드에서 갈 수 있는 노드의 인덱스
            int distance = Integer.parseInt(tk1.nextToken()); //해당 노드까지의 거리
            graph.get(start_idx).add(new H_Node(idx, distance));
            System.out.println(start_idx + " " + idx + " " + distance);
        }
        br.close();
        dijkstra(start);

        for(int i = 1; i < n + 1; i++){
            if(d[i] == INF){
                System.out.println("불가능");
            }
            else{
                System.out.println(start + "Node -> " + i + "번 Node까지의 최단거리 : " +  d[i]);
            }
        }
    }
}
