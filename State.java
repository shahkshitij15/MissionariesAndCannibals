
package ads;
import java.util.ArrayList;
import java.util.List;

//special java type to create a collection of constants
enum Position {RIGHT, LEFT}

public class State 
{
    private int cannibalsLeft;
	private int missionariesLeft;
	private int cannibalsRight;
	private int missionariesRight;
	private Position boat;

	private State parentState;

        //constructor to set values
	public State(int cannibalsLeft, int missionariesLeft, Position boat,
			int cannibalsRight, int missionariesRight)
        {
		this.cannibalsLeft = cannibalsLeft;
		this.missionariesLeft = missionariesLeft;
		this.boat = boat;
		this.cannibalsRight = cannibalsRight;
		this.missionariesRight = missionariesRight;
	}

	public boolean isGoal()
        {
		return cannibalsLeft == 0 && missionariesLeft == 0;
	}

        /*
        method to check validity of state that is
        number of missionaries is greater than equal to number of cannibals
        on both the sides
        */
	public boolean isValid() 
        {
		if (missionariesLeft >= 0 && missionariesRight >= 0 && cannibalsLeft >= 0 && cannibalsRight >= 0
	               && (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft)
	               && (missionariesRight == 0 || missionariesRight >= cannibalsRight))
                {
			return true;
		}
		return false;
	}

        //method to generate all the childs
	public List<State> generateSuccessors() 
        {
		List<State> successor = new ArrayList<>();  //create an array list which will store object of the class
		if (boat == Position.LEFT) 
                {
                    /*
                    making object of all cases
                    2M 0C
                    0M 2C
                    1M 1C
                    1M 0C
                    0M 1C
                    */
			check(successor, new State(cannibalsLeft, missionariesLeft - 2, Position.RIGHT,
					cannibalsRight, missionariesRight + 2)); 
			check(successor, new State(cannibalsLeft - 2, missionariesLeft, Position.RIGHT,
					cannibalsRight + 2, missionariesRight)); 
			check(successor, new State(cannibalsLeft - 1, missionariesLeft - 1, Position.RIGHT,
					cannibalsRight + 1, missionariesRight + 1));
			check(successor, new State(cannibalsLeft, missionariesLeft - 1, Position.RIGHT,
					cannibalsRight, missionariesRight + 1)); 
			check(successor, new State(cannibalsLeft - 1, missionariesLeft, Position.RIGHT,
					cannibalsRight + 1, missionariesRight)); 
		} 
                else
                {
			check(successor, new State(cannibalsLeft, missionariesLeft + 2, Position.LEFT,
					cannibalsRight, missionariesRight - 2)); 
			check(successor, new State(cannibalsLeft + 2, missionariesLeft, Position.LEFT,
					cannibalsRight - 2, missionariesRight)); 
			check(successor, new State(cannibalsLeft + 1, missionariesLeft + 1, Position.LEFT,
					cannibalsRight - 1, missionariesRight - 1));
			check(successor, new State(cannibalsLeft, missionariesLeft + 1, Position.LEFT,
					cannibalsRight, missionariesRight - 1)); 
			check(successor, new State(cannibalsLeft + 1, missionariesLeft, Position.LEFT,
					cannibalsRight - 1, missionariesRight)); 
		}
		return successor;
	}

        //method to check validity of the object and then add to the array list
	private void check(List<State> successor, State newState) 
        {
		if (newState.isValid())
                {
			newState.setParentState(this);
			successor.add(newState);
		}
	}

	public State getParentState()
        {
		return parentState;
	}

	public void setParentState(State parentState)
        {
		this.parentState = parentState;
	}

	@Override
	public String toString()
        {
		if (boat == Position.LEFT) 
                {
			return "[ c: " + cannibalsLeft + ", m: " + missionariesLeft + " ] b~~~~~ [ c: "
        			+ cannibalsRight + " , m: " + missionariesRight + " ]";
		} else 
                {
			return "[ c: " + cannibalsLeft + ", m: " + missionariesLeft + " ] ~~~~~b [ c: "
        			+ cannibalsRight + " , m: " + missionariesRight + " ]";
		}
     }
}
