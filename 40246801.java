import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        Queue<Integer> sta=new LinkedList<>();
        int last=0;
        StringTokenizer st;
        for(int i=0; i<t; i++){
            st=new StringTokenizer(br.readLine());
            String tmp=st.nextToken();
            if(tmp.equals("push")){
                int tt=Integer.parseInt(st.nextToken());
                last=tt;
                sta.offer(tt);
            }
            else if(tmp.equals("front")){
                if(sta.size()==0){
                    System.out.println(-1);
                }
                else{
                    System.out.println(sta.peek());
                }
            }
            else if(tmp.equals("back")){
                if(sta.size()==0){
                    System.out.println(-1);
                }
                else{
                    System.out.println(last);
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
                System.out.println(sta.poll());
                }
            }
        }
    }
}