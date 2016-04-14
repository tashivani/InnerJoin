import java.util.ArrayList;
import java.util.List;

public class InnerJoin
{
	public static void main(String[] args)
	{
		List<String[]> table1 = new ArrayList<String[]>();
		List<String[]> table2 = new ArrayList<String[]>();
		String[] row1 = {"A","1"};
		String[] row2 = {"B", "2"};
		table1.add(row1);
		table1.add(row2);

		String[] row3 = {"1", "X"};
		String[] row4 = {"1", "Y"};
		String[] row5 = {"2", "Z"};
		table2.add(row3);
		table2.add(row4);
		table2.add(row5);

		printTable(table1);
		printTable(table2);

		runInnerJoin();
	}

	public static void runInnerJoin()
	{
		// • Idea:
		// for each tuple r in R do
		// 	for each tuple s in S do
		// 		if r, s satisfies join cond, then
		// 		add r, s to result


		// • For each tuple in the outer relation R, we scan the
		// entire inner relations S
		System.out.println("InnerJoin:");
	}

	public static void printTable(List<String[]> table)
	{
        for(String[] row: table){
        	for (String elem: row){
	        	System.out.print(elem);
        	}
        	System.out.println();
        }
		System.out.println();
	}
}