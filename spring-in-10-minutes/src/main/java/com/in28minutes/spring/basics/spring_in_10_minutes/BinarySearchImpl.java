package com.in28minutes.spring.basics.spring_in_10_minutes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

	@Autowired
	private SortAlgorithm sortAlgorithm;
	
	/**
	 * Constructor injection
	 * @param sortAlgorithm
	 */
	public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		super();
		this.sortAlgorithm = sortAlgorithm;
	}
	
	/**
	 * Setter injection. The same is achieved by omitting  both constructor and set method
	 * @param sortAlgorithm
	 */
//	public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
//		this.sortAlgorithm = sortAlgorithm;
//	}

	public int binarySearch(int[] numbers, int numberToSearchFor) {
		System.out.println(sortAlgorithm);
		return binarySearch(sortAlgorithm.sort(numbers), 0, numbers.length - 1, numberToSearchFor); 
	}
	
	private int binarySearch(int arr[], int l, int r, int x) 
    { 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
  
            // If the element is present at the 
            // middle itself 
            if (arr[mid] == x) 
                return mid; 
  
            // If element is smaller than mid, then 
            // it can only be present in left subarray 
            if (arr[mid] > x) 
                return binarySearch(arr, l, mid - 1, x); 
  
            // Else the element can only be present 
            // in right subarray 
            return binarySearch(arr, mid + 1, r, x); 
        } 
  
        // We reach here when element is not present 
        // in array 
        return -1; 
    }
}
