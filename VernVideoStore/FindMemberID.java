import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FindMemberID
{
    public FindMemberID(VernMain n) throws IOException, FileNotFoundException
    {
        MemberGUI m=new MemberGUI();
        final VernMain main=n;
        final JFrame frame=new JFrame("Member Search");
        JPanel panel=new JPanel();
        final JTextField id=new JTextField("Find Member By ID", 25);
        JButton searchID=new JButton("Search by ID");
        panel.add(id);
        panel.add(searchID);
        searchID.addActionListener(new ActionListener()    
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    String idNumber=id.getText();
                    int number=Integer.parseInt(idNumber);
                    String result=main.login(number);
                    JOptionPane.showMessageDialog(null, result);
                    frame.dispose();
                    if(result.equals("Success"))
                        m.createAndShowGUI(main,main.getName());
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(400,135);
        frame.setVisible(true);
    }
}
