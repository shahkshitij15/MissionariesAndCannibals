
package ads;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//class to play the game
public class Play extends JFrame implements ActionListener
{
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2;
    
    int totalMissionaries,totalCannibals,missionaries,cannibals,capacity;
    int m,c,count,a;
    static int i,k;
    int boatposition;
    long ta,tb;
    
    Play(int m,int c)
    {
        count=0;
        a=0;
        i=1;
        k=0;
        boatposition=0;
        totalCannibals=c;
        cannibals=c;
        capacity=2;
        totalMissionaries=m;
        missionaries=m;
        
        display(0);
        
        b1=new JButton("GoLeft");
        b2=new JButton("Exit");
        b3=new JButton("GoRight");
        l1=new JLabel("Missionaries");
        l2=new JLabel("Cannibals");
        t1=new JTextField();
        t2=new JTextField();
        l5=new JLabel("Enter the number of missionaries and cannibals");
        l3=new JLabel("State: "+i+" [ m: "+missionaries+" c: "+cannibals+" ] b~~~~~~~~~~~~  [ m: "+(totalMissionaries-missionaries)+" c: "+(totalCannibals-cannibals)+" ]");
        
        setSize(500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Game");
        
        b2.setBackground(Color.PINK);
        b1.setBackground(Color.CYAN);
        b3.setBackground(Color.CYAN);
        
        setLayout(null);
        l1.setBounds(100,50,80,20);
        t1.setBounds(200,50,200,20);
        l2.setBounds(100,100,100,20);
        t2.setBounds(200,100,200,20);
        b3.setBounds(200,150,100,30);
        b1.setBounds(200,200,100,30);
        b2.setBounds(0,650,80,30);
        l3.setBounds(100,250,300,30);
        l5.setBounds(0,0,500,30);
        l5.setFont(new Font("Serif",Font.BOLD,14));
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        add(b1);
        add(b2);
        add(b3);
        add(t1);
        add(t2);
        add(l1);
        add(l2);
        add(l3);
        add(l5);
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        
        if(ae.getSource()==b1)
        {
            if(a==0)
            {
                ta=System.currentTimeMillis();
                a++;
            }
            if(boatposition==1)
            {
                count=0;
            int m=Integer.parseInt(t1.getText());
            int c=Integer.parseInt(t2.getText());
            
            if(c>(totalCannibals-cannibals) || c<0 &&count==0)
            {
                count++;
                JOptionPane.showMessageDialog(this,"Cannibals cannot be negative or more than the total present!Restarting");
                Instruction i1=new Instruction();
                dispose();
            }
            
            else if(m>(totalMissionaries-missionaries) || m<0 &&count==0)
            {
                count++;
                JOptionPane.showMessageDialog(this,"Missionaries cannot be negative or more than the total present!Restarting");
                Instruction i1=new Instruction();
                dispose();
            }
             cannibals=cannibals+c;
             missionaries=missionaries+m;
             
             if(!checkCapacity(m, c) && count==0)
             {
                 count++;
                 JOptionPane.showMessageDialog(this,"Maximum capacity of the boat is 2 and minimum capacity is 1!Restarting");
                  Instruction i1=new Instruction();
                  dispose();
             }
             
            else if(checkState() && count==0)
             {
                 count++;
                 JOptionPane.showMessageDialog(this,"Number of cannibals cannot exceed the number of missionaries!Restarting");
                 Instruction i1=new Instruction();
                 dispose();
             }
             boatposition=0;
             i=i+1;
             k+=20;
            
            l4=new JLabel("State: "+i+" [ m: "+missionaries+" c: "+cannibals+" ] b~~~~~~~~~~~~  [ m: "+(totalMissionaries-missionaries)+" c: "+(totalCannibals-cannibals)+" ]");
            l4.setBounds(100,250+k,300,30);
            add(l4);
            validate();
            repaint();
            
            display(0);
            
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Cannot go to the left");
            }
        }
        else if(ae.getSource()==b2)
        {
            Menu m=new Menu();
            dispose();
        }
        else if(ae.getSource()==b3)
        {
            if(boatposition==0){
                count=0;
            int m=Integer.parseInt(t1.getText());
            int c=Integer.parseInt(t2.getText());
            
            if(c>cannibals || c>totalCannibals || c<0 && count==0)
            {
                count++;
                JOptionPane.showMessageDialog(this,"Cannibals cannot be negative or more than the total present!Restarting");
                Instruction i1=new Instruction();
                dispose();
            }
            
            else if(m>missionaries || m>totalMissionaries || m<0 && count==0)
            {
                count++;
                JOptionPane.showMessageDialog(this,"Missionaries cannot be negative or more than the total present!Restarting");
                Instruction i1=new Instruction();
                dispose();
            }
            
            cannibals=cannibals-c;
            missionaries=missionaries-m;
            
            if(!checkCapacity(m, c) && count==0)
            {
                count++;
                JOptionPane.showMessageDialog(this,"Maximum capacity of the boat is 2 and minimum capacity is 1!Restarting");
                Instruction i1=new Instruction();
                dispose();
                
            }
            
            else if(checkState() && count==0)
            {
                count++;
                 JOptionPane.showMessageDialog(this,"Number of cannibals cannot exceed the number of missionaries!Restarting");
                 Instruction i1=new Instruction();
                 dispose();
            }
            
            boatposition=1;
            i+=1;
            k+=20;
            
            l4=new JLabel("State: "+i+" [ m: "+missionaries+" c: "+cannibals+" ] ~~~~~~~~~~~~b  [ m: "+(totalMissionaries-missionaries)+" c: "+(totalCannibals-cannibals)+" ]");
            l4.setBounds(100,250+k,300,30);
            add(l4);
            validate();
            repaint();
            
            display(1);
            
             
             if(isTerminalState() && count==0)
            {
                count++;
                tb=System.currentTimeMillis();
                JLabel jl=new JLabel("Duration: "+(tb-ta)+" milliseconds");
                jl.setBounds(0,600,500,20);
                add(jl);
                validate();
                repaint();
                JOptionPane.showMessageDialog(this,"Congratulations!You won!");
                int n=JOptionPane.showConfirmDialog(this,"Do you want to play again?","Restart Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(n==JOptionPane.YES_OPTION)
                {
                    Instruction i1=new Instruction();
                    this.dispose();
                }
                else
                {
                    this.dispose();
                    Menu obj=new Menu();
                }
            }
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Cannot go to the right");
            }
        }
    }
    
    //to check boat capacity
    boolean checkCapacity(int m,int c)
    {
        if(m>=0 && c>=0 && m+c>=1 && m+c<=capacity)
        return true;
        
        return false;
    }
    
    
    //check number of cannibals and missionaries on both the sides
    boolean checkState()
    {
        if(missionaries<cannibals && missionaries>0)
            return true;
        
        if((totalMissionaries-missionaries)>0 && (totalMissionaries-missionaries)<(totalCannibals-cannibals))
            return true;
        
        return false;
    }
    
    void display(int j)
    {
        System.out.print("\nState: "+i+" [ m: "+missionaries+" c: "+cannibals+" ]");
        if(j==0)
        {
            System.out.print(" b~~~~~ ");
          
        }
        else
        {
            System.out.print(" ~~~~b ");
          
        }
        System.out.print(" [ m: "+(totalMissionaries-missionaries)+" c: "+(totalCannibals-cannibals)+" ]");
    }
    
    boolean isTerminalState()
    {
        if(missionaries==0 && cannibals==0)
            return true;
        
        return false;
    }
}
