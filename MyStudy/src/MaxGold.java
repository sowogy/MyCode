import java.util.*;
import java.io.*;

public class MaxGold {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine()); //테스트 케이스 입력
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());
        int[][] dp = new int[n][m];
        for(int z = 0; z < K; z++){
            Integer[] gold = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            int c = 0;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = gold[c++];
                }
            }
            for (int i = 1; i < m; i++) { //열
                for (int j = 0; j < n; j++) { //행
                    int left, left_up, left_down;
                    if (j == 0) { //첫번째 행일 경우 left_up은 존재하지 않으므로 0을 저장
                        left_up = 0;
                    }
                    else{
                        left_up = dp[j - 1][i - 1];
                    }
                    if (j == (n - 1)) { //맨 마지막 행일 경우 left_down은 존잴하지 않으므로 0을 저장
                        left_down = 0;
                    }
                    else{
                        left_down = dp[j + 1][i - 1];
                    }
                    left = dp[j][i - 1]; //left는 어떤 열에서든 존재
                    dp[j][i] = dp[j][i] + Math.max(left_up, Math.max(left, left_down)); //현재 행열의 값을 저장
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i][m - 1]);
        }
        wr.write(String.valueOf(result));
        wr.flush();
        wr.close();
    }
}
