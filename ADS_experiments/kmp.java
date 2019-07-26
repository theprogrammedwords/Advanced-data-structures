
package org.yourorghere;
import java.util.Scanner;
/**
 *
 * @author Ashish
 */
public class kmp {
    
    public static void main(String[] args) {
    
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the main string: ");
        String string=sc.next();
        System.out.println("Enter pattern string: ");
        String pattern=sc.next();
        
        int prefixtable[] = prefix(" "+pattern);
        for(int i=0;i<pattern.length();i++)
        System.out.print(pattern.charAt(i)+"\t");
        
        System.out.println("");
        
        for(int i=1;i<prefixtable.length;i++)
            prefixtable[i-1]= prefixtable[i];
        
        for(int i=0;i<prefixtable.length-1;i++)
            System.out.print(prefixtable[i]+"\t");
        
        int i=0,j=0,cmpcount=0;       
        while(true)
        {
            if(i+1==string.length())
            {
                System.out.println("Pattern not found");
                break;
            }           
            
            if(string.charAt(i)==pattern.charAt(j))
            {
                System.out.print("\nMatch: "+string.charAt(i)+" "+pattern.charAt(j));
                if(j==pattern.length()-2)
                {
                    System.out.println("\nPattern found at location: "+(j+1));
                    break;
                }
                cmpcount++;i++;j++;
            }
            else{
                System.out.println("\nMismatch: "+string.charAt(i)+" "+pattern.charAt(j));
                if(j-1<0)
                {
                    j=0;i++;
                    continue;
                }
                j=prefixtable[j-1];                
            }
        }
        System.out.println("No of comparisons: "+cmpcount);
    }
    
    static int[] prefix(String p){
        
        int m = p.length();
        int []pi = new int[m];
        
        pi[1] = 0;
        int k=0;
        
        for(int i=2; i<m; i++)
        {
            while(k>0 && (p.charAt(k+1)!=p.charAt(i)))
            {
                k=pi[k];
            }
            if(p.charAt(k+1)==p.charAt(i))
            {
                k++;
            }
            pi[i]=k;
        }
        return pi;
    }
}
