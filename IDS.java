
package ads;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


/*IDS is similar to DLS only difference being limt is initally set in DLS
whereas in IDS it starts from 0.
Also in IDS the search always starts from root
One will always get an answer in IDS not true for DLS
IDS is a combination of BFS and DFS
*/

//implementing iterative deepening search
public class IDS extends JFrame implements ActionListener
{
    int depth;
    long ta,tb;
    JButton b1;
    JLabel l1,l2,l3;
    
    IDS()
    {
        b1=new JButton("Exit");
        l1=new JLabel("Iterative Depth-Limited Search");
        l3=new JLabel();
        
        setLayout(null);
        l1.setBounds(50,0,400,30);
        b1.setBounds(400,425,80,20);
        l1.setFont(new Font("Serif",Font.BOLD,24));
        b1.setBackground(Color.PINK);
        
        setSize(500,500);
        setTitle("BFS");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(l1);
        add(b1);
        
        b1.addActionListener(this);
    }
    
     @Override
    public void actionPerformed(ActionEvent ae)
    {
        Menu m=new Menu();
        dispose();
    }
    
    public void setComponents(State initialState)
    {
        ta=System.currentTimeMillis();
	State solution = execute(initialState);
        tb=System.currentTimeMillis();
	printSolution(solution);
    }
    
    public State execute(State start)
    {
        depth=0;  //set initial limit as 0
        if(depth>=0)
        {
            for(int i=0;i<=depth;i++)
        {
            State solution=recursiveDLS(start,depth);  //calling DLS 
            if(solution!=null)
            {
                return solution;  //meanig goal state 
            }
            depth++;  //increment the depth
        }
        }
        return null;
    }
    
    private State recursiveDLS(State state, int limit) 
        {
		if (state.isGoal())  //if initialState is goal
                {
			return state;
		} 
                else if (limit == 0) 
                {
			return null;
		} 
                else 
                {
                    //store the children in linked list
			List<State> successors = state.generateSuccessors();
			for (State child : successors)
                        {
				State result = recursiveDLS(child, limit - 1);
				if (result!=null) //goalState
                                {
					return result;
				}
			}
			return null;
		}
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
			List<State> path = new ArrayList<State>();//create list to store the path
			State state = solution;
			while(state!=null) 
                        {
				path.add(state); //add goal first
				state = state.getParentState(); //add parent
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
                        System.out.println("Depth:"+(depth+1));
                        System.out.println("Duration: "+(tb-ta)+" milliseconds");
                        JLabel l4=new JLabel("Depth: "+(depth+1));
                        l4.setBounds(0,410,200,20);
                        add(l4);
                        validate();
                        repaint();
                        l3.setText("Duration: "+(tb-ta)+" milliseconds");
                        l3.setBounds(0,425,200,20);
                        add(l3);
                        validate();
                        repaint();
		}
    }
}
