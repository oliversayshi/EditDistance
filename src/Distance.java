import java.util.*;


public class Distance {
	
	public int[][] words;
	public String start;
	public String end;
	
	public Distance(String s, String e) {
		// TODO Auto-generated constructor stub
		start = " " + s;
		end = " " + e;
		words = new int[start.length()][end.length()];
		
	}
	
	public void setUp() {
		
		//Set column 0
		for (int i = 0; i < start.length(); i++) {
			words[i][0] = i;
		}
		
		//Set row 0
		for (int i = 0; i < end.length(); i++) {
			words[0][i] = i;
		}
		
		//Calculate
		
		for (int x = 1; x < words.length; x++) {
			
			
			for (int y = 1; y < words[0].length; y++) {
				
				if (start.charAt(x) == end.charAt(y)) {
					//Same letter
					words[x][y] = Math.max(words[x - 1][y], words[x][y - 1]) - 1;
					
					continue;
				}

				
				//No matches
				int temp = Math.min(words[x - 1][y], words[x][y - 1]);
				words[x][y] = Math.min(temp, words[x - 1][y - 1]) + 1;
				
			}
		}
		
	}
	
	public int getMinChanges() {
		return words[words.length - 1][words[0].length - 1];
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String output = "";
		
		for (int x = 0; x < words.length; x++) {
		
			for (int y = 0; y < words[0].length; y++) {
				
				output += words[x][y] + ", ";
			}
			output += "\n";
		}
		
		
		return output;
	}
	
	
}
