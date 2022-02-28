import java.io.*;
import java.util.*;
import java.math.*;
public class Main{
    public static boolean[] prime=new boolean[10001];
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        int t=Integer.parseInt(br.readLine());
        isprime();
        for(int j=0; j<t; j++){
            int tmp=Integer.parseInt(br.readLine());
            int p=tmp/2;
            int q=tmp/2;
            while(true){
                if(!prime[p]&&!prime[q]){
                    System.out.println(p+" "+q);
                    break;
                }
                p--;
                q++;
            }
        }
    }
    public static void isprime(){
        prime[0]=prime[1]=true;
        for(int i=2; i<=Math.sqrt(prime.length); i++){
            if(prime[i]) continue;
            for(int j=i*i; j<prime.length; j+=i){
                    prime[j]=true;               
            }
        }
    }
}