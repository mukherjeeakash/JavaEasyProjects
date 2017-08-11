import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;

public class ClickListener implements ActionListener
{
    private String type;
    private VernMain main;;
    private JOptionPane pane;
    private JFrame frame;
    private Font myFont=new Font("Courier",Font.PLAIN,12);
    
    public ClickListener(String input, JFrame f,VernMain n)
    {
        type=input;
        frame=f;
        main=n;
    }
    public ClickListener(String input, VernMain n)throws IOException,FileNotFoundException
    {
        type=input;
        pane=new JOptionPane();
        main=n;
    }
    public void actionPerformed(ActionEvent event)
    {
        try
        {
            if(type.equals("add"))
            {
                String id=String.valueOf(main.addMember(pane.showInputDialog("Enter Name:")));
                pane.showMessageDialog(null,id);
            }
            else if(type.equals("delete"))
            {
                String message=main.deleteMember(Integer.parseInt(pane.showInputDialog("Enter ID:")));
                pane.showMessageDialog(null,message);
            }
            else if(type.equals("movie"))
            {
                String[] choices={"All","G","PG","PG13","R"};
                String input=(String)pane.showInputDialog(null, "Rating","", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
                JTextArea textArea;
                if(input.equals("All"))
                    textArea = new JTextArea(main.search(pane.showInputDialog("Enter Movie:")));
                else
                    textArea = new JTextArea(main.search(pane.showInputDialog("Enter Movie:"),input));
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(myFont);
                scrollPane.setPreferredSize(new Dimension(700,600));
                JOptionPane.showMessageDialog(null, scrollPane);
            }
            else if(type.equals("memberName"))
            {
                FindMemberName m=new FindMemberName(main);
            }
            else if(type.equals("memberID"))
            {
                FindMemberID m=new FindMemberID(main);
            }
            else if(type.equals("rent"))
            {
                String e=main.rent(Integer.parseInt(pane.showInputDialog("Enter Movie ID:")));
                pane.showMessageDialog(null,e);
            }
            else if(type.equals("return"))
            {
                String e=main.returnDVD(Integer.parseInt(pane.showInputDialog("Enter Movie ID:")));
                pane.showMessageDialog(null,e);
            }
            else if(type.equals("past"))
            {
                JTextArea textArea = new JTextArea(main.printPastRent());
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(myFont);
                scrollPane.setPreferredSize(new Dimension(700,600));
                JOptionPane.showMessageDialog(null, scrollPane);
            }
            else if(type.equals("current"))
            {
                JTextArea textArea = new JTextArea(main.printCurRent());
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(myFont);
                scrollPane.setPreferredSize(new Dimension(700,600));
                JOptionPane.showMessageDialog(null, scrollPane);
            }
            else if(type.equals("browse"))
            {
                JTextArea textArea = new JTextArea(main.search(""));
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(myFont);
                scrollPane.setPreferredSize(new Dimension(700,600));
                JOptionPane.showMessageDialog(null, scrollPane);
            }
            else if(type.equals("member"))
            {
                JTextArea textArea = new JTextArea(main.printMembers());
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(myFont);
                scrollPane.setPreferredSize(new Dimension(700,600));
                JOptionPane.showMessageDialog(null, scrollPane);
            }
            else if(type.equals("addMovie"))
            {
                String[] choices={"G","PG","PG13","R"};
                String name=pane.showInputDialog("Enter Movie Name");
                int stock= Integer.parseInt(pane.showInputDialog("Enter Stock"));
                String input=(String)pane.showInputDialog(null, "Rating","", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
                String message=main.addMovie(name,stock,input);
                pane.showMessageDialog(null,message);
            }
            else if(type.equals("deleteMovie"))
            {
                String message=main.deleteMovie(pane.showInputDialog("Enter Movie Name:"));
                pane.showMessageDialog(null,message);
            }
            else if(type.equals("close"))
            {
                main.close();
                frame.dispose();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
