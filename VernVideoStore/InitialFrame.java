import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.nio.file.Paths;

public class InitialFrame implements ItemListener
{
    private JPanel cards;
    private final String ADMIN = "Admin";
    private final String EXISTING_MEMBER = "Find an Existing Member";
    private VernMain main;
    private JFrame frame;
    private final int SPACING = 124;
    private final String LOC=Paths.get(".").toAbsolutePath().normalize().toString();
    private final String IMAGE_FILENAME = LOC+"\\Vern's Logo.png";
    
    public void createAndShowGUI() throws IOException, FileNotFoundException
    {
        main = new VernMain();
        frame = new JFrame();
        frame.setDefaultCloseOperation(main.close());
        addComponentToPane(frame.getContentPane(), main);
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void addComponentToPane(Container pane, VernMain vM) throws IOException,FileNotFoundException
    {
        JPanel panel=new JPanel();
        String[] items=new String[]{ADMIN, EXISTING_MEMBER};
        JComboBox<String> box=new JComboBox<String>(items);
        box.setEditable(false);
        box.addItemListener(this);
        panel.add(box);
        
        JPanel adm=new JPanel();
        JButton a1=new JButton("Add New Member");
        ActionListener al1=new ClickListener("add",main);
        a1.addActionListener(al1);
        adm.add(a1);
        JButton a2=new JButton("Delete Member");
        ActionListener al2=new ClickListener("delete",main);
        a2.addActionListener(al2);
        adm.add(a2);
        JButton a3=new JButton("Search Inventory");
        ActionListener al3=new ClickListener("movie",main);
        a3.addActionListener(al3);
        adm.add(a3);
        JButton a5=new JButton("Print Members");
        ActionListener al5=new ClickListener("member", main);
        a5.addActionListener(al5);
        adm.add(a5);
        JButton a6=new JButton("Add Movie");
        ActionListener al6=new ClickListener("addMovie", main);
        a6.addActionListener(al6);
        adm.add(a6);
        JButton a7=new JButton("Delete Movie");
        ActionListener al7=new ClickListener("deleteMovie",main);
        a7.addActionListener(al7);
        adm.add(a7);
        JButton a4=new JButton("Save & Close");
        ActionListener al4=new ClickListener("close",frame, main);
        a4.addActionListener(al4);
        adm.add(a4);
        JPanel search=new JPanel();
        JButton b1=new JButton("Find Member by Name");
        ActionListener bl1=new ClickListener("memberName",main);
        b1.addActionListener(bl1);
        JButton b2=new JButton("Find Member by ID");
        ActionListener bl2=new ClickListener("memberID",main);
        b2.addActionListener(bl2);
        search.add(b1);
        search.add(b2);
        JButton b3=new JButton("Save & Close");
        ActionListener b13=new ClickListener("close",frame, main);
        b3.addActionListener(b13);
        search.add(b3);
        
        cards=new JPanel(new CardLayout());
        cards.add(adm,ADMIN);
        cards.add(search,EXISTING_MEMBER);
        
        String store="";
        for(int x=0; x<SPACING; x++)
            store+=" ";
        store+="Vern's Video Store";
        
        JPanel p = new JPanel();
        p.add(new JLabel(new ImageIcon(IMAGE_FILENAME)));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(panel,BorderLayout.CENTER);
        p1.add(cards,BorderLayout.PAGE_END);
        p1.add(new JLabel(store),BorderLayout.PAGE_START);
        
        pane.add(p, BorderLayout.NORTH);
        pane.add(p1, BorderLayout.SOUTH);
    }
    public void itemStateChanged(ItemEvent event)
    {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, (String)event.getItem());
    }
}