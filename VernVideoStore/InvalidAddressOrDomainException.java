import javax.mail.MessagingException;

public class InvalidAddressOrDomainException extends MessagingException
{
    private String message;
    public InvalidAddressOrDomainException()
    { 
        super();
    }
    public InvalidAddressOrDomainException(String message)
    {
        super(message);
        this.message=message;
    }
    public String getMessage()
    {
        return message;
    }
}
