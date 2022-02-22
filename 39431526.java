import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        int count=1,prevcount=0; 
        while(true){
            if(t<=count+prevcount){
                if(count%2==0){
                    
                    System.out.print((t-prevcount)+"/"+(count-(t-prevcount-1)));
                    break;
                }
                else{
                    System.out.print((count-(t-prevcount-1))+"/"+(t-prevcount));
                    break;
                }
            }
            else{
                prevcount+=count;
                count++;
            }
        }
    }
}