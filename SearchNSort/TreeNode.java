public class TreeNode
{
    private Object value;
    private TreeNode left,right;
    
    public TreeNode()
    {
        value = null;
        left = null;
        right = null;
    }
    public TreeNode(Object initValue, TreeNode l, TreeNode r)
    {
        value = initValue;
        left = l;
        right = r;
    }
    
    //=========================================================
    public Object getValue()
    { 
        return value;
    }
    public TreeNode getLeft()
    { 
        return left;
    }
     public TreeNode getRight()
    { 
        return right;
    }
    
    //=========================================================
    public void setValue(Object newValue)
    {
        value = newValue;
    }
    public void setLeft(TreeNode n)
    {
        left = n;
    }
    public void setRight(TreeNode n)
    {
        right = n;
    }
}
