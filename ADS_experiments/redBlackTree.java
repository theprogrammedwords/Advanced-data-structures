/*
*   NAME : ASHISH PRASAD
    ROLL NO : 50 ( 6B )
   
    ----------------------------------------------------------------------------
    PROBLEM STATEMENT : 
    ----------------------------------------------------------------------------
    CONSIDER A REDBLACK TREE WITH 5 NODES.
    PERFORM THE FOLLOWING OPERATIONS.
    
    >>  INSERT KEY 20
    >>  INSERT KEY 16
    >>  INSERT KEY 17
    >>  DELETE KEY 6
    >>  DELETE KEY 4
    >>  DELETE KEY 20
    ----------------------------------------------------------------------------
         FOR EACH INSERTION OR DELETION OPERATION, 
         THERE MUST BE REPRESENTATION FOR EACH OF THE FOLLOWING :

    >>   INITIAL POSITION OF KEY
    >>   ANALYSIS WITH RESPECT TO PROPERTIES
    >>   NECESSARY ROOT AND COLOR ASSIGNMENT
    >>   FIND SHAPE OF THE TREE
    ----------------------------------------------------------------------------
    ----------------------------------------------------------------------------
*/

import java.util.Scanner;

class RBTree
{
    Nodes root = null;
    void insert(Nodes x)
    {
        
        if(root == null)
        {
            root = x;
            x.color = "B";  
        }
        else
        {
            Nodes y=null;
            Nodes z = root;
         
        
            while(z!=null)
            {
                y = z;
                if(x.data<z.data) 
                    z = z.left;
                else
                    z = z.right;
            }
            x.parent = y;
            
            if(y == null)
                root = x;
            else
            {
                if(x.data<y.data)
                    y.left = x;
                else
                    y.right = x;
            }

            x.left = null;
            x.right = null;
            x.color = "R";
            if(x.parent!=root)
                insert_fixup(x);
        }
    }
    void insert_fixup(Nodes z)
    {
        while(z!=root && z.parent.color.equals("R"))
        {
            if(z.parent==z.parent.parent.left)
            {
                Nodes y = z.parent.parent.right;
                if(y!=null && y.color.equalsIgnoreCase("R"))
                {
                    z.parent.color = "B";
                    y.color = "B";
                    z.parent.parent.color = "R";
                    z = z.parent.parent;
                }
                else
                {
                    if(z==z.parent.right)
                    {
                        z = z.parent;
                        leftrotate(z);
                    }
                    z.parent.color = "B";
                    z.parent.parent.color = "R";
                    rightrotate(z.parent.parent);
                }
                
            }
            else
            {
                Nodes y = z.parent.parent.left;
                if(y!=null && y.color.equalsIgnoreCase("R"))
                {
                    z.parent.color = "B";
                    y.color = "B";
                    z.parent.parent.color = "R";
                    z = z.parent.parent;
                }
                else
                {
                    if(z==z.parent.left)
                    {
                        z = z.parent;
                        rightrotate(z);
                    }
                    z.parent.color = "B";
                    z.parent.parent.color = "R";
                    leftrotate(z.parent.parent);
                }
                
            }
        }
        root.color = "B";
    }
    void delete(int data)
    {
        Nodes z = search(data);
        if(z==null)
            return;
        Nodes y = null, x;
        if((z.left==null)&&(z.right==null))
            y = z;
        else
            y = successor(z);
        if(y==null)
            y = predecessor(z);
        if(y.left!=null)
            x = y.left;
        else
            x = y.right;
        if(x!=null)
            x.parent = y.parent;
        if(y.parent==null)
            root=x;
        else
        {
            if(y == y.parent.left)
                y.parent.left = x;
            else
                y.parent.right = x;
        }
        if(y!=z)
            z.data = y.data;
        if(y.color.equals("B"))
            delete_fixup(x);
    }
    
