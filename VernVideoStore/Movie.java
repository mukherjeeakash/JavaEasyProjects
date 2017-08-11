
public class Movie
{
    int idMovie;
    int number;
    int rentNum=0;
    String rating;
    String name;
    
    public Movie(int id, int stock, String rate, String movie)
    {
        idMovie=id;
        number=stock;
        rating=rate;
        name=movie;
    }
    
    public boolean rent()
    {
        if(number==0)
            return false;
        number--;
        rentNum++;
        return true;
    }
    
    public void returnDVD()
    {
        number++;
    }
    
    public int getId()
    {
        return idMovie;
    }
    
    public int getStock()
    {
        return number;
    }
    
    public int getNumRented()
    {
        return rentNum;
    }
    
    public String getRate()
    {
        return rating;
    }
    
    public String getName()
    {
        return name;
    }
}
