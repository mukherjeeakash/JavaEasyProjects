
public class Node
{
    private Object value;
    private Node next;

    public Node()
    {
        value=null;
        next=null;
    }
    public Node(Object initValue,Node initNext)
    {
        value=initValue;
        next=initNext;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    public Node getNext()
    {
        return next;
    }
    
    public void setValue(Object i)
    {
        value=i;
    }
    
    public void setNext(Node i)
    {
        next=i;
    }
}
