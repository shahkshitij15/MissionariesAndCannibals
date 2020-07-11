
package ads;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/*
In DLS an intial limit is set to the graph.It does not searches below that level
so if the solution is after a paticular level the we wont get the solution
*/

//function to implement Depth Limited Search
public class DLS extends JFrame implements ActionListener
{
    JLabel l1,l2,l3;
    long ta,tb;
    JButton b1;
    
    DLS()
    {
        l1=new JLabel("Depth Limited Search");
        b1=new JButton("Exit");
        l3=new JLabel();
        
        setLayout(null);
        l1.setBounds(50,0,400,30);
        b1.setBounds(400,660,80,20);
        l1.setFont(new Font("Serif",Font.BOLD,36));
        b1.setBackground(Color.PINK);
        
        setSize(500,1000);
        setTitle("DLS");
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
                        l3.setBounds(0,660,200,20);
                        add(l3);
                        validate();
                        repaint();
		}
    }
    
    public State execute(State initialState)
    {
		int limit = 20;//set limit
		return recursiveDLS(initialState, limit);
    }

	private State recursiveDLS(State state, int limit) 
        {
		if (state.isGoal()) 
                {
			return state;
		} 
                else if (limit == 0) 
                {
			return null;
		} 
                else 
                {
			List<State> successors = state.generateSuccessors();//generate children
			for (State child : successors)
                        {
				State result = recursiveDLS(child, limit - 1); //recall the same function
				if (result!=null) 
                                {
					return result;
				}
			}
			return null;
		}
	}

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        Menu m=new Menu();
        dispose();
    }
}