    void delete_fixup(Nodes x)
    {
        Nodes w;
        while((x!=root)&&(x!=null)&&(x.color.equals("B")))
        {
            if(x == x.parent.left)
            {
                w = x.parent.right;
                if(w!=null)
                {
                    if(w.color.equals("R"))
                    {
                        w.color = "B";
                        x.parent.color = "R";
                        leftrotate(x.parent);
                        w = x.parent.right;
                    }
                    if(((w.left==null)||(w.left.color.equals("B")))&&((w.right==null)||(w.right.color.equals("B"))))
                    {
                        w.color = "R";
                        x = x.parent;
                    }
                    else
                    {
                        if((w.right==null)||(w.right.color.equals("B")))
                        {
                            if(w.left!=null)
                                w.left.color = "B";
                            w.color = "R";
                            rightrotate(w);
                            w = x.parent.right;
                        }
                        w.color = x.parent.color;
                        x.parent.color = "B";
                        w.right.color = "B";
                        leftrotate(x.parent);
                        x = root;
                    }
                }
            }
            else
            {
                w = x.parent.left;
                if(w!=null)
                {
                    if(w.color.equals("R"))
                    {
                        w.color = "B";
                        x.parent.color = "R";
                        rightrotate(x.parent);
                        w = x.parent.left;
                    }
                    if(((w.right==null)||(w.right.color.equals("B")))&&((w.left==null)||(w.left.color.equals("B"))))
                    {
                        w.color = "R";
                        x = x.parent;
                    }
                    else
                    {
                        if((w.left==null)||(w.left.color.equals("B")))
                        {
                            if(w.right!=null)
                                w.right.color = "B";
                            w.color = "R";
                            leftrotate(w);
                            w = x.parent.left;
                        }
                        w.color = x.parent.color;
                        x.parent.color = "B";
                        w.left.color = "B";
                        rightrotate(x.parent);
                        x = root;
                    }
                }
            }
        }
        if(x!=null)
            x.color = "B";
    }
    public Nodes search(int data)
    {
        Nodes n = root;
        while(n != null)
        {
            if(n.data==data)
                return n;
            else
            {
                if(data<n.data)
                    n = n.left;
                else
                    n = n.right;
            }
        }
        System.out.println("Data not found");
        return null;
    }
    public Nodes successor(Nodes n)
    {
        Nodes m = root;
        if(n.right!=null)
        {
            Nodes c = n.right;
            while(c.left!=null)
                c = c.left;
            return c;
        }
        else
        {
            Nodes p = n.parent;
            while(p!=null&&(n==p.right))
            {
                n = p;
                p = p.parent;
            }
            return p;
        }
    }
    
     public Nodes predecessor(Nodes n)
    {
        Nodes m = root;
        if(n.left!=null)
        {
            Nodes c = n.left;
            while(c.right!=null)
                c = c.right;
            return c;
        }
        else
        {
            Nodes p = n.parent;
            while(p!=null&&(n==p.left))
            {
                n = p;
                p = p.parent;
            }
            return p;
        }
    }

       public void leftrotate(Nodes x)
    {
        Nodes y = x.right;
        x.right = y.left;
        if(y.left!=null)
            y.left.parent = x;
        y.parent = x.parent;
        if(x.parent == null)
            root = y;
        else
        {
            if(x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    public void rightrotate(Nodes x)
    {
        Nodes y = x.left;
        x.left = y.right;
        if(y.right!=null)
            y.right.parent = x;
        y.parent = x.parent;
        if(x.parent == null)
            root = y;
        else
        {
            if(x == x.parent.right)
                x.parent.right = y;
            else
                x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
     public void print(Nodes n)
    {
        if(n==null)
            System.out.println("Empty tree");
        else
        {
            if(n.parent!=null)
                System.out.println("Node: "+n.data+" Color: "+n.color+" Parent: "+n.parent.data);
            else
                System.out.println("Node: "+n.data+" Color: "+n.color+" This is root node");    
            if(n.left!=null)
                print(n.left);
            if(n.right!=null)
                print(n.right);
        }
    }
     public void print_node(Nodes n)
    {
        if(n.parent!=null)
            System.out.println("Node: "+n.data+" Color: "+n.color+" Parent: "+n.parent.data);
        else
            System.out.println("Node: "+n.data+" Color: "+n.color+" This is root node");
    }
}
class Nodes
{
    int data;
    String color;
    Nodes left;
    Nodes right;
    Nodes parent;
    public Nodes(int data)
    {
        this.data = data;
        this.color = "R";
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
public class redBlackTree {
      public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        RBTree rb = new RBTree();
        Nodes a = new Nodes(6);
        Nodes b = new Nodes(4);
        Nodes c = new Nodes(7);
        Nodes d = new Nodes(2);
        Nodes e = new Nodes(5);
        Nodes f = new Nodes(20);
        Nodes g = new Nodes(16);
        Nodes h = new Nodes(17);
        rb.insert(a);
        rb.insert(b);
        rb.insert(c);
        rb.insert(d);
        rb.insert(e);
        
        System.out.println("Inserting nodes 20, 16, 17");
        rb.insert(f);
        rb.insert(g);
        rb.insert(h);
        rb.print(rb.root);
        
        System.out.println("Deleting 6:");
        rb.delete(6);
        rb.print(rb.root);
        
        System.out.println("Deleting 4:");
        rb.delete(4);
        rb.print(rb.root);
        
        System.out.println("Deleting 20:");
        rb.delete(20);
        rb.print(rb.root);
    }
    
}