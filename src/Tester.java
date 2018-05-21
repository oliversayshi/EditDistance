import java.io.*;
import java.util.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Distance distance = new Distance("relevant", "elephant");
		distance.setUp();
		System.out.println(distance.getMinChanges());
		System.out.println(distance);
		
	}

}
