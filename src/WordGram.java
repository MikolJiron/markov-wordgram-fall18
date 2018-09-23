/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author MICHAEL JIRON
 *
 */
import java.util.Random;

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram, an object that represents a set of strings and its hashcode, as well as a single string representing the entire array
	 * @param source this is a set of strings
	 * @param start this is the first element k that would start the myWords array
	 * @param size this is the size, or length of the intended WordGram
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];

		for(int k = 0; k < size; k++){
			myWords[k] = source[start + k];
		}

		myToString = null;
		myHash = 0;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * This returns the length of the myWords array
	 * @return the length of the myWords array
	 */
	public int length(){
		// TODO: change this

		return myWords.length;
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}

		WordGram wg = (WordGram) o;
		if(this.myWords.length != wg.length()){
			return false;
		}

		for(int k = 0; k < this.myWords.length; k++){
			if(this.myWords[k] != wg.myWords[k]){
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode(){
		if(myHash == 0){
			int k = new Random().nextInt();
			myHash = this.toString().length() + k;
		}
		return myHash;
	}
	

	/**
	 * this method deletes the first element, shifts every other element to the left, and the adds a new string at the last position
	 * of a WordGram object, creating a new one in the process
	 * @param last is last String of returned WordGram
	 * @return the resulting WordGram object from the operations aforementioned
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		for(int k = 1; k < myWords.length; k++){
			wg.myWords[k] = this.myWords[k-1];
		}
		wg.myWords[wg.length()-1] = last;

		return wg;
	}

	@Override
	public String toString(){
		if(myToString == null) {
			myToString = String.join(" ", myWords);
		}
		return myToString;
	}
}
