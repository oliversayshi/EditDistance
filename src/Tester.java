import java.io.*;
import java.util.*;



public class Tester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(new File("input.txt"));
		int cases = reader.nextInt();
		
		for (int c = 0; c < cases; c++) {
			String start = reader.next();
			String end = reader.next();
			Distance distance = new Distance(start, end);
			System.out.print("The starting word is: " + start);
			System.out.println(" The ending word is: " + end);
			
			
			//Sets up
			distance.setUp();
			System.out.println("");
			System.out.println("The mininum number of changes needed is: " + distance.getMinChanges());
			System.out.println();
			
			//Gets Path
			distance.getPath();
			System.out.println("The path to change is");
			distance.printPath();
			System.out.println("\n");
		}
		
		
	}

}
