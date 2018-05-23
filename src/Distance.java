import java.util.*;

import sun.net.www.content.audio.x_aiff;


public class Distance {
	
	public int[][] words;
	public String start;
	public String path;
	public String end;
	public int offset;
	public Stack<String> wordLadder;
	
	public Distance(String s, String e) {
		// TODO Auto-generated constructor stub
		start = " " + s;
		end = " " + e;
		words = new int[start.length()][end.length()];
		offset = 0;
		wordLadder = new Stack<>();
		
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
	
	public void getPath() {	
		path = end;
		//System.out.println(path);
		wordLadder.push(end.substring(1));
		getPath(words.length - 1, words[0].length - 1);
		//wordLadder.push(end.substring(1));
	}
	
	
	public void getPath(int x, int y) {
		//Goes backwards from end.
		
		//System.out.println(x + ", " + y);
		
		//Base Case: reached end
		if (words[x][y] == 0) {
			//wordLadder.push(path.substring(1));
			return;
		}
		
		if (x - 1 >= 0 && y - 1 >= 0) {
			//No change from previously
			
			//Replacement
			//Diagonal up with a decrease in value
			if (words[x - 1][y - 1] == words[x][y] - 1) {
				//Break down into characters and replace
				char[] characters = path.toCharArray();
				characters[y] = start.charAt(x);
				
				//System.out.println("replacement");
				
				//Update path and wordLadder
				path = new String(characters);
				wordLadder.push(path.substring(1));
				getPath(x - 1, y - 1);
				return;
			}
		}
		

		if (y - 1 >= 0) {
			//Insertion
			if (words[x][y - 1] == words[x][y] - 1) {
				//Break down into characters and removes
				char[] characters = path.toCharArray();
				characters[y] = Character.MIN_VALUE;
				//System.out.println("insertion");
				//Insertion
				
				
				//Update path and wordLadder
				path = new String(characters);
				wordLadder.push(path.substring(1));
				getPath(x, y - 1);
				return;
			}
		}
		
		if (x - 1 >= 0) {
			//Deletion
			if (words[x - 1][y] == words[x][y] - 1) {
				//Break down into characters and adds
				char[] characters = path.toCharArray();
				char[] charactersTemp = new char[characters.length + 1];
		
				int temp = x;
				if (x > characters.length) {
					temp = characters.length;
				}
				
				for (int i = 0; i < temp; i++) {
					charactersTemp[i] = characters[i];
				}
				
				for (int i = temp; i < characters.length; i++) {
					charactersTemp[i + 1] = characters[i];
				}
				
				charactersTemp[temp] = start.charAt(x);
				
				//System.out.println("deletion");
				
				//Update path and wordLadder
				path = new String(charactersTemp);
				wordLadder.push(path.substring(1));
				getPath(x - 1, y);
				return;
				
			}
		}
		
		if (x - 1 >= 0 && y - 1 >= 0) {
			//No change from previously
			//Diagonal up with no change in value
			if (words[x - 1][y - 1] == words[x][y]) {
				getPath(x - 1, y - 1); 
				return;
			}
		}
		
		
		/*
		if (words[x][y] == 0) {
			System.out.println(path);
			return;
		}
		
		if (x - 1 >= 0 && y - 1 >= 0 ) {
			//No Change
			if (words[x - 1][y - 1] == words[x][y]) {
				getPath(x - 1, y - 1);
				return;
			}
		
			//Replacement
			if (words[x - 1][y - 1] == words[x][y] - 1) {
				getPath(x - 1, y - 1);
				StringBuffer sb = new StringBuffer(path);
				sb.replace(x - 1 + offset, x + offset, end.charAt(y) + "");
				path = sb.toString();
				System.out.println(path);
				
				return;
			}
		}
		
		
		if (y - 1 >= 0) {
			//Insertion
			if (words[x][y - 1] == words[x][y] - 1) {
				getPath(x, y - 1);
				StringBuffer sb = new StringBuffer(path);
				sb.insert(x - 1 + offset, end.charAt(y));
				path = sb.toString();
				offset++;
				System.out.println(path);
				
				return;
			}
		}
		
		if (x - 1 >= 0) {
			//Deletion
			if (words[x - 1][y] == words[x][y] - 1) {
				getPath(x - 1, y);
				StringBuffer sb = new StringBuffer(path);
				sb.replace(x - 1 + offset, x + offset, "");
				path = sb.toString();
				offset--;
				System.out.println(path);
	
				return;
			}
		}
		*/
		
	}
	
	public void printPath() {
		while (!(wordLadder.isEmpty())) {
			System.out.println(wordLadder.pop());
		}
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String output = " ,";
		
		
		for (int i = 0; i < end.length(); i++) {
			output += end.charAt(i) + ", ";
		}
		output += "\n";
		
		for (int x = 0; x < words.length; x++) {
			output += start.charAt(x) + " ";
			for (int y = 0; y < words[0].length; y++) {
				
				output += words[x][y] + ", ";
			}
			output += "\n";
		}
		
		
		return output;
	}
	
	
	
	
}
