import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for(int i=0; i<t; i++){
            st=new StringTokenizer(br.readLine());
            int[] tmp1=new int[3];
            int[] tmp2=new int[3];
            int count=0;
            
            for(int j=0; j<3; j++){
                tmp1[j]=Integer.parseInt(st.nextToken());
            }
            
            for(int j=0; j<3; j++){
                tmp2[j]=Integer.parseInt(st.nextToken());
            }
            System.out.println(result(tmp1,tmp2));
        }
    }
                            
    public static int result(int t1[],int t2[]){
        int dis=(int)(Math.pow(t2[0]-t1[0],2)+Math.pow(t2[1]-t1[1],2));
        
        if(t1[0]==t2[0]&&t1[1]==t2[1]&&t1[2]==t2[2]){
            return -1;
        }
        else if(dis>Math.pow(t1[2]+t2[2],2)){
            return 0;
        }
        else if(dis==Math.pow(t1[2]+t2[2],2)){
            return 1;
        }
        else if(dis<Math.pow(t1[2]-t2[2],2)){
            return 0;
        }
        else if(dis==Math.pow(t1[2]-t2[2],2)){
            return 1;
        }                        
        else{
            return 2;
        }
      }
  }
               