import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        int[] arr=new int[t];
        int sum=0;
        for(int i=0; i<t; i++){
            arr[i]=Integer.parseInt(br.readLine());
            sum+=arr[i];
        }
        int[] tmp=arr;
        Arrays.sort(tmp);
        
        int max=0;
        int mode=10000;
        boolean flag=false;
        
        for(int i=0; i<t; i++){
            int jump=0;
            int count=1;
            int i_val=tmp[i];
            
            for(int j=i+1; j<t; j++){
                if(i_val!=tmp[j])break;
                jump++;
                count++;
            }
            if(max<count){
                flag=true;
                max=count;
                mode=i_val;
            }
            else if(flag&&max==count){             
                mode=i_val;
                flag=false;
            }
            
            i+=jump;
        }
        
        System.out.println((int)Math.round((double)sum/t));
        System.out.println(tmp[t/2]);
        System.out.println(mode);
        System.out.print(tmp[t-1]-tmp[0]);
    }
}