import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        int N=666;
        int count=1;
        while(count!=t){
            N++;
            if(String.valueOf(N).contains("666"))
                count++;
            
        }
        System.out.print(N);
    }
}