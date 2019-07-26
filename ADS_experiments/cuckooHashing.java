
/**
 * @author theVirtual_HP
 * 
 * Practical Aim : Write a program to implement Cuckoo Hashing 
 * and also detect the deadlock, if any.
 * 
 * Date of execution : 21 January 2019
 */
import java.util.Scanner;

public class cuckooHashing
{
    int tableASize=5,tableBSize=7;
    int dataSet[],tableA[],tableB[];
    int key,collCount=0;
    int dead=0;
    public cuckooHashing()
    {
        System.out.println("----------------------------------------");
        System.out.println("SIZE OF TABLE A : 5 \nSIZE OF TABLE B : 7 "
                + "\nHash Function A: Mod(5)\nHash Function A: Mod(7)");
        System.out.println("----------------------------------------");
        
        System.out.println("====== ENTER THE NUMBER OF VALUES ======");
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        System.out.println("====== ENTER THE TOTAL "+n+" VALUES ======");
        
        
        dataSet=new int[n];
        tableA=new int[5];
        tableB=new int[7]; 
        
        for(int i=0;i<7;i++)
        {
            if(i<5)
            tableA[i]=-1;
            tableB[i]=-1;
        }
       
        for(int i=0;i<n;i++){
            
            //while(dead!=1) 
            //{
                dataSet[i]=sc.nextInt(); 
                key=eval(dataSet[i]); 
            //}
            }
        
        System.out.println("====== TABLE A CONTENTS ======");//table1
        for(int i=0;i<5;i++)
        {
            System.out.println(tableA[i]);
        }
        System.out.println("====== TABLE B CONTENTS ======"); // table 2
        for(int i=0;i<7;i++)
        {
            System.out.println(tableB[i]);
        } 
        System.out.println("============ RESULT ============ ");
        System.out.println("\nNUMBER OF COLLISIONS ENCOUNTERED ->"+collCount);
        if(dead==0)
        {
            System.out.println("\nNO DEADLOCK ENCOUNTERED."+"\n"+ "TRY SOME OTHER DATA SET !");
        }
        
    }
    int hash1(int k)
    {
        
        k%=tableASize;
        return k;
    }
    int hash2(int k)
    {
        k%=tableBSize;
        return k;
    }
    int eval(int k)
    {
        int p=hash1(k);
        
        if(tableA[p]==-1) 
        {
         
            tableA[p]=k;
            return 1;
        }
        else{
            
            collCount+=1;
            int t=tableA[p];
            
            tableA[p]=k;
            
            int q=hash2(t);
            
            if(tableB[q]==-1)
            {
                tableB[q]=t;
                return 1;
            }
            else
                
                collCount+=1;
                if(k==tableB[q])
                {    System.out.println("DEADLOCK ENCOUNTERED WITH VALUES -> "+k+","+t);
                    dead=1;    
                    return -1;
                }
                k=tableB[q];
                //reassigning temp values 
                tableB[q]=t;
                eval(k);
        }
        return 0;
    }
}
class demo
{
    public static void main(String[] args) 
    {
        cuckooHashing h = new cuckooHashing();
       
    }
}
