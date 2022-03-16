import java.io.*;
import java.util.*;
public class Main{
    public static void main (String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        int t=Integer.parseInt(st.nextToken());
        int need=Integer.parseInt(st.nextToken());
        
        st=new StringTokenizer(br.readLine());
        int[] trees=new int[t];
        int max=0;
          
            
        for(int i=0; i<t; i++){
            trees[i]=Integer.parseInt(st.nextToken());
            if(max<trees[i])
                max=trees[i];
        }
        
        int lo=0; 
        int hi=max;
        int mid=0;
        
        while(lo<hi){
            mid=(lo+hi)/2;
            long count=0;
            for(int i=0; i<t; i++){
                int rest=trees[i]-mid;
                if(rest>0){
                    count+=rest;
                }
            }
            if(count<need){
            	hi=mid;  
            }
            else{
            	lo=mid+1;
            }
        }
        System.out.print(lo-1);
    }
}