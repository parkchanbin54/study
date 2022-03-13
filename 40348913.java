import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        
        int[] out=new int[N+1];
        ArrayList<Integer> seq=new ArrayList<Integer>();
        
        for(int i=0; i<N+1; i++){
            out[i]=0;
        }
        int j=1;            
        for(int i=0; i<N; i++){
            int count=0;

            while(true){
                if(j==N+1){
                    j=1;
                }
                if(out[j]==0){
                    count++;
                }
                if(count==K){
                    count=0;
                    seq.add(j);
                    out[j]=1;
                    break;   
                }
                j++;
            }
        }
        System.out.print("<");
        for(int i=0; i<N-1; i++){
            System.out.print(seq.get(i)+", ");   
        }
        System.out.print(seq.get(N-1)+">");
}
}