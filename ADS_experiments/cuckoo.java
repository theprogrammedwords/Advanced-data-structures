
import java.util.Scanner;

public class cuckoo {
      
    public static void main(String args[])
    {
        int data[] = null, hash[],key;
        int cp[]=null;
         int h,x,f, probe;       ;
        
        Scanner sc = new Scanner(System.in);
        hash = new int[data.length];
        
        System.out.println("Enter the data");
        for(int i=0;i<hash.length;i++)
        {
            hash[i] = -1;
            cp[i]=0;  
        }
        
        for(int i=0;i<data.length;i++)
        {
            if(data[i]!=999)
            {
                x = data[i];
                probe = 1;
                for(int j=0; j<data.length; j++)
                {
                    f = j;
                    h = (x+f)%data.length;
                    if(hash[h]==-1)
                    {
                        hash[h] = x;
                        cp[h] = probe;
                        break;
                    }
                    probe++;
                }
            }
    }
    
        for(int i=0; i<data.length;i++)
        {
            
        System.out.println(cp[i]);
        }
}
}
