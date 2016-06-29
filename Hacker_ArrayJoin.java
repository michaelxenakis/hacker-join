import java.util.Random;

public class Hacker_ArrayJoin {
    public static void main(String[] args) {
    	
    	for (int case_idx = 0; case_idx < 5; case_idx++) {
        	String[] leftArray = CreateArray();
        	String[] rightArray = CreateArray();
        	
        	System.out.println();
        	System.out.println("TEST CASE " + case_idx + 1);
        	
        	System.out.println("* * * LEFT ARRAY * * *");
    		for (int idx = 0; idx < leftArray.length; idx++) {
            	System.out.println(leftArray[idx]);
    		}
        	System.out.println("* * * RIGHT ARRAY * * *");
    		for (int idx = 0; idx < rightArray.length; idx++) {
            	System.out.println(rightArray[idx]);
    		}
        	
        	int[][] finalResults = JoinArrays("left", leftArray, rightArray);
        	System.out.println("* * * Left Join * * *");
    		for (int idx = 0; idx < finalResults.length; idx++) {
            	System.out.println(finalResults[idx][0] + " " + finalResults[idx][1]);
    		}
        	finalResults = JoinArrays("right", leftArray, rightArray);
        	System.out.println("* * * Right Join * * *");
    		for (int idx = 0; idx < finalResults.length; idx++) {
            	System.out.println(finalResults[idx][0] + " " + finalResults[idx][1]);
    		}
        	finalResults = JoinArrays("inner", leftArray, rightArray);
        	System.out.println("* * * Inner Join * * *");
    		for (int idx = 0; idx < finalResults.length; idx++) {
            	System.out.println(finalResults[idx][0] + " " + finalResults[idx][1]);
    		}
    	}
    }
    
    public static String[] CreateArray() {
    	String[] sourceArray = new String[] {"apple", "banana", "blueberry", "grape", "kiwi", "melon", "orange", "strawberry", "bacon", "eggs"};
    	Random sizeGenerator = new Random();
    	Random indexGenerator = new Random();
    	
    	int size = sizeGenerator.nextInt(sourceArray.length + 1);
    	String[] newArray = new String[size];
    	for (int idx = 0; idx < size; idx++) {
    		newArray[idx] = sourceArray[indexGenerator.nextInt(sourceArray.length)];
    	}
    	
    	return newArray;
    }
    
    public static int[][] JoinArrays(String joinType, String[] leftArray, String[] rightArray) {
//    	String[] leftArray = new String[] {"apple", "grape", "grape", "melon"};
//    	String[] rightArray = new String[] {"grape", "orange", "apple"};
    	
    	int finalResults[][];
    	
    	// left join
    	if (joinType.equals("left")) { // create scope block
    		int leftLength = leftArray.length;
    		int rightLength = rightArray.length;
    		int[][] resultsArray = new int[(leftLength * rightLength)][2];
    		// we need to manually track how deep we are in the results array to account for repetition
    		int resultsIdx = 0;
    		
    		// for each left, let's see if we find it "in each" right
    		for (int leftIdx = 0; leftIdx < leftLength; leftIdx++) {
    			boolean found = false; // always start assuming it wasn't found
    			for (int rightIdx = 0; rightIdx < rightLength; rightIdx++) {
    				if (leftArray[leftIdx].equals(rightArray[rightIdx])) {
    					resultsArray[resultsIdx] = new int[] {leftIdx, rightIdx};
    					resultsIdx++;
    					found = true;
    				}
    			}
    			if (!found) {
    				resultsArray[resultsIdx] = new int[] {leftIdx, (-1)};
    				resultsIdx++;
    			}
    		}
    		finalResults = new int[resultsIdx][2];
    		for (int idx = 0; idx < resultsIdx; idx++) {
    			finalResults[idx] = resultsArray[idx];
    		}
    	}
    	// right join
    	else if (joinType.equals("right")) { // create scope block
    		int leftLength = leftArray.length;
    		int rightLength = rightArray.length;
    		int[][] resultsArray = new int[(leftLength * rightLength)][2];
    		// we need to manually track how deep we are in the results array to account for repetition
    		int resultsIdx = 0;
    		
    		// for each right, let's see if we find it "in each" left
    		for (int rightIdx = 0; rightIdx < rightLength; rightIdx++) {
    			boolean found = false; // always start assuming it wasn't found
    			for (int leftIdx = 0; leftIdx < leftLength; leftIdx++) {
    				if (rightArray[rightIdx].equals(leftArray[leftIdx])) {
    					resultsArray[resultsIdx] = new int[] {leftIdx, rightIdx};
    					resultsIdx++;
    					found = true;
    				}
    			}
    			if (!found) {
    				resultsArray[resultsIdx] = new int[] {(-1), rightIdx};
    				resultsIdx++;
    			}
    		}
    		finalResults = new int[resultsIdx][2];
    		for (int idx = 0; idx < resultsIdx; idx++) {
    			finalResults[idx] = resultsArray[idx];
    		}
    	}
    	// inner join
    	else { // create scope block
    		int leftLength = leftArray.length;
    		int rightLength = rightArray.length;
    		int[][] resultsArray = new int[(leftLength * rightLength)][2];
    		// we need to manually track how deep we are in the results array to account for repetition
    		int resultsIdx = 0;
    		
    		// for each left, let's see if we find it "in each" right
    		for (int leftIdx = 0; leftIdx < leftLength; leftIdx++) {
    			for (int rightIdx = 0; rightIdx < rightLength; rightIdx++) {
    				if (leftArray[leftIdx].equals(rightArray[rightIdx])) {
    					resultsArray[resultsIdx] = new int[] {leftIdx, rightIdx};
    					resultsIdx++;
    				}
    			}
    		}
    		finalResults = new int[resultsIdx][2];
    		for (int idx = 0; idx < resultsIdx; idx++) {
    			finalResults[idx] = resultsArray[idx];
    		}
    	}
    	
    	return finalResults;
    }
}
