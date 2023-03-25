import java.util.ArrayList;
import java.util.Arrays;
public class Problem{
	public ArrayList<Integer> set;
	public Integer sum;
	
	public Problem(ArrayList<Integer> inputList, Integer inputSum)
	{
		set = (ArrayList)inputList.clone();
		sum = inputSum;
	}
}