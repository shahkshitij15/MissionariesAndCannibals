
package ads;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.swing.*;

/*
Uniform cost search is used for weighted graph but since in this graph all 
edges have the same weight that is 1 UCS is converted to BFS and we are actually 
implementing BReadth First Search
*/

//implementing uniform cost search
public class UCS extends JFrame implements ActionListener
{
    JLabel l1,l2,l3;
    long ta,tb;
    JButton b1;
    
    UCS()
    {
        l1=new JLabel("Breadth First Search");
        b1=new JButton("Exit");
        l3=new JLabel();
        
        setLayout(null);
        l1.setBounds(50,0,400,30);
        b1.setBounds(400,425,80,20);
        l1.setFont(new Font("Serif",Font.BOLD,36));
        b1.setBackground(Color.PINK);
        
        setSize(500,500);
        setTitle("BFS");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(l1);
        add(b1);
        
        
        b1.addActionListener(this);
    }
    
    
    void setComponents(State initialState)
    {
        ta=System.currentTimeMillis();
	State solution = execute(initialState);
        tb=System.currentTimeMillis();
	printSolution(solution);
    }
    
    void printSolution(State solution)
    {
        if (null == solution) 
                {
			//System.out.print("\nNo solution found.");
                    l2=new JLabel("No solution found");
                    l2.setFont(new Font("Serif",Font.BOLD,24));
                    l2.setBounds(50,100,300,20);
                    add(l2);
                    validate();
                    repaint();
		} 
                else 
                {
                    int k=0;
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) 
                        {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
                        int j=1;
			for (int i = depth; i >= 0; i--) 
                        {
				state = path.get(i);
				if (state.isGoal()) 
                                {
                                    l2=new JLabel("State "+j+" : "+state.toString());
                                    l2.setBounds(50,50+k,300,20);
                                    add(l2);
                                    validate();
                                    repaint();
                                    System.out.print("State "+j+" : ");
                                    System.out.println(state.toString());
                                    k=k+30;
				}
                                else 
                                {
                                    System.out.print("State "+j+" : ");
                                    System.out.println(state.toString());
                                    l2=new JLabel("State "+j+" : "+state.toString());
                                    l2.setBounds(50,50+k,300,20);
                                    add(l2);
                                    validate();
                                    repaint();
                                    k=k+30;
				}
                                j=j+1;
			}
                        System.out.println("\nDuration: "+(tb-ta)+" milliseconds");
                        l3.setText("Duration: "+(tb-ta)+" milliseconds");
                        l3.setBounds(0,425,200,20);
                        add(l3);
                        validate();
                        repaint();
		}
    }
    
    public State execute(State initialState) 
    {
		if (initialState.isGoal()) //if initalState is goal
                {
			return initialState;
		}
		Queue<State> q = new LinkedList<>();  //create a queue
		Set<State> explored = new HashSet<>(); //create a hashset as duplicate values not allowed
		q.add(initialState);
		while (true)
                {
			if (q.isEmpty()) //if queue is empty
                        {
				return null;	
			}
			State state = q.poll(); //dequeue
			explored.add(state);  //add to explored hashset
			List<State> successors = state.generateSuccessors();//generate children
			for (State child : successors) 
                        {
				if (!explored.contains(child) || !q.contains(child))//if child is not visited or not present in queue
                                {
					if (child.isGoal()) //if child is goal return the child
                                        {
						return child;
					}
					q.add(child); //else add to the queue
				}
			}
		}
	}

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Menu m=new Menu();
        dispose();
    }
    
   
}


