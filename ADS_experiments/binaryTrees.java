/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.Scanner;

public class binaryTrees {

    
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter numbers of elements");
        int n=sc.nextInt();
        System.out.println("Enter numbers for binary tree");
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        
        int[][] adm=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                adm[i][j]=0;
            }
        }
        
        adm[0][0]=1;
        int j=1;
        for(int k=0;k<n;k++)
        {
            if(j>=n )
            {
                break;
            }
  
            adm[k][j]=1;adm[j][k]=1;
            if(j+1<n)
            {
            adm[k][j+1]=1;adm[j+1][k]=1;
            }
            j=j+2;
        }
        
        for(int l=0;l<n;l++)
        {
            System.out.println("");
            for(int m=0;m<n;m++)
            {
                System.out.print(" "+adm[l][m]);
            }
        }
        System.out.println("");
        
        int count=0;
        j=1;
        for(int i=0;i<n;i++)
        {
            if(j>=n)
            {
                break;
            }
            
            if(adm[i][j]!=0)
            {
                count=count+1;       
            } 
            
           j=j+2;
        }
        count=n-count;
       
        System.out.println("No of leaf nodes: "+count);
        
    }
    
}
