public class HeapTreeNode 
{
    private HeapTreeNode left;
    private HeapTreeNode right;
    private HeapTreeNode parent;
    private int value;
    private int burns;
    
    public HeapTreeNode(int num)
    {
        value = num;
    }
    
    public HeapTreeNode(int num, HeapTreeNode tn)
    {
        value = num;
        parent = tn;
    }
    
    public void setValue(int num)
    {
        value = num;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void setLeft(HeapTreeNode tn)
    {
        left = tn;
    }
    
    public void setRight(HeapTreeNode tn)
    {
        right = tn;
    }
    
    public boolean hasLeft()
    {
        return left != null;
    }
    
    public boolean hasRight()
    {
        return right != null;
    }
    
    public boolean hasParent()
    {
        return parent != null;
    }
    
    public HeapTreeNode getLeft()
    {
        return left;
    }
    
    public HeapTreeNode getParent()
    {
        return parent;
    }
    
    public HeapTreeNode getRight()
    {
        return right;
    }
    
    public void swap(HeapTreeNode tn)
    {
        int temp = tn.getValue();
        tn.setValue(getValue());
        setValue(temp);
    }
    
    public void burn(){
        if(burns==0 && right == null){
            left = null;
            burns++;
        }else if(burns==0){
            right = null;
        }else if(burns==1){
            left = null;
        }else if(burns>1){
            //results in error to let user know something failed logic-wise
            right.getValue();
        }
        burns++;
    }
    
    public boolean isHeaped()
    {
        if(right == null){
            if(left == null)
                return true;
            else
                return left.isHeaped() && value>left.getValue();
        }
        return value>left.getValue() && value>right.getValue() && left.isHeaped() && right.isHeaped();
    }
}
