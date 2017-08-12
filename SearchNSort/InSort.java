
public class InSort
{
    public static void main()
    {
        int[] arr=createArr();
        System.out.println("Before Sorting:");
        for(int val:arr)
            System.out.print(val+"  ");
        sort(arr);
        System.out.println("\nAfter Sorting:");
        for(int val:arr)
            System.out.print(val+"  ");
    }
    
    public static int[] sort(int[] a)
    {
        for(int i=1;i<a.length;i++)
        {
            int small=a[i];
            int j=i;
            while(j>0&&a[j-1]>small)
            {
                a[j]=a[j-1];
                j--;
            }
            a[j]=small;
        }
        return a;
    }
    
    public static int[] createArr()
    {
        int[]B=new int[100000];
        for (int i = 0; i < B.length; i++) {
            B[i] = (int) (Math.random() * 100);
        }
        return B;
    }
}
