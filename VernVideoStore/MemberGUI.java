import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.nio.file.Paths;

public class MemberGUI
{
    private VernMain main;
    private JFrame frame;
    private final int SPACING=90;
    private final String LOC=Paths.get(".").toAbsolutePath().normalize().toString();
    private final String IMAGE_FILENAME = LOC+"\\Vern's Logo.png";
    
    public void createAndShowGUI(VernMain vM, String name) throws IOException, FileNotFoundException
    {
        main = vM;
        frame = new JFrame();
        addComponentToPane(frame.getContentPane(), main, name);
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void addComponentToPane(Container pane, VernMain main, String name) throws IOException,FileNotFoundException
    {
        JPanel adm = new JPanel();
        JButton a1 = new JButton("Rent");
        ActionListener al1 = new ClickListener("rent", main);
        a1.addActionListener(al1);
        adm.add(a1);
        
        JButton a2 = new JButton("Return");
        ActionListener al2=new ClickListener("return", main);
        a2.addActionListener(al2);
        adm.add(a2);
        
        JButton a3 = new JButton("Browse");
        ActionListener al3 = new ClickListener("browse", main);
        a3.addActionListener(al3);
        adm.add(a3);
        
        JButton a4 = new JButton("Print Current Movies");
        ActionListener al4 = new ClickListener("current", main);
        a4.addActionListener(al4);
        adm.add(a4);
        
        JButton a5 = new JButton("Print Past Movies");
        ActionListener al5 = new ClickListener("past", main);
        a5.addActionListener(al5);
        adm.add(a5);
        
        JButton a6 = new JButton("Search");
        ActionListener al6 = new ClickListener("movie", main);
        a6.addActionListener(al6);
        adm.add(a6);
        
        JButton a7 = new JButton("Close");
        ActionListener al7 = new ClickListener("close", frame, main);
        a7.addActionListener(al7);
        adm.add(a7);
        
        String store = "";
        for(int x=0; x<SPACING; x++)
            store+=" ";
        store+="Member: "+name.toUpperCase();
        JLabel title = new JLabel();
        title.setFont(new Font("", Font.BOLD, 24));
        
        JPanel p = new JPanel();
        p.add(new JLabel(new ImageIcon(IMAGE_FILENAME)));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(adm,BorderLayout.PAGE_END);
        p1.add(new JLabel(store),BorderLayout.PAGE_START);
        
        pane.add(p, BorderLayout.NORTH);
        pane.add(p1, BorderLayout.SOUTH);
    }
}
