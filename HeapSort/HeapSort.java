import java.lang.Math;
import java.util.*;

public class HeapSort 
{
    private static int[] nums;
    private static HeapTreeNode root;
    private static Stack<HeapTreeNode> s = new Stack<HeapTreeNode>();
    
    public static void main(String[] args)
    {
        System.out.println("How many numbers to be heap-sorted? ");
        Scanner input = new Scanner(System.in);
        generateArray(input.nextInt());
        System.out.println(printArr(nums));
        heapify();
        System.out.println(printArr(sort()));
    }
    
    public static void generateArray(int num)
    {
        nums = new int[num];
        for(int i = 0; i<num; i++)
            nums[i] = (int)(10000 * Math.random());
    }
    
    public static String printArr(int[] arr)
    {
        String line = "";
        for(int i : arr)
            line+= i + " ";
        return line;
    }
    
    public static void heapify()
    {
        int counter = 1;
        Queue<HeapTreeNode> q = new LinkedList<HeapTreeNode>();
        root = new HeapTreeNode(nums[0]);
        q.add(root);
        while(!q.isEmpty())
        {
            HeapTreeNode current = q.poll();
            
            if(counter<nums.length){
                HeapTreeNode left = new HeapTreeNode(nums[counter], current);
                current.setLeft(left);
                q.add(left);
                counter++;
            }
            
            if(counter<nums.length){
                HeapTreeNode right = new HeapTreeNode(nums[counter], current);
                current.setRight(right);
                q.add(right);
                counter++;
            }
        }
        
        Queue<HeapTreeNode> sortQ = new LinkedList<HeapTreeNode>();
        sortQ.add(root);
        s.add(root);
        while(!sortQ.isEmpty())
        {
            HeapTreeNode current = sortQ.poll();
            HeapTreeNode max;
            HeapTreeNode parent;
            
            if(current.hasLeft()){
                sortQ.add(current.getLeft());
                s.add(current.getLeft());
                if(current.hasRight()){
                    sortQ.add(current.getRight());
                    s.add(current.getRight());
                    if(current.getLeft().getValue() > current.getRight().getValue()){
                        max = current.getLeft();
                    }else
                        max = current.getRight();
                }else
                    max = current.getLeft();
                parent = current;
            }else{
                parent = current.getParent();
                max = current;
            }
            boolean check = true;
            
            while(check && max.getValue()>parent.getValue()){
                    max.swap(parent);
                    if(parent.hasParent()){
                        max = parent;
                        parent = max.getParent();
                    }else{
                        check = false;
                        //root = max;
                    }
                    
                }
        }
    }
    
    public static int[] sort()
    {
        int[] sorted = new int[nums.length];
        
        for(int i = sorted.length-1; i >= 0; i--){
            sorted[i] = root.getValue();
            HeapTreeNode small = s.pop();
            root.swap(small);
            if(small.hasParent())
                small.getParent().burn();
            siftDown();
        }
        
        return sorted;
    }
    
    public static void siftDown()
    {
        HeapTreeNode current = root;
        HeapTreeNode max;
        
        while(current.hasLeft()){
            if(current.hasRight()){
                if(current.getLeft().getValue() > current.getRight().getValue())
                    max = current.getLeft();
                else
                    max = current.getRight();
            }else{
                max = current.getLeft();
            }
            if(max.getValue()>current.getValue())
                max.swap(current);
            current = max;
        }
    }
    
    /*public static void printTree()
    {
        while(!s.isEmpty())
            System.out.print(s.pop().getValue() + " ");
        System.out.println("\n" + root.isHeaped());
    }*/
}
