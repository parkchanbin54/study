import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            String tt=br.readLine();
            if(tt.equals("0 0 0"))
                break;
            st=new StringTokenizer(tt);
         
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            tmp.add(Integer.parseInt(st.nextToken()));
            tmp.add(Integer.parseInt(st.nextToken()));
            tmp.add(Integer.parseInt(st.nextToken()));
            Collections.sort(tmp);
            if(tmp.get(2)==Math.sqrt(Math.pow(tmp.get(0),2)+Math.pow(tmp.get(1),2)))
                System.out.println("right");
            else
                System.out.println("wrong");
        }
    }
}