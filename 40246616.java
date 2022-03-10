import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        Stack<Integer> sta=new Stack<>();
        StringTokenizer st;
        for(int i=0; i<t; i++){
            st=new StringTokenizer(br.readLine());
            String tmp=st.nextToken();
            if(tmp.equals("push")){
                int tt=Integer.parseInt(st.nextToken());
                sta.push(tt);
            }
            else if(tmp.equals("top")){
                if(sta.size()==0){
                    System.out.println(-1);
                }
                else{
                    int tmp2=sta.pop();
                    System.out.println(tmp2);
                    sta.push(tmp2);
                }
            }
            else if(tmp.equals("size")){
                System.out.println(sta.size());
            }
            else if(tmp.equals("empty")){
                if(sta.size()==0){
                    System.out.println(1);
                }
                else{
                    System.out.println(0);
                }
            }
            else if(tmp.equals("pop")){
                 if(sta.size()==0){
                    System.out.println(-1);
                }
                else{
                System.out.println(sta.pop());
                }
            }
        }
    }
}