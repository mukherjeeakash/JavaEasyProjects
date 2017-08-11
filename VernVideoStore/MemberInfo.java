import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;

public class MemberInfo
{
    private ArrayList<Member> members;
    private final String LOC=Paths.get(".").toAbsolutePath().normalize().toString();
    
    public MemberInfo()
    {
        members=new ArrayList<Member>();
    }
    
    public void enterInfo() throws IOException,FileNotFoundException
    {
        Scanner inF=new Scanner(new File(LOC+"\\memberlist.txt"));
        
        while(inF.hasNextLine())
        {
            ArrayList<RentedMovie> cRent=new ArrayList<RentedMovie>();
            ArrayList<Movie> pRent=new ArrayList<Movie>();
        
            members.add(new Member(inF.nextLine(),Integer.parseInt(inF.nextLine())));
            int j=Integer.parseInt(inF.nextLine());
            for(int i=0;i<j;i++)
            {
                cRent.add(new RentedMovie(inF.nextInt(),inF.nextInt(),inF.next(),inF.nextLine()));
            }
            members.get(members.size()-1).setCurrentMovies(cRent);
            int k=Integer.parseInt(inF.nextLine());
            for(int i=0;i<k;i++)
            {
                pRent.add(new Movie(inF.nextInt(),inF.nextInt(),inF.next(),inF.nextLine()));
            }
            members.get(members.size()-1).setPastMovies(pRent);
            inF.nextLine();
        }
    }
    
    public String printMembers()
    {
        String j="";
        for(Member m:members)
        {
            if(m.getName().length()<15)
                j+=m.getName()+"\t\t"+m.getId()+"\n";
            else
                j+=m.getName()+"\t"+m.getId()+"\n";
        }
        
        return j;
    }
    
    public Member getMember(int id)
    {
        for(Member m:members)
            if(m.getId()==id)
                return m;
        return null;
    }
    
    public Member getMember(String name)
    {
        for(Member m:members)
            if(m.getName().equalsIgnoreCase(name))
                return m;
        return null;
    }
    
    public int addMember(String n)
    {
        int i=members.get(members.size()-1).getId()+1;
        members.add(new Member(n, i));
        return i;
    }
    
    public boolean deleteMember(int id)
    {
        for(Member m:members)
            if(m.getId()==id)
            {
                members.remove(m);
                return true;
            }
        return false;
    }
    
    public void save() throws IOException, FileNotFoundException
    {
        FileWriter writer = new FileWriter(LOC+"\\memberlist.txt");
        PrintWriter outF = new PrintWriter(writer);
        for(int i=0;i<members.size();i++)
        {
            Member m=members.get(i);
            outF.println(m.getName());
            outF.println(m.getId());
            outF.println(m.rentedMoviesNum());
            outF.print(m.printCurrentMovies());
            outF.println(m.pastMoviesNum());
            outF.println(m.printPastMovies());
        }
        outF.close();
    }
}