import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Folder;
import javax.mail.Store;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import java.util.Properties;
import javax.swing.JOptionPane;
public class EmailFailedToSend
{
    private boolean check;
    private Folder inbox;
    private Properties props;
    private String body;
    private String target;
    private final String USERNAME = "akash.mukherjee@gmail.com";
    private final String PASSWORD = "vern1234";
    public EmailFailedToSend(String address)
    {
        target = address;
        props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
    }
    private boolean getEmails()
    {
        try
        {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", USERNAME, PASSWORD);
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            for(int num=inbox.getMessageCount(); num>0; num--)
            {
                if(searchForFailure(num)==true)
                {
                    return true;
                }
            }
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    private boolean searchForFailure(int num)
    {
        try
        {
            Message msg = inbox.getMessage(num);
            Object content = msg.getContent();
            if(content instanceof String)  
            {  
                body = (String)content;
            }  
            else if(content instanceof Multipart)
            {  
                Multipart mp = (Multipart)content;
                BodyPart bp = mp.getBodyPart(0);
                body = (String)bp.getContent();
            }
            for(int x=0; x<body.length(); x++)
            {
                if(body.substring(x, x+target.length()).equals(target))
                {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean returnBoolean()
    {
        return getEmails();
    }
}