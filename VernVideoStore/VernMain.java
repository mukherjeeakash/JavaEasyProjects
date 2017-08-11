import java.io.*;

public class VernMain
{
    Inventory movies;
    MemberInfo members;
    Member bob;
    
    public VernMain()throws IOException,FileNotFoundException
    {
        movies=new Inventory();
        movies.fillStock();
        members=new MemberInfo();
        members.enterInfo();
    }
    
    public int addMember(String n)
    {
        return members.addMember(n);
    }
    
    public String deleteMember(int id)
    {
        if(members.deleteMember(id))
            return "Success!";
        return "Error: Member Not Found";
    }
    
    public String deleteMovie(String movie)
    {
        if(movies.deleteMovie(movie))
            return "Success!";
        return "Error: Movie Not Found";
    }
    
    public String addMovie(String name, int stock, String rating)
    {
        if(movies.addMovie(name,stock,rating))
            return "Success";
        return "Error: Movie Already In Inventory";
    }
    
    public String login(int id)
    {
        bob=members.getMember(id);
        if(bob==null)
            return "Error: Invalid ID";
        return "Success";
    }
    
    public String login(String name)
    {
        bob=members.getMember(name);
        if(bob==null)
            return "Error: Invalid Name";
        return "Success";
    }
    
    public String rent(int id)
    {
        Movie m=movies.getMovie(id);
        if(m==null)
            return "Error: Movie Not Found";
        if(!bob.rentMovie(m))
            return "Error: Out Of Stock";
        return "Success!";
    }
    
    public String returnDVD(int id)
    {
        if(!bob.returnMovie(id))
            return "Error: Movie Not Rented";
        return "Success!";
    }
    
    public String printMembers()
    {
        return members.printMembers();
    }
    
    public String printCurRent()
    {
        return bob.getCurrent();
    }
    
    public String printPastRent()
    {
        return bob.getPast();
    }
    
    public int close()throws IOException,FileNotFoundException
    {
        movies.save();
        members.save();
        return 3;
    }
    
    public String search(String entry)
    {
        return movies.search(entry);
    }
    
    public String search(String entry,String rating)
    {
        return movies.search(entry,rating);
    }
    
    public String getName()
    {
        return bob.getName();
    }
}