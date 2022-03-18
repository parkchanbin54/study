import java.io.*;
import java.util.*;
public class Main{
    public static void main (String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int t=Integer.parseInt(br.readLine());
        int[] arr=new int[t];
        
        for(int i=0; i<t; i++) {
        	arr[i]=Integer.parseInt(br.readLine());
        }
        
        int[] out=new int[t];
        
        Stack<Integer> st=new Stack<Integer>();
        StringBuilder sb=new StringBuilder();
        boolean flag=true;
        int count=0;
        for(int i=0; i<t; i++) {
        	while(arr[i]>count) {
        		st.add(count+1);
        		sb.append("+").append("\n");
        		count++;
        	}
        	if(st.peek()!=arr[i]) {
        		System.out.println("NO");
        		flag=false;
        		break;
        	}
        	st.pop();
        	sb.append("-").append("\n");
        }
        
        if(flag)
        	System.out.print(sb);
       
    }
}