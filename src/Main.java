import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int array[][] = new int[9][9];
        int x = 0, y = 0, max;
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 9; i++){             //배열 입력
            for(int j = 0; j < 9; j++){
                array[i][j] = sc.nextInt();
            }
        }
        max = array[0][0];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(max < array[i][j]){
                    max = array[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        System.out.println(max);
        System.out.print((x+1) + " " + (y+1));
    }
}
