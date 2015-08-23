
public class Main {
	public static void main(String[] args) {
		System.out.println(fixSpelling("hostleworld"));
	}
	
	public static String fixSpelling(String name) {
		// NEW string OBJECT is created as opposed to literal "hostleworld" - nothing wrong as yet
		String wordToCheck = new String(name);
		
		// To compound this an object evaluator of == is applied to check if this STRING object
		// with unique underlying hash (regardless of data(string spelling inside)) is == to
		// the object created 'on the fly' by java of literal string "hostleworld" (regardless of spelling)
		// This would always eval to false therefore hitting into the recursively calling fixSpelling inside
		// in the else block
		if(wordToCheck == "hostleworld" ) {
			name = "hostelworld";
		} else {
			fixSpelling(name);
		}
		
		return name;
	}
}