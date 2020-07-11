
package ads;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Instruction extends JFrame implements ActionListener
{
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5;
    Instruction()
    {
        b1=new JButton("Next");
        b2=new JButton("Exit");
        l5=new JLabel("Instructions...");
        l1=new JLabel("->On one bank of a river are three missionaries and three cannibals");
        l2=new JLabel("->There is one boat available that can hold up to two people and that they would like to use to cross the river");
        l3=new JLabel("->If the cannibals ever outnumber the missionaries on either of the river’s banks, the missionaries will get eaten.");
        l4=new JLabel("How can the boat be used to safely carry all the missionaries and cannibals across the river?");
        
        setLayout(null);
        l5.setFont(new Font("Serif",Font.BOLD,36));
        l1.setFont(new Font("Serif",Font.BOLD,16));
        l2.setFont(new Font("Serif",Font.BOLD,16));
        l3.setFont(new Font("Serif",Font.BOLD,16));
        l4.setFont(new Font("Serif",Font.BOLD,24));
        
        b1.setBackground(Color.YELLOW);
        b2.setBackground(Color.PINK);
        
        l5.setBounds(0,0,950,50);
        l1.setBounds(0,50,950,50);
        l2.setBounds(0,90,950,50);
        l3.setBounds(0,130,950,50);
        l4.setBounds(0,180,950,50);
        b1.setBounds(850,400,100,30);
        b2.setBounds(50,400,100,30);
        
        setSize(1000,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Instruction");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        add(l5);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(b1);
        add(b2);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            Play p=new Play(3,3);
            dispose();
        }
        else
        {
            Menu m=new Menu();
            dispose();
        }
    }
}
