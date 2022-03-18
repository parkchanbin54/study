import java.io.*;
import java.util.*;
public class Main{
    public static void main (String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        Stack<Integer> st=new Stack<Integer>();        
        int t=Integer.parseInt(br.readLine());
        int sum=0;
        for(int i=0; i<t; i++) {
        	int tmp=Integer.parseInt(br.readLine());
        	if(tmp==0) {
        		sum=sum-st.pop();
        	}
        	else {
        		sum+=tmp;
        	 	st.add(tmp);	       
        	}
        }
        
       System.out.println(sum);
    }
}