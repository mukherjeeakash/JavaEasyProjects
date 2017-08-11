import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
public class SendEmail
{
    private String msg;
    private String className;
    private final String USERNAME = "akash.mukherjee@gmail.com";
    private final String PASSWORD = "vern1234";
    private final String HOST = "smtp.gmail.com";
    public SendEmail(String email, String uN, String pW)
    {
        String to = email;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, null);
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Vern's Video Store Login Info");
            message.setText("Username: "+uN+"\nPassword: "+pW);
            
            boolean check=false;
            for(int x=0; x<to.length(); x++)
            {
                if(to.substring(x,x+1).equals("@"))
                {
                    check=true;
                    x=to.length();
                }
            }
            if(!check)
                throw new InvalidAddressOrDomainException();
            Transport.send(message, USERNAME, PASSWORD);
            msg = "Email Successfully Sent";
        }
        catch(Exception ex)
        {
            msg = ex.getClass().getName();
        }
    }
    public String result()
    {
        return msg;
    }
}