import java.util.ArrayList;

public class Member
{
    private ArrayList<Movie> pastRental=new ArrayList<Movie>();
    private ArrayList<RentedMovie> currentRental=new ArrayList<RentedMovie>();
    private String name;
    private int idNum;
    public Member(String n, int id)
    {
        name=n;
        idNum=id;
    }
    
    public boolean rentMovie(Movie m)
    {
        if(m.rent())
        {
            currentRental.add(new RentedMovie(m.getId(), m.getStock(), m.getRate(), m.getName()));//possible date insertion
            return true;
        }
        return false;
    }
    
    public void setCurrentMovies(ArrayList<RentedMovie> m)
    {
        currentRental=m;
    }
    
    public void setPastMovies(ArrayList<Movie> m)
    {
        pastRental=m;
    }
    
    public boolean returnMovie(int n)
    {
        for(int i=0;i<currentRental.size();i++)
        {
            RentedMovie m=currentRental.get(i);
            if(m.getId()==n)
            {
                m.returnDVD();
                currentRental.remove(i);
                pastRental.add(new Movie(m.getId(), m.getStock(), m.getRate(), m.getName()));
                return true;
            }
        }
        return false;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getId()
    {
        return idNum;
    }
    
    public int rentedMoviesNum()
    {
        return currentRental.size();
    }
    
    public int pastMoviesNum()
    {
        return pastRental.size();
    }
    
    public String getCurrent()
    {
        String j="";
        for(RentedMovie m:currentRental)
        {
            j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
        }
        return j;
    }
    
    public String getPast()
    {
        String j="";
        for(Movie m:pastRental)
        {
            j+="ID: "+m.getId()+"\tStock: "+m.getStock()+"\tRating:  "+m.getRate()+"\tTitle: "+m.getName()+"\n";
        }
        return j;
    }
    
    public String printCurrentMovies()
    {
        String j="";
        for(int i=0;i<currentRental.size();i++)
        {
            j+=currentRental.get(i).getId()+" "+currentRental.get(i).getStock()+" "+currentRental.get(i).getRate()+currentRental.get(i).getName();
            j+=(System.getProperty("line.separator"));
        }
        return j;
    }
    
    public String printPastMovies()
    {
        String j="";
        for(int i=0;i<pastRental.size();i++)
        {
            j+=pastRental.get(i).getId()+" "+pastRental.get(i).getStock()+" "+pastRental.get(i).getRate()+pastRental.get(i).getName();
            j+=(System.getProperty("line.separator"));
        }
        return j;
    }
}
