import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
       
        
        for(int i=0; i<t; i++){
            String tmp=br.readLine();
            int open=0;
            for(int j=0; j<tmp.length(); j++){
                if(tmp.substring(j,j+1).equals("(")){
                    open++;
                }
                else if(tmp.substring(j,j+1).equals(")")){
                    if(open<=0){
                        open--;
                        break;
                    }
                    open--;
                }
                
        }
        if(open==0){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
        }
        
    }
}