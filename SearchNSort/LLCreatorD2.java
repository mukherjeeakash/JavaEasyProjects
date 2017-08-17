import java.util.Scanner;

public class LLCreatorD2
{
    public static void main()
    {
        Node list=createLL();
        printList(list);
    } 
    public static Node createLL()
    {
        Node list,t,n,p;
        String num;
        
        list=new Node(getNum(),null);
        
        num = getNum();
        while(!num.equals("-999"))
        {
            n=new Node(num, null);
            
            t=list;
            p=list;
            while(p!= null && Integer.parseInt(num)> Integer.parseInt((String)p.getValue()))
            {
                t=p;
                p=p.getNext();
            }       
            n.setNext(p);
            if(t==p)
                list=n;
            else
                t.setNext(n);
            num = getNum();
        }
        return list;
    }
    public static void printList(Node n)
    {
        while(n != null)
        { 
            System.out.print(n.getValue() +" ");
            n = n.getNext();
        }
        System.out.println();
    }    
    public static String getNum()
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter Num <-999 terminates: ");        
        return  in.nextLine( );
    }   
}
