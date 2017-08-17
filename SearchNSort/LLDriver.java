import java.util.Scanner;

public class LLDriver
{
    public static void main()
    {
        Node list,n,t;
        String num;
        
        list=new Node(getNum(),null);
        t=list;
        
        num=getNum();
        while(!num.equals("-999"))
        {
            n=new Node(num,null);
            t.setNext(n);
            t=n;
            num=getNum();
        } 
        printList(list);
    }
    
    public static String getNum()
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter Num <-999> Terminates:");
        return in.nextLine();
    }
    
    public static void printList(Node p)
    {
        while(p!=null)
        {
            System.out.print(p.getValue()+" ");
            p=p.getNext();
        }
    }
}
