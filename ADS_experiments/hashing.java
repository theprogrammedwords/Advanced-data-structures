/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;


public class hashing {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList data=new ArrayList();
        
        System.out.println("Enter Table Values | | (-1) to exit");
        int key;
        while(true){
           key=sc.nextInt();
           if(key==-1)
               break;
           data.add(key);
        }  
         int ch;
    do{
        System.out.println("\n 1.Linear Hash\n 2.QUADRATIC Hash\n 3.DOUBLE HASH\n");
        ch=sc.nextInt();
        switch(ch)
        {
            case 1:System.out.print("***************LINEAR HASHING***************");
                  LinearHashing lh=new LinearHashing();
                  lh.linearProbing(data);
                  lh.displayTable();
                  break;
            case 2: System.out.print("***************QUADRATIC HASHING***************");
                    QuadraticHashing qh=new QuadraticHashing();
                    qh.quadraticProbing(data);
                    qh.displayTable();
                    break;
            case 3: System.out.print("***************DOUBLE HASHING******************");
                     DoubleHashing dh=new DoubleHashing();
                     dh.doubleHashing(data);
                     dh.displayTable();
                break;
        }
    }while(ch!=0);
      
    }
}
class LinearHashing{
    int table[][]=new int[11][2];
    int collision;
    float loadFactor;
    LinearHashing(){
        this.collision=0;
        this.loadFactor=0;
        for(int i=0;i<11;i++){
            table[i][0]=i;
            table[i][1]=-1;
        }
    }
    void linearProbing(ArrayList<Integer> data){
        int hashValue=0;
        for(int i=0;i<data.size();i++){
            hashValue=data.get(i)%11;
            if(this.table[hashValue][1]==-1)
                table[hashValue][1]=data.get(i);
            else{                
                while(this.table[hashValue][1]!=-1){
                    hashValue++;
                    if(hashValue>10)
                        hashValue=0;
                    collision++;
                }
                table[hashValue][1]=data.get(i);
            }       
        }
        loadFactor=(float)data.size()/11;
    }
    void displayTable(){
        for(int i=0;i<11;i++){
            System.out.println("");
            System.out.print(" "+table[i][0]+":");
            if(table[i][1]!=-1)
                System.out.print(table[i][1]);
        }
        System.out.println("");
        System.out.println("Load Factor:"+this.loadFactor);
        System.out.println("No .Of Collisions "+this.collision);
    }
}
class QuadraticHashing{
    int table[][]=new int[11][2];
    int collision;
    float loadFactor;
    QuadraticHashing(){
        this.collision=0;
        this.loadFactor=0;
        for(int i=0;i<11;i++){
            this.table[i][0]=i;
            this.table[i][1]=-1;
        }
    }
    void quadraticProbing(ArrayList<Integer> data){
        int hashValue=0;
        for(int i=0;i<data.size();i++){
            hashValue=data.get(i)%11;
            if(this.table[hashValue][1]==-1)
                this.table[hashValue][1]=data.get(i);
            else{                
                int k=1;
                while(this.table[hashValue][1]!=-1){
                    hashValue=(hashValue+k*k)%11;
                    k++;
                    collision++;
                }
                this.table[hashValue][1]=data.get(i);
            }
                
        }
        loadFactor=(float)data.size()/11;
    }   
    void displayTable(){
        for(int i=0;i<11;i++){
            System.out.println("");
            System.out.print(" "+this.table[i][0]+":>");
            if(table[i][1]!=-1)
                System.out.print(this.table[i][1]);
        }
        System.out.println("");
        System.out.println("Load Factor:"+this.loadFactor);
        System.out.println("No .Of Collisions "+this.collision);
    }
    
}
class DoubleHashing{
    int table[][]=new int[11][2];
    int collision;
   float loadFactor;
    DoubleHashing()
    {
        this.collision=0;
        this.loadFactor=(float) 0.0;
        for(int i=0;i<11;i++){
            this.table[i][0]=i;
            this.table[i][1]=-1;
        }
    }
    void doubleHashing(ArrayList<Integer> data){
        int hashValue=0;
        for(int i=0;i<data.size();i++){
            hashValue=data.get(i)%11;
            if(this.table[hashValue][1]==-1)
                this.table[hashValue][1]=data.get(i);
            else{                
                int k=1;
                while(this.table[hashValue][1]!=-1){
                    hashValue=(data.get(i)+(k*(7-(data.get(i)%7))))%11;
                    k++;
                    collision++;
                }
                this.table[hashValue][1]=data.get(i);
            }
                
        }
        loadFactor=(float)data.size()/11;
    }   
    void displayTable(){
        for(int i=0;i<11;i++){
            System.out.println("");
            System.out.print(" "+this.table[i][0]+":");
            if(table[i][1]!=-1)
                System.out.print(this.table[i][1]);
        }
        System.out.println("");
        System.out.println("Load Factor:"+this.loadFactor);
        System.out.println("No .Of Collisions "+this.collision);
    }
}

