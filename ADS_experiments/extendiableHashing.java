

package org.yourorghere;
import java.util.Scanner;

/**
 *
 * @author theVirtualInk_HP
 */
public class extendiableHashing {
       
       static int[] hashSet= {4068,1752,3429,2130,2854,1591,2203,1423,2333,4876};
       int rehash=0;
       static int key, gainfactor;
       public static void main(String args[])
       {
           
       extendiableHashing ex= new extendiableHashing();
           Scanner sc = new Scanner(System.in);
           
           System.out.println("ENTER THE KEY");
           key= sc.nextInt(); 
           
           System.out.println("ENTER THE GAIN FACTOR");
           gainfactor = sc.nextInt();
       ex.hash();
       
       
    }
 void hash(){
     int val;
     int bitValue=0;
           String[] binary= new String[13];
           String[] weight =new String[13];
           String[][] hashTable;
           
          bitValue= (int) Math.pow(2,gainfactor);
          System.out.println("GAINFACTOR "+gainfactor+" BITVALUE"+bitValue);
           hashTable= new String[bitValue+1][bitValue+1];
           for(int i=0;i<hashSet.length; i++)
           {
               val= hashSet[i]%key;
               binary[i] = Integer.toBinaryString(val);
               weight[i] = binary[i].substring(0, gainfactor);
           }
           
           for(int i=0;i<bitValue;i++)
           {
               hashTable[0][i]= Integer.toBinaryString(i);
               
           }
           
           for(int i=1;i<=bitValue;i++)
           {
               for(int j=0;j<bitValue;j++)
               {
                   hashTable[i][j]=""+-1;
               }
           }
           
           
           int rowIndex=0;
           String check=""+-1;
           int allotCount=0;
           System.out.println("============ ALLOCATION PASS 1 RESULT ============ ");
           for(int i=0;i<bitValue;i++){
               for(int j=0;j<hashSet.length;j++)
            
              if(weight[j].equals( hashTable[rowIndex][i]))
              {
                  for(int k=1;k<=4;k++)
                  {
                      if(hashTable[rowIndex+k][i].equals(check))
                      {
                            hashTable[rowIndex+k][i]= ""+hashSet[j];
                            
                            System.out.println(hashSet[j]+" ALLOCATED AT "+weight[j]);   
                            allotCount++;
                            break;
                      }
                      
                  }
              }    
           }
           
           for(int i=0;i<=bitValue;i++)
           {
               for(int j=0;j<bitValue;j++)
               {
                   System.out.print(hashTable[i][j]+"\t");
               }
               System.out.print("\n");
           }
       System.out.println("ALLOCATION_COUNT :"+allotCount); 
       
       if(allotCount<hashSet.length)
       {
           System.out.println(hashSet.length-allotCount+" Dataset yet to be allocated! \nRehashing the tables.");
       }
       gainfactor++;
       bitValue= (int) Math.pow(2,gainfactor);
       hashTable= new String[bitValue+1][bitValue+1];
       
       for(int i=4;i<bitValue;i++){
               hashTable[4][i]= Integer.toBinaryString(i);
           }
       
       for(int i=5;i<=bitValue;i++)
           {
               for(int j=4;j<bitValue;j++)
               {
                   hashTable[i][j]=""+-1;
               }
           }
       System.out.println("Rehashed Buckets");
       
       
            rowIndex=4;
            check=""+-1;
            allotCount=0;
            for(int i=0;i<hashSet.length; i++)
           {
               weight[i] = binary[i].substring(0, gainfactor); 
           }
            System.out.println("============ ALLOCATION PASS 2 RESULT ============ ");
           for(int i=4;i<=bitValue;i++){
               for(int j=0;j<hashSet.length;j++)
              if(weight[j].equals( hashTable[rowIndex][i]))
              {
                  for(int k=1;k<=4;k++)
                  {
                      if(hashTable[rowIndex+k][i].equals(check))
                      {
                            hashTable[rowIndex+k][i]= ""+hashSet[j];
                            
                            System.out.println(hashSet[j]+" ALLOCATED AT "+weight[j]);   
                            allotCount++;
                            break;
                      }
                      
                  }
              }
           }
           for(int i=4;i<=bitValue;i++)
           {
               for(int j=4;j<bitValue;j++)
               {
                   System.out.print(hashTable[i][j]+"\t");
               }
               System.out.print("\n");
           }
           System.out.println("ALLOCATION_COUNT:"+allotCount); 
          
           System.out.println("Hashing completed !");
       
}

}