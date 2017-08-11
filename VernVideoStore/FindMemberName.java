import java.awt.event.*;
import javax.swing.*;

public class FindMemberName
{
    public FindMemberName(VernMain n)
    {
        MemberGUI m=new MemberGUI();
        final VernMain main=n;
        final JFrame frame=new JFrame("Member Search");
        JPanel panel=new JPanel();
        final JTextField name=new JTextField("Find Member By Name", 25);
        JButton searchName=new JButton("Search by Name");
        panel.add(name);
        panel.add(searchName);
        searchName.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    String nameFirst=name.getText();
                    String result=main.login(nameFirst);
                    JOptionPane.showMessageDialog(null, result);
                    frame.dispose();
                    if(result.equals("Success"))
                        m.createAndShowGUI(main,nameFirst);
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
