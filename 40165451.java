import java.io.*;
import java.util.*;
public class Main{    
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr=new int[t][2];
        for(int i=0; i<t; i++){
            st=new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }
       
        for(int i=0; i<t; i++){
            int rank=1;
            for(int j=0; j<t; j++){
               if(i==j)continue;
               if(arr[i][0]<arr[j][0]&&arr[i][1]<arr[j][1]){
                   rank++;
               }
            }
           System.out.print(rank+" "); 
        }
        
        
      
        
    }
    
}