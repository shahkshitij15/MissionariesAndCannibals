
package ads;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Graph.gui;
import java.awt.Color;


public class Menu extends JFrame implements ActionListener
{
    JButton b1,b2,b3,b4,b5,b6;
    JLabel jl;
    public Menu()
    {
        jl=new JLabel("Missionaries and Cannibals");
        b1=new JButton("Play");
        b2=new JButton("Uniform Cost Search");
        b3=new JButton("Depth-Limited Search");
        b4=new JButton("Exit");
        b6=new JButton("Display Tree");
        b5=new JButton("Iterative-Depth Limited Search");
        
        jl.setFont(new Font("Serif",Font.BOLD,36));
        
        setTitle("Missionaries and Cannibals");
        setSize(1000,1500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(null);
        jl.setBounds(250,0,700,200);
        b1.setBounds(300,150,350,50);
        b2.setBounds(300,220,350,50);
        b3.setBounds(300,290,350,50);
        b5.setBounds(300,360,350,50);
        b4.setBounds(300,500,350,50);
        b6.setBounds(300,430,350,50);
        b4.setBackground(Color.PINK);
                
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        
        add(jl);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==b1)
        {
            Instruction i=new Instruction();
            dispose();
        }
        else if(e.getSource()==b2)
        {
            UCS u=new UCS();
            State initialState = new State (3, 3, Position.LEFT, 0, 0);
            u.setComponents(initialState);
            this.dispose();
        }
        else if(e.getSource()==b3)
        {
            DLS d=new DLS();
            State initialState = new State (3, 3, Position.LEFT, 0, 0);
            d.setComponents(initialState);
            this.dispose();
        }
        else if(e.getSource()==b6)
        {
            gui g=new gui();
            
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
        
        this.dispose();
        
        }
        else if(e.getSource()==b5)
        {
            IDS i=new IDS();
            State initialState=new State(3,3,Position.LEFT,0,0);
            i.setComponents(initialState);
            this.dispose();
        }
        else
        {
            System.exit(0);
        }
    }
}

