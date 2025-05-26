import java.io.*;
import java.util.*;

public class Floyd_Warshall {
    public static int INF = (int)1e9;
    public static int arr[][] = new int[501][501];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); //노드의 개수
        int m = Integer.parseInt(br.readLine()); //간선의 개수

        //그래프 정보 초기화
        for(int i = 0; i < 501; i++){
            Arrays.fill(arr[i], INF);
        }
        //자기 자신으로 가는 비용은 0으로 초기화
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(i == j){
                    arr[i][j] = 0;
                }
            }
        }

        //간선 정보 입력 받기
        for(int i = 0; i < m; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int start_node = Integer.parseInt(tk.nextToken());
            int destination = Integer.parseInt(tk.nextToken());
            int distance = Integer.parseInt(tk.nextToken());
            arr[start_node][destination] = distance;
        }

        //플로이드 워셜 수행
        for(int k = 1; k < n + 1; k++){  //거쳐가는 노드
            for(int i = 1; i < n + 1; i++){ //시작 노드
                for(int j = 1; j < n + 1; j++){ // 목적지 노드
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]); //i에서 j까지 k를 거쳐가는게 비용이 더 작을 경우
                }
            }
        }

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(arr[i][j] == INF){
                    wr.write(String.valueOf(i) + " To " + String.valueOf(j) + " is " + "INF" + "\n");
                    wr.flush();
                }
                wr.write(String.valueOf(i) + "To" + String.valueOf(j) + " -> " + String.valueOf(arr[i][j]) + "\n");
                wr.flush();
            }
        }
        wr.close();
    }
}
