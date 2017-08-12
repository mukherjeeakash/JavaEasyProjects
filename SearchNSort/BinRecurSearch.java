import java.util.*;

public class BinRecurSearch
{
    public static void main(String[] args)
    {
        System.out.println("Enter Numbers for List (-999 to signal end): ");
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        while(input.hasNextInt())
        {
            int x = input.nextInt();
            if(x == -999)
            {
                break;
            }
            
            nums.add(x);
        }
        
        int[] a = new int[nums.size()];
        
        for (int i = 0; i < a.length; i++) {
            a[i] = (int)nums.get(i);
        }
        
        Arrays.sort(a);
        
        for(int i : a)
        {
            System.out.print(i + " ");
        }
        
        System.out.println("\nEnter Number to Find: ");
        
        Scanner input2 = new Scanner(System.in);
        int b = input2.nextInt();
        
        System.out.println("Number Found's Index is: " + bin(a,b,0,a.length - 1));
        System.out.println("\nNote:");
        System.out.println("This program finds the closest logarithmic index if there are duplicates present. -999 result indicates not found.");
    }
    public static int bin(int[]a, int key, int f, int l)
    {
        if (f<=l){
            int m=(f+l)/2;
            if (key==a[m])
                return m;
            else if (key>a[m])
                return(bin(a,key,m+1,l));
            else
                return(bin(a,key,f,m-1));
        }
        return -999;
    }
}
