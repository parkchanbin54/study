import java.io.*;
import java.util.*;
public class Main{
    public static boolean[] arr=new boolean[246913];
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        prime();
        while(true){
            int t=Integer.parseInt(br.readLine());
            int count=0;
            if(t==0){
                break;
            }
            for(int i=t+1; i<=t*2; i++){
                if(!arr[i]){
                    count++;
                }
            }
            System.out.println(count);
        }
        
    }
    public static void prime(){
        arr[0]=arr[1]=true;
        for(int i=2; i<=Math.sqrt(arr.length); i++){  
            if(arr[i])continue;   
            for(int j=i*i; j<arr.length; j+=i){
                arr[j]=true;
            }
        } 
        
    }
}