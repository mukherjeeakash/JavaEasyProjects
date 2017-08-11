import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Paths;

public class Inventory
{
    private ArrayList<Movie> movies=new ArrayList<Movie>();
    private Scanner inF;
    private final String LOC=Paths.get(".").toAbsolutePath().normalize().toString();
    private final String INVENTORY_FILENAME = LOC+"\\movielist.txt";
    public Inventory() throws IOException, FileNotFoundException
    {
        inF = new Scanner(new File(INVENTORY_FILENAME));
    }
    
    public void fillStock()
    {
        while(inF.hasNextLine())
        {
            movies.add(new Movie(inF.nextInt(), inF.nextInt(), inF.next(), inF.nextLine()));
        }
        inF.close();
    }
    
    public Movie getMovie(int id)
    {
        for(Movie m:movies)
            if(m.getId()==id)
                return m;
        return null;
    }
    
    public boolean addMovie(String name, int stock, String rating)
    {
        name="\t"+name;
        for(Movie m:movies)
            if(m.getName().equalsIgnoreCase(name))
                return false;
        movies.add(new Movie(movies.get(movies.size()-1).getId()+1, stock, rating, name));
        return true;
    }
    
    public boolean deleteMovie(String name)
    {
        name="\t"+name;
        for(Movie m:movies)
            if(m.getName().equalsIgnoreCase(name))
                {
                    movies.remove(m);
                    return true;
                }
        return false;
    }
    
    public void save() throws IOException, FileNotFoundException
    {
        FileWriter writer = new FileWriter(INVENTORY_FILENAME);
        PrintWriter outF = new PrintWriter(writer);
        for(int i=0;i<movies.size();i++)
        {
            outF.println(movies.get(i).getId()+" "+movies.get(i).getStock()+" "+movies.get(i).getRate()+movies.get(i).getName());
        }
        outF.close();
    }
    
    public String search(String entry)
    {
        String j="";
        for(Movie m:movies)
            if(m.getName().toUpperCase().indexOf(entry.toUpperCase())!=-1)
                j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
        return j;
    }
    
    public String search(String entry, String rating)
    {
        ArrayList<Movie> rateDVD=new ArrayList<Movie>();
        for(Movie m:movies)
            if(m.getRate().equals(rating))
                rateDVD.add(m);
        
        String j="";
        for(Movie m:rateDVD)
        {
            if(m.getName().toUpperCase().indexOf(entry.toUpperCase())!=-1)
            {
                if(m.getRate().equals("G")||m.getRate().equals("R"))
                    j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
                else if(m.getRate().equals("PG"))
                    j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
                else 
                    j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
            }
        }
        return j;
    }
}
