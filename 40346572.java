import java.util.*;
import java.io.*;
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
        
        Arrays.sort(arr,new Comparator<int[]>(){
           public int compare(int[] o1,int[] o2){
               if(o1[0]==o2[0]){
                   return o1[1]-o2[1];
               }
               else{
                   return o1[0]-o2[0];
               }
           }  
        });
        
        for(int i=0; i<t; i++){
            System.out.println(arr[i][0]+" "+arr[i][1]);
        }
    }
}