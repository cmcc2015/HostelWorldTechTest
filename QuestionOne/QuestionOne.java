/*
* Author: Colin McCormack
* To run: (Assuming java is on your path): java QuestionOne
*/


import java.util.*;

public class QuestionOne {
	
	private String[] leftArrayData = {"Punishment", "The Morse code", "Snooze alarms", "Halley's Comet", "One good turn deserves another.", "Alec Guinness", "NotAnagramTest"};
	private String[] rightArrayData = {"Nine Thumps", "Here come dots", "Alas! No more Zs", "Shall yet come", "Do rogues endorse that? No, never!", "Genuine Class", "DeffoNotAnagram"};
	private List<String> leftList = new ArrayList();
	private List<String> rightList = new ArrayList();

	public QuestionOne() {
		
		// 1) insert the strings from the question first off 
		populateLists();
		
		// 2) check for anagram-ness
		checkForAnagrams();
	}
	
	private void populateLists() {
		
		/*
		* Using the length of the left side array:
		* - Populate both respective Lists
		* - At each turn also replace non-'Word' based chars with nothing so get rid of .,?! and spaces!
		* - also lowercase each word too
		*/
		for(int i=0; i<leftArrayData.length; i++) {
		    leftList.add(leftArrayData[i].replaceAll("\\W", "").toLowerCase());
			rightList.add(rightArrayData[i].replaceAll("\\W", "").toLowerCase());
		}
		
		// be proper and just verify list sizes are ok
		assert(leftList.size() == rightList.size());
		
		/*
		// Debug
		for (String currStr : leftList )
			System.out.println("First List: "+currStr);
		
		for (String currStr : rightList )
			System.out.println("Second List: "+currStr);
		*/
	}
	
	private void checkForAnagrams() {
		
		for(int i=0; i<leftArrayData.length; i++) {
			
			char leftCharArray[] = null;
			char rightCharArray[] = null;
			// reduce calls to get(i)
			String currLeftWord = leftList.get(i);
			String currRightWord = rightList.get(i);
			
			// first is easy, if the lengths are off they can't be real anagrams
			if(currLeftWord.length() == currRightWord.length()) {
				
				// next if they are equal just compare as sorted char arrays
				leftCharArray = currLeftWord.toCharArray();
				rightCharArray = currRightWord.toCharArray();
				
				// do the 'numeric' ascii Sort here
				Arrays.sort(leftCharArray);
				Arrays.sort(rightCharArray);
				
				if(Arrays.equals(leftCharArray, rightCharArray)){
					System.out.println(currLeftWord+" IS an anagram of: "+currRightWord);
				} else {
					System.out.println(currLeftWord+" is NOT an anagram of: "+currRightWord);
				}
				
			} else {
				System.out.println(currLeftWord+" is NOT an anagram of: "+currRightWord);
			}
			
		}
	}
	


	public static void main(String[] args) {
		new QuestionOne();
	}

}