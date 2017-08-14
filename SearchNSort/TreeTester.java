import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class TreeTester
{
   public static void main()
   {
       TreeNode root = createTree(); 
       printTree(root);
   }

   public static TreeNode createTree()
   {
       String input = JOptionPane.showInputDialog("Enter Numbers for Tree: ");
       StringTokenizer token = new StringTokenizer(input);
       
       int nValue = 0, tValue, pValue;
       
       TreeNode t,      //t -> trailer
                p,      //p -> current pointer
                n;      //n -> new TreeNode

       TreeNode root = initNode(token.nextToken());
       
       while(token.hasMoreTokens())
       {
           n = initNode(token.nextToken());
           //
           
           //2. Traverse list to find location:
           //       p will become null
           //       t will trail one node behind
           
           p= root;
           t = p;
           while( p!= null )
           {
                nValue = Integer.parseInt((String)n.getValue());
                pValue = Integer.parseInt((String)p.getValue());
             
                if(nValue > pValue )
                  p = p.getRight();
                else
                  p = p.getLeft();
                       
                // only update trailer if !null
                if( p!=null ) 
                  t = p;
           } 
           
           tValue = Integer.parseInt((String)t.getValue());
           //3. Insert in correct location
           if( nValue < tValue )
                t.setLeft(n);
           else
                t.setRight(n);
           }
           return root;
   }
   
   public static void printTree(TreeNode root)
   {
       TreeNode p = root;
       
       if( p!=null )
       {
           printTree(p.getLeft()); //L
           System.out.print(Integer.parseInt((String)p.getValue())+" "); //N
           printTree(p.getRight()); //P
        }   
           
    }
    
   public static TreeNode initNode(Object obj)
   {
       return (new TreeNode(obj,null,null));
   }
}