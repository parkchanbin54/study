import java.io.*;
import java.util.*;
public class Main{
    public static int[] set;
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        set=new int[t];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0; i<t; i++){
            set[i]=Integer.parseInt(st.nextToken());
        }
        
        int s=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        Arrays.sort(set);
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<s; i++){
            int tmp=Integer.parseInt(st.nextToken());
            sb.append(upper(tmp)-under(tmp)).append(" ");
        }
        System.out.print(sb);
    }
    public static int under(int t){
        int lo=0;
        int hi=set.length;
        while(lo<hi){
            int mid=(lo+hi)/2;
            
            if(t<=set[mid]){
                hi=mid;
            }
            else{
                lo=mid+1;
            }
        }
        return lo;
    }
     public static int upper(int t){
        int lo=0;
        int hi=set.length;
        while(lo<hi){
            int mid=(lo+hi)/2;
            
            if(t<set[mid]){
                hi=mid;
            }
            else{
                lo=mid+1;
            }
        }
        return lo;
    }
}