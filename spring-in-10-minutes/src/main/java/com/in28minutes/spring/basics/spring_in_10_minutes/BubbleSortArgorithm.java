package com.in28minutes.spring.basics.spring_in_10_minutes;

import org.springframework.stereotype.Component;

@Component 
public class BubbleSortArgorithm implements SortAlgorithm{

	public int[] sort(int numbers[]) { 
        int n = numbers.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (numbers[j] > numbers[j+1]){ 
                    int temp = numbers[j]; 
                    numbers[j] = numbers[j+1]; 
                    numbers[j+1] = temp; 
                } 
        return numbers;
    } 
}