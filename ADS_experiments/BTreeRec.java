

import java.util.Stack;

public class BTreeRec {
    
   
    Node rootNode;
    public BTreeRec(){
    
        rootNode = null;
    }
    
    public void insert(int val){
        if(rootNode == null){
            rootNode = new Node(val);
        }else{
            rootNode.insert(val);
        }
    }
    
    public int getLeafNodeCount(){
        return rootNode.getLeafNodeCount();
    }
    
    public int getNonLeafNodeCount(){
        return rootNode.getNonLeafNodeCount();
    }
    
    public void print(){
        if(rootNode != null)
            rootNode.print();
    }
    
    public int getHeight(){
        return rootNode.getHeight();
        
    }
    
    private static class Node{
        int val;
        Node left;
        Node right;
    
        public Node(int val){
            this.val = val;
            left = null;
            right = null;
        }
        public void insert(int val){
            if(val == this.val){
                System.out.println("Duplicates not allowed");
                return;
            }
            if(val < this.val){
                if(left != null){
                    left.insert(val);
                }else{
                    left = new Node(val);
                }
            }else if(val > this.val){
                if(right != null){
                    right.insert(val);
                }else{
                    right = new Node(val);
                }
            }
        }
        public void print(){
            System.out.print(val+" ");
            if(left != null){
                left.print();
            }
            if(right != null){
                right.print();
            }
        }
        public int getHeight(){
            int maxLeft=0,maxRight=0;
            if(left != null){
                maxLeft = left.getHeight();
            }
            if(right != null){
                maxRight = right.getHeight();
            }
            return max(maxLeft,maxRight) + 1;
        }
        public int max(int i,int j){
            if(i < j){
                return j;
            }
            return i;
        }
        public int getLeafNodeCount(){
            int leftLeafNodes=0,rightLeafNodes=0;
            if(left == null && right == null){
                return 1;
            }else{
                if(left != null){
                    leftLeafNodes = left.getLeafNodeCount();
                }
                if(right != null){
                    rightLeafNodes = right.getLeafNodeCount();
                }
            }
            return leftLeafNodes+rightLeafNodes;
        }
        public int getNonLeafNodeCount(){
            int leftNonLeafNodes=0,rightNonLeafNodes=0;
            if(left == null && right == null){
                return 0;
            }else{
                if(left != null){
                    leftNonLeafNodes = left.getNonLeafNodeCount();
                }
                if(right != null){
                    rightNonLeafNodes = right.getNonLeafNodeCount();
                }
            }
            return leftNonLeafNodes+rightNonLeafNodes+1;
        }
    }
 public static void main(String args[])
    {
        BTreeRec bt= new BTreeRec();
        
        
        bt.insert(5);
        bt.insert(3);
        bt.insert(4);
        bt.insert(1);
        bt.insert(2);  
        bt.insert(6);
        
        System.out.println("HEIGHT OF TREE "+bt.getHeight());
        System.out.println("LEAF NODE COUNT OF TREE "+bt.getLeafNodeCount());
        System.out.println("NON LEAF NODE COUNT OF TREE "+bt.getNonLeafNodeCount());
        
        
             
    }

}

/*     Tree representation :-
 * 
 *           5
 *          / \  
 *         3   6-leaf three 
 *        / \
 *        1  4- leaf two 
 *         \
 *          2- leaf one 
 */ 