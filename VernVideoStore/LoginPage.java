import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.net.ConnectException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.nio.file.Paths;

public class LoginPage
{
     private JTextField user;
     private JTextField pass;
     private JFrame frame;
     private JPanel pane1;
     private JPanel pane2;
     private JPanel pane3;
     private JPanel pane4;
     private String username;
     private String password;
     private String mailAddress;
     private String msg;
     private final String LOC=Paths.get(".").toAbsolutePath().normalize().toString();
     private final String userTrue;
     private final String passTrue;
     private final String TITLE = "Vern's Video Store";
     private final String ERROR = "Error";
     private final String SEND = "Send Email";
     private final String LOGIN = "Login";
     private final String ENSURE = "Ensure address and domain are correct.";
     private final String SUCCESS = "Email Successfully Sent";
     private final String SENT = "Sent";
     private final String SENT_TO = "Message Sent to";
     private final String SENT_FAILED = "Message Failed to Send to";
     private final String SET_ENTER = "Enter Email Address";
     private final String SET_USER = "Username";
     private final String SET_PASS = "Password";
     private final String SET_BACK = "Back to Login Page";
     private final String SET_WRONG = "Invalid Login Attempt";
     private final String SET_ADDRESS = "Email Login Information";
     private final String SET_AGAIN = "Attempt to Login Again";
     private final String INTERNET_ERROR = "Failed to connect to email server. Check device's internet and network connectivity.";
     private final String INTERNET_EXCEPTION = "com.sun.mail.util.MailConnectException";
     private final String ADDRESS_EXCEPTION = "javax.mail.internet.AddressException";
     private final String INVALID_EXCEPTION = "InvalidAddressOrDomainException";
     //private final String IMAGE_FILENAME = "//Volumes//CONNOR P//Vern's Logo.png";
     private final String IMAGE_FILENAME = LOC+"\\Vern's Logo.png";
     //private final String CHECK_FILENAME = "//Volumes//CONNOR P//Check.jpg";
     private final String CHECK_FILENAME = "F:\\Check.jpg";
     private final String COLON = ": ";
     private final int TEXT_LENGTH = 25;
     private final int PAUSE_TIME = 2000;
     public LoginPage(String u, String p) throws IOException, FileNotFoundException
     {
          frame = new JFrame(TITLE);
          pane1 = new JPanel();
          pane2 = new JPanel();
          pane3 = new JPanel();
          pane4 = new JPanel();
          user = new JTextField(SET_USER, TEXT_LENGTH);
          pass = new JTextField(SET_PASS, TEXT_LENGTH);
          JButton login = new JButton(LOGIN);
          userTrue = u;
          passTrue = p;
          
          pane1.add(user);
          pane2.add(pass);
          pane3.add(login);
          pane4.add(new JLabel(new ImageIcon(IMAGE_FILENAME)));
          
          JPanel big = new JPanel();
          big.setLayout(new BorderLayout());
          big.add(pane1, BorderLayout.WEST);
          big.add(pane2, BorderLayout.EAST);
          big.add(pane3, BorderLayout.SOUTH);
          big.add(pane4, BorderLayout.NORTH);
          
          frame.add(big);
          frame.pack();
          frame.setSize(650,275);
          frame.setResizable(false);
          frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          frame.setVisible(true);
          
          login.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
              {
                  username = user.getText();
                  password = pass.getText();
                  frame.setVisible(false);
                  try
                  {
                      if(login())
                          success();
                      else
                          failed();
                  }
                  catch(Exception ex)
                  {
                      JOptionPane.showMessageDialog(null, ERROR+COLON+ex.getClass().getName(), ERROR, JOptionPane.ERROR_MESSAGE);
                  }
              }
          });
     }
     private boolean login()
     {
          if(username.equals(userTrue) && password.equals(passTrue))
             return true;
          else
             return false;
     }
     private void failed() throws IOException, FileNotFoundException
     {
         try
         {
             final JFrame error = new JFrame(TITLE);
             JPanel errorP = new JPanel();
             JLabel wrong = new JLabel(SET_WRONG);
             JButton address = new JButton(SET_ADDRESS);
             JButton again = new JButton(SET_AGAIN);
              
             address.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e)
                 {
                     error.dispose();
                     final JFrame send = new JFrame(SEND);  
                     JPanel pane = new JPanel();
                     final JTextField enter = new JTextField(SET_ENTER, TEXT_LENGTH);
                     JButton back = new JButton(SET_BACK);
                     JButton text = new JButton(SEND);
                     pane.add(enter);
                     pane.add(text);
                     pane.add(back);
                     text.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {
                             mailAddress = enter.getText();
                             final ImageIcon check = new ImageIcon(CHECK_FILENAME);
                             String failCheck = SENT_FAILED+COLON+mailAddress+". \n"+ENSURE;
                             SendEmail mail = new SendEmail(mailAddress, userTrue, passTrue);
                             msg = mail.result();
                             if(msg.equals(SUCCESS))
                             {
                                 try
                                 {
                                     Thread.sleep(PAUSE_TIME);
                                     EmailFailedToSend sendChecker = new EmailFailedToSend(mailAddress);
                                     boolean sendCheckerBoolean = sendChecker.returnBoolean();
                                     if(sendCheckerBoolean==true)
                                     {
                                         throw new InvalidAddressOrDomainException();
                                     }
                                     JOptionPane.showMessageDialog(null, SENT_TO+COLON+mailAddress, SENT, JOptionPane.INFORMATION_MESSAGE, check);
                                     frame.setVisible(true);
                                     user.setText(SET_USER);
                                     pass.setText(SET_PASS);
                                     send.dispose();
                                 }
                                 catch(InvalidAddressOrDomainException iad)
                                 {
                                     enter.setText(SET_ENTER);
                                     JOptionPane.showMessageDialog(null, failCheck, ERROR, JOptionPane.ERROR_MESSAGE);
                                 }
                                 catch(InterruptedException ex)
                                 {
                                     String msg = ex.getMessage();
                                     if(msg==null)
                                         JOptionPane.showMessageDialog(null, ex.getClass().getName(), ERROR, JOptionPane.ERROR_MESSAGE);
                                     else
                                         JOptionPane.showMessageDialog(null, msg, ERROR, JOptionPane.ERROR_MESSAGE);
                                 }
                             }
                             else
                             {
                                 if(msg.equals(INTERNET_EXCEPTION))
                                     msg = INTERNET_ERROR;
                                 else if(msg.equals(ADDRESS_EXCEPTION) || msg.equals(INVALID_EXCEPTION))
                                     msg = failCheck;
                                 JOptionPane.showMessageDialog(null, msg, ERROR, JOptionPane.ERROR_MESSAGE);
                                 enter.setText(SET_ENTER);
                             }
                         }
                     });
                     
                     back.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {
                             frame.setVisible(true);
                             user.setText(SET_USER);
                             pass.setText(SET_PASS);
                             send.dispose();
                         }
                     });
                     
                     send.add(pane); 
                     send.setResizable(false);
                     send.pack();
                     send.setSize(350,250);
                     send.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                     send.setVisible(true);
                 }
             });
              
             again.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent f)
                 {
                     try
                     {
                         LoginPage page = new LoginPage(userTrue, passTrue);
                         error.dispose();
                     }
                     catch(Exception ex)
                     {
                         String msg = ex.getMessage();
                         if(msg==null)
                             JOptionPane.showMessageDialog(null, ERROR+COLON+ex.getClass().getName(), ERROR, JOptionPane.ERROR_MESSAGE);
                         else
                             JOptionPane.showMessageDialog(null, msg, ERROR+COLON+msg, JOptionPane.ERROR_MESSAGE);
                     }
                 }
             });
          
             errorP.add(wrong);
             errorP.add(address);
             errorP.add(again);
             error.add(errorP);            
             error.setResizable(false);
             error.setSize(250,200);
             error.pack();
             error.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             error.setVisible(true);    
         }
         catch(Exception ex)
         {
             String msg = ex.getMessage();
             if(msg==null)
                 JOptionPane.showMessageDialog(null, ERROR+COLON+ex.getClass().getName(), ERROR, JOptionPane.ERROR_MESSAGE);
             else
                 JOptionPane.showMessageDialog(null, msg, ERROR+COLON+msg, JOptionPane.ERROR_MESSAGE);
         }
     }
     private void success() throws IOException, FileNotFoundException
     {
         InitialFrame initFrame = new InitialFrame();
         initFrame.createAndShowGUI();
     }
} 